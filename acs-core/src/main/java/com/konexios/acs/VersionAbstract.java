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

import java.util.jar.Attributes;
import java.util.jar.Manifest;

import com.konexios.acs.client.model.VersionModel;

public abstract class VersionAbstract {
	protected final static Loggable LOGGER = new Loggable(VersionAbstract.class.getName()) {
	};

	protected static VersionModel readManifest(Class<?> clazz) {
		VersionModel model = new VersionModel().withMajor(0).withMinor(0).withBuild(0).withName("Unknown")
				.withDescription("Unknown");
		try {
			Manifest manifest = ManifestUtils.readManifest(clazz);
			Attributes attrs = manifest.getMainAttributes();
			String title = attrs.getValue(Attributes.Name.IMPLEMENTATION_TITLE);
			model.withName(title).withDescription(title);
			String version = attrs.getValue(Attributes.Name.IMPLEMENTATION_VERSION);
			String[] tokens = version.split("\\.");
			if (tokens.length > 0) {
				try {
					model.withMajor(Integer.parseInt(tokens[0]));
				} catch (Exception e) {
				}
				if (tokens.length > 1) {
					try {
						model.withMinor(Integer.parseInt(tokens[1]));
					} catch (Exception e) {
					}
					if (tokens.length > 2) {
						try {
							model.withBuild(Integer.parseInt(tokens[2]));
						} catch (Exception e) {
						}
					}
				}
			}
			model.withVendor(attrs.getValue(Attributes.Name.IMPLEMENTATION_VENDOR));
			model.withBuiltBy(attrs.getValue("Built-By"));
			model.withBuiltDate(attrs.getValue("Built-Date"));
			model.withBuiltJdk(attrs.getValue("Built-Jdk"));
			model.withGitBranch(attrs.getValue("Git-Branch"));
			model.withGitLastCommit(attrs.getValue("Git-Last-Commit"));
		} catch (Throwable t) {
		}
		return model;
	}
}
