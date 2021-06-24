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

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ZoneModel extends DefinitionModelAbstract<ZoneModel> {
	private static final long serialVersionUID = -8201172265880650584L;

	private String systemName;
	private String regionHid;
	@JsonIgnore
	private RegionModel refRegion;

	@Override
	protected ZoneModel self() {
		return this;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public ZoneModel withSystemName(String systemName) {
		setSystemName(systemName);
		return this;
	}

	public String getRegionHid() {
		return regionHid;
	}

	public void setRegionHid(String regionHid) {
		this.regionHid = regionHid;
	}

	public ZoneModel withRegionHid(String regionHid) {
		setRegionHid(regionHid);
		return this;
	}

	public void setRefRegion(RegionModel refRegion) {
		this.refRegion = refRegion;
	}

	public RegionModel getRefRegion() {
		return refRegion;
	}
}
