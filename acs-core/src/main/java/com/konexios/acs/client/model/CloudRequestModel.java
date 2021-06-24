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
import java.util.Map;

public class CloudRequestModel implements Serializable {
	private static final long serialVersionUID = 5398531391540906265L;

	private String requestId;
	private String eventName;
	private boolean encrypted;
	private Map<String, String> parameters;
	private String signature;
	private String signatureVersion;

	public CloudRequestModel withRequestId(String requestId) {
		setRequestId(requestId);
		return this;
	}

	public CloudRequestModel withEventName(String eventName) {
		setEventName(eventName);
		return this;
	}

	public CloudRequestModel withEncrypted(boolean encrypted) {
		setEncrypted(encrypted);
		return this;
	}

	public CloudRequestModel withParameters(Map<String, String> parameters) {
		setParameters(parameters);
		return this;
	}

	public CloudRequestModel withSignature(String signature) {
		setSignature(signature);
		return this;
	}

	public CloudRequestModel withSignatureVersion(String signatureVersion) {
		setSignatureVersion(signatureVersion);
		return this;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public boolean isEncrypted() {
		return encrypted;
	}

	public void setEncrypted(boolean encrypted) {
		this.encrypted = encrypted;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getSignatureVersion() {
		return signatureVersion;
	}

	public void setSignatureVersion(String signatureVersion) {
		this.signatureVersion = signatureVersion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (encrypted ? 1231 : 1237);
		result = prime * result + ((eventName == null) ? 0 : eventName.hashCode());
		result = prime * result + ((parameters == null) ? 0 : parameters.hashCode());
		result = prime * result + ((requestId == null) ? 0 : requestId.hashCode());
		result = prime * result + ((signature == null) ? 0 : signature.hashCode());
		result = prime * result + ((signatureVersion == null) ? 0 : signatureVersion.hashCode());
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
		CloudRequestModel other = (CloudRequestModel) obj;
		if (encrypted != other.encrypted)
			return false;
		if (eventName == null) {
			if (other.eventName != null)
				return false;
		} else if (!eventName.equals(other.eventName))
			return false;
		if (parameters == null) {
			if (other.parameters != null)
				return false;
		} else if (!parameters.equals(other.parameters))
			return false;
		if (requestId == null) {
			if (other.requestId != null)
				return false;
		} else if (!requestId.equals(other.requestId))
			return false;
		if (signature == null) {
			if (other.signature != null)
				return false;
		} else if (!signature.equals(other.signature))
			return false;
		if (signatureVersion == null) {
			if (other.signatureVersion != null)
				return false;
		} else if (!signatureVersion.equals(other.signatureVersion))
			return false;
		return true;
	}

}
