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
package com.arrow.acs.client.search;

import com.konexios.acs.client.search.PageSearchCriteria;
import com.konexios.acs.client.search.SearchCriteria;

public class ApplicationSearchCriteria extends SearchCriteria {
	private static final String COMPANY_HID = "companyHid";
	private static final String PRODUCT_HID = "productHid";
	private static final String SUBSCRIPTION_HID = "subscriptionHid";
	private static final String CODE = "code";
	private static final String INCLUDE_DISABLED = "includeDisabled";
	private PageSearchCriteria pageSearchCriteria = new PageSearchCriteria();

	public ApplicationSearchCriteria withCompanyHid(String companyHid) {
		simpleCriteria.put(COMPANY_HID, companyHid);
		return this;
	}

	public ApplicationSearchCriteria withProductHid(String productHid) {
		simpleCriteria.put(PRODUCT_HID, productHid);
		return this;
	}

	public ApplicationSearchCriteria withSubscriptionHid(String subscriptionHid) {
		simpleCriteria.put(SUBSCRIPTION_HID, subscriptionHid);
		return this;
	}

	public ApplicationSearchCriteria withCode(String code) {
		simpleCriteria.put(CODE, code);
		return this;
	}

	public ApplicationSearchCriteria withIncludeDisabled(boolean includeDisabled) {
		simpleCriteria.put(INCLUDE_DISABLED, Boolean.toString(includeDisabled));
		return this;
	}

	public ApplicationSearchCriteria withPage(long page) {
		pageSearchCriteria.withPage(page);
		simpleCriteria.putAll(pageSearchCriteria.getSimpleCriteria());
		return this;
	}

	public ApplicationSearchCriteria withSize(long size) {
		pageSearchCriteria.withSize(size);
		simpleCriteria.putAll(pageSearchCriteria.getSimpleCriteria());
		return this;
	}
}
