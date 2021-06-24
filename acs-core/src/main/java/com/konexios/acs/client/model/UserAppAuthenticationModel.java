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

public class UserAppAuthenticationModel extends UserAuthenticationModel {

	private static final long serialVersionUID = -5006638956859437797L;

	private String applicationCode;

	public UserAppAuthenticationModel withApplicationCode(String applicationCode) {
		setApplicationCode(applicationCode);
		return this;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((applicationCode == null) ? 0 : applicationCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAppAuthenticationModel other = (UserAppAuthenticationModel) obj;
		if (applicationCode == null) {
			if (other.applicationCode != null)
				return false;
		} else if (!applicationCode.equals(other.applicationCode))
			return false;
		return true;
	}
}
