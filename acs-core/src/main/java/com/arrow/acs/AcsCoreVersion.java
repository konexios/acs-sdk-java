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
package com.arrow.acs;

import com.arrow.acs.client.model.VersionModel;

public final class AcsCoreVersion extends VersionAbstract {
	private final static VersionModel VERSION = readManifest(AcsCoreVersion.class);

	public static VersionModel get() {
		return VERSION;
	}
}
