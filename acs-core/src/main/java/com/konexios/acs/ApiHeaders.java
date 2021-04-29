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

public interface ApiHeaders {
	String X_ARROW_VERSION = "x-arrow-version";
	String X_ARROW_APIKEY = "x-arrow-apikey";
	String X_ARROW_DATE = "x-arrow-date";
	String X_ARROW_SIGNATURE = "x-arrow-signature";

	String X_ARROW_VERSION_1 = "1";
	String X_ARROW_VERSION_2 = "2";
	String X_ARROW_VERSION_3 = "3";

	// backward compatible
	String X_AUTH_TOKEN = "x-auth-token";
}
