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

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RoleModel extends DefinitionModelAbstract<RoleModel> {
	private static final long serialVersionUID = -5378066840269175868L;

	private String productHid;
	private String applicationHid;
	private boolean editable;
	private List<String> privilegeHids = new ArrayList<>();
	@JsonIgnore
	private ProductModel refProduct;
	@JsonIgnore
	private ApplicationModel refApplication;
	@JsonIgnore
	private List<PrivilegeModel> refPrivileges = new ArrayList<>();

	@Override
	protected RoleModel self() {
		return this;
	}

	public List<PrivilegeModel> getRefPrivileges() {
		return refPrivileges;
	}

	public void setRefProduct(ProductModel refProduct) {
		this.refProduct = refProduct;
	}

	public ProductModel getRefProduct() {
		return refProduct;
	}

	public void setRefApplication(ApplicationModel refApplication) {
		this.refApplication = refApplication;
	}

	public ApplicationModel getRefApplication() {
		return refApplication;
	}

	public void setRefPrivileges(List<PrivilegeModel> refPrivileges) {
		this.refPrivileges = refPrivileges;
	}

	public RoleModel withProductHid(String productHid) {
		setProductHid(productHid);
		return this;
	}

	public RoleModel withApplicationHid(String applicationHid) {
		setApplicationHid(applicationHid);
		return this;
	}

	public RoleModel withEditable(boolean editable) {
		setEditable(editable);
		return this;
	}

	public String getProductHid() {
		return productHid;
	}

	public void setProductHid(String productHid) {
		this.productHid = productHid;
	}

	public String getApplicationHid() {
		return applicationHid;
	}

	public void setApplicationHid(String applicationHid) {
		this.applicationHid = applicationHid;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public List<String> getPrivilegeHids() {
		return privilegeHids;
	}

	public void setPrivilegeHids(List<String> privilegeHids) {
		this.privilegeHids = privilegeHids;
	}
}
