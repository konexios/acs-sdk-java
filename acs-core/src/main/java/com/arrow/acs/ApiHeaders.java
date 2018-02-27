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

public interface ApiHeaders {
    public final static String X_ARROW_VERSION = "x-arrow-version";
    public final static String X_ARROW_APIKEY = "x-arrow-apikey";
    public final static String X_ARROW_DATE = "x-arrow-date";
    public final static String X_ARROW_SIGNATURE = "x-arrow-signature";

    public final static String X_ARROW_VERSION_1 = "1";

    // backward compatible
    public final static String X_AUTH_TOKEN = "x-auth-token";
}
