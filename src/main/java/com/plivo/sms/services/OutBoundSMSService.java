package com.plivo.sms.services;

import java.sql.Timestamp;

import com.plivo.sms.PlivoConfiguration;
import com.plivo.sms.api.SMSRequest;
import com.plivo.sms.cache.CacheManager;
import com.plivo.sms.cache.CacheManagerFactory;
import com.plivo.sms.db.dao.AccountDAO;
import com.plivo.sms.db.dao.PhoneNumberDAO;
import com.plivo.sms.db.model.Account;
import com.plivo.sms.db.model.PhoneNumber;
import com.plivo.sms.util.ParameterException;
import com.plivo.sms.util.SMSCounter;
import com.plivo.sms.util.SMSResponse;
import com.plivo.sms.validator.DataValidator;
import com.plivo.sms.validator.OutboundSMSValidator;

public class OutBoundSMSService {

	private PlivoConfiguration config;
	private DataValidator validator;
	private AccountDAO accountDAO;
	private PhoneNumberDAO phoneNumberDAO;
	private CacheManager cacheMgr;
	
	private final static String SUCCESS_MSG = "outbound sms ok";
	private final static String FROM_NUM_NOT_FOUND = "FROM parameter not found";
	private final static String STOP_MSG = "sms from %s to %s blocked by STOP request";
	private final static String UNKNOWN_MSG = "unknown failure";
	private final static String LIMIT_MSG = "limit reached for from %s";
	
	
	public OutBoundSMSService(PlivoConfiguration config, AccountDAO accountDAO, PhoneNumberDAO  phoneNumberDAO) {
		
		this.config = config;
		this.validator = new OutboundSMSValidator();
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
			
			String username = smsRequest.getUsername();
			
			Account account = accountDAO.findByUsername(username);
			PhoneNumber phoneNumber = phoneNumberDAO.findByAccountANDNumber(account.getId(), smsRequest.getFrom());
			
			if(phoneNumber == null){
				throw new ParameterException(FROM_NUM_NOT_FOUND);
			}
			
			String to_from = new String(smsRequest.getTo()+"_"+smsRequest.getFrom());
			if(this.cacheMgr.get(to_from) != null)
			{
				String msg = String.format(STOP_MSG, smsRequest.getFrom(), smsRequest.getTo()); 
				throw new ParameterException(msg);
			}
			
			if(!canSend(smsRequest.getFrom())){
				throw new ParameterException(String.format(LIMIT_MSG, smsRequest.getFrom()));
			}
			
			} catch(ParameterException pex){
				error = pex.getMessage();
			} catch(Exception ex){
				ex.printStackTrace();
				error = UNKNOWN_MSG;
		}
		if(error == ""){
			message = SUCCESS_MSG;
		}
		return new SMSResponse(message, error);
	}



	/**
	 * This method checks how many sms were sent from same number in last 24 hours
	 * If count crosses given limit, returns false
	 * if count is with in the limit for last 24 hours, returns true
	 * @param from
	 * @return false If count crosses given limit in last 24 hrs
	 * @throws Exception
	 */
	private boolean canSend(String from) throws Exception
	{
		CacheManager cache= CacheManagerFactory.getCacheManager();
		String cacheKey = "RATE_"+from;
		Object obj = cache.get("RATE_"+from);
		boolean canSend = true;
		int count = 1;
		Timestamp curTime = new Timestamp(System.currentTimeMillis());
		Timestamp newTimestamp = curTime;
		if(obj != null)
		{
			/*
			 *  Get the counter from cache and check the current count and timestamp.
			 *  Calculates the time diff from cached timestamp and current timestamp
			 *  if 
			 */
			
			
			SMSCounter counter = (SMSCounter)cache.get(cacheKey);
			
			Timestamp t = counter.getStartTime();
			count = counter.getCurrentCount();
			long msdiff = curTime.getTime() - t.getTime();
			int hourDiff = (int) (msdiff/(1000*60*60));
			
			//Determine current SMS is being sent  within configured TIME PERIOD from first sms from same number
			if(hourDiff < Integer.parseInt(config.getCounterTime()))
			{
				//Determine if configured SMS limit is reached
				if(count <  Integer.parseInt(config.getCounterLimit())){
					//Keep the time stamp of first SMS and increment the counter
					newTimestamp = t; 
					count++;
				}
				else{
					canSend = false;
				}
				
			}
			else{
				//RESET the counters if it crossed the configured TIME PERIOD
				count = 1;
				newTimestamp = curTime;
				
			}
		}
		 
		/*
		 * if it is determined that we can send SMS from this number and reset the counter accordingly
		 */
		if(canSend)
		{
			SMSCounter newCounter = new SMSCounter(newTimestamp, count);
			cache.set(cacheKey, Integer.parseInt(config.getCounterTime())*3600, newCounter);
		}
		
		
		return canSend;
	}
	
	
}