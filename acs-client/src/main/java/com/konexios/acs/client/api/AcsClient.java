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

import com.konexios.acs.AcsUtils;

public final class AcsClient {
	private ApiConfig apiConfig;
	private ApplicationApi applicationApi;
	private CacheApi cacheApi;
	private CompanyApi companyApi;
	private PrivilegeApi privilegeApi;
	private ProductApi productApi;
	private RoleApi roleApi;
	private SubscriptionApi subscriptionApi;
	private UserApi userApi;

	public AcsClient(ApiConfig apiConfig) {
		setApiConfig(apiConfig);
	}

	public void setApiConfig(ApiConfig apiConfig) {
		AcsUtils.notNull(apiConfig, "apiConfig is not set");
		this.apiConfig = apiConfig;
		if (applicationApi != null)
			applicationApi.setApiConfig(apiConfig);
		if (cacheApi != null)
			cacheApi.setApiConfig(apiConfig);
		if (companyApi != null)
			companyApi.setApiConfig(apiConfig);
		if (privilegeApi != null)
			privilegeApi.setApiConfig(apiConfig);
		if (productApi != null)
			productApi.setApiConfig(apiConfig);
		if (roleApi != null)
			roleApi = new RoleApi(apiConfig);
		if (subscriptionApi != null)
			subscriptionApi = new SubscriptionApi(apiConfig);
		if (userApi != null)
			userApi = new UserApi(apiConfig);
	}

	public ApiConfig getApiConfig() {
		return apiConfig;
	}

	public synchronized ApplicationApi getApplicationApi() {
		return applicationApi != null ? applicationApi : (applicationApi = new ApplicationApi(apiConfig));
	}

	public synchronized CacheApi getCacheApi() {
		return cacheApi != null ? cacheApi : (cacheApi = new CacheApi(apiConfig));
	}

	public synchronized CompanyApi getCompanyApi() {
		return companyApi != null ? companyApi : (companyApi = new CompanyApi(apiConfig));
	}

	public synchronized PrivilegeApi getPrivilegeApi() {
		return privilegeApi != null ? privilegeApi : (privilegeApi = new PrivilegeApi(apiConfig));
	}

	public synchronized ProductApi getProductApi() {
		return productApi != null ? productApi : (productApi = new ProductApi(apiConfig));
	}

	public synchronized RoleApi getRoleApi() {
		return productApi != null ? roleApi : (roleApi = new RoleApi(apiConfig));
	}

	public synchronized SubscriptionApi getSubscriptionApi() {
		return subscriptionApi != null ? subscriptionApi : (subscriptionApi = new SubscriptionApi(apiConfig));
	}

	public synchronized UserApi getUserApi() {
		return userApi != null ? userApi : (userApi = new UserApi(apiConfig));
	}

}
