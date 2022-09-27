package com.liferay.site.navigation.language.web.internal.display;

import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.settings.configuration.admin.display.PortalSettingsConfigurationScreenContributor;
import com.liferay.portal.util.PropsValues;
import com.liferay.site.navigation.language.web.internal.configuration.SiteNavigationLocaleFriendlyUrlConfiguration;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.ResourceBundle;

@Component(service = PortalSettingsConfigurationScreenContributor.class)
public class LocaleFriendlyUrlConfigurationScreenContributor
		implements PortalSettingsConfigurationScreenContributor {

		@Override
		public String getCategoryKey() {
			return "localization";
		}

		@Override
		public String getJspPath() {
			return "/portal_settings/locale_friendly_url_style.jsp";
		}

		@Override
		public String getKey() {
			return "locale-friendly-url-configuration";
		}

		@Override
		public String getName(Locale locale) {
			ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
				"content.Language", locale, getClass());

			return _language.get(resourceBundle, "site-navigation-locale-friendly-url-configuration-name");
		}

		@Override
		public String getSaveMVCActionCommandName() {
			return "/locale_friendly_url_style/save_company_configuration";
		}

		@Override
		public ServletContext getServletContext() {
			return _servletContext;
		}

		@Override
		public void setAttributes(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			SiteNavigationLocaleFriendlyUrlConfiguration siteNavigationLocaleFriendlyUrlConfiguration = null;

			try {
				siteNavigationLocaleFriendlyUrlConfiguration =
					_configurationProvider.getCompanyConfiguration(
						SiteNavigationLocaleFriendlyUrlConfiguration.class,
						CompanyThreadLocal.getCompanyId());

			}
			catch (PortalException portalException) {
				ReflectionUtil.throwException(portalException);
			}

			httpServletRequest.setAttribute(
				String.valueOf(PropsValues.LOCALE_PREPEND_FRIENDLY_URL_STYLE),
				siteNavigationLocaleFriendlyUrlConfiguration.localeFriendlyUrlStyle()
			);
		}

		@Reference
		private ConfigurationProvider _configurationProvider;

		@Reference
		private Language _language;

		@Reference(
			target = "(osgi.web.symbolicname=com.liferay.site.navigation.language.web)",
			unbind = "-"
		)
		private ServletContext _servletContext;
}
