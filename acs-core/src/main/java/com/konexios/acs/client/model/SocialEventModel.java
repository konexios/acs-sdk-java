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

public class SocialEventModel extends AuditableDocumentModelAbstract<SocialEventModel> {

	private static final long serialVersionUID = 6192819880679221475L;

	private String name;
	private String startDate;
	private String endDate;
	private String zoneHid;
	private String latitude;
	private String longitude;
	private Integer zoomLevel;
	private String zoneSystemName;

	@Override
	protected SocialEventModel self() {
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getZoneHid() {
		return zoneHid;
	}

	public void setZoneHid(String zoneHid) {
		this.zoneHid = zoneHid;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Integer getZoomLevel() {
		return zoomLevel;
	}

	public void setZoomLevel(Integer zoomLevel) {
		this.zoomLevel = zoomLevel;
	}

	public String getZoneSystemName() {
		return zoneSystemName;
	}

	public void setZoneSystemName(String zoneSystemName) {
		this.zoneSystemName = zoneSystemName;
	}

}
