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
package com.konexios.acs.client.search;

public class PrivilegeSearchCriteria extends SearchCriteria {
	private static final String NAME = "name";
	private static final String SYSTEM_NAME = "systemName";
	private static final String PRODUCT_HID = "productHid";
	private static final String ENABLED = "enabled";

	public PrivilegeSearchCriteria withName(String name) {
		simpleCriteria.put(NAME, name);
		return this;
	}

	public PrivilegeSearchCriteria withSystemName(String systemName) {
		simpleCriteria.put(SYSTEM_NAME, systemName);
		return this;
	}

	public PrivilegeSearchCriteria withProductHid(String productHid) {
		simpleCriteria.put(PRODUCT_HID, productHid);
		return this;
	}

	public PrivilegeSearchCriteria withEnabled(boolean enabled) {
		simpleCriteria.put(ENABLED, Boolean.toString(enabled));
		return this;
	}
}
