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

import java.io.Serializable;

public class UcmaModel implements Serializable {
	private static final long serialVersionUID = 828082615100573973L;

	private String applicationName;
	private String host;
	private int port;
	private String gruu;
	private String certificateName;
	private String applicationEndpointSip;
	private String applicationEndpointHost;
	private int applicationEndpointPort;

	// TODO need to be moved to a Themis configuration
	private String impersonateDomain;
	private String subject;
	private String toastMessage;

	public UcmaModel withApplicationName(String applicationName) {
		setApplicationName(applicationName);
		return this;
	}

	public UcmaModel withHost(String host) {
		setHost(host);
		return this;
	}

	public UcmaModel withPort(int port) {
		setPort(port);
		return this;
	}

	public UcmaModel withGruu(String gruu) {
		setGruu(gruu);
		return this;
	}

	public UcmaModel withCertificateName(String certificateName) {
		setCertificateName(certificateName);
		return this;
	}

	public UcmaModel withApplicationEndpointSip(String applicationEndpointSip) {
		setApplicationEndpointSip(applicationEndpointSip);
		return this;
	}

	public UcmaModel withApplicationEndpointHost(String applicationEndpointHost) {
		setApplicationEndpointHost(applicationEndpointHost);
		return this;
	}

	public UcmaModel withApplicationEndpointPort(int applicationEndpointPort) {
		setApplicationEndpointPort(applicationEndpointPort);
		return this;
	}

	public String getApplicationEndpointSip() {
		return applicationEndpointSip;
	}

	public void setApplicationEndpointSip(String applicationEndpointSip) {
		this.applicationEndpointSip = applicationEndpointSip;
	}

	public String getApplicationEndpointHost() {
		return applicationEndpointHost;
	}

	public void setApplicationEndpointHost(String applicationEndpointHost) {
		this.applicationEndpointHost = applicationEndpointHost;
	}

	public int getApplicationEndpointPort() {
		return applicationEndpointPort;
	}

	public void setApplicationEndpointPort(int applicationEndpointPort) {
		this.applicationEndpointPort = applicationEndpointPort;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getGruu() {
		return gruu;
	}

	public void setGruu(String gruu) {
		this.gruu = gruu;
	}

	public String getCertificateName() {
		return certificateName;
	}

	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}

	public String getImpersonateDomain() {
		return impersonateDomain;
	}

	public void setImpersonateDomain(String impersonateDomain) {
		this.impersonateDomain = impersonateDomain;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getToastMessage() {
		return toastMessage;
	}

	public void setToastMessage(String toastMessage) {
		this.toastMessage = toastMessage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((applicationEndpointHost == null) ? 0 : applicationEndpointHost.hashCode());
		result = prime * result + applicationEndpointPort;
		result = prime * result + ((applicationEndpointSip == null) ? 0 : applicationEndpointSip.hashCode());
		result = prime * result + ((applicationName == null) ? 0 : applicationName.hashCode());
		result = prime * result + ((certificateName == null) ? 0 : certificateName.hashCode());
		result = prime * result + ((gruu == null) ? 0 : gruu.hashCode());
		result = prime * result + ((host == null) ? 0 : host.hashCode());
		result = prime * result + port;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UcmaModel other = (UcmaModel) obj;
		if (applicationEndpointHost == null) {
			if (other.applicationEndpointHost != null)
				return false;
		} else if (!applicationEndpointHost.equals(other.applicationEndpointHost))
			return false;
		if (applicationEndpointPort != other.applicationEndpointPort)
			return false;
		if (applicationEndpointSip == null) {
			if (other.applicationEndpointSip != null)
				return false;
		} else if (!applicationEndpointSip.equals(other.applicationEndpointSip))
			return false;
		if (applicationName == null) {
			if (other.applicationName != null)
				return false;
		} else if (!applicationName.equals(other.applicationName))
			return false;
		if (certificateName == null) {
			if (other.certificateName != null)
				return false;
		} else if (!certificateName.equals(other.certificateName))
			return false;
		if (gruu == null) {
			if (other.gruu != null)
				return false;
		} else if (!gruu.equals(other.gruu))
			return false;
		if (host == null) {
			if (other.host != null)
				return false;
		} else if (!host.equals(other.host))
			return false;
		if (port != other.port)
			return false;
		return true;
	}
}
