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
package com.arrow.acs.client.api;

import java.net.URI;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import com.arrow.acs.client.search.SubscriptionSearchCriteria;
import com.fasterxml.jackson.core.type.TypeReference;
import com.konexios.acs.JsonUtils;
import com.konexios.acs.client.api.ApiConfig;
import com.konexios.acs.client.model.AccessKeyModel;
import com.konexios.acs.client.model.ApplicationModel;
import com.konexios.acs.client.model.HidModel;
import com.konexios.acs.client.model.ListResultModel;
import com.konexios.acs.client.model.SubscriptionModel;
import com.konexios.acs.client.model.UpdateApplicationModel;

public final class SubscriptionApi extends AcsApiAbstract {
	private static final String SUBSCRIPTIONS_ROOT_URL = WEB_SERVICE_ROOT_URL + "/subscriptions";

	// instantiation is expensive for these objects
	private TypeReference<ListResultModel<AccessKeyModel>> accessKeyModelTypeRef;
	private TypeReference<ListResultModel<ApplicationModel>> applicationModelTypeRef;
	private TypeReference<ListResultModel<SubscriptionModel>> subscriptionModelTypeRef;

	SubscriptionApi(ApiConfig apiConfig) {
		super(apiConfig);
	}

	public SubscriptionModel findByHid(String hid) {
		String method = "	public";
		try {
			URI uri = buildUri(SUBSCRIPTIONS_ROOT_URL + "/hids/" + hid);
			SubscriptionModel result = execute(new HttpGet(uri), SubscriptionModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "result: %s", JsonUtils.toJson(result));
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public ListResultModel<SubscriptionModel> findByCriteria(SubscriptionSearchCriteria criteria) {
		String method = "findByCriteria";
		try {
			URI uri = buildUri(SUBSCRIPTIONS_ROOT_URL, criteria);
			ListResultModel<SubscriptionModel> result = execute(new HttpGet(uri), getSubscriptionModelTypeRef());
			log(method, result);
			return result;
		} catch (Throwable t) {
			throw handleException(t);
		}
	}

	public ListResultModel<AccessKeyModel> findAccessKeyBySubscriptionHid(String hid) {
		String method = "findAccessKeyBySubscriptionHid";
		try {
			URI uri = buildUri(SUBSCRIPTIONS_ROOT_URL + "/" + hid + "/access-keys");
			ListResultModel<AccessKeyModel> result = execute(new HttpGet(uri), getAccessKeyModelTypeRef());
			if (result != null && isDebugEnabled())
				log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public ListResultModel<ApplicationModel> findApplicationsBySubscriptionHid(String hid) {
		String method = "findApplicationsBySubscriptionHid";
		try {
			URI uri = buildUri(SUBSCRIPTIONS_ROOT_URL + "/" + hid + "/applications");
			ListResultModel<ApplicationModel> result = execute(new HttpGet(uri), getApplicationModelTypeRef());
			if (result != null && isDebugEnabled())
				log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public HidModel createSubscription(SubscriptionModel model) {
		String method = "createSubscription";
		try {
			URI uri = buildUri(SUBSCRIPTIONS_ROOT_URL);
			HidModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), HidModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "result: %s", JsonUtils.toJson(result));
			return result;
		} catch (Throwable t) {
			throw handleException(t);
		}
	}

	public HidModel updateSubscription(String hid, UpdateApplicationModel model) {
		String method = "updateSubscription";
		try {
			URI uri = buildUri(SUBSCRIPTIONS_ROOT_URL + "/" + hid);
			HidModel result = execute(new HttpPut(uri), JsonUtils.toJson(model), HidModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "result: %s", JsonUtils.toJson(result));
			return result;
		} catch (Throwable t) {
			throw handleException(t);
		}
	}

	synchronized TypeReference<ListResultModel<AccessKeyModel>> getAccessKeyModelTypeRef() {
		return accessKeyModelTypeRef != null ? accessKeyModelTypeRef
				: (accessKeyModelTypeRef = new TypeReference<ListResultModel<AccessKeyModel>>() {
				});
	}

	synchronized TypeReference<ListResultModel<ApplicationModel>> getApplicationModelTypeRef() {
		return applicationModelTypeRef != null ? applicationModelTypeRef
				: (applicationModelTypeRef = new TypeReference<ListResultModel<ApplicationModel>>() {
				});
	}

	synchronized TypeReference<ListResultModel<SubscriptionModel>> getSubscriptionModelTypeRef() {
		return subscriptionModelTypeRef != null ? subscriptionModelTypeRef
				: (subscriptionModelTypeRef = new TypeReference<ListResultModel<SubscriptionModel>>() {
				});
	}
}
