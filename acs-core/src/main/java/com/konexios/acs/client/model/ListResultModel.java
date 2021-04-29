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

public class ListResultModel<T> implements Serializable {
	private static final long serialVersionUID = -6654116888756092498L;

	private long size;
	private List<T> data = new ArrayList<>();

	public ListResultModel<T> withSize(long size) {
		setSize(size);
		return this;
	}

	public ListResultModel<T> withData(List<T> data) {
		setData(data);
		return this;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
