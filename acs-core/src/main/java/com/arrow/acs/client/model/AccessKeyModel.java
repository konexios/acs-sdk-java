package com.arrow.acs.client.model;

import java.io.Serializable;

public class AccessKeyModel implements Serializable {
    private static final long serialVersionUID = 2009328107616383251L;

    private String pri;
    private String apiKey;
    private String secretKey;
    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getPri() {
        return pri;
    }

    public void setPri(String pri) {
        this.pri = pri;
    }
}
