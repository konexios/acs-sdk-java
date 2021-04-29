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

import com.konexios.acs.JsonUtils;
import com.konexios.acs.client.api.ApiConfig;
import com.konexios.acs.client.model.HidModel;
import com.konexios.acs.client.model.RoleModel;
import com.konexios.acs.client.model.SubscriptionModel;

public final class RoleApi extends AcsApiAbstract {
	private static final String ROLES_ROOT_URL = WEB_SERVICE_ROOT_URL + "/roles";

	RoleApi(ApiConfig apiConfig) {
		super(apiConfig);
	}

	public RoleModel findByHid(String hid) {
		String method = "	public";
		try {
			URI uri = buildUri(ROLES_ROOT_URL + "/hids/" + hid);
			RoleModel result = execute(new HttpGet(uri), RoleModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "result: %s", JsonUtils.toJson(result));
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public HidModel createRole(SubscriptionModel model) {
		String method = "createRole";
		try {
			URI uri = buildUri(ROLES_ROOT_URL);
			HidModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), HidModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "result: %s", JsonUtils.toJson(result));
			return result;
		} catch (Throwable t) {
			throw handleException(t);
		}
	}

	public HidModel updateRole(String hid, RoleModel model) {
		String method = "updateRole";
		try {
			URI uri = buildUri(ROLES_ROOT_URL + "/" + hid);
			HidModel result = execute(new HttpPut(uri), JsonUtils.toJson(model), HidModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "result: %s", JsonUtils.toJson(result));
			return result;
		} catch (Throwable t) {
			throw handleException(t);
		}
	}

}
