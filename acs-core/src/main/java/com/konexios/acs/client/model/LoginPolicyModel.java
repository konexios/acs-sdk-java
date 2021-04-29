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

public class LoginPolicyModel implements Serializable {
	private static final long serialVersionUID = 5249540968674733320L;

	private int maxFailedLogins;
	private int lockTimeoutSecs;

	public LoginPolicyModel withMaxFailedLogins(int maxFailedLogins) {
		setMaxFailedLogins(maxFailedLogins);
		return this;
	}

	public LoginPolicyModel withLockTimeoutSecs(int lockTimeoutSecs) {
		setLockTimeoutSecs(lockTimeoutSecs);
		return this;
	}

	public int getMaxFailedLogins() {
		return maxFailedLogins;
	}

	public void setMaxFailedLogins(int maxFailedLogins) {
		this.maxFailedLogins = maxFailedLogins;
	}

	public int getLockTimeoutSecs() {
		return lockTimeoutSecs;
	}

	public void setLockTimeoutSecs(int lockTimeoutSecs) {
		this.lockTimeoutSecs = lockTimeoutSecs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + lockTimeoutSecs;
		result = prime * result + maxFailedLogins;
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
		LoginPolicyModel other = (LoginPolicyModel) obj;
		if (lockTimeoutSecs != other.lockTimeoutSecs)
			return false;
		if (maxFailedLogins != other.maxFailedLogins)
			return false;
		return true;
	}
}
