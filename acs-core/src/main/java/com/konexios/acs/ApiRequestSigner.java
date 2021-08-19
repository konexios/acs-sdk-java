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

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApiRequestSigner extends Loggable {
	private String secretKey;
	private String method;
	private String uri;
	private String apiKey;
	private String timestamp;
	private String payload;
	private List<String> parameters;

	private ApiRequestSigner(String secretKey) {
		this.parameters = new ArrayList<>();
		this.secretKey = secretKey;
		this.payload = "";
	}

	public static ApiRequestSigner create(String secretKey) {
		AcsUtils.notEmpty(secretKey, "secretKey is empty");
		return new ApiRequestSigner(secretKey);
	}

	public ApiRequestSigner payload(String payload) {
		AcsUtils.notNull(payload, "payload is null");
		this.payload = payload;
		return this;
	}

	public ApiRequestSigner method(String method) {
		AcsUtils.notEmpty(method, "method is empty");
		this.method = method.toUpperCase();
		return this;
	}

	public ApiRequestSigner canonicalUri(String uri) {
		AcsUtils.notEmpty(uri, "uri is empty");
		this.uri = uri;
		return this;
	}

	public ApiRequestSigner parameter(String name, String value) {
		AcsUtils.notEmpty(name, "name is empty");
		try {
			parameters.add(
					String.format("%s=%s", URLEncoder.encode(name.toLowerCase(), StandardCharsets.UTF_8.toString()),
							AcsUtils.trimToEmpty(value)));
		} catch (Exception e) {
			throw new AcsSystemException("system error", e);
		}
		return this;
	}

	public ApiRequestSigner apiKey(String apiKey) {
		AcsUtils.notEmpty(apiKey, "apiKey is empty");
		this.apiKey = apiKey;
		return this;
	}

	public ApiRequestSigner timestamp(String timestamp) {
		AcsUtils.notEmpty(timestamp, "timestamp is empty");
		this.timestamp = timestamp;
		return this;
	}

	public String signV0() {
		String method = "signV0";
		validateRequired();
		StringBuilder builder = buildCanonicalRequest();
		builder.append(String.format("%s=%s\n", ApiHeaders.X_KONEXIOS_APIKEY, apiKey));
		builder.append(String.format("%s=%s", ApiHeaders.X_KONEXIOS_DATE, timestamp));

		String stringToSign = builder.toString();
		logDebug(method, "stringToSign: %s\n", stringToSign);
		return AcsUtils.hmacSha256Hex(secretKey, stringToSign);
	}

	public String signV1() {
		String method = "signV1";
		validateRequired();

		StringBuilder builder = buildCanonicalRequest();
		builder.append(AcsUtils.sha256Hex(payload));
		logDebug(method, "payload:\n%s", payload);

		String canonicalRequest = builder.toString();
		logDebug(method, "canonicalRequest:\n%s", canonicalRequest);

		StringBuilder stringToSign = new StringBuilder();
		stringToSign.append(AcsUtils.sha256Hex(canonicalRequest)).append('\n');
		stringToSign.append(apiKey).append('\n');
		stringToSign.append(timestamp).append('\n');
		stringToSign.append(ApiHeaders.X_KONEXIOS_VERSION_1);
		logDebug(method, "stringToSign:\n%s", stringToSign);

		return AcsUtils.hmacSha256Hex(
				AcsUtils.hmacSha256Hex(ApiHeaders.X_KONEXIOS_VERSION_1,
						AcsUtils.hmacSha256Hex(timestamp, AcsUtils.hmacSha256Hex(apiKey, secretKey))),
				stringToSign.toString());
	}

	public String signV2() {
		String method = "signV2";
		validateRequired();

		StringBuilder builder = buildCanonicalRequest();
		builder.append(AcsUtils.sha256Hex(payload));
		logDebug(method, "payload:\n%s", payload);

		String canonicalRequest = builder.toString();
		logDebug(method, "canonicalRequest:\n%s", canonicalRequest);

		StringBuilder stringToSign = new StringBuilder();
		stringToSign.append(AcsUtils.sha256Hex(canonicalRequest)).append('\n');
		stringToSign.append(apiKey).append('\n');
		stringToSign.append(timestamp).append('\n');
		stringToSign.append(ApiHeaders.X_KONEXIOS_VERSION_2);
		logDebug(method, "stringToSign:\n%s", stringToSign);

		return AcsUtils.hmacSha256Hex(
				AcsUtils.hmacSha256Hex(ApiHeaders.X_KONEXIOS_VERSION_2,
						AcsUtils.hmacSha256Hex(timestamp, AcsUtils.hmacSha256Hex(secretKey, apiKey))),
				stringToSign.toString());
	}

	public String signV3() {
		String method = "signV3";
		validateRequired();

		StringBuilder builder = buildCanonicalRequest();
		builder.append(AcsUtils.sha256Hex(payload));
		logDebug(method, "payload:\n%s", payload);

		String canonicalRequest = builder.toString();
		logDebug(method, "canonicalRequest:\n%s", canonicalRequest);

		StringBuilder stringToSign = new StringBuilder();
		stringToSign.append(AcsUtils.sha256Hex(canonicalRequest)).append('\n');
		stringToSign.append(apiKey).append('\n');
		stringToSign.append(timestamp).append('\n');
		stringToSign.append(ApiHeaders.X_KONEXIOS_VERSION_3);
		logDebug(method, "stringToSign:\n%s", stringToSign);

		return AcsUtils.hmacSha256Hex(
				AcsUtils.hmacSha256Hex(AcsUtils.hmacSha256Hex(AcsUtils.hmacSha256Hex(secretKey, apiKey), timestamp),
						ApiHeaders.X_KONEXIOS_VERSION_3),
				stringToSign.toString());
	}

	private void validateRequired() {
		AcsUtils.notEmpty(apiKey, "apiKey is required");
		AcsUtils.notEmpty(secretKey, "secretKey is required");
		AcsUtils.notEmpty(timestamp, "timestamp is required");
	}

	private StringBuilder buildCanonicalRequest() {

		StringBuilder builder = new StringBuilder();

		// append method
		AcsUtils.notEmpty(method, "method is empty");
		builder.append(method).append('\n');

		// append uri
		AcsUtils.notEmpty(uri, "uri is empty");
		builder.append(uri).append('\n');

		// append parameters
		if (parameters.size() > 0) {
			Collections.sort(parameters);
			parameters.forEach(p -> builder.append(p).append('\n'));
		}
		return builder;
	}
}
