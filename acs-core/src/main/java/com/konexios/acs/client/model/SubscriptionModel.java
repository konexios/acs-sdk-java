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

public class SubscriptionModel extends DefinitionModelAbstract<SubscriptionModel> {
	private static final long serialVersionUID = -2611087253790754890L;

	private String companyHid;
	private String startDate;
	private String endDate;
	private ContactModel contact;
	private ContactModel billingContact;
	@JsonIgnore
	private CompanyModel refCompany;

	@Override
	protected SubscriptionModel self() {
		return this;
	}

	public SubscriptionModel withCompanyHid(String companyHid) {
		setCompanyHid(companyHid);
		return this;
	}

	public SubscriptionModel withStartDate(String startDate) {
		setStartDate(startDate);
		return this;
	}

	public SubscriptionModel withEndDate(String endDate) {
		setEndDate(endDate);
		return this;
	}

	public SubscriptionModel withContact(ContactModel contact) {
		setContact(contact);
		return this;
	}

	public SubscriptionModel withBillingContact(ContactModel contact) {
		setBillingContact(contact);
		return this;
	}

	public CompanyModel getRefCompany() {
		return refCompany;
	}

	public void setRefCompany(CompanyModel refCompany) {
		this.refCompany = refCompany;
	}

	public String getCompanyHid() {
		return companyHid;
	}

	public void setCompanyHid(String companyHid) {
		this.companyHid = companyHid;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
}
