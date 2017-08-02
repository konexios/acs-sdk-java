package com.arrow.acs.client.model;

public class UpdateSubscriptionModel extends CreateSubscriptionModel {

	private static final long serialVersionUID = -4374072722271565293L;

	private String hid;

	public String getHid() {
		return hid;
	}

	public void setHid(String hid) {
		this.hid = hid;
	}
}
