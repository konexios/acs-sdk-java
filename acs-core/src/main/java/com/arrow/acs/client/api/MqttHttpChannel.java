package com.arrow.acs.client.api;

import com.arrow.acs.client.model.CloudRequestModel;
import com.arrow.acs.client.model.CloudResponseModel;

public interface MqttHttpChannel {
	CloudResponseModel sendRequest(CloudRequestModel request, long timeoutSecs);
}
