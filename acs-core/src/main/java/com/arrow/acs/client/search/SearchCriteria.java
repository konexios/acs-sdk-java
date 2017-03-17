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
package com.arrow.acs.client.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SearchCriteria {
	protected Map<String, String> simpleCriteria = new HashMap<>();
	protected Map<String, String[]> arrayCriteria = new HashMap<>();

	public Map<String, String> getSimpleCriteria() {
		return Collections.unmodifiableMap(simpleCriteria);
	}

	public Map<String, String[]> getArrayCriteria() {
		return Collections.unmodifiableMap(arrayCriteria);
	}

	public List<Pair> getAllCriteria() {
		List<Pair> pairs = new ArrayList<>();
		for (Entry<String, String> entry : simpleCriteria.entrySet()) {
			String value = entry.getValue();
			if (!value.isEmpty()) {
				pairs.add(new Pair(entry.getKey(), value));
			}
		}
		for (Entry<String, String[]> entry : arrayCriteria.entrySet()) {
			for (String value : entry.getValue()) {
				if (!value.isEmpty()) {
					pairs.add(new Pair(entry.getKey(), value));
				}
			}
		}
		return pairs;
	}

	public String toString() {
		String result = "?";
		for (Pair entry : getAllCriteria()) {
			result += entry.getName() + "=" + entry.getValue() + "&";
		}
		return result.substring(0, result.length() - 1);
	}

	public static class Pair {
		String name;
		String value;

		public Pair(String name, String value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public String getValue() {
			return value;
		}
	}
}
