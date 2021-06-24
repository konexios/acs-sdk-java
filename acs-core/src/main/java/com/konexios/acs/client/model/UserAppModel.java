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

public class UserAppModel extends UserModel {

	private static final long serialVersionUID = 2516879981186432405L;

	private String applicationHid;
	private String applicationName;
	private String userHid;
	private String zoneSystemName;
	private String zoneHid;
	private String zoneName;
	private String regionHid;
	private String regionName;

	public UserAppModel withApplicationHid(String applicationHid) {
		setApplicationHid(applicationHid);
		return this;
	}

	public UserAppModel withApplicationName(String applicationName) {
		setApplicationName(applicationName);
		return this;
	}

	public UserAppModel withUserHid(String userHid) {
		setUserHid(userHid);
		return this;
	}

	public UserAppModel withZoneSystemName(String zoneSystemName) {
		setZoneSystemName(zoneSystemName);
		return this;
	}

	public UserAppModel withZoneHid(String zoneHid) {
		setZoneHid(zoneHid);
		return this;
	}

	public UserAppModel withZoneName(String zoneName) {
		setZoneName(zoneName);
		return this;
	}

	public UserAppModel withRegionHid(String regionHid) {
		setRegionHid(regionHid);
		return this;
	}

	public UserAppModel withRegionName(String regionName) {
		setRegionName(regionName);
		return this;
	}

	public String getApplicationHid() {
		return applicationHid;
	}

	public void setApplicationHid(String applicationHid) {
		this.applicationHid = applicationHid;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getUserHid() {
		return userHid;
	}

	public void setUserHid(String userHid) {
		this.userHid = userHid;
	}

	public String getZoneSystemName() {
		return zoneSystemName;
	}

	public void setZoneSystemName(String zoneSystemName) {
		this.zoneSystemName = zoneSystemName;
	}

	public String getZoneHid() {
		return zoneHid;
	}

	public void setZoneHid(String zoneHid) {
		this.zoneHid = zoneHid;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public String getRegionHid() {
		return regionHid;
	}

	public void setRegionHid(String regionHid) {
		this.regionHid = regionHid;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
}
