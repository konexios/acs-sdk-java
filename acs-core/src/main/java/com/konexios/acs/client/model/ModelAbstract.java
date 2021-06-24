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
import java.util.HashMap;
import java.util.Map;

public abstract class ModelAbstract<T extends ModelAbstract<T>> implements Serializable {
	private static final long serialVersionUID = 5491748225878069743L;

	public final static String SELF_LINK = "self";

	private String hid;
	private String pri;
	private Map<String, String> links = new HashMap<>();

	protected abstract T self();

	public T withHid(String hid) {
		setHid(hid);
		return self();
	}

	public T withPri(String pri) {
		setPri(pri);
		return self();
	}

	public T withLinks(Map<String, String> links) {
		setLinks(links);
		return self();
	}

	public String getPri() {
		return pri;
	}

	public void setPri(String pri) {
		this.pri = pri;
	}

	public String getHid() {
		return hid;
	}

	public void setHid(String hid) {
		this.hid = hid;
	}

	public Map<String, String> getLinks() {
		return links;
	}

	public void setLinks(Map<String, String> links) {
		this.links = links;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hid == null) ? 0 : hid.hashCode());
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
		ModelAbstract<?> other = (ModelAbstract<?>) obj;
		if (hid == null) {
			if (other.hid != null)
				return false;
		} else if (!hid.equals(other.hid))
			return false;
		return true;
	}
}
