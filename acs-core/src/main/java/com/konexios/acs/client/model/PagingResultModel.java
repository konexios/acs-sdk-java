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

public class PagingResultModel<T> extends ListResultModel<T> {
	private static final long serialVersionUID = -7461075045946232595L;

	private long page;
	private long totalSize;
	private long totalPages;

	public PagingResultModel<T> withPage(long page) {
		setPage(page);
		return this;
	}

	public PagingResultModel<T> withTotalSize(long totalSize) {
		setTotalSize(totalSize);
		return this;
	}

	public PagingResultModel<T> withTotalPages(long totalPages) {
		setTotalPages(totalPages);
		return this;
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}

	public long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}
}
