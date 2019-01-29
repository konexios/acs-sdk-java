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

import olympus.client.model.ModelAbstract;

public abstract class TsModelAbstract<T extends TsModelAbstract<T>> extends ModelAbstract<T> {
	private static final long serialVersionUID = -6573132535292516518L;

	private String createdDate;
	private String createdBy;

	public T withCreatedDate(String createdDate) {
		setCreatedDate(createdDate);
		return self();
	}

	public T withCreatedBy(String createdBy) {
		setCreatedBy(createdBy);
		return self();
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
}
