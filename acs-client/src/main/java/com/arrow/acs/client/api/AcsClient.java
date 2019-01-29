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

import olympus.client.api.ApiConfig;
import olympus.util.AcsUtils;

public final class AcsClient {
	private ApiConfig apiConfig;
	private ClientCacheApi cacheApi;
	private ClientUserApi userApi;
	private ClientApplicationApi applicationApi;

	public AcsClient(ApiConfig apiConfig) {
		AcsUtils.notNull(apiConfig, "apiConfig is not set");
		this.apiConfig = apiConfig;
		this.cacheApi = new ClientCacheApi(apiConfig);
		this.userApi = new ClientUserApi(apiConfig);
		this.applicationApi = new ClientApplicationApi(apiConfig);
	}

	public void setApiConfig(ApiConfig apiConfig) {
		AcsUtils.notNull(apiConfig, "apiConfig is not set");
		this.apiConfig = apiConfig;
		this.cacheApi.setApiConfig(apiConfig);
		this.userApi.setApiConfig(apiConfig);
	}

	public ApiConfig getApiConfig() {
		return apiConfig;
	}

	public ClientCacheApi getCacheApi() {
		return cacheApi;
	}

	public ClientUserApi getUserApi() {
		return userApi;
	}

	public ClientApplicationApi getApplicationApi() {
		return applicationApi;
	}
}
