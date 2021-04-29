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

import com.arrow.acs.client.search.ApplicationSearchCriteria;
import com.fasterxml.jackson.core.type.TypeReference;
import com.konexios.acs.JsonUtils;
import com.konexios.acs.client.api.ApiConfig;
import com.konexios.acs.client.model.AccessKeyModel;
import com.konexios.acs.client.model.ApplicationModel;
import com.konexios.acs.client.model.HidModel;
import com.konexios.acs.client.model.ListResultModel;
import com.konexios.acs.client.model.PagingResultModel;
import com.konexios.acs.client.model.UpdateApplicationModel;

public class ApplicationApi extends AcsApiAbstract {
	private static final String APPLICATIONS_ROOT_URL = WEB_SERVICE_ROOT_URL + "/applications";

	// instantiation is expensive for these objects
	private TypeReference<ListResultModel<AccessKeyModel>> accessKeyModelTypeRef;
	private TypeReference<PagingResultModel<ApplicationModel>> applicationModelTypeRef;

	ApplicationApi(ApiConfig apiConfig) {
		super(apiConfig);
	}

	public ApplicationModel findByHid(String hid) {
		String method = "findByHid";
		try {
			URI uri = buildUri(APPLICATIONS_ROOT_URL + "/hids/" + hid);
			ApplicationModel result = execute(new HttpGet(uri), ApplicationModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "result: %s", JsonUtils.toJson(result));
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public PagingResultModel<ApplicationModel> findByCriteria(ApplicationSearchCriteria criteria) {
		String method = "findByCriteria";
		try {
			URI uri = buildUri(APPLICATIONS_ROOT_URL, criteria);
			PagingResultModel<ApplicationModel> result = execute(new HttpGet(uri), getApplicationModelTypeRef());
			log(method, result);
			return result;
		} catch (Throwable t) {
			throw handleException(t);
		}
	}

	public HidModel createApplication(ApplicationModel model) {
		String method = "createApplication";
		try {
			URI uri = buildUri(APPLICATIONS_ROOT_URL);
			HidModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), HidModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "result: %s", JsonUtils.toJson(result));
			return result;
		} catch (Throwable t) {
			throw handleException(t);
		}
	}

	public HidModel updateApplication(String hid, UpdateApplicationModel model) {
		String method = "updateApplication";
		try {
			URI uri = buildUri(APPLICATIONS_ROOT_URL + "/" + hid);
			HidModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), HidModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "result: %s", JsonUtils.toJson(result));
			return result;
		} catch (Throwable t) {
			throw handleException(t);
		}
	}

	public ListResultModel<AccessKeyModel> findAccessKeyByApplicationHid(String hid) {
		String method = "findAccessKeyByApplicationHid";
		try {
			URI uri = buildUri(APPLICATIONS_ROOT_URL + "/" + hid + "/access-keys");
			ListResultModel<AccessKeyModel> result = execute(new HttpGet(uri), getAccessKeyModelTypeRef());
			if (result != null && isDebugEnabled())
				log(method, result);
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	synchronized TypeReference<ListResultModel<AccessKeyModel>> getAccessKeyModelTypeRef() {
		return accessKeyModelTypeRef != null ? accessKeyModelTypeRef
				: (accessKeyModelTypeRef = new TypeReference<ListResultModel<AccessKeyModel>>() {
				});
	}

	synchronized TypeReference<PagingResultModel<ApplicationModel>> getApplicationModelTypeRef() {
		return applicationModelTypeRef != null ? applicationModelTypeRef
				: (applicationModelTypeRef = new TypeReference<PagingResultModel<ApplicationModel>>() {
				});
	}
}
