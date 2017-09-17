/*******************************************************************************
 * Copyright (c) 2017 Arrow Electronics, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License 2.0
 * which accompanies this distribution, and is available at
 * http://apache.org/licenses/LICENSE-2.0
 *
 * Contributors:
 *     Arrow Electronics, Inc.
 *******************************************************************************/
package com.arrow.acs.client.model;

public class AuthModel extends AuditableDocumentModelAbstract<AuthModel> {
	private static final long serialVersionUID = -3916291794363534624L;

	private AuthLdapModel ldap;
	private AuthSamlModel saml;
	private String companyHid;
	private AuthType type;
	private boolean enabled;

	@Override
	protected AuthModel self() {
		return this;
	}

	public String getCompanyHid() {
		return companyHid;
	}

	public void setCompanyHid(String companyHid) {
		this.companyHid = companyHid;
	}

	public AuthLdapModel getLdap() {
		return ldap;
	}

	public void setLdap(AuthLdapModel ldap) {
		this.ldap = ldap;
	}

	public AuthSamlModel getSaml() {
		return saml;
	}

	public void setSaml(AuthSamlModel saml) {
		this.saml = saml;
	}

	public AuthType getType() {
		return type;
	}

	public void setType(AuthType type) {
		this.type = type;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
