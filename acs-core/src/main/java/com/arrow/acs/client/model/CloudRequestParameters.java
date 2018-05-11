package com.arrow.acs.client.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class CloudRequestParameters implements Serializable {
	private static final long serialVersionUID = -195973692659359430L;
	
	private static final String URI_PARAMETER_NAME = "uri";
	private static final String METHOD_PARAMETER_NAME = "method";
	private static final String API_KEY_PARAMETER_NAME = "apiKey";
	private static final String BODY_PARAMETER_NAME = "body";
	private static final String API_REQUEST_SIGNATURE_PARAMETER_NAME = "apiRequestSignature";
	private static final String API_REQUEST_SIGNATURE_VERSION_PARAMETER_NAME = "apiRequestSignatureVersion";
	private static final String TIMESTAMP_PARAMETER_NAME = "timestamp";
	
	private String uri;
	private CloudRequestMethodName method;
	private String apiKey;
	private String body;
	private String apiRequestSignature;
	private String apiRequestSignatureVersion;
	private String timestamp;

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public CloudRequestMethodName getMethod() {
		return method;
	}

	public void setMethod(CloudRequestMethodName method) {
		this.method = method;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getApiRequestSignature() {
		return apiRequestSignature;
	}

	public void setApiRequestSignature(String apiRequestSignature) {
		this.apiRequestSignature = apiRequestSignature;
	}

	public String getApiRequestSignatureVersion() {
		return apiRequestSignatureVersion;
	}

	public void setApiRequestSignatureVersion(String apiRequestSignatureVersion) {
		this.apiRequestSignatureVersion = apiRequestSignatureVersion;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Map<String, String> getParameters() {
		Map<String, String> parameters = new LinkedHashMap<>();
		
		parameters.put(URI_PARAMETER_NAME, getUri());
		// TODO verify result of String.valueOf here
		parameters.put(METHOD_PARAMETER_NAME, String.valueOf(getMethod()));
		parameters.put(API_KEY_PARAMETER_NAME, getApiKey());
		parameters.put(BODY_PARAMETER_NAME, getBody());
		parameters.put(API_REQUEST_SIGNATURE_PARAMETER_NAME, getApiRequestSignature());
		parameters.put(API_REQUEST_SIGNATURE_VERSION_PARAMETER_NAME, getApiRequestSignatureVersion());
		parameters.put(TIMESTAMP_PARAMETER_NAME, getTimestamp());
		
		return parameters;
	}
	
}
