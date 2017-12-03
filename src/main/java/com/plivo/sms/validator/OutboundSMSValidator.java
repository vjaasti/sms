package com.plivo.sms.validator;

import com.plivo.sms.PlivoConfiguration;
import com.plivo.sms.api.SMSRequest;
import com.plivo.sms.util.ParameterException;

public class OutboundSMSValidator extends DataValidator {

	public boolean validate(PlivoConfiguration config, SMSRequest smsRequest) throws ParameterException {
				
		return super.validate(config,smsRequest);
	}

	
}
