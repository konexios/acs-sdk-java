package com.arrow.acs.client.model;

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
