package com.arrow.acs.client.api;

import com.arrow.acs.Loggable;
import com.arrow.acs.client.model.CloudRequestModel;
import com.arrow.acs.client.model.CloudRequestParameters;

public class MqttApiAbstract extends Loggable {

	private ApiConfig apiConfig;

	public void setApiConfig(ApiConfig apiConfig) {
		this.apiConfig = apiConfig;
	}

	public ApiConfig getApiConfig() {
		return apiConfig;
	}
	
	public void send(String requestId, String apiMethod, String url, String jsonBody){
		String method = "send";
		logInfo(method, "...");
		
		// add request parameters
		CloudRequestModel requestModel = new CloudRequestModel();
		CloudRequestParameters parameters = new CloudRequestParameters();
		requestModel.setParameters(parameters.getParameters());
		
		// data model is prepared. need to send
		// mqtt.send(topic, model);
	}
	
	
}
