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
import java.util.ArrayList;
import java.util.List;

public class CreateApplicationModel implements Serializable {

	private static final long serialVersionUID = 1096427303857446507L;

	private String zoneHid;
	private String companyHid;
	private String productHid;
	private String subscriptionHid;
	private YesNoInherit apiSigningRequired;
	private String applicationEngineHid;
	private String defaultSamlEntityId;
	private String code;
	private List<ConfigurationPropertyModel> configurations = new ArrayList<>();
	private List<String> productExtensionHids = new ArrayList<>();
	private String description;
	private String name;
	private boolean enabled;

	public String getZoneHid() {
		return zoneHid;
	}

	public void setZoneHid(String zoneHid) {
		this.zoneHid = zoneHid;
	}
	
	public CreateApplicationModel withZoneHid(String zoneHid) {
		setZoneHid(zoneHid);
		return this;
	}

	public String getCompanyHid() {
		return companyHid;
	}

	public void setCompanyHid(String companyHid) {
		this.companyHid = companyHid;
	}

	public CreateApplicationModel withCompanyHid(String companyHid) {
		setCompanyHid(companyHid);
		return this;
	}
	
	public String getSubscriptionHid() {
		return subscriptionHid;
	}

	public void setSubscriptionHid(String subscriptionHid) {
		this.subscriptionHid = subscriptionHid;
	}
	
	public CreateApplicationModel withSubscriptionHid(String subscriptionHid) {
		setSubscriptionHid(subscriptionHid);
		return this;
	}

	public String getProductHid() {
		return productHid;
	}

	public void setProductHid(String productHid) {
		this.productHid = productHid;
	}

	public CreateApplicationModel withProductHid(String productHid) {
		setProductHid(productHid);
		return this;
	}
	
	public YesNoInherit getApiSigningRequired() {
		return apiSigningRequired;
	}

	public void setApiSigningRequired(YesNoInherit apiSigningRequired) {
		this.apiSigningRequired = apiSigningRequired;
	}
	
	public CreateApplicationModel withApiSigningRequired(YesNoInherit apiSigningRequired) {
		setApiSigningRequired(apiSigningRequired);
		return this;
	}

	public String getApplicationEngineHid() {
		return applicationEngineHid;
	}

	public void setApplicationEngineHid(String applicationEngineHid) {
		this.applicationEngineHid = applicationEngineHid;
	}
	
	public CreateApplicationModel withApplicationEngineHid(String applicationEngineHid) {
		setApplicationEngineHid(applicationEngineHid);
		return this;
	}

	public String getDefaultSamlEntityId() {
		return defaultSamlEntityId;
	}

	public void setDefaultSamlEntityId(String defaultSamlEntityId) {
		this.defaultSamlEntityId = defaultSamlEntityId;
	}
	
	public CreateApplicationModel withDefaultSamlEntityId(String defaultSamlEntityId) {
		setDefaultSamlEntityId(defaultSamlEntityId);
		return this;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public CreateApplicationModel withCode(String code) {
		setCode(code);
		return this;
	}

	public List<ConfigurationPropertyModel> getConfigurations() {
		return configurations;
	}

	public void setConfigurations(List<ConfigurationPropertyModel> configurations) {
		this.configurations = configurations;
	}
	
	public CreateApplicationModel withConfigurations(List<ConfigurationPropertyModel> configurations) {
		setConfigurations(configurations);
		return this;
	}

	public List<String> getProductExtensionHids() {
		return productExtensionHids;
	}

	public void setProductExtensionHids(List<String> productExtensionHids) {
		this.productExtensionHids = productExtensionHids;
	}

	public CreateApplicationModel withProductExtensionHids(List<String> productExtensionHids) {
		setProductExtensionHids(productExtensionHids);
		return this;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CreateApplicationModel withDescription(String description) {
		setDescription(description);
		return this;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CreateApplicationModel withName(String name) {
		setName(name);
		return this;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public CreateApplicationModel withEnabled(boolean enabled) {
		setEnabled(enabled);
		return this;
	}
}
