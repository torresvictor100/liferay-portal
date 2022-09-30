<%--
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
--%>

<%@ include file="/init.jsp" %>
<%@ page import="com.liferay.portal.util.PropsValues" %>

<%
DecoratorConfiguration decoratorConfiguration = (DecoratorConfiguration)request.getAttribute(DecoratorConfiguration.class.getName());

String decorate = null;

if (decorateConfiguration.equals("")) {
	decorate = PropsValues.DEFAULT_PORTLET_DECORATOR_ID;
}
else {
	decorate = decoratorConfiguration.applicationDecorators();
}
%>

<div class="row">
	<div class="col-md-12">
		<br />

		<aui:select label="select-properties-application-decorators" name="applicationDecorators" required="<%= true %>" type="text" value="<%= decorate %>">
			<aui:option label="Barebone" value="barebone" />
			<aui:option label="Borderless" value="borderless" />
			<aui:option label="Decorate" value="decorate" />
		</aui:select>
	</div>
</div>