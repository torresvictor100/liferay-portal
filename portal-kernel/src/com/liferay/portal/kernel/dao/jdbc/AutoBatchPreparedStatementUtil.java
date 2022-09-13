/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.kernel.dao.jdbc;

import com.liferay.petra.concurrent.NoticeableExecutorService;
import com.liferay.petra.concurrent.NoticeableFuture;
import com.liferay.petra.executor.PortalExecutorManager;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.ServiceProxyFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Shuyang Zhou
 * @author Preston Crary
 */
public class AutoBatchPreparedStatementUtil {

	public static PreparedStatement autoBatch(Connection connection, String sql)
		throws SQLException {

		DatabaseMetaData databaseMetaData = connection.getMetaData();

		if (databaseMetaData.supportsBatchUpdates()) {
			return (PreparedStatement)ProxyUtil.newProxyInstance(
				ClassLoader.getSystemClassLoader(), _INTERFACES,
				new BatchInvocationHandler(connection, sql));
		}

		return (PreparedStatement)ProxyUtil.newProxyInstance(
			ClassLoader.getSystemClassLoader(), _INTERFACES,
			new NoBatchInvocationHandler(connection, sql));
	}

	public static PreparedStatement concurrentAutoBatch(
			Connection connection, String sql)
		throws SQLException {

		DatabaseMetaData databaseMetaData = connection.getMetaData();

		if (databaseMetaData.supportsBatchUpdates()) {
			return (PreparedStatement)ProxyUtil.newProxyInstance(
				ClassLoader.getSystemClassLoader(), _INTERFACES,
				new ConcurrentBatchInvocationHandler(connection, sql));
		}

		return (PreparedStatement)ProxyUtil.newProxyInstance(
			ClassLoader.getSystemClassLoader(), _INTERFACES,
			new ConcurrentNoBatchInvocationHandler(connection, sql));
	}

	private static final int _HIBERNATE_JDBC_BATCH_SIZE = GetterUtil.getInteger(
		PropsUtil.get(PropsKeys.HIBERNATE_JDBC_BATCH_SIZE));

	private static final Class<?>[] _INTERFACES = new Class<?>[] {
		PreparedStatement.class
	};

	private static final Log _log = LogFactoryUtil.getLog(
		AutoBatchPreparedStatementUtil.class);

	private static final Method _addBatchMethod;
	private static final Method _closeMethod;
	private static final Method _executeBatchMethod;
	private static final Method _getConnectionMethod;
	private static volatile PortalExecutorManager _portalExecutorManager =
		ServiceProxyFactory.newServiceTrackedInstance(
			PortalExecutorManager.class, AutoBatchPreparedStatementUtil.class,
			"_portalExecutorManager", true);

	static {
		try {
			_addBatchMethod = PreparedStatement.class.getMethod("addBatch");
			_closeMethod = PreparedStatement.class.getMethod("close");
			_executeBatchMethod = PreparedStatement.class.getMethod(
				"executeBatch");
			_getConnectionMethod = PreparedStatement.class.getMethod(
				"getConnection");
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new ExceptionInInitializerError(noSuchMethodException);
		}
	}

	private static class BatchInvocationHandler
		extends NoBatchInvocationHandler {

		@Override
		protected void doAddBatch() throws SQLException {
			PreparedStatement localPreparedStatement = getPreparedStatement();

			localPreparedStatement.addBatch();

			if (++_count >= _HIBERNATE_JDBC_BATCH_SIZE) {
				_count = 0;

				localPreparedStatement.executeBatch();
			}
		}

		@Override
		protected int[] doExecuteBatch() throws SQLException {
			if (_count > 0) {
				_count = 0;

				PreparedStatement localPreparedStatement =
					getPreparedStatement();

				return localPreparedStatement.executeBatch();
			}

			return new int[0];
		}

		private BatchInvocationHandler(Connection connection, String sql) {
			super(connection, sql);
		}

		private int _count;

	}

