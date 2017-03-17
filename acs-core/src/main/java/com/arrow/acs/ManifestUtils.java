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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class ManifestUtils {
	private final static Loggable LOGGER = new Loggable(ManifestUtils.class.getName()) {
	};

	public static Manifest readManifest(Class<?> clazz) {
		String method = "readManifest";
		String jarFile = null;
		String path = clazz.getProtectionDomain().getCodeSource().getLocation().toString();
		for (String token : path.split("/")) {
			token = token.replace("!", "").toLowerCase().trim();
			if (token.endsWith(".jar")) {
				jarFile = token;
				break;
			}
		}
		LOGGER.logInfo(method, "className: %s, path: %s, jarFile: %s", clazz.getName(), path, jarFile);
		InputStream is = null;
		try {
			if (!StringUtils.isEmpty(jarFile)) {
				Enumeration<URL> enumeration = Thread.currentThread().getContextClassLoader()
				        .getResources(JarFile.MANIFEST_NAME);
				while (enumeration.hasMoreElements()) {
					URL url = enumeration.nextElement();
					for (String token : url.toString().split("/")) {
						token = token.replace("!", "").toLowerCase();
						if (token.equals(jarFile)) {
							LOGGER.logInfo(method, "loading manifest from: %s", url.toString());
							return new Manifest(is = url.openStream());
						}
					}
				}
			} else {
				URL url = new URL(path + "/META-INF/MANIFEST.MF");
				LOGGER.logInfo(method, "loading manifest from: %s", url.toString());
				return new Manifest(is = url.openStream());
			}
		} catch (IOException e) {
		} finally {
			IOUtils.closeQuietly(is);
		}
		LOGGER.logError(method, "manifest file not found for: %s", clazz.getName());
		return null;
	}
}
