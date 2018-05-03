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
package com.arrow.acs.client.model;

import java.io.Serializable;

public class CreateCompanyModel implements Serializable {

	private static final long serialVersionUID = 737309244917140959L;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CreateCompanyModel withName(String name) {
		setName(name);
		return this;
	}
	
	public String getAbbrName() {
		return abbrName;
	}

	public void setAbbrName(String abbrName) {
		this.abbrName = abbrName;
	}

	public CreateCompanyModel withAbbrName(String abbrName) {
		setAbbrName(abbrName);
		return this;
	}
	
	public CompanyStatus getStatus() {
		return status;
	}

	public void setStatus(CompanyStatus status) {
		this.status = status;
	}
	
	public CreateCompanyModel withStatus(CompanyStatus status) {
		setStatus(status);
		return this;
	}

	public AddressModel getAddress() {
		return address;
	}

	public void setAddress(AddressModel address) {
		this.address = address;
	}
	
	public CreateCompanyModel withAddress(AddressModel address) {
		setAddress(address);
		return this;
	}


	public AddressModel getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(AddressModel billingAddress) {
		this.billingAddress = billingAddress;
	}

	public CreateCompanyModel withBillingAddress(AddressModel billingAddress) {
		setBillingAddress(billingAddress);
		return this;
	}
	
	public ContactModel getContact() {
		return contact;
	}

	public void setContact(ContactModel contact) {
		this.contact = contact;
	}

	public CreateCompanyModel withContact(ContactModel contact) {
		setContact(contact);
		return this;
	}
	
	public ContactModel getBillingContact() {
		return billingContact;
	}

	public void setBillingContact(ContactModel billingContact) {
		this.billingContact = billingContact;
	}

	public CreateCompanyModel withBillingContact(ContactModel billingContact) {
		setBillingContact(billingContact);
		return this;
	}
	
	public PasswordPolicyModel getPasswordPolicy() {
		return passwordPolicy;
	}

	public void setPasswordPolicy(PasswordPolicyModel passwordPolicy) {
		this.passwordPolicy = passwordPolicy;
	}
	
	public CreateCompanyModel withPasswordPolicy(PasswordPolicyModel passwordPolicy) {
		setPasswordPolicy(passwordPolicy);
		return this;
	}

	public LoginPolicyModel getLoginPolicy() {
		return loginPolicy;
	}

	public void setLoginPolicy(LoginPolicyModel loginPolicy) {
		this.loginPolicy = loginPolicy;
	}

	public CreateCompanyModel withLoginPolicy(LoginPolicyModel loginPolicy) {
		setLoginPolicy(loginPolicy);
		return this;
	}
	
	public String getParentCompanyHid() {
		return parentCompanyHid;
	}

	public void setParentCompanyHid(String parentCompanyHid) {
		this.parentCompanyHid = parentCompanyHid;
	}
	
	public CreateCompanyModel withParentCompanyHid(String parentCompanyHid) {
		setParentCompanyHid(parentCompanyHid);
		return this;
	}
}
