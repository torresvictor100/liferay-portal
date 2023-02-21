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
import com.liferay.portal.kernel.json.JSONArray;
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
import com.liferay.translation.translator.deepl.internal.model.SupportedLanguageCode;
import com.liferay.translation.translator.deepl.internal.model.Translation;

import java.io.IOException;

import java.util.ArrayList;
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

		if (deepLTranslatorConfiguration.enabled() &&
			!Validator.isBlank(deepLTranslatorConfiguration.authKey()) &&
			!Validator.isBlank(deepLTranslatorConfiguration.url()) &&
			!Validator.isBlank(
				deepLTranslatorConfiguration.validateLanguageURL())) {

			return true;
		}

		return false;
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
					"No target language available for ", targetLanguageCode,
					". Supported languages are: ",
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
					_deepLTranslatorConfiguration.url(),
					_deepLTranslatorConfiguration.authKey(), entry.getValue(),
					sourceLanguageCode, targetLanguageCode));
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
		List<String> list = Arrays.asList(
			StringUtil.split(languageId, CharPool.UNDERLINE));

		return StringUtil.toUpperCase(list.get(0));
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
			List<String> languageCodes = new ArrayList<>();

			List<SupportedLanguageCode> supportedLanguageCodes =
				_toSupportedLanguageCodes(
					deepLTranslatorConfiguration.authKey(), "target",
					deepLTranslatorConfiguration.validateLanguageURL());

			supportedLanguageCodes.forEach(
				supportedLanguageCode -> languageCodes.add(
					supportedLanguageCode.getLanguageCode()));

			return languageCodes;
		}
		catch (IOException ioException) {
			_log.error(
				"Failed to call supported language list." +
					System.lineSeparator() + ioException.getLocalizedMessage());

			return Collections.emptyList();
		}
	}

	private List<Translation> _getTranslateResponse(
			String authKey, String text, String sourceLanguageId,
			String targetLanguageId, String url)
		throws IOException, PortalException {

		JSONObject jsonObject = _jsonFactory.createJSONObject(
			_getTranslation(
				authKey, text, sourceLanguageId, targetLanguageId, url));

		return JSONUtil.toList(
			jsonObject.getJSONArray("translations"),
			customFieldJSONObject -> new Translation(
				customFieldJSONObject.getString("detected_source_language"),
				customFieldJSONObject.getString("text")),
			_log);
	}

	private String _getTranslation(
			String authKey, String text, String sourceLanguageId,
			String targetLanguageId, String url)
		throws IOException, PortalException {

		Http.Options options = new Http.Options();

		options.addHeader(
			HttpHeaders.CONTENT_TYPE,
			ContentTypes.APPLICATION_X_WWW_FORM_URLENCODED);
		options.addPart("auth_key", authKey);
		options.addPart("source_lang", sourceLanguageId);
		options.addPart("target_lang", targetLanguageId);
		options.addPart("text", text);

		options.setLocation(
			URLBuilder.create(
				url
			).addParameter(
				"auth_key", authKey
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

	private List<SupportedLanguageCode> _toSupportedLanguageCodes(
			String authKey, String target, String url)
		throws IOException, PortalException {

		JSONArray jsonArray = _jsonFactory.createJSONArray(
			_getSupportedLanguageCode(authKey, target, url));

		return JSONUtil.toList(
			jsonArray,
			customFieldJSONObject -> new SupportedLanguageCode(
				customFieldJSONObject.getString("language"),
				customFieldJSONObject.getString("name"),
				customFieldJSONObject.getBoolean("supports_formality")),
			_log);
	}

	private String _translate(
			String url, String authKey, String text, String sourceLanguageCode,
			String targetLanguageCode)
		throws PortalException {

		try {
			if (Validator.isBlank(text)) {
				return text;
			}

			List<Translation> translations = _getTranslateResponse(
				authKey, text, sourceLanguageCode, targetLanguageCode, url);

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