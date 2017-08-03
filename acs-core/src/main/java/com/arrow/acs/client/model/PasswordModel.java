package com.arrow.acs.client.model;

import java.io.Serializable;

public class PasswordModel implements Serializable {
	private static final long serialVersionUID = 3108123494888832412L;
	
	private String password;

	public PasswordModel withPassword(String password) {
		setPassword(password);
		return this;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}