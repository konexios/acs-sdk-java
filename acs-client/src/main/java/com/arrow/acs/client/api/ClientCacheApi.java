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

import java.net.URI;

import org.apache.http.client.methods.HttpGet;

import com.arrow.acs.client.model.ApplicationModel;
import com.arrow.acs.client.model.CompanyModel;
import com.arrow.acs.client.model.PrivilegeModel;
import com.arrow.acs.client.model.ProductModel;
import com.arrow.acs.client.model.RoleModel;
import com.arrow.acs.client.model.SubscriptionModel;
import com.arrow.acs.client.model.UserModel;

public final class ClientCacheApi extends ClientApiAbstract {
	private static final String CACHE_ROOT_URL = WEB_SERVICE_ROOT_URL + "/cache";

	ClientCacheApi(ApiConfig apiConfig) {
		super(apiConfig);
	}

	public UserModel findUserByHid(String hid) {
		String method = "findUserByHid";
		try {
			URI uri = buildUri(CACHE_ROOT_URL + "/users/hids/" + hid);
			UserModel result = execute(new HttpGet(uri), UserModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "hid: %s", result.getHid());
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public ApplicationModel findApplicationByHid(String hid) {
		String method = "findApplicationByHid";
		try {
			URI uri = buildUri(CACHE_ROOT_URL + "/applications/hids/" + hid);
			ApplicationModel result = execute(new HttpGet(uri), ApplicationModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "hid: %s, name: %s", result.getHid(), result.getName());
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public ApplicationModel findApplicationByName(String name) {
		String method = "findApplicationByName";
		try {
			URI uri = buildUri(CACHE_ROOT_URL + "/applications/names/" + name);
			ApplicationModel result = execute(new HttpGet(uri), ApplicationModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "hid: %s, name: %s", result.getHid(), result.getName());
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public CompanyModel findCompanyByHid(String hid) {
		String method = "findCompanyByHid";
		try {
			URI uri = buildUri(CACHE_ROOT_URL + "/companies/hids/" + hid);
			CompanyModel result = execute(new HttpGet(uri), CompanyModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "hid: %s, name: %s", result.getHid(), result.getName());
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public ProductModel findProductByHid(String hid) {
		String method = "findProductByHid";
		try {
			URI uri = buildUri(CACHE_ROOT_URL + "/products/hids/" + hid);
			ProductModel result = execute(new HttpGet(uri), ProductModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "hid: %s, name: %s", result.getHid(), result.getName());
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public ProductModel findProductBySystemName(String systemName) {
		String method = "findProductBySystemName";
		try {
			URI uri = buildUri(CACHE_ROOT_URL + "/products/system-names/" + systemName);
			ProductModel result = execute(new HttpGet(uri), ProductModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "hid: %s, name: %s", result.getHid(), result.getName());
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public SubscriptionModel findSubscriptionByHid(String hid) {
		String method = "findSubscriptionByHid";
		try {
			URI uri = buildUri(CACHE_ROOT_URL + "/subscriptions/hids/" + hid);
			SubscriptionModel result = execute(new HttpGet(uri), SubscriptionModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "hid: %s, name: %s", result.getHid(), result.getName());
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public RoleModel findRoleByHid(String hid) {
		String method = "findRoleByHid";
		try {
			URI uri = buildUri(CACHE_ROOT_URL + "/roles/hids/" + hid);
			RoleModel result = execute(new HttpGet(uri), RoleModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "hid: %s, name: %s", result.getHid(), result.getName());
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}

	public PrivilegeModel findPrivilegeByHid(String hid) {
		String method = "findPrivilegeByHid";
		try {
			URI uri = buildUri(CACHE_ROOT_URL + "/privileges/hids/" + hid);
			PrivilegeModel result = execute(new HttpGet(uri), PrivilegeModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "hid: %s, name: %s", result.getHid(), result.getName());
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}
}
