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
AssetListManagementToolbarDisplayContext assetListManagementToolbarDisplayContext = new AssetListManagementToolbarDisplayContext(request, liferayPortletRequest, liferayPortletResponse, assetListDisplayContext);
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems='<%= assetListDisplayContext.getNavigationItems("collections") %>'
/>

<clay:management-toolbar
	managementToolbarDisplayContext="<%= assetListManagementToolbarDisplayContext %>"
	propsTransformer="js/ManagementToolbarPropsTransformer"
/>

<portlet:actionURL name="/asset_list/delete_asset_list_entries" var="deleteAssetListEntryURL">
	<portlet:param name="redirect" value="<%= currentURL %>" />
</portlet:actionURL>

<aui:form action="<%= deleteAssetListEntryURL %>" cssClass="container-fluid container-fluid-max-xl" name="fm">
	<liferay-site-navigation:breadcrumb
		breadcrumbEntries="<%= BreadcrumbEntriesUtil.getBreadcrumbEntries(request, true, false, false, true, true) %>"
	/>

	<c:choose>
		<c:when test="<%= assetListDisplayContext.getAssetListEntriesCount() > 0 %>">
			<liferay-ui:search-container
				id="assetListEntries"
				searchContainer="<%= assetListDisplayContext.getAssetListEntriesSearchContainer() %>"
			>
				<liferay-ui:search-container-row
					className="com.liferay.asset.list.model.AssetListEntry"
					keyProperty="assetListEntryId"
					modelVar="assetListEntry"
				>

					<%
					row.setData(
						HashMapBuilder.<String, Object>put(
							"actions", assetListManagementToolbarDisplayContext.getAvailableActions(assetListEntry)
						).build());

					Date statusDate = assetListEntry.getCreateDate();

					AssetEntryListActionDropdownItems assetEntryListActionDropdownItems = new AssetEntryListActionDropdownItems(assetListEntry, liferayPortletRequest, liferayPortletResponse);
					%>

					<c:choose>
						<c:when test='<%= Objects.equals(assetListDisplayContext.getDisplayStyle(), "descriptive") %>'>
							<liferay-ui:search-container-column-icon
								icon="list"
							/>

							<liferay-ui:search-container-column-text
								colspan="<%= 2 %>"
							>
								<h5>
									<aui:a href="<%= assetListDisplayContext.getEditURL(assetListEntry) %>">
										<strong><%= HtmlUtil.escape(assetListEntry.getTitle()) %></strong>
									</aui:a>
								</h5>

								<h6 class="text-default">
									<liferay-ui:message key="<%= HtmlUtil.escape(assetListEntry.getTypeLabel()) %>" />
								</h6>

								<h6 class="text-default">
									<%= assetListDisplayContext.getAssetEntrySubtypeLabel(assetListEntry) %>
								</h6>

								<liferay-ui:message arguments="<%= LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - statusDate.getTime(), true) %>" key="x-ago" translateArguments="<%= false %>" />
							</liferay-ui:search-container-column-text>

							<liferay-ui:search-container-column-text>
								<clay:dropdown-actions
									aria-label='<%= LanguageUtil.get(request, "show-actions") %>'
									dropdownItems="<%= assetEntryListActionDropdownItems.getActionDropdownItems() %>"
									propsTransformer="js/AssetEntryListDropdownDefaultPropsTransformer"
								/>
							</liferay-ui:search-container-column-text>
						</c:when>
						<c:when test='<%= Objects.equals(assetListDisplayContext.getDisplayStyle(), "icon") %>'>
							<liferay-ui:search-container-column-text>
								<clay:vertical-card
									propsTransformer="js/AssetEntryListDropdownDefaultPropsTransformer"
									verticalCard="<%= new AssetListEntryVerticalCard(assetListDisplayContext, assetListEntry, renderRequest, searchContainer.getRowChecker()) %>"
								/>
							</liferay-ui:search-container-column-text>
						</c:when>
						<c:otherwise>
							<liferay-ui:search-container-column-text
								cssClass="table-cell-expand text-truncate"
								name="name"
							>
								<clay:icon
									cssClass="mr-2 text-secondary"
									symbol="list"
								/>

								<aui:a href="<%= assetListDisplayContext.getEditURL(assetListEntry) %>">
									<%= HtmlUtil.escape(assetListEntry.getTitle()) %>
								</aui:a>
							</liferay-ui:search-container-column-text>

							<liferay-ui:search-container-column-text
								cssClass="table-cell-expand text-truncate"
								name="type"
							>
								<liferay-ui:message key="<%= HtmlUtil.escape(assetListEntry.getTypeLabel()) %>" />
							</liferay-ui:search-container-column-text>

							<liferay-ui:search-container-column-text
								cssClass="table-cell-expand text-truncate"
								name="item-type"
								value="<%= assetListDisplayContext.getAssetEntryTypeLabel(assetListEntry) %>"
							/>

							<liferay-ui:search-container-column-text
								cssClass="table-cell-expand text-truncate"
								name="subtype"
								value="<%= assetListDisplayContext.getClassTypeLabel(assetListEntry) %>"
							/>

							<liferay-ui:search-container-column-text
								cssClass="table-cell-expand text-truncate"
								name="modified"
							>
								<liferay-ui:message arguments="<%= LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - statusDate.getTime(), true) %>" key="x-ago" translateArguments="<%= false %>" />
							</liferay-ui:search-container-column-text>

							<liferay-ui:search-container-column-text>
								<clay:dropdown-actions
									aria-label='<%= LanguageUtil.get(request, "show-actions") %>'
									dropdownItems="<%= assetEntryListActionDropdownItems.getActionDropdownItems() %>"
									propsTransformer="js/AssetEntryListDropdownDefaultPropsTransformer"
								/>
							</liferay-ui:search-container-column-text>
						</c:otherwise>
					</c:choose>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator
					displayStyle="<%= assetListDisplayContext.getDisplayStyle() %>"
					markupView="lexicon"
				/>
			</liferay-ui:search-container>
		</c:when>
		<c:otherwise>
			<liferay-frontend:empty-result-message
				actionDropdownItems="<%= assetListDisplayContext.isShowAddAssetListEntryAction() ? assetListDisplayContext.getAddAssetListEntryDropdownItems() : null %>"
				buttonCssClass="secondary"
				description="<%= assetListDisplayContext.getEmptyResultMessageDescription() %>"
				elementType='<%= LanguageUtil.get(request, "collections") %>'
				propsTransformer="js/EmptyResultMessagePropsTransformer"
				propsTransformerServletContext="<%= application %>"
			/>
		</c:otherwise>
	</c:choose>
</aui:form>