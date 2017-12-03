package com.plivo.sms.validator;

import com.plivo.sms.PlivoConfiguration;
import com.plivo.sms.api.SMSRequest;
import com.plivo.sms.util.ParameterException;

public class DataValidator {

	/**
	 * This method validates the given inputs based on conditions given. 
	 * Method throws ParameterException with proper message if some validation fails
	 * 
	 * @param config
	 * @param from
	 * @param to
	 * @param text
	 * @return boolean if all inputs are validated
	 * @throws ParameterException
	 */
	public boolean validate(PlivoConfiguration config, SMSRequest smsRequest) throws ParameterException
	{
				String from = smsRequest.getFrom();
				String to = smsRequest.getTo();
				String text = smsRequest.getText();
				
				
				//Validate Inputs
				if(from == null ){
					throw new ParameterException("FROM is missing");
				}
				if(to == null ){
					throw new ParameterException("TO is missing");
				}
				if(text == null ){
					throw new ParameterException("TEXT is missing");
				}
				
				
				//Validate from num length
				if(from.length() < Integer.parseInt(config.getNummin()) || from.length() > Integer.parseInt(config.getNummax())){
					throw new ParameterException("FROM is invalid");
				}
				
				//Validate to num length
				if(to.length() < Integer.parseInt(config.getNummin()) || to.length() > Integer.parseInt(config.getNummax())){
					throw new ParameterException("TO is invalid");
				}
				
				//Validate text length
				
				if(text.length() < Integer.parseInt(config.getTextmin()) || to.length() > Integer.parseInt(config.getTextmax())){
					throw new ParameterException("TEXT is invalid");
				}
				
				return true;
	}
}

