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
package com.konexios.acs.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VersionAndLibraryModel implements Serializable {
	private static final long serialVersionUID = 1249740022051964590L;

	private VersionModel versionModel;
	private List<VersionModel> libraries = new ArrayList<>();

	public VersionModel getVersionModel() {
		return versionModel;
	}

	public void setVersionModel(VersionModel versionModel) {
		this.versionModel = versionModel;
	}

	public VersionAndLibraryModel withVersionModel(VersionModel versionModel) {
		setVersionModel(versionModel);

		return this;
	}

	public List<VersionModel> getLibraries() {
		return libraries;
	}

	public void setLibraries(List<VersionModel> libraries) {
		if (libraries != null)
			this.libraries = libraries;
	}

	public VersionAndLibraryModel withLibraries(List<VersionModel> libraries) {
		setLibraries(libraries);

		return this;
	}
}
