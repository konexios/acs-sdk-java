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

public class ApplicationExtensionModel extends AuditableDocumentModelAbstract<ApplicationExtensionModel> {
	private static final long serialVersionUID = 4656876691147842639L;

	private String applicationHid;
	private String productExtensionHid;
	private boolean enabled = true;

	@Override
	protected ApplicationExtensionModel self() {
		return this;
	}

	public ApplicationExtensionModel withApplicationHid(String applicationHid) {
		setApplicationHid(applicationHid);
		return this;
	}

	public ApplicationExtensionModel withProductionExtensionHid(String productExtensionHid) {
		setProductExtensionHid(productExtensionHid);
		return this;
	}

	public ApplicationExtensionModel withEnabled(boolean enabled) {
		setEnabled(enabled);
		return this;
	}

	public String getApplicationHid() {
		return applicationHid;
	}

	public void setApplicationHid(String applicationHid) {
		this.applicationHid = applicationHid;
	}

	public String getProductExtensionHid() {
		return productExtensionHid;
	}

	public void setProductExtensionHid(String productExtensionHid) {
		this.productExtensionHid = productExtensionHid;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
