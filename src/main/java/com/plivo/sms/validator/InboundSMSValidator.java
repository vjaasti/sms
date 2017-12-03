package com.plivo.sms.validator;

import com.plivo.sms.PlivoConfiguration;
import com.plivo.sms.api.SMSRequest;
import com.plivo.sms.db.dao.AccountDAO;
import com.plivo.sms.util.ParameterException;

public class InboundSMSValidator extends DataValidator {

	public boolean validate(PlivoConfiguration config, SMSRequest smsRequest) throws ParameterException {
				
		boolean isValidData = super.validate(config,smsRequest);
		//boolean isValidParam = true; // Future Specialization for Inbound SMS data
		
		
		
		
		
		return isValidData;
	}

	
}
