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

public class AccessPrivilegeModel implements Serializable {
	private static final long serialVersionUID = 8938016265302233038L;
	
	private String pri;
    private AccessLevel level;

	public String getPri() {
		return pri;
	}

	public void setPri(String pri) {
		this.pri = pri;
	}
	
	public AccessPrivilegeModel withPri(String pri){
		setPri(pri);
		return this;
	}

	public AccessLevel getLevel() {
		return level;
	}

	public void setLevel(AccessLevel level) {
		this.level = level;
	}
	
	public AccessPrivilegeModel withLevel(AccessLevel level){
		setLevel(level);
		return this;
	}
}
