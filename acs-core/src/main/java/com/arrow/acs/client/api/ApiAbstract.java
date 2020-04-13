/*******************************************************************************
 * Copyright (c) 2018 Arrow Electronics, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License 2.0
 * which accompanies this distribution, and is available at
 * http://apache.org/licenses/LICENSE-2.0
 *
 * Contributors:
 *     Arrow Electronics, Inc.
 *******************************************************************************/
package com.arrow.acs.client.api;

import java.io.File;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;

import com.arrow.acs.AcsErrorResponse;
import com.arrow.acs.AcsLogicalException;
import com.arrow.acs.AcsSystemException;
import com.arrow.acs.AcsUtils;
import com.arrow.acs.ApiHeaders;
import com.arrow.acs.ApiRequestSigner;
import com.arrow.acs.GatewayPayloadSigner;
import com.arrow.acs.JsonUtils;
import com.arrow.acs.Loggable;
import com.arrow.acs.client.AcsClientException;
import com.arrow.acs.client.model.CloudRequestMethodName;
import com.arrow.acs.client.model.CloudRequestModel;
import com.arrow.acs.client.model.CloudRequestParameters;
import com.arrow.acs.client.model.CloudResponseModel;
import com.arrow.acs.client.model.DownloadFileInfo;
import com.arrow.acs.client.model.ExternalHidModel;
import com.arrow.acs.client.model.HidModel;
import com.arrow.acs.client.model.ListResultModel;
import com.arrow.acs.client.model.ModelAbstract;
import com.arrow.acs.client.model.PagingResultModel;
import com.arrow.acs.client.model.StatusModel;
import com.arrow.acs.client.search.SearchCriteria;
import com.fasterxml.jackson.core.type.TypeReference;

public abstract class ApiAbstract extends Loggable {

	private static final String SIGNATURE_MSG = "signature: %s";
	private static final String MIME_APPLICATION_JSON = "application/json";
	private static final Pattern EXTRA_SLASHES = Pattern.compile("/{2,}");

	private ApiConfig apiConfig;
	private MqttHttpChannel mqttHttpChannel;

	public void setApiConfig(ApiConfig apiConfig) {
		this.apiConfig = apiConfig;
	}

	public ApiConfig getApiConfig() {
		return apiConfig;
	}

	public MqttHttpChannel getMqttHttpChannel() {
		return mqttHttpChannel;
	}

	public void setMqttHttpChannel(MqttHttpChannel mqttHttpChannel) {
		this.mqttHttpChannel = mqttHttpChannel;
	}

	protected URI buildUri(String path) {
		return buildUri(path, null);
	}

	protected URI buildUri(String path, SearchCriteria criteria) {
		AcsUtils.notNull(apiConfig, "apiConfig is not set");
		String baseUrl = apiConfig.getBaseUrl();
		return buildUri(baseUrl, path, criteria);
	}

	protected URI buildWebSocketUri(String path) {
		return buildWebSocketUri(path, null);
	}

	protected URI buildWebSocketUri(String path, SearchCriteria criteria) {
		AcsUtils.notNull(apiConfig, "apiConfig is not set");
		String baseUrl = apiConfig.getBaseWebSocketUrl();
		return buildUri(baseUrl, path, criteria);
	}

	private URI buildUri(String baseUrl, String path, SearchCriteria criteria) {
		try {
			URIBuilder uriBuilder = new URIBuilder(AcsUtils.isEmpty(baseUrl) ? AcsUtils.EMPTY_TRING : baseUrl);
			if (!AcsUtils.isEmpty(path)) {
				uriBuilder.setPath(
						EXTRA_SLASHES.matcher(AcsUtils.trimToEmpty(uriBuilder.getPath()) + '/' + path).replaceAll("/"));
			}
			if (criteria != null) {
				uriBuilder.setParameters(criteria.getAllCriteria());
			}
			return uriBuilder.build();
		} catch (URISyntaxException e) {
			String error = String.format("Invalid baseUrl: %s, path: %s", baseUrl, path);
			throw new AcsClientException(error,
					new AcsErrorResponse().withStatus(HttpStatus.SC_BAD_REQUEST).withMessage(error));
		}
	}

	protected <T> T execute(HttpEntityEnclosingRequestBase request, String payload, Class<T> clazz) throws Exception {
		AcsUtils.notNull(apiConfig, "apiConfig is not set");
		return JsonUtils.fromJson(execute(sign(request, payload)), clazz);
	}

	protected <T> T execute(HttpEntityEnclosingRequestBase request, String payload, TypeReference<T> typeRef)
			throws Exception {
		AcsUtils.notNull(apiConfig, "apiConfig is not set");
		return JsonUtils.fromJson(execute(sign(request, payload)), typeRef);
	}

	protected <T> T execute(HttpRequestBase request, Class<T> clazz) throws Exception {
		AcsUtils.notNull(apiConfig, "apiConfig is not set");
		return JsonUtils.fromJson(execute(sign(request)), clazz);
	}

	protected <T> T execute(HttpRequestBase request, TypeReference<T> typeRef) throws Exception {
		AcsUtils.notNull(apiConfig, "apiConfig is not set");
		return JsonUtils.fromJson(execute(sign(request)), typeRef);
	}

	protected <T> T execute(HttpRequestBase request, SearchCriteria criteria, TypeReference<T> typeRef)
			throws Exception {
		AcsUtils.notNull(criteria, "criteria is null");
		AcsUtils.notNull(apiConfig, "apiConfig is not set");
		return JsonUtils.fromJson(execute(sign(request, criteria)), typeRef);
	}

	protected <T> T execute(HttpRequestBase request, SearchCriteria criteria, Class<T> clazz) throws Exception {
		AcsUtils.notNull(criteria, "criteria is null");
		AcsUtils.notNull(apiConfig, "apiConfig is not set");
		return JsonUtils.fromJson(execute(sign(request, criteria)), clazz);
	}

	protected long execute(HttpRequestBase request, OutputStream outputStream) throws Exception {
		AcsUtils.notNull(request, "request is null");
		AcsUtils.notNull(apiConfig, "apiConfig is not set");
		String method = "execute";
		logInfo(method, "URI: %s", request.getURI());

		if (getMqttHttpChannel() != null) {
			throw new AcsSystemException("MqttHttpChannel is not implemented for this method yet!");
		}

		try (CloseableHttpResponse response = ConnectionManager.getInstance().getSharedClient()
				.execute(sign(request))) {
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				String content = AcsUtils.streamToString(response.getEntity().getContent(), StandardCharsets.UTF_8);
				String message = String.format("error response: %d - %s, error: %s", statusCode,
						response.getStatusLine().getReasonPhrase(), content);
				throw new AcsClientException(message,
						new AcsErrorResponse().withStatus(statusCode).withMessage(message));
			}
			return AcsUtils.fastCopy(response.getEntity().getContent(), outputStream);
		}
	}

	protected DownloadFileInfo downloadFile(HttpRequestBase request) throws Exception {
		return downloadFile(request, File.createTempFile("acs_", ".dat"));
	}

	protected DownloadFileInfo downloadFile(HttpRequestBase request, File outputFile) throws Exception {
		AcsUtils.notNull(request, "request is null");
		AcsUtils.notNull(apiConfig, "apiConfig is not set");
		String method = "execute";
		logInfo(method, "url: %s", request.getURI());

		if (getMqttHttpChannel() != null) {
			throw new AcsSystemException("MqttHttpChannel is not implemented for this method yet!");
		}

		try (CloseableHttpResponse response = ConnectionManager.getInstance().getSharedClient()
				.execute(sign(request))) {
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				String content = AcsUtils.streamToString(response.getEntity().getContent(), StandardCharsets.UTF_8);
				String message = String.format("error response: %d - %s, error: %s", statusCode,
						response.getStatusLine().getReasonPhrase(), content);
				throw new AcsClientException(message,
						new AcsErrorResponse().withStatus(statusCode).withMessage(message));
			}
			String fileName = null;
			Header contentDispositionHeader = response.getFirstHeader("Content-Disposition");
			if (contentDispositionHeader != null) {
				String contentDisposition = contentDispositionHeader.getValue();
				logInfo(method, "found Content-Disposition: %s", contentDisposition);
				String[] tokens = contentDisposition.split(";", -1);
				for (String token : tokens) {
					if (token.contains("=")) {
						String[] values = token.split("=");
						if (values.length == 2 && values[0].trim().equalsIgnoreCase("filename")) {
							fileName = values[1].trim().replace("\"", "");
						}
					}
				}
				logInfo(method, "fileName: %s", fileName);
			} else {
				logWarn(method, "Content-Disposition header not found!");
			}
			AcsUtils.fastCopy(response.getEntity().getContent(), outputFile);
			return new DownloadFileInfo().withTempFile(outputFile).withFileName(fileName).withSize(outputFile.length());
		}
	}

	private String execute(HttpRequestBase request) throws Exception {
		AcsUtils.notNull(request, "request is null");
		String method = "execute";
		logInfo(method, "URI: %s", request.getURI());

		if (getMqttHttpChannel() != null) {
			return executeCloudRequest(request);
		} else {
			try (CloseableHttpResponse response = ConnectionManager.getInstance().getSharedClient()
					.execute(sign(request))) {
				String content = AcsUtils.streamToString(response.getEntity().getContent(), StandardCharsets.UTF_8);
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
	}

	private ApiRequestSigner getSigner(HttpRequestBase request, Instant timestamp) {
		AcsUtils.notNull(request, "request is null");
		AcsUtils.notNull(timestamp, "timestamp is null");
		AcsUtils.notEmpty(apiConfig.getApiKey(), "apiKey is empty");
		AcsUtils.notEmpty(apiConfig.getSecretKey(), "secretKey is empty");
		return ApiRequestSigner.create(apiConfig.getSecretKey()).method(request.getMethod())
				.canonicalUri(request.getURI().getPath()).apiKey(apiConfig.getApiKey()).timestamp(timestamp.toString());
	}

	private HttpRequestBase sign(HttpRequestBase request) {
		return sign(request, null);
	}

	private HttpRequestBase sign(HttpRequestBase request, SearchCriteria criteria) {
		String method = "sign";
		Header[] existing = request.getHeaders(ApiHeaders.X_ARROW_SIGNATURE);
		if (existing == null || existing.length == 0) {
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
		}
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
		AcsUtils.notNull(msg, "msg is null");
		AcsUtils.notNull(timestamp, "timestamp is null");
		AcsUtils.notEmpty(apiConfig.getApiKey(), "apiKey is empty");
		msg.setHeader(HttpHeaders.CONTENT_TYPE, MIME_APPLICATION_JSON);
		msg.setHeader(HttpHeaders.ACCEPT, MIME_APPLICATION_JSON);
		msg.setHeader(ApiHeaders.X_ARROW_APIKEY, apiConfig.getApiKey());
		msg.setHeader(ApiHeaders.X_ARROW_DATE, timestamp.toString());
		msg.setHeader(ApiHeaders.X_ARROW_VERSION, ApiHeaders.X_ARROW_VERSION_1);
		msg.setHeader(ApiHeaders.X_ARROW_SIGNATURE, signature);
	}

	private String executeCloudRequest(HttpRequestBase request) throws Exception {
		String method = "executeCloudRequest";
		CloudRequestModel requestModel = buildCloudRequestModel(sign(request));

		CloudResponseModel responseModel = getMqttHttpChannel().sendRequest(requestModel, 120);
		logDebug(method, "responseModel: %s", JsonUtils.toJson(responseModel));

		Map<String, String> parameters = responseModel.getParameters();
		String status = parameters.get(CloudResponseModel.STATUS_PARAMETER_NAME);
		String message = parameters.get(CloudResponseModel.MESSAGE_PARAMETER_NAME);
		String payload = parameters.get(CloudResponseModel.PAYLOAD_PARAMETER_NAME);

		if (Objects.equals(status, "OK")) {
			if (!AcsUtils.isEmpty(payload)) {
				return payload;
			} else if (!AcsUtils.isEmpty(message)) {
				return message;
			} else {
				return status;
			}
		} else {
			throw new AcsLogicalException(JsonUtils.toJson(parameters));
		}
	}

	private CloudRequestModel buildCloudRequestModel(HttpRequestBase request) throws Exception {
		String method = "buildCloudRequestModel";

		// TODO need to revisit
		String eventName = "GatewayToServer_ApiRequest";
		String requestId = AcsUtils.randomString(32);

		CloudRequestModel requestModel = new CloudRequestModel().withEncrypted(false).withRequestId(requestId)
				.withEventName(eventName).withSignatureVersion(ApiHeaders.X_ARROW_VERSION_1);

		CloudRequestParameters parameters = new CloudRequestParameters()
				.withMethod(CloudRequestMethodName.valueOf(request.getMethod())).withUri(request.getURI().getPath())
				.withApiKey(request.getFirstHeader(ApiHeaders.X_ARROW_APIKEY).getValue())
				.withTimestamp(request.getFirstHeader(ApiHeaders.X_ARROW_DATE).getValue())
				.withApiRequestSignature(request.getFirstHeader(ApiHeaders.X_ARROW_SIGNATURE).getValue())
				.withApiRequestSignatureVersion(request.getFirstHeader(ApiHeaders.X_ARROW_VERSION).getValue());

		if (HttpEntityEnclosingRequestBase.class.isAssignableFrom(request.getClass())) {
			HttpEntity entity = ((HttpEntityEnclosingRequestBase) request).getEntity();
			if (entity != null) {
				parameters.withBody(AcsUtils.streamToString(entity.getContent(), StandardCharsets.UTF_8));
			}
		}

		requestModel.withParameters(parameters.getParameters());

		GatewayPayloadSigner signer = GatewayPayloadSigner.create(getApiConfig().getSecretKey())
				.withApiKey(getApiConfig().getApiKey()).withHid(requestModel.getRequestId())
				.withName(requestModel.getEventName()).withEncrypted(requestModel.isEncrypted());

		requestModel.getParameters().forEach((name, value) -> signer.withParameter(name, value));
		requestModel.setSignature(signer.signV1());

		logDebug(method, "requestModel: %s", JsonUtils.toJson(requestModel));

		return requestModel;
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

	protected void log(String method, DownloadFileInfo model) {
		logDebug(method, "tempFile: %s, fileName: %s, size: %d", model.getTempFile().getAbsolutePath(),
				model.getFileName(), model.getSize());
	}

	protected AcsClientException handleException(Throwable t) {
		String method = "handleException";
		if (t instanceof AcsClientException) {
			return (AcsClientException) t;
		} else {
			String error = String.format("Internal System Error: %s", t.getMessage());
			logError(method, error, t);
			return new AcsClientException(error, new AcsErrorResponse().withStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR)
					.withMessage(error).withExceptionClassName(t.getClass().getName()));
		}
	}
}
