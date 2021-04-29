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

public class AuthSamlModel implements Serializable {
	private static final long serialVersionUID = -6186730033865463827L;

	private String idp;
	private String firstNameAttr;
	private String lastNameAttr;
	private String emailAttr;

	public AuthSamlModel withIdp(String idp) {
		setIdp(idp);
		return this;
	}

	public AuthSamlModel withFirstNameAttr(String firstNameAttr) {
		setFirstNameAttr(firstNameAttr);
		return this;
	}

	public AuthSamlModel withLastNameAttr(String lastNameAttr) {
		setLastNameAttr(lastNameAttr);
		return this;
	}

	public AuthSamlModel withEmailAttr(String emailAttr) {
		setEmailAttr(emailAttr);
		return this;
	}

	public String getIdp() {
		return idp;
	}

	public void setIdp(String idp) {
		this.idp = idp;
	}

	public String getFirstNameAttr() {
		return firstNameAttr;
	}

	public void setFirstNameAttr(String firstNameAttr) {
		this.firstNameAttr = firstNameAttr;
	}

	public String getLastNameAttr() {
		return lastNameAttr;
	}

	public void setLastNameAttr(String lastNameAttr) {
		this.lastNameAttr = lastNameAttr;
	}

	public String getEmailAttr() {
		return emailAttr;
	}

	public void setEmailAttr(String emailAttr) {
		this.emailAttr = emailAttr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idp == null) ? 0 : idp.hashCode());
		result = prime * result + ((firstNameAttr == null) ? 0 : firstNameAttr.hashCode());
		result = prime * result + ((lastNameAttr == null) ? 0 : lastNameAttr.hashCode());
		result = prime * result + ((emailAttr == null) ? 0 : emailAttr.hashCode());
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
		AuthSamlModel other = (AuthSamlModel) obj;
		if (idp == null) {
			if (other.idp != null)
				return false;
		} else if (!idp.equals(other.idp))
			return false;
		if (firstNameAttr == null) {
			if (other.firstNameAttr != null)
				return false;
		} else if (!firstNameAttr.equals(other.firstNameAttr))
			return false;
		if (lastNameAttr == null) {
			if (other.lastNameAttr != null)
				return false;
		} else if (!lastNameAttr.equals(other.lastNameAttr))
			return false;
		if (emailAttr == null) {
			if (other.emailAttr != null)
				return false;
		} else if (!emailAttr.equals(other.emailAttr))
			return false;
		return true;
	}
}
