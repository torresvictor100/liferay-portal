package com.liferay.site.navigation.language.web.internal.portlet.action;

import com.liferay.configuration.admin.constants.ConfigurationAdminPortletKeys;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.site.navigation.language.web.internal.configuration.SiteNavigationLocaleFriendlyUrlConfiguration;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Albert Gomes
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + ConfigurationAdminPortletKeys.INSTANCE_SETTINGS,
		"mvc.command.name=/locale_friendly_url_style/save_company_configuration"
	},
	service = MVCActionCommand.class
)
public class SaveLocaleFriendlyUrlConfigurationMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (!permissionChecker.isCompanyAdmin(themeDisplay.getCompanyId())) {
			SessionErrors.add(actionRequest, PrincipalException.class);

			actionResponse.setRenderParameter("mvcPath", "/error.jsp");

			return;
		}

		_configurationProvider.saveCompanyConfiguration(
			SiteNavigationLocaleFriendlyUrlConfiguration.class, themeDisplay.getCompanyId(),
			HashMapDictionaryBuilder.<String, Object>put(
				"localePrependFriendlyUrlStyle",
				ParamUtil.getString(actionRequest, "localePrependFriendlyUrlStyle")
			).build());

		PropsUtil.set(
				PropsKeys.LOCALE_PREPEND_FRIENDLY_URL_STYLE,
				ParamUtil.getString(actionRequest, "localePrependFriendlyUrlStyle"));

		PropsValues.LOCALE_PREPEND_FRIENDLY_URL_STYLE = Integer.parseInt(
			PropsUtil.get(PropsKeys.LOCALE_PREPEND_FRIENDLY_URL_STYLE));
	}

	@Reference
	private ConfigurationProvider _configurationProvider;
}
