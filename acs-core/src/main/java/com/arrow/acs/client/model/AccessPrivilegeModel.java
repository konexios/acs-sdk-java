package com.arrow.acs.client.model;


public class AccessPrivilegeModel {

    private String pri;
    private AccessLevel level;

	public String getPri() {
		return pri;
	}

	public void setPri(String pri) {
		this.pri = pri;
	}
	
	public AccessPrivilegeModel withPri(String pri){
		setPri(pri);
		return this;
	}

	public AccessLevel getLevel() {
		return level;
	}

	public void setLevel(AccessLevel level) {
		this.level = level;
	}
	
	public AccessPrivilegeModel withLevel(AccessLevel level){
		setLevel(level);
		return this;
	}
}
