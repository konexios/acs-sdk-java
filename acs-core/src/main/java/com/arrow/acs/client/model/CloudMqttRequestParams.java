package com.arrow.acs.client.model;

import com.arrow.acs.client.search.SearchCriteria;

public class CloudMqttRequestParams {
	
	// TODO: complete class
	
	private String requestId;
	private CloudRequestMethodName httpMethod;
	private String url;
	private String jsonBody;
	private SearchCriteria criteria;
	private boolean encrypted;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public CloudRequestMethodName getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(CloudRequestMethodName httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getJsonBody() {
		return jsonBody;
	}

	public void setJsonBody(String jsonBody) {
		this.jsonBody = jsonBody;
	}

	public SearchCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(SearchCriteria criteria) {
		this.criteria = criteria;
	}

	public boolean isEncrypted() {
		return encrypted;
	}

	public void setEncrypted(boolean encrypted) {
		this.encrypted = encrypted;
	}

}
