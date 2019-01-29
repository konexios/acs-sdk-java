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

import java.net.URI;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;

import com.arrow.acs.JsonUtils;
import com.arrow.acs.client.model.UserAuthenticationModel;
import com.arrow.acs.client.model.UserModel;

import olympus.client.api.ApiConfig;

public final class ClientUserApi extends ClientApiAbstract {
	private static final String USERS_ROOT_URL = WEB_SERVICE_ROOT_URL + "/users";

	ClientUserApi(ApiConfig apiConfig) {
		super(apiConfig);
	}

	public UserModel authenticate(String username, String password) {
		String method = "authenticate";
		try {
			URI uri = buildUri(USERS_ROOT_URL + "/auth");
			UserAuthenticationModel model = new UserAuthenticationModel().withUsername(username).withPassword(password);
			UserModel result = execute(new HttpPost(uri), JsonUtils.toJson(model), UserModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "hid: %s, login: %s", result.getHid(), result.getLogin());
			return result;
		} catch (Throwable t) {
			throw handleException(t);
		}
	}

	public UserModel findByHid(String hid) {
		String method = "authenticate";
		try {
			URI uri = buildUri(USERS_ROOT_URL + "/" + hid);
			UserModel result = execute(new HttpGet(uri), UserModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "hid: %s, login: %s", result.getHid(), result.getLogin());
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
				logDebug(method, "hid: %s, login: %s", result.getHid(), result.getLogin());
			return result;
		} catch (Throwable t) {
			throw handleException(t);
		}
	}
}
