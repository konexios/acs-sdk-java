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
package com.arrow.acs.client.model;
import java.util.ArrayList;
import java.util.List;

public class AccessKeyModel extends ModelAbstract<AccessKeyModel> {
    private static final long serialVersionUID = 2490976112052458168L;
    
    private String applicationHid;
    private String companyHid;
    private String apiKey;
    private String secretKey;  
	private String expiration;
    private String name;
    private List<AccessPrivilegeModel> privileges = new ArrayList<>();
    
	public String getApplicationHid() {
		return applicationHid;
	}
	
	public void setApplicationHid(String applicationHid) {
		this.applicationHid = applicationHid;
	}
	
	public AccessKeyModel withApplicationHid(String applicationHid) {
		setApplicationHid(applicationHid);
	    return this;
	}
	
	public String getCompanyHid() {
		return companyHid;
	}
	
	public void setCompanyHid(String companyHid) {
		this.companyHid = companyHid;
	}
	
	public AccessKeyModel withCompanyHid(String companyHid) {
		setCompanyHid(companyHid);
	    return this;
	}
	
	public String getApiKey() {
		return apiKey;
	}
	
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public AccessKeyModel withApiKey(String apiKey) {
		setApiKey(apiKey);
	    return this;
	}
	
	public String getSecretKey() {
		return secretKey;
	}
	
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	
	public AccessKeyModel withSecretKey(String secretKey) {
		setSecretKey(secretKey);
	    return this;
	}
	
	public String getExpiration() {
		return expiration;
	}
	
	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
	
	public AccessKeyModel withExpiration(String expiration){
		setExpiration(expiration);
		return this;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public AccessKeyModel withName(String name){
		setName(name);
		return this;
	}
	
	public List<AccessPrivilegeModel> getPrivileges() {
		return privileges;
	}
	
	public void setPrivileges(List<AccessPrivilegeModel> privileges) {
		this.privileges = privileges;
	}
	
	public AccessKeyModel withPrivileges(List<AccessPrivilegeModel> privileges){
		setPrivileges(privileges);
		return this;
	}

	@Override
	protected AccessKeyModel self() {
		return this;
	}
	
    
	
    

}
