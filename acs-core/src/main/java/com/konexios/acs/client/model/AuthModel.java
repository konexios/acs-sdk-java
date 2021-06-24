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

	public AuthModel withLdap(AuthLdapModel ldap) {
		setLdap(ldap);
		return this;
	}

	public AuthModel withSaml(AuthSamlModel saml) {
		setSaml(saml);
		return this;
	}

	public AuthModel withCompanyHid(String companyHid) {
		setCompanyHid(companyHid);
		return this;
	}

	public AuthModel withType(AuthType type) {
		setType(type);
		return this;
	}

	public AuthModel withEnabled(boolean enabled) {
		setEnabled(enabled);
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
