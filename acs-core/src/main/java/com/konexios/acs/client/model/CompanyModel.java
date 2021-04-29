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

public class CompanyModel extends AuditableDocumentModelAbstract<CompanyModel> {
	private static final long serialVersionUID = -1173608869956123803L;

	private String name;
	private String abbrName;
	private CompanyStatus status;
	private AddressModel address;
	private AddressModel billingAddress;
	private ContactModel contact;
	private ContactModel billingContact;
	private PasswordPolicyModel passwordPolicy;
	private LoginPolicyModel loginPolicy;
	private String parentCompanyHid;

	@Override
	protected CompanyModel self() {
		return this;
	}

	public CompanyModel withName(String name) {
		setName(name);
		return this;
	}

	public CompanyModel withAbbrName(String abbrName) {
		setAbbrName(abbrName);
		return this;
	}

	public CompanyModel withStatus(CompanyStatus status) {
		setStatus(status);
		return this;
	}

	public CompanyModel withAddress(AddressModel address) {
		setAddress(address);
		return this;
	}

	public CompanyModel withBillingAddress(AddressModel address) {
		setBillingAddress(address);
		return this;
	}

	public CompanyModel withContact(ContactModel contact) {
		setContact(contact);
		return this;
	}

	public CompanyModel withBillingContact(ContactModel contact) {
		setBillingContact(contact);
		return this;
	}

	public CompanyModel withPasswordPolicy(PasswordPolicyModel passwordPolicy) {
		setPasswordPolicy(passwordPolicy);
		return this;
	}

	public CompanyModel withLoginPolicy(LoginPolicyModel loginPolicy) {
		setLoginPolicy(loginPolicy);
		return this;
	}

	public CompanyModel withParentCompanyHid(String parentCompanyHid) {
		setParentCompanyHid(parentCompanyHid);
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbrName() {
		return abbrName;
	}

	public void setAbbrName(String abbrName) {
		this.abbrName = abbrName;
	}

	public CompanyStatus getStatus() {
		return status;
	}

	public void setStatus(CompanyStatus status) {
		this.status = status;
	}

	public AddressModel getAddress() {
		return address;
	}

	public void setAddress(AddressModel address) {
		this.address = address;
	}

	public AddressModel getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(AddressModel billingAddress) {
		this.billingAddress = billingAddress;
	}

	public ContactModel getContact() {
		return contact;
	}

	public void setContact(ContactModel contact) {
		this.contact = contact;
	}

	public ContactModel getBillingContact() {
		return billingContact;
	}

	public void setBillingContact(ContactModel billingContact) {
		this.billingContact = billingContact;
	}

	public PasswordPolicyModel getPasswordPolicy() {
		return passwordPolicy;
	}

	public void setPasswordPolicy(PasswordPolicyModel passwordPolicy) {
		this.passwordPolicy = passwordPolicy;
	}

	public LoginPolicyModel getLoginPolicy() {
		return loginPolicy;
	}

	public void setLoginPolicy(LoginPolicyModel loginPolicy) {
		this.loginPolicy = loginPolicy;
	}

	public String getParentCompanyHid() {
		return parentCompanyHid;
	}

	public void setParentCompanyHid(String parentCompanyHid) {
		this.parentCompanyHid = parentCompanyHid;
	}
}
