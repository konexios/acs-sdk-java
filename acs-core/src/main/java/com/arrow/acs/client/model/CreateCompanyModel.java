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
