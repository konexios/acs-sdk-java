/*******************************************************************************
 * Copyright (c) 2018 Arrow Electronics, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License 2.0
 * which accompanies this distribution, and is available at
 * http://apache.org/licenses/LICENSE-2.0
 *
 * Contributors:
 *     Arrow Electronics, Inc.
 *******************************************************************************/
package com.arrow.acs.client.model;

import java.io.Serializable;

public class UserAppModel implements Serializable {

	private static final long serialVersionUID = 2516879981186432405L;

	private String applicationHid;
	private String userHid;
	private String companyHid;
	private String zoneSystemName;

	public String getApplicationHid() {
		return applicationHid;
	}

	public void setApplicationHid(String applicationHid) {
		this.applicationHid = applicationHid;
	}

	public String getUserHid() {
		return userHid;
	}

	public void setUserHid(String userHid) {
		this.userHid = userHid;
	}

	public String getCompanyHid() {
		return companyHid;
	}

	public void setCompanyHid(String companyHid) {
		this.companyHid = companyHid;
	}

	public String getZoneSystemName() {
		return zoneSystemName;
	}

	public void setZoneSystemName(String zoneSystemName) {
		this.zoneSystemName = zoneSystemName;
	}
}
