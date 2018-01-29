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
package com.arrow.acs;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.HmacUtils;

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
		builder.append(String.format("%s=%s\n", ApiHeaders.X_ARROW_APIKEY, apiKey));
		builder.append(String.format("%s=%s", ApiHeaders.X_ARROW_DATE, timestamp));

		String stringToSign = builder.toString();
		logDebug(method, "stringToSign: %s\n", stringToSign);
		return HmacUtils.hmacSha256Hex(secretKey, stringToSign);
	}

	public String signV1() {
		String method = "signV1";
		validateRequired();

		StringBuilder builder = buildCanonicalRequest();
		builder.append(hash(payload));

		StringBuilder stringToSign = new StringBuilder();
		stringToSign.append(hash(builder.toString())).append('\n');
		stringToSign.append(apiKey).append('\n');
		stringToSign.append(timestamp).append('\n');
		stringToSign.append(ApiHeaders.X_ARROW_VERSION_1);
		logDebug(method, "stringToSign: %s\n", stringToSign);

		String signingKey = HmacUtils.hmacSha256Hex(ApiHeaders.X_ARROW_VERSION_1,
		        HmacUtils.hmacSha256Hex(timestamp, HmacUtils.hmacSha256Hex(apiKey, secretKey)));

		return HmacUtils.hmacSha256Hex(signingKey, stringToSign.toString());
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

	private String hash(String value) {
		return DigestUtils.sha256Hex(value);
	}
}
