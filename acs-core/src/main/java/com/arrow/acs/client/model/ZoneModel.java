/*******************************************************************************
 * Copyright (c) 2017 Arrow Electronics, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License 2.0
 * which accompanies this distribution, and is available at
 * http://apache.org/licenses/LICENSE-2.0
 *
 * Contributors:
 *     Arrow Electronics, Inc.
 *******************************************************************************/
package com.arrow.acs.client.model;

public class ZoneModel extends DefinitionModelAbstract<ZoneModel> {
	private static final long serialVersionUID = -180946967438207823L;

	private String systemName;
	private String regionHid;

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

	@Override
	protected ZoneModel self() {
		return this;
	}
}