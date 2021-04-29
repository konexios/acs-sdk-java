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
import org.apache.http.client.utils.URIBuilder;

import com.fasterxml.jackson.core.type.TypeReference;

import com.konexios.acs.JsonUtils;
import com.konexios.acs.client.model.HidModel;
import com.konexios.acs.client.model.PagingResultModel;
import com.konexios.acs.client.model.PasswordModel;
import com.konexios.acs.client.model.StatusMessagesModel;
import com.konexios.acs.client.model.UserAppAuthenticationModel;
import com.konexios.acs.client.model.UserAuthenticationModel;
import com.konexios.acs.client.model.UserModel;
import com.konexios.acs.client.search.UserSearchCriteria;

public final class UserApi extends AcsApiAbstract {
	private static final String USERS_ROOT_URL = WEB_SERVICE_ROOT_URL + "/users";

	// instantiation is expensive for these objects
	private TypeReference<PagingResultModel<UserModel>> userModelTypeRef;

	UserApi(ApiConfig apiConfig) {
		super(apiConfig);
	}

	public UserModel authenticate(String username, String password) {
		String method = "authenticate";
		try {
			URI uri = buildUri(USERS_ROOT_URL + "/auth");
			UserAuthenticationModel model = new UserAuthenticationModel().withUsername(username).withPassword(password);
			UserModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), UserModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "result: %s", JsonUtils.toJson(result));
			return result;
		} catch (Throwable t) {
			throw handleException(t);
		}
	}

	public UserModel authenticate2(String username, String password, String applicationCode) {
		String method = "authenticate";
		try {
			URI uri = buildUri(USERS_ROOT_URL + "/auth2");
			UserAppAuthenticationModel model = (UserAppAuthenticationModel) new UserAppAuthenticationModel()
					.withUsername(username).withPassword(password);
			model.setApplicationCode(applicationCode);
			UserModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), UserModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "result: %s", JsonUtils.toJson(result));
			return result;
		} catch (Throwable t) {
			throw handleException(t);
		}
	}

	public UserModel findByHid(String hid) {
		String method = "findByHid";
		try {
			URI uri = buildUri(USERS_ROOT_URL + "/" + hid);
			UserModel result = execute(new HttpGet(uri), UserModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "result: %s", JsonUtils.toJson(result));
			return result;
		} catch (Throwable t) {
			throw handleException(t);
		}
	}

	public UserModel findByLogin(String username) {
		String method = "findByLogin";
		try {
			URI uri = new URIBuilder(buildUri(USERS_ROOT_URL) + "/logins").addParameter("login", username).build();
			UserModel result = execute(new HttpGet(uri), UserModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "result: %s", JsonUtils.toJson(result));
			return result;
		} catch (Throwable t) {
			throw handleException(t);
		}
	}

	public PagingResultModel<UserModel> findByCriteria(UserSearchCriteria criteria) {
		String method = "findByCriteria";
		try {
			URI uri = buildUri(USERS_ROOT_URL, criteria);
			PagingResultModel<UserModel> result = execute(new HttpGet(uri), getUserModelTypeRef());
			log(method, result);
			return result;
		} catch (Throwable t) {
			throw handleException(t);
		}
	}

	public PasswordModel resetPassword(String hid) {
		String method = "resetPassword";
		try {
			URI uri = buildUri(USERS_ROOT_URL + "/" + hid + "/reset-password");
			PasswordModel result = execute(new HttpPost(uri), PasswordModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "result: %s", JsonUtils.toJson(result));
			return result;
		} catch (Throwable t) {
			throw handleException(t);
		}
	}

	public StatusMessagesModel setNewPassword(String hid, PasswordModel model) {
		String method = "setNewPassword";
		try {
			URI uri = buildUri(USERS_ROOT_URL + "/" + hid + "/set-new-password");
			StatusMessagesModel result = execute(new HttpPost(uri), StatusMessagesModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "result: %s", JsonUtils.toJson(result));
			return result;
		} catch (Throwable t) {
			throw handleException(t);
		}
	}

	public HidModel createUser(UserModel model) {
		String method = "createUser";
		try {
			URI uri = buildUri(USERS_ROOT_URL);
			HidModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), HidModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "result: %s", JsonUtils.toJson(result));
			return result;
		} catch (Throwable t) {
			throw handleException(t);
		}
	}

	public HidModel updateUser(String hid, UserModel model) {
		String method = "updateUser";
		try {
			URI uri = buildUri(USERS_ROOT_URL + "/" + hid);
			HidModel result = execute(new HttpPut(uri), JsonUtils.toJson(model), HidModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "result: %s", JsonUtils.toJson(result));
			return result;
		} catch (Throwable t) {
			throw handleException(t);
		}
	}

	synchronized TypeReference<PagingResultModel<UserModel>> getUserModelTypeRef() {
		return userModelTypeRef != null ? userModelTypeRef
				: (userModelTypeRef = new TypeReference<PagingResultModel<UserModel>>() {
				});
	}
}
