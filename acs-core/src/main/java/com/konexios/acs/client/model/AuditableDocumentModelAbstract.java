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

public abstract class AuditableDocumentModelAbstract<T extends AuditableDocumentModelAbstract<T>>
        extends TsModelAbstract<T> {
	private static final long serialVersionUID = -1644864075961766068L;

	private String lastModifiedDate;
	private String lastModifiedBy;

	public T withLastModifiedDate(String lastModifiedDate) {
		setLastModifiedDate(lastModifiedDate);
		return self();
	}

	public T withLastModifiedBy(String lastModifiedBy) {
		setLastModifiedBy(lastModifiedBy);
		return self();
	}

	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
}
