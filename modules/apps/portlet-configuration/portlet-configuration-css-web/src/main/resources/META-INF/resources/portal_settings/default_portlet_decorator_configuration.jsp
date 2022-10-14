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

<%
DefaultPortletDecoratorConfiguration defaultPortletDecoratorConfiguration = (DefaultPortletDecoratorConfiguration)request.getAttribute(DefaultPortletDecoratorConfiguration.class.getName());
<<<<<<< HEAD
=======

String portletDecoratorId = null;
String defaultPortletDecoratorId = defaultPortletDecoratorConfiguration.defaultPortletDecoratorId();

if (defaultPortletDecoratorId.equals("")) {
	portletDecoratorId = PropsValues.DEFAULT_PORTLET_DECORATOR_ID;
}
else {
	portletDecoratorId = defaultPortletDecoratorId;
}
>>>>>>> 6c39d99 (LPS-163650 Rename to keep consistency)
%>

<div class="row">
	<div class="col-md-12">
		<br />

		<aui:select label="select-default-portlet-decorator-id" name="defaultPortletDecoratorId" required="<%= true %>" type="text" value="<%= portletDecoratorId %>">
			<aui:option label="Barebone" value="barebone" />
			<aui:option label="Borderless" value="borderless" />
			<aui:option label="Decorate" value="decorate" />
		</aui:select>
	</div>
</div>