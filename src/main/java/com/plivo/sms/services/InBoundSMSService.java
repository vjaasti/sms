package com.plivo.sms.services;

import java.util.ArrayList;

import com.plivo.sms.PlivoConfiguration;
import com.plivo.sms.api.SMSRequest;
import com.plivo.sms.cache.CacheManager;
import com.plivo.sms.cache.CacheManagerFactory;
import com.plivo.sms.db.dao.AccountDAO;
import com.plivo.sms.db.dao.PhoneNumberDAO;
import com.plivo.sms.db.model.Account;
import com.plivo.sms.db.model.PhoneNumber;
import com.plivo.sms.util.ParameterException;
import com.plivo.sms.util.SMSResponse;
import com.plivo.sms.validator.DataValidator;
import com.plivo.sms.validator.InboundSMSValidator;

public class InBoundSMSService {

	private PlivoConfiguration config;
	private DataValidator validator;
	private AccountDAO accountDAO;
	private PhoneNumberDAO phoneNumberDAO;
	private CacheManager cacheMgr;
	
	private final static String SUCCESS_MSG = "inbound sms ok";
	private final static String TO_NUM_NOT_FOUND = "to parameter not found";
	private final static String UNKNOWN_MSG = "unknown failure";

	
	
	public InBoundSMSService(PlivoConfiguration config, AccountDAO accountDAO, PhoneNumberDAO  phoneNumberDAO) {
		this.config = config;
		this.validator = new InboundSMSValidator();
		this.accountDAO = accountDAO;
		this.phoneNumberDAO = phoneNumberDAO;
		this.cacheMgr = CacheManagerFactory.getCacheManager();
		
		
	}


	/**
	 * This service validates the SMSRequest for its data and other business validations
	 * If given data is validated, process the SMS and sends SMSResponse
	 * 
	 * @param smsRequest
	 * @return SMSResponse with proper message and error
	 */
	public SMSResponse sms(SMSRequest smsRequest) {

		String message = "";
		String error = "";
		
		try {
			validator.validate(config, smsRequest);
			
			String to = smsRequest.getTo();
			
			String username = smsRequest.getUsername();
			
			Account account = accountDAO.findByUsername(username);
			PhoneNumber phoneNumber = phoneNumberDAO.findByAccountANDNumber(account.getId(), to);
			
			if(phoneNumber == null){
				throw new ParameterException(TO_NUM_NOT_FOUND);
			}
			
			
			
			// When text matches with one of the stop words, cache the combination of from_to for 4 hours
			
			if(isTextStopWord(smsRequest.getText()))
			{
				String from_to = new String(smsRequest.getFrom()+"_"+to);
				cacheMgr.set(from_to, Integer.parseInt(this.config.getCacheExpiry()), new Boolean(true));
				
			}
			
			message = SUCCESS_MSG;
			
			
		} catch(ParameterException pex){
			error = pex.getMessage();
		} catch(Exception ex){
			ex.printStackTrace();
			error = UNKNOWN_MSG;
		}
		
		
		return new SMSResponse(message, error);
	}


	private boolean isTextStopWord(String text) {
		
		//TODO move to config???
		
		if(text.equals("STOP") || text.equals("STOP\n") || text.equals("STOP\r") || text.equals("STOP\r\n"))
			return true;
		return false;
	}

	
	
}