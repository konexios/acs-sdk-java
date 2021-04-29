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
package com.konexios.acs.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProductModel extends DefinitionModelAbstract<ProductModel> {
	private static final long serialVersionUID = 1078834605777348220L;

	private String systemName;
	private boolean apiSigningRequired;
	private String parentProductHid;

	@JsonIgnore
	private ProductModel refParentProduct;

	@Override
	protected ProductModel self() {
		return this;
	}

	public ProductModel withSystemName(String systemName) {
		setSystemName(systemName);
		return this;
	}

	public ProductModel withApiSigningRequired(boolean apiSigningRequired) {
		setApiSigningRequired(apiSigningRequired);
		return this;
	}

	public ProductModel withParentProductHid(String parentProductHid) {
		setParentProductHid(parentProductHid);
		return this;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public boolean isApiSigningRequired() {
		return apiSigningRequired;
	}

	public void setApiSigningRequired(boolean apiSigningRequired) {
		this.apiSigningRequired = apiSigningRequired;
	}

	public String getParentProductHid() {
		return parentProductHid;
	}

	public void setParentProductHid(String parentProductHid) {
		this.parentProductHid = parentProductHid;
	}

	public void setRefParentProduct(ProductModel refParentProduct) {
		this.refParentProduct = refParentProduct;
	}

	public ProductModel getRefParentProduct() {
		return refParentProduct;
	}
}
