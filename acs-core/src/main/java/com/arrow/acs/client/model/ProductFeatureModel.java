package com.arrow.acs.client.model;

public class ProductFeatureModel {
	
	private String name;
	private String description;
	private String systemName;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ProductFeatureModel withName(String name) {
		setName(name);
		return this;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public ProductFeatureModel withDescription(String description) {
		setDescription(description);
		return this;
	}

	public String getSystemName() {
		return systemName;
	}
	
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	
	public ProductFeatureModel withSystemName(String systemName){
		setSystemName(systemName);
		return this;
	}

}
