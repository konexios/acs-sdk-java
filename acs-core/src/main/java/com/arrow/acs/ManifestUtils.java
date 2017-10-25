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

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.jar.JarEntry;
import java.util.jar.Manifest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.loader.jar.JarFile;

public class ManifestUtils {
	private final static Loggable LOGGER = new Loggable(ManifestUtils.class.getName()) {
	};

	public static Manifest readManifest(Class<?> clazz) {
		String method = "readManifest";
		String jarFile = null;
		String path = clazz.getProtectionDomain().getCodeSource().getLocation().toString();

		if (path.startsWith("jar:file:/")) {
			jarFile = path.substring(10);
		}

		LOGGER.logInfo(method, "className: %s, path: %s, jarFile: %s", clazz.getName(), path, jarFile);
		InputStream is = null;
		JarFile file = null;
		try {

			if (!StringUtils.isEmpty(jarFile)) {
				String[] tokens = jarFile.split("!");
				file = new JarFile(new File(tokens[0].trim()));
				LOGGER.logInfo(method, "loading jar file: %s", file.getName());
				if (tokens.length > 1) {
					JarEntry entry = file.getJarEntry(tokens[1].substring(1));
					IOUtils.closeQuietly(file);
					file = file.getNestedJarFile(entry);
					LOGGER.logInfo(method, "loading nested jar file: %s ", file.getName());
					return file.getManifest();
				} else {
					return file.getManifest();
				}
			} else {
				URL url = new URL(path + "META-INF/MANIFEST.MF");
				LOGGER.logInfo(method, "trying to load manifest from: %s", url.toString());
				return new Manifest(is = url.openStream());
			}
		} catch (Throwable e) {
		} finally {
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(file);
		}
		LOGGER.logError(method, "manifest file not found for: %s", clazz.getName());
		return null;
	}
}
