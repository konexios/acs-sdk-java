/*******************************************************************************
 * Copyright (c) 2017 Arrow Electronics, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License 2.0
 * which accompanies this distribution, and is available at
 * http://apache.org/licenses/LICENSE-2.0
 *
 * Contributors:
 *     Arrow Electronics, Inc.
 *******************************************************************************/
package com.arrow.acs.client.api;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import com.arrow.acs.AcsErrorResponse;
import com.arrow.acs.ApiHeaders;
import com.arrow.acs.ApiRequestSigner;
import com.arrow.acs.JsonUtils;
import com.arrow.acs.Loggable;
import com.arrow.acs.client.AcsClientException;
import com.arrow.acs.client.model.ExternalHidModel;
import com.arrow.acs.client.model.HidModel;
import com.arrow.acs.client.model.ListResultModel;
import com.arrow.acs.client.model.ModelAbstract;
import com.arrow.acs.client.model.PagingResultModel;
import com.arrow.acs.client.model.StatusModel;
import com.arrow.acs.client.search.SearchCriteria;

public abstract class ApiAbstract extends Loggable {

	private static final String SIGNATURE_MSG = "signature: %s";
	private static final String MIME_APPLICATION_JSON = "application/json";
	private static final Pattern EXTRA_SLASHES = Pattern.compile("/{2,}");

	private ApiConfig apiConfig;

	public void setApiConfig(ApiConfig apiConfig) {
		this.apiConfig = apiConfig;
	}

	public ApiConfig getApiConfig() {
		return apiConfig;
	}

	public URI buildUri(String path) {
		return buildUri(path, null);
	}

	public URI buildUri(String path, SearchCriteria criteria) {
		Validate.notNull(apiConfig, "apiConfig is not set");
		String baseUrl = apiConfig.getBaseUrl();
		try {
			URIBuilder uriBuilder = new URIBuilder(StringUtils.isBlank(baseUrl) ? "" : baseUrl);
			if (!StringUtils.isEmpty(path)) {
				uriBuilder.setPath(EXTRA_SLASHES.matcher(uriBuilder.getPath() + '/' + path).replaceAll("/"));
			}
			if (criteria != null) {
				uriBuilder.setParameters(criteria.getAllCriteria());
			}
			return uriBuilder.build();
		} catch (URISyntaxException e) {
			throw new AcsClientException("Invalid base: " + baseUrl + ", path: " + path, new AcsErrorResponse());
		}
	}

	public <T> T execute(HttpEntityEnclosingRequestBase request, String payload, Class<T> clazz) throws IOException {
		Validate.notNull(apiConfig, "apiConfig is not set");
		return JsonUtils.fromJson(execute(sign(request, payload)), clazz);
	}

	public <T> T execute(HttpEntityEnclosingRequestBase request, String payload, TypeReference<T> typeRef)
	        throws IOException {
		Validate.notNull(apiConfig, "apiConfig is not set");
		return JsonUtils.fromJson(execute(sign(request, payload)), typeRef);
	}

	public <T> T execute(HttpRequestBase request, Class<T> clazz) throws IOException {
		Validate.notNull(apiConfig, "apiConfig is not set");
		return JsonUtils.fromJson(execute(sign(request)), clazz);
	}

	public <T> T execute(HttpRequestBase request, TypeReference<T> typeRef) throws IOException {
		Validate.notNull(apiConfig, "apiConfig is not set");
		return JsonUtils.fromJson(execute(sign(request)), typeRef);
	}

	public <T> T execute(HttpRequestBase request, SearchCriteria criteria, TypeReference<T> typeRef)
	        throws IOException {
		Validate.notNull(criteria, "criteria is null");
		Validate.notNull(apiConfig, "apiConfig is not set");
		return JsonUtils.fromJson(execute(sign(request, criteria)), typeRef);
	}

	public <T> T execute(HttpRequestBase request, SearchCriteria criteria, Class<T> clazz)
	        throws IOException {
		Validate.notNull(criteria, "criteria is null");
		Validate.notNull(apiConfig, "apiConfig is not set");
		return JsonUtils.fromJson(execute(sign(request, criteria)), clazz);
	}

