package com.arrow.acs.client.api.request;


import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import java.net.URI;


public class HttpDeleteWithPayload extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "DELETE";
    public String getMethod() { return METHOD_NAME; }

    public HttpDeleteWithPayload(final String uri) {
        super();
        setURI(URI.create(uri));
    }
    public HttpDeleteWithPayload(final URI uri) {
        super();
        setURI(uri);
    }
    public HttpDeleteWithPayload() { super(); }
}