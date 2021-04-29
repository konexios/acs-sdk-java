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

import java.io.File;
import java.io.Serializable;

public class DownloadFileInfo implements Serializable {
	private static final long serialVersionUID = 4089147421358793617L;

	private String fileName;
	private long size;
	private File tempFile;

	public DownloadFileInfo withFileName(String fileName) {
		setFileName(fileName);
		return this;
	}

	public DownloadFileInfo withSize(long size) {
		setSize(size);
		return this;
	}

	public DownloadFileInfo withTempFile(File tempFile) {
		setTempFile(tempFile);
		return this;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public File getTempFile() {
		return tempFile;
	}

	public void setTempFile(File tempFile) {
		this.tempFile = tempFile;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + (int) (size ^ (size >>> 32));
		result = prime * result + ((tempFile == null) ? 0 : tempFile.hashCode());
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
		DownloadFileInfo other = (DownloadFileInfo) obj;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (size != other.size)
			return false;
		if (tempFile == null) {
			if (other.tempFile != null)
				return false;
		} else if (!tempFile.equals(other.tempFile))
			return false;
		return true;
	}
}