	private static class ConcurrentBatchInvocationHandler
		extends ConcurrentNoBatchInvocationHandler {

		@Override
		protected void doAddBatch() throws SQLException {
			PreparedStatement localPreparedStatement = getPreparedStatement();

			localPreparedStatement.addBatch();

			if (++_count >= _HIBERNATE_JDBC_BATCH_SIZE) {
				_count = 0;

				executeAsync(PreparedStatement::executeBatch);
			}
		}

		@Override
		protected int[] doExecuteBatch() throws SQLException {
			if (_count > 0) {
				_count = 0;

				executeAsync(PreparedStatement::executeBatch);
			}

			return new int[0];
		}

		private ConcurrentBatchInvocationHandler(
			Connection connection, String sql) {

			super(connection, sql);
		}

		private int _count;

	}

	private static class ConcurrentNoBatchInvocationHandler
		extends PreparedStatementInvocationHandler {

		@Override
		protected void doAddBatch() throws SQLException {
			executeAsync(PreparedStatement::executeUpdate);
		}

		@Override
		protected void doClose() throws Throwable {
			Throwable throwable1 = null;

			for (Future<Void> future : _futures) {
				try {
					future.get();
				}
				catch (Throwable throwable2) {
					if (throwable2 instanceof ExecutionException) {
						throwable2 = throwable2.getCause();
					}

					if (throwable1 == null) {
						throwable1 = throwable2;
					}
					else {
						throwable1.addSuppressed(throwable2);
					}
				}
			}

			if (throwable1 != null) {
				throw throwable1;
			}
		}

		@Override
		protected int[] doExecuteBatch() throws SQLException {
			return new int[0];
		}

		protected void executeAsync(
				UnsafeConsumer<PreparedStatement, SQLException>
					actionUnsafeConsumer)
			throws SQLException {

			PreparedStatement localPreparedStatement = getPreparedStatement();

			NoticeableFuture<Void> noticeableFuture =
				_noticeableExecutorService.submit(
					() -> {
						try {
							actionUnsafeConsumer.accept(localPreparedStatement);
						}
						finally {
							localPreparedStatement.close();
						}

						return null;
					});

			_futures.add(noticeableFuture);

			noticeableFuture.addFutureListener(
				future -> {
					try {
						future.get();

						_futures.remove(future);
					}
					catch (Throwable throwable) {
						if (_log.isDebugEnabled()) {
							_log.debug(throwable);
						}
					}
				});

			preparedStatement = null;
		}

		private ConcurrentNoBatchInvocationHandler(
			Connection connection, String sql) {

			super(connection, sql);
		}

		private final Set<Future<Void>> _futures = Collections.newSetFromMap(
			new ConcurrentHashMap<>());
		private final NoticeableExecutorService _noticeableExecutorService =
			_portalExecutorManager.getPortalExecutor(
				ConcurrentNoBatchInvocationHandler.class.getName());

	}

	private static class NoBatchInvocationHandler
		extends PreparedStatementInvocationHandler {

		@Override
		protected void doAddBatch() throws SQLException {
			PreparedStatement localPreparedStatement = getPreparedStatement();

			localPreparedStatement.executeUpdate();
		}

		@Override
		protected void doClose() throws Throwable {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		}

		@Override
		protected int[] doExecuteBatch() throws SQLException {
			return new int[0];
		}

		private NoBatchInvocationHandler(Connection connection, String sql) {
			super(connection, sql);
		}

	}

	private abstract static class PreparedStatementInvocationHandler
		implements InvocationHandler {

		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {

			if (method.equals(_getConnectionMethod)) {
				return _connection;
			}

			if (method.equals(_closeMethod)) {
				doClose();

				return null;
			}

			if (method.equals(_addBatchMethod)) {
				doAddBatch();

				return null;
			}

			if (method.equals(_executeBatchMethod)) {
				return doExecuteBatch();
			}

			return method.invoke(getPreparedStatement(), args);
		}

		protected PreparedStatementInvocationHandler(
			Connection connection, String sql) {

			_connection = connection;
			_sql = sql;
		}

		protected abstract void doAddBatch() throws SQLException;

		protected abstract void doClose() throws Throwable;

		protected abstract int[] doExecuteBatch() throws SQLException;

		protected PreparedStatement getPreparedStatement() throws SQLException {
			if (preparedStatement == null) {
				preparedStatement = _connection.prepareStatement(_sql);
			}

			return preparedStatement;
		}

		protected PreparedStatement preparedStatement;

		private final Connection _connection;
		private final String _sql;

	}

}