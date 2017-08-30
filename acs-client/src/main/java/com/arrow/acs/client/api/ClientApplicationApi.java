package com.arrow.acs.client.api;

import java.net.URI;

import org.apache.http.client.methods.HttpGet;

import com.arrow.acs.client.model.AccessKeyModel;
import com.arrow.acs.client.model.ListResultModel;
import com.fasterxml.jackson.core.type.TypeReference;

public class ClientApplicationApi extends ClientApiAbstract {
	private static final String APPLICATIONS_ROOT_URL = WEB_SERVICE_ROOT_URL + "/applications";

	ClientApplicationApi(ApiConfig apiConfig) {
		super(apiConfig);
	}

	public ListResultModel<AccessKeyModel> findAccessKeyByApplicationHid(String hid) {
		String method = "findAccessKeyByApplicationHid";
		try {
			URI uri = buildUri(APPLICATIONS_ROOT_URL + "/" + hid + "/access-keys");
			ListResultModel<AccessKeyModel> result = execute(new HttpGet(uri),
			        new TypeReference<ListResultModel<AccessKeyModel>>() {
			        });
			if (result != null && isDebugEnabled())
				logDebug(method, "access keys found: %s", result.getSize());
			return result;
		} catch (Throwable e) {
			throw handleException(e);
		}
	}
}
