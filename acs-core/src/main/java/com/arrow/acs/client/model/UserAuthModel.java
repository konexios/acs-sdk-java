package com.arrow.acs.client.model;

public class UserAuthModel {
	
	private boolean enabled;
	private String principal;
	private String refId;
	private AuthType type;

	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public UserAuthModel withEnabled(boolean enabled) {
		setEnabled(enabled);
		return this;
	}
	
	public String getPrincipal() {
		return principal;
	}
	
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	
	public UserAuthModel withPrincipal(String principal) {
		setPrincipal(principal);
		return this;
	}
	
	public String getRefId() {
		return refId;
	}
	
	public void setRefId(String refId) {
		this.refId = refId;
	}
	
	public UserAuthModel withRefId(String refId) {
		setRefId(refId);
		return this;
	}
	
	public AuthType getType() {
		return type;
	}
	
	public void setType(AuthType type) {
		this.type = type;
	}
	
	public UserAuthModel withtype(AuthType type) {
		setType(type);
		return this;
	}
	
	

}
