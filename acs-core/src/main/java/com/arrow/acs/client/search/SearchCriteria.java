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
package com.arrow.acs.client.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public abstract class SearchCriteria {
	protected Map<String, String> simpleCriteria = new HashMap<>();
	protected Map<String, String[]> arrayCriteria = new HashMap<>();

	public Map<String, String> getSimpleCriteria() {
		return Collections.unmodifiableMap(simpleCriteria);
	}

	public Map<String, String[]> getArrayCriteria() {
		return Collections.unmodifiableMap(arrayCriteria);
	}

	public List<NameValuePair> getAllCriteria() {
		List<NameValuePair> pairs = new ArrayList<>();
		for (Entry<String, String> entry : simpleCriteria.entrySet()) {
			String value = entry.getValue();
			if (!value.isEmpty()) {
				pairs.add(new BasicNameValuePair(entry.getKey(), value));
			}
		}
		for (Entry<String, String[]> entry : arrayCriteria.entrySet()) {
			String name = entry.getKey();
			for (String value : entry.getValue()) {
				if (!value.isEmpty()) {
					pairs.add(new BasicNameValuePair(name, value));
				}
			}
		}
		return pairs;
	}

	public String toString() {
		StringBuilder result = new StringBuilder("?");
		for (NameValuePair entry : getAllCriteria()) {
			result.append(entry.getName()).append('=').append(entry.getValue()).append('&');
		}
		return result.substring(0, result.length() - 1);
	}
}
