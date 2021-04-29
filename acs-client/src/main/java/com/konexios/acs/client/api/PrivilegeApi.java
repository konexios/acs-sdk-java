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

import java.net.URI;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import com.fasterxml.jackson.core.type.TypeReference;

import com.konexios.acs.JsonUtils;
import com.konexios.acs.client.model.HidModel;
import com.konexios.acs.client.model.ListResultModel;
import com.konexios.acs.client.model.PrivilegeModel;
import com.konexios.acs.client.search.ApplicationSearchCriteria;

public final class PrivilegeApi extends AcsApiAbstract {
	private static final String PRIVS_ROOT_URL = WEB_SERVICE_ROOT_URL + "/privileges";

	// instantiation is expensive for these objects
	private TypeReference<ListResultModel<PrivilegeModel>> privModelTypeRef;

	PrivilegeApi(ApiConfig apiConfig) {
		super(apiConfig);
	}

	public PrivilegeModel findByHid(String hid) {
		String method = "findByHid";
		try {
			URI uri = buildUri(String.format("%s/%s", PRIVS_ROOT_URL, hid));
			PrivilegeModel result = execute(new HttpGet(uri), PrivilegeModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "result: %s", JsonUtils.toJson(result));
			return result;
		} catch (Throwable t) {
			throw handleException(t);
		}
	}

	public ListResultModel<PrivilegeModel> findByCriteria(ApplicationSearchCriteria criteria) {
		String method = "findByCriteria";
		try {
			URI uri = buildUri(PRIVS_ROOT_URL, criteria);
			ListResultModel<PrivilegeModel> result = execute(new HttpGet(uri), getPrivModelTypeRef());
			log(method, result);
			return result;
		} catch (Throwable t) {
			throw handleException(t);
		}
	}

	public HidModel createPrivilege(PrivilegeModel model) {
		String method = "createPrivilege";
		try {
			URI uri = buildUri(PRIVS_ROOT_URL);
			HidModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), HidModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "result: %s", JsonUtils.toJson(result));
			return result;
		} catch (Throwable t) {
			throw handleException(t);
		}
	}

	public HidModel updatePrivilege(String hid, PrivilegeModel model) {
		String method = "updatePrivilege";
		try {
			URI uri = buildUri(String.format("%s/%s", PRIVS_ROOT_URL, hid));
			HidModel result = execute(new HttpPut(uri), JsonUtils.toJson(model), HidModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "result: %s", JsonUtils.toJson(result));
			return result;
		} catch (Throwable t) {
			throw handleException(t);
		}
	}

	synchronized TypeReference<ListResultModel<PrivilegeModel>> getPrivModelTypeRef() {
		return privModelTypeRef != null ? privModelTypeRef
				: (privModelTypeRef = new TypeReference<ListResultModel<PrivilegeModel>>() {
				});
	}
}
