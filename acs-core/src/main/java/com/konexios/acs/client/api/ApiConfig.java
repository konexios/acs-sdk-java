/*******************************************************************************
 * Copyright 2021 Konexios, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.konexios.acs.client.api;

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
