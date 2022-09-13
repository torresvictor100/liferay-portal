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

<%@ include file="/navigation/init.jsp" %>

<%
KBArticleNavigationFragmentDisplayContext kbArticleNavigationFragmentDisplayContext = (KBArticleNavigationFragmentDisplayContext)request.getAttribute(KBArticleNavigationFragmentDisplayContext.class.getName());

int level = GetterUtil.getInteger(request.getAttribute("view_navigation_kb_articles.jsp-level"));
long parentResourcePrimKey = (long)request.getAttribute("view_navigation_kb_articles.jsp-parentResourcePrimKey");

for (KBArticle kbArticle : kbArticleNavigationFragmentDisplayContext.getKBArticles(parentResourcePrimKey, level)) {
%>

	<ul>
		<li class="<%= kbArticleNavigationFragmentDisplayContext.getKBArticleCssClass(kbArticle, level) %>">
			<c:choose>
				<c:when test="<%= kbArticleNavigationFragmentDisplayContext.isSelected(kbArticle) %>">
					<span class="sr-only"><liferay-ui:message key="current-article" /></span>

					<%= HtmlUtil.escape(kbArticle.getTitle()) %>
				</c:when>
				<c:otherwise>
					<a href="<%= kbArticleNavigationFragmentDisplayContext.getKBArticleFriendlyURL(kbArticle) %>"><%= HtmlUtil.escape(kbArticle.getTitle()) %></a>
				</c:otherwise>
			</c:choose>

			<c:if test="<%= kbArticleNavigationFragmentDisplayContext.isFurtherExpansionRequired(kbArticle, level) %>">

				<%
				request.setAttribute("view_navigation_kb_articles.jsp-level", level + 1);
				request.setAttribute("view_navigation_kb_articles.jsp-parentResourcePrimKey", kbArticle.getResourcePrimKey());
				%>

				<liferay-util:include page="/navigation/view_navigation_kb_articles.jsp" servletContext="<%= application %>" />
			</c:if>
		</li>
	</ul>

<%
}
%>