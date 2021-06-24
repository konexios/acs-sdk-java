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
package com.konexios.acs;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.jar.JarEntry;
import java.util.jar.Manifest;

import org.springframework.boot.loader.jar.JarFile;

public class ManifestUtils {
	private static final String JAR_FILE_PREFIX = "jar:file:";
	private final static Loggable LOGGER = new Loggable(ManifestUtils.class.getName()) {
	};

	public static Manifest readManifest(Class<?> clazz) {
		String method = "readManifest";
		String jarFile = null;
		String path = clazz.getProtectionDomain().getCodeSource().getLocation().toString();

		if (path.startsWith(JAR_FILE_PREFIX)) {
			jarFile = path.substring(JAR_FILE_PREFIX.length());
		}

		LOGGER.logInfo(method, "className: %s, path: %s, jarFile: %s", clazz.getName(), path, jarFile);
		InputStream is = null;
		JarFile file = null;
		try {

			if (!AcsUtils.isEmpty(jarFile)) {
				String[] tokens = jarFile.split("!");
				file = new JarFile(new File(tokens[0].trim()));
				LOGGER.logInfo(method, "loading jar file: %s", file.getName());
				if (tokens.length > 1) {
					JarEntry entry = file.getJarEntry(tokens[1].substring(1));
					AcsUtils.close(file);
					file = file.getNestedJarFile(entry);
					LOGGER.logInfo(method, "loading nested jar file: %s ", file.getName());
					return file.getManifest();
				} else {
					return file.getManifest();
				}
			} else {
				if (!path.endsWith("/")) {
					path += "/";
				}
				URL url = new URL(path + "META-INF/MANIFEST.MF");
				LOGGER.logInfo(method, "trying to load manifest from: %s", url.toString());
				return new Manifest(is = url.openStream());
			}
		} catch (Throwable e) {
		} finally {
			AcsUtils.close(is);
			AcsUtils.close(file);
		}
		LOGGER.logError(method, "manifest file not found for: %s", clazz.getName());
		return null;
	}
}