	public long execute(HttpRequestBase request, OutputStream outputStream) throws IOException {
		Validate.notNull(request, "request is null");
		Validate.notNull(apiConfig, "apiConfig is not set");
		String method = "execute";
		logInfo(method, "url: %s", request.getURI());
		try (CloseableHttpClient httpClient = ConnectionManager.getInstance().getConnection()) {
			HttpResponse response = httpClient.execute(sign(request));
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				String content = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
				String message = String.format("error response: %d - %s, error: %s", statusCode,
				        response.getStatusLine().getReasonPhrase(), content);
				throw new AcsClientException(message,
				        new AcsErrorResponse().withStatus(statusCode).withMessage(message));
			}
			return IOUtils.copyLarge(response.getEntity().getContent(), outputStream);
		}
	}

	private String execute(HttpRequestBase request) throws IOException {
		Validate.notNull(request, "request is null");
		String method = "execute";
		logInfo(method, "url: %s", request.getURI());
		try (CloseableHttpClient httpClient = ConnectionManager.getInstance().getConnection()) {
			HttpResponse response = httpClient.execute(request);
			String content = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				logError(method, "ERROR: %s", content);
				String message = String.format("error response: %d - %s, error: %s", statusCode,
				        response.getStatusLine().getReasonPhrase(), content);
				throw new AcsClientException(message, JsonUtils.fromJson(content, AcsErrorResponse.class));
			}
			return content;
		}
	}

	private ApiRequestSigner getSigner(HttpRequestBase request, Instant timestamp) {
		Validate.notNull(request, "request is null");
		Validate.notNull(timestamp, "timestamp is null");
		Validate.notEmpty(apiConfig.getApiKey(), "apiKey is empty");
		Validate.notEmpty(apiConfig.getSecretKey(), "secretKey is empty");
		return ApiRequestSigner.create(apiConfig.getSecretKey()).method(request.getMethod())
		        .canonicalUri(request.getURI().getPath()).apiKey(apiConfig.getApiKey()).timestamp(timestamp.toString());
	}

	private HttpRequestBase sign(HttpRequestBase request) {
		return sign(request, null);
	}

	private HttpRequestBase sign(HttpRequestBase request, SearchCriteria criteria) {
		String method = "sign";
		Instant timestamp = Instant.now();
		ApiRequestSigner signer = getSigner(request, timestamp);
		if (criteria != null) {
			for (NameValuePair pair : criteria.getAllCriteria()) {
				signer.parameter(pair.getName(), pair.getValue());
			}
		}
		String signature = signer.signV1();
		logDebug(method, SIGNATURE_MSG, signature);
		addHeaders(request, timestamp, signature);
		return request;
	}

	private HttpEntityEnclosingRequestBase sign(HttpEntityEnclosingRequestBase request, String payload) {
		String method = "sign";
		Instant timestamp = Instant.now();
		String signature = getSigner(request, timestamp).payload(payload).signV1();
		logDebug(method, SIGNATURE_MSG, signature);
		addHeaders(request, timestamp, signature);
		request.setEntity(new StringEntity(payload, StandardCharsets.UTF_8));
		return request;
	}

	private void addHeaders(HttpRequestBase msg, Instant timestamp, String signature) {
		Validate.notNull(msg, "msg is null");
		Validate.notNull(timestamp, "timestamp is null");
		Validate.notEmpty(apiConfig.getApiKey(), "apiKey is empty");
		msg.addHeader(HttpHeaders.CONTENT_TYPE, MIME_APPLICATION_JSON);
		msg.addHeader(HttpHeaders.ACCEPT, MIME_APPLICATION_JSON);
		msg.addHeader(ApiHeaders.X_ARROW_APIKEY, apiConfig.getApiKey());
		msg.addHeader(ApiHeaders.X_ARROW_DATE, timestamp.toString());
		msg.addHeader(ApiHeaders.X_ARROW_VERSION, ApiHeaders.X_ARROW_VERSION_1);
		msg.addHeader(ApiHeaders.X_ARROW_SIGNATURE, signature);
	}

	protected void log(String method, ModelAbstract<?> model) {
		logDebug(method, "hid: %s", model.getHid());
	}

	protected void log(String method, HidModel model) {
		logDebug(method, "hid: %s, message: %s", model.getHid(), model.getMessage());
	}

	protected void log(String method, ExternalHidModel model) {
		logDebug(method, "hid: %s, externalId: %s, message: %s", model.getHid(), model.getExternalId(),
		        model.getMessage());
	}

	protected void log(String method, StatusModel model) {
		logDebug(method, "status: %s, message: %s", model.getStatus(), model.getMessage());
	}

	protected void log(String method, ListResultModel<?> model) {
		logDebug(method, "size: %d", model.getSize());
	}

	protected void log(String method, PagingResultModel<?> model) {
		logDebug(method, "size: %d, totalSize: %d, page: %d, totalPage: %d", model.getSize(), model.getTotalSize(),
		        model.getPage(), model.getTotalPages());
	}

	protected AcsClientException handleException(Throwable t) {
		String method = "handleException";
		if (t instanceof AcsClientException) {
			return (AcsClientException) t;
		} else {
			logError(method, t);
			return new AcsClientException(t);
		}
	}
}
