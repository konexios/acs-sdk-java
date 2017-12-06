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
package com.arrow.acs.client.model;

import java.io.Serializable;

public class VersionModel implements Serializable {
	private static final long serialVersionUID = -5828507529810423672L;

	private String name;
	private String description;
	private Integer major;
	private Integer minor;
	private Integer build;
	private Integer compatibleMajor;
	private Integer compatibleMinor;
	private String vendor;

	// build info
	private String builtBy;
	private String builtJdk;
	private String builtDate;
	private String gitBranch;
	private String gitLastCommit;

	public VersionModel withName(String name) {
		setName(name);
		return this;
	}

	public VersionModel withDescription(String description) {
		setDescription(description);
		return this;
	}

	public VersionModel withGitBranch(String gitBranch) {
		setGitBranch(gitBranch);
		return this;
	}

	public VersionModel withGitLastCommit(String gitLastCommit) {
		setGitLastCommit(gitLastCommit);
		return this;
	}

	public VersionModel withVendor(String vendor) {
		setVendor(vendor);
		return this;
	}

	public VersionModel withBuiltBy(String builtBy) {
		setBuiltBy(builtBy);
		return this;
	}

	public VersionModel withBuiltJdk(String buildJdk) {
		setBuiltJdk(buildJdk);
		return this;
	}

	public VersionModel withBuiltDate(String builtDate) {
		setBuiltDate(builtDate);
		return this;
	}

	public VersionModel withMajor(Integer major) {
		setMajor(major);
		return this;
	}

	public VersionModel withMinor(Integer minor) {
		setMinor(minor);
		return this;
	}

	public VersionModel withBuild(Integer build) {
		setBuild(build);
		return this;
	}

	public VersionModel withCompatibleMajor(Integer compatibleMajor) {
		setCompatibleMajor(compatibleMajor);
		return this;
	}

	public VersionModel withCompatibleMinor(Integer compatibleMinor) {
		setCompatibleMinor(compatibleMinor);
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMajor() {
		return major;
	}

	public void setMajor(Integer major) {
		this.major = major;
	}

	public Integer getMinor() {
		return minor;
	}

	public void setMinor(Integer minor) {
		this.minor = minor;
	}

	public Integer getBuild() {
		return build;
	}

	public void setBuild(Integer build) {
		this.build = build;
	}

	public Integer getCompatibleMajor() {
		return compatibleMajor;
	}

	public void setCompatibleMajor(Integer compatibleMajor) {
		this.compatibleMajor = compatibleMajor;
	}

	public Integer getCompatibleMinor() {
		return compatibleMinor;
	}

	public void setCompatibleMinor(Integer compatibleMinor) {
		this.compatibleMinor = compatibleMinor;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getBuiltBy() {
		return builtBy;
	}

	public void setBuiltBy(String builtBy) {
		this.builtBy = builtBy;
	}

	public String getBuiltJdk() {
		return builtJdk;
	}

	public void setBuiltJdk(String builtJdk) {
		this.builtJdk = builtJdk;
	}

	public String getBuiltDate() {
		return builtDate;
	}

	public void setBuiltDate(String builtDate) {
		this.builtDate = builtDate;
	}

	public String getGitBranch() {
		return gitBranch;
	}

	public void setGitBranch(String gitBranch) {
		this.gitBranch = gitBranch;
	}

	public String getGitLastCommit() {
		return gitLastCommit;
	}

	public void setGitLastCommit(String gitLastCommit) {
		this.gitLastCommit = gitLastCommit;
	}
}
