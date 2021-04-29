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

import com.fasterxml.jackson.core.type.TypeReference;
import com.konexios.acs.JsonUtils;
import com.konexios.acs.client.api.ApiConfig;
import com.konexios.acs.client.model.ListResultModel;
import com.konexios.acs.client.model.ProductModel;

public final class ProductApi extends AcsApiAbstract {
	private static final String PRODUCTS_ROOT_URL = WEB_SERVICE_ROOT_URL + "/products";

	// instantiation is expensive for these objects
	private TypeReference<ListResultModel<ProductModel>> productModelTypeRef;

	ProductApi(ApiConfig apiConfig) {
		super(apiConfig);
	}

	public ProductModel findByHid(String hid) {
		String method = "findByHid";
		try {
			URI uri = buildUri(String.format("%s/%s", PRODUCTS_ROOT_URL, hid));
			ProductModel result = execute(new HttpGet(uri), ProductModel.class);
			if (result != null && isDebugEnabled())
				logDebug(method, "result: %s", JsonUtils.toJson(result));
			return result;
		} catch (Throwable t) {
			throw handleException(t);
		}
	}

	public ListResultModel<ProductModel> findAll() {
		String method = "findAll";
		try {
			URI uri = buildUri(PRODUCTS_ROOT_URL);
			ListResultModel<ProductModel> result = execute(new HttpGet(uri), getProductModelTypeRef());
			log(method, result);
			return result;
		} catch (Throwable t) {
			throw handleException(t);
		}
	}

	synchronized TypeReference<ListResultModel<ProductModel>> getProductModelTypeRef() {
		return productModelTypeRef != null ? productModelTypeRef
				: (productModelTypeRef = new TypeReference<ListResultModel<ProductModel>>() {
				});
	}
}
