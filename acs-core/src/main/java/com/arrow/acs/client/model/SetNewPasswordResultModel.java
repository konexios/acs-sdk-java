package com.arrow.acs.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SetNewPasswordResultModel implements Serializable {
	private static final long serialVersionUID = -1564274397421638128L;
	
	private List<String> result = new ArrayList<>();
	
	public List<String> getResult() {
		return result;
	}

	public void setResult(List<String> result) {
		this.result = result;
	}
	
	public SetNewPasswordResultModel withResult(List<String> result) {
		setResult(result);
		return this;
	}
}
