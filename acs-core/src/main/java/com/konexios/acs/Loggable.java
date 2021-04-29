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

import java.util.IllegalFormatException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Loggable {
	private Logger logger = LoggerFactory.getLogger(getClass());

	public Loggable() {
	}

	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}

	public Loggable(String category) {
		logger = LoggerFactory.getLogger(category);
	}

	public void logInfo(String method, String message) {
		logger.info(formatLog(method, message));
	}

	public void logInfo(String method, String format, Object... args) {
		try {
			logInfo(method, String.format(format, args));
		} catch (IllegalFormatException e) {
			logInfo(method, "<IllegalFormatException> " + format);
		}
	}

	public void logWarn(String method, String message) {
		logger.warn(formatLog(method, message));
	}

	public void logWarn(String method, String message, Object... args) {
		try {
			logWarn(method, String.format(message, args));
		} catch (IllegalFormatException e) {
			logInfo(method, "<IllegalFormatException> " + message);
		}
	}

	public void logError(String method, String message) {
		logger.error(formatLog(method, message));
	}

	public void logError(String method, String message, Object... args) {
		try {
			logError(method, String.format(message, args));
		} catch (IllegalFormatException e) {
			logInfo(method, "<IllegalFormatException> " + message);
		}
	}

	public void logError(String method, Throwable throwable) {
		logger.error(formatLog(method, AcsUtils.getStackTrace(throwable)));
	}

	public void logError(String method, String message, Throwable throwable) {
		try {
			logger.error(formatLog(method, String.format("%s \n %s", message, AcsUtils.getStackTrace(throwable))));
		} catch (IllegalFormatException e) {
			logInfo(method, "<IllegalFormatException> " + message);
		}

	}

	public void logTrace(String method, String message) {
		logger.trace(formatLog(method, message));
	}

	public void logTrace(String method, String message, Object... args) {
		try {
			logTrace(method, String.format(message, args));
		} catch (IllegalFormatException e) {
			logInfo(method, "<IllegalFormatException> " + message);
		}
	}

	public void logDebug(String method, String message) {
		logger.debug(formatLog(method, message));
	}

	public void logDebug(String method, String message, Object... args) {
		try {
			logDebug(method, String.format(message, args));
		} catch (IllegalFormatException e) {
			logInfo(method, "<IllegalFormatException> " + message);
		}
	}

	protected String formatLog(String method, String message) {
		return String.format("%s| %s", method, message);
	}
}
