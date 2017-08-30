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

import java.io.Serializable;

public class ApiConfig implements Serializable {
	private static final long serialVersionUID = 3202503635018331127L;

	private String baseUrl;
	private String baseWebSocketUrl;
	private String apiKey;
	private String secretKey;

	public ApiConfig withBaseUrl(String baseUrl) {
		setBaseUrl(baseUrl);
		return this;
	}

	public ApiConfig withBaseWebSocketUrl(String baseWebSocketUrl) {
		setBaseWebSocketUrl(baseWebSocketUrl);
		return this;
	}

	public ApiConfig withApiKey(String apiKey) {
		setApiKey(apiKey);
		return this;
	}

	public ApiConfig withSecretkey(String secretKey) {
		setSecretKey(secretKey);
		return this;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getBaseWebSocketUrl() {
		return baseWebSocketUrl;
	}

	public void setBaseWebSocketUrl(String baseWebSocketUrl) {
		this.baseWebSocketUrl = baseWebSocketUrl;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
}
