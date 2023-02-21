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

package com.liferay.translation.translator.deepl.internal.translator;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.url.URLBuilder;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.translation.exception.TranslatorException;
import com.liferay.translation.translator.Translator;
import com.liferay.translation.translator.TranslatorPacket;
import com.liferay.translation.translator.deepl.internal.configuration.DeepLTranslatorConfiguration;
import com.liferay.translation.translator.deepl.internal.model.Translation;

import java.io.IOException;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yasuyuki Takeo
 */
@Component(
	configurationPid = "com.liferay.translation.translator.deepl.internal.configuration.DeepLTranslatorConfiguration",
	service = Translator.class
)
public class DeepLTranslator implements Translator {

	@Override
	public boolean isEnabled(long companyId) throws ConfigurationException {
		DeepLTranslatorConfiguration deepLTranslatorConfiguration =
			_configurationProvider.getCompanyConfiguration(
				DeepLTranslatorConfiguration.class, companyId);

		return deepLTranslatorConfiguration.enabled();
	}

	@Override
	public TranslatorPacket translate(TranslatorPacket translatorPacket)
		throws PortalException {

		if (!isEnabled(translatorPacket.getCompanyId())) {
			return translatorPacket;
		}

		List<String> supportedLanguageCodes = _getSupportedLanguageCodes(
			_deepLTranslatorConfiguration);

		String targetLanguageCode = _getLanguageCode(
			translatorPacket.getTargetLanguageId());

		if (!_isSupportedLanguageCode(
				supportedLanguageCodes, targetLanguageCode)) {

			_log.error(
				StringBundler.concat(
					"Target language code ", targetLanguageCode,
					" is not among the supported langauge codes: ",
					StringUtil.merge(
						supportedLanguageCodes, StringPool.COMMA_AND_SPACE)));

			return translatorPacket;
		}

		String sourceLanguageCode = _getLanguageCode(
			translatorPacket.getSourceLanguageId());

		Map<String, String> translatedFieldsMap = new HashMap<>();

		Map<String, String> fieldsMap = translatorPacket.getFieldsMap();

		for (Map.Entry<String, String> entry : fieldsMap.entrySet()) {
			translatedFieldsMap.put(
				entry.getKey(),
				_translate(
					sourceLanguageCode, targetLanguageCode, entry.getValue()));
		}

		return new TranslatorPacket() {

			@Override
			public long getCompanyId() {
				return translatorPacket.getCompanyId();
			}

			@Override
			public Map<String, String> getFieldsMap() {
				return translatedFieldsMap;
			}

			@Override
			public String getSourceLanguageId() {
				return translatorPacket.getSourceLanguageId();
			}

			@Override
			public String getTargetLanguageId() {
				return translatorPacket.getTargetLanguageId();
			}

		};
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_deepLTranslatorConfiguration = ConfigurableUtil.createConfigurable(
			DeepLTranslatorConfiguration.class, properties);
	}

	private String _getLanguageCode(String languageId) {
		String[] parts = StringUtil.split(languageId, CharPool.UNDERLINE);

		return StringUtil.toUpperCase(parts[0]);
	}

	private String _getSupportedLanguageCode(
			String authKey, String target, String url)
		throws IOException, PortalException {

		Http.Options options = new Http.Options();

		options.addHeader(
			HttpHeaders.CONTENT_TYPE,
			ContentTypes.APPLICATION_X_WWW_FORM_URLENCODED);
		options.addPart("auth_key", authKey);
		options.addPart("target", target);

		options.setLocation(
			URLBuilder.create(
				url
			).addParameter(
				"auth_key", authKey
			).build());

		options.setMethod(Http.Method.POST);

		String supportedLanguageCode = _http.URLtoString(options);

		Http.Response response = options.getResponse();

		Response.Status status = Response.Status.fromStatusCode(
			response.getResponseCode());

		if (status == Response.Status.OK) {
			return supportedLanguageCode;
		}

		throw new TranslatorException(
			"The status is " + status + ". Please retry after a while.");
	}

	private List<String> _getSupportedLanguageCodes(
			DeepLTranslatorConfiguration deepLTranslatorConfiguration)
		throws PortalException {

		try {
			return JSONUtil.toList(
				_jsonFactory.createJSONArray(
					_getSupportedLanguageCode(
						deepLTranslatorConfiguration.authKey(), "target",
						deepLTranslatorConfiguration.validateLanguageURL())),
				customFieldJSONObject -> customFieldJSONObject.getString(
					"language"),
				_log);
		}
		catch (IOException ioException) {
			_log.error(
				"Failed to call supported language list." +
					System.lineSeparator() + ioException.getLocalizedMessage());

			return Collections.emptyList();
		}
	}

	private List<Translation> _getTranslateResponse(
			String sourceLanguageId, String targetLanguageId, String text)
		throws IOException, PortalException {

		JSONObject jsonObject = _jsonFactory.createJSONObject(
			_getTranslation(sourceLanguageId, targetLanguageId, text));

		return JSONUtil.toList(
			jsonObject.getJSONArray("translations"),
			customFieldJSONObject -> new Translation(
				customFieldJSONObject.getString("detected_source_language"),
				customFieldJSONObject.getString("text")),
			_log);
	}

	private String _getTranslation(
			String sourceLanguageId, String targetLanguageId, String text)
		throws IOException, PortalException {

		Http.Options options = new Http.Options();

		options.addHeader(
			HttpHeaders.CONTENT_TYPE,
			ContentTypes.APPLICATION_X_WWW_FORM_URLENCODED);
		options.addPart("auth_key", _deepLTranslatorConfiguration.authKey());
		options.addPart("source_lang", sourceLanguageId);
		options.addPart("target_lang", targetLanguageId);
		options.addPart("text", text);

		options.setLocation(
			URLBuilder.create(
				_deepLTranslatorConfiguration.url()
			).addParameter(
				"auth_key", _deepLTranslatorConfiguration.authKey()
			).build());

		options.setMethod(Http.Method.POST);

		String translation = _http.URLtoString(options);

		Http.Response response = options.getResponse();

		Response.Status status = Response.Status.fromStatusCode(
			response.getResponseCode());

		if (status == Response.Status.OK) {
			return translation;
		}

		throw new TranslatorException(
			"The status is " + status + ". Please retry after a while.");
	}

	private boolean _isSupportedLanguageCode(
		List<String> supportedLanguageCodes, String languageCode) {

		if (Collections.disjoint(
				supportedLanguageCodes, Arrays.asList(languageCode))) {

			_log.error(
				"DeepL does not support " + languageCode +
					". Abort processing translation.");

			return false;
		}

		return true;
	}

	private String _translate(
			String sourceLanguageCode, String targetLanguageCode, String text)
		throws PortalException {

		try {
			if (Validator.isBlank(text)) {
				return text;
			}

			List<Translation> translations = _getTranslateResponse(
				sourceLanguageCode, targetLanguageCode, text);

			Translation translation = translations.get(0);

			return translation.getText();
		}
		catch (IOException ioException) {
			throw new TranslatorException(
				"DeepL translator returns original text. " +
					ioException.getLocalizedMessage());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DeepLTranslator.class);

	@Reference
	private ConfigurationProvider _configurationProvider;

	private volatile DeepLTranslatorConfiguration _deepLTranslatorConfiguration;

	@Reference
	private Http _http;

	@Reference
	private JSONFactory _jsonFactory;

}