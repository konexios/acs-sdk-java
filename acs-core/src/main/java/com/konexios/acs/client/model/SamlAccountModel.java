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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.konexios.acs.AcsUtils;

public class SamlAccountModel implements Serializable {
	private static final long serialVersionUID = 1789272637985518778L;

	private String principal;
	private String firstName;
	private String lastName;
	private String email;
	private boolean enabled;
	private List<String> defaultRoleHids = new ArrayList<>();
	@JsonIgnore
	private List<RoleModel> refRoles = new ArrayList<>();

	public SamlAccountModel withPrincipal(String principal) {
		setPrincipal(principal);
		return this;
	}

	public SamlAccountModel withFirstName(String firstName) {
		setFirstName(firstName);
		return this;
	}

	public SamlAccountModel withLastName(String lastName) {
		setLastName(lastName);
		return this;
	}

	public SamlAccountModel withEmail(String email) {
		setEmail(email);
		return this;
	}

	public SamlAccountModel withEnabled(boolean enabled) {
		setEnabled(enabled);
		return this;
	}

	public SamlAccountModel withDefaultRoleHids(List<String> defaultRoleHids) {
		setDefaultRoleHids(defaultRoleHids);
		return this;
	}

	public List<RoleModel> getRefRoles() {
		return refRoles;
	}

	public void setRefRoles(List<RoleModel> refRoles) {
		this.refRoles = refRoles;
	}

	public boolean validate() {
		return AcsUtils.isNotEmpty(principal) && (AcsUtils.isNotEmpty(firstName) || AcsUtils.isNotEmpty(lastName));
	}

	public List<String> getDefaultRoleHids() {
		return defaultRoleHids;
	}

	public void setDefaultRoleHids(List<String> defaultRoleHids) {
		this.defaultRoleHids = defaultRoleHids;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((principal == null) ? 0 : principal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SamlAccountModel other = (SamlAccountModel) obj;
		if (principal == null) {
			if (other.principal != null)
				return false;
		} else if (!principal.equals(other.principal))
			return false;
		return true;
	}

	public String toString() {
		return String.format("[%s|%s|%s|%s|%s]", principal, firstName, lastName, email, enabled);
	}
}
