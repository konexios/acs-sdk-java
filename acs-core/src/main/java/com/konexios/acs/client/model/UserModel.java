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

public class UserModel extends AuditableDocumentModelAbstract<UserModel> {
	private static final long serialVersionUID = -3099948569824181477L;

	private String login;
	private UserStatus status;
	private String companyHid;
	private ContactModel contact;
	private AddressModel address;
	private List<String> roleHids = new ArrayList<>();
	@JsonIgnore
	private List<RoleModel> refRoles = new ArrayList<>();

	@Override
	protected UserModel self() {
		return this;
	}

	public UserModel withLogin(String login) {
		setLogin(login);
		return this;
	}

	public UserModel withStatus(UserStatus status) {
		setStatus(status);
		return this;
	}

	public UserModel withCompanyHid(String companyHid) {
		setCompanyHid(companyHid);
		return this;
	}

	public UserModel withContact(ContactModel contact) {
		setContact(contact);
		return this;
	}

	public UserModel withAddress(AddressModel address) {
		setAddress(address);
		return this;
	}

	public UserModel withRoleHids(List<String> roleHids) {
		setRoleHids(roleHids);
		return this;
	}

	public List<RoleModel> getRefRoles() {
		return refRoles;
	}

	public void setRefRoles(List<RoleModel> refRoles) {
		this.refRoles = refRoles;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getCompanyHid() {
		return companyHid;
	}

	public void setCompanyHid(String companyHid) {
		this.companyHid = companyHid;
	}

	public ContactModel getContact() {
		return contact;
	}

	public void setContact(ContactModel contact) {
		this.contact = contact;
	}

	public AddressModel getAddress() {
		return address;
	}

	public void setAddress(AddressModel address) {
		this.address = address;
	}

	public List<String> getRoleHids() {
		return roleHids;
	}

	public void setRoleHids(List<String> roleHids) {
		this.roleHids = roleHids;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}
}
