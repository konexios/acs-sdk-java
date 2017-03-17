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
package com.arrow.acs;

import java.util.jar.Attributes;
import java.util.jar.Manifest;

import com.arrow.acs.client.model.VersionModel;

public abstract class VersionAbstract {
	protected final static Loggable LOGGER = new Loggable(VersionAbstract.class.getName()) {
	};

	protected static VersionModel readManifest(Class<?> clazz) {
		try {
			Manifest manifest = ManifestUtils.readManifest(clazz);
			String title = manifest.getMainAttributes().getValue(Attributes.Name.IMPLEMENTATION_TITLE);
			String version = manifest.getMainAttributes().getValue(Attributes.Name.IMPLEMENTATION_VERSION);
			String[] tokens = version.split("\\.", -1);
			return (VersionModel) new VersionModel().withMajor(Integer.parseInt(tokens[0]))
			        .withMinor(Integer.parseInt(tokens[1])).withName(title).withDescription(title);
		} catch (Throwable t) {
			return (VersionModel) new VersionModel().withMajor(0).withMinor(0).withName("unknown")
			        .withDescription("unknown");
		}
	}
}
