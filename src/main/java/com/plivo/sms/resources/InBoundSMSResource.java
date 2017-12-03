package com.plivo.sms.resources;

import com.plivo.sms.PlivoConfiguration;
import com.plivo.sms.api.SMSRequest;
import com.plivo.sms.api.SMSResponse;
import com.plivo.sms.db.dao.AccountDAO;
import com.plivo.sms.db.dao.PhoneNumberDAO;
import com.plivo.sms.services.AuthService;
import com.plivo.sms.services.InBoundSMSService;
import com.codahale.metrics.annotation.Timed;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/inbound")
@Produces(MediaType.APPLICATION_JSON)
public class InBoundSMSResource {

	PlivoConfiguration config;
	InBoundSMSService smsService;
	AccountDAO accountDAO;
    public InBoundSMSResource(PlivoConfiguration config, AccountDAO accountDAO, PhoneNumberDAO phoneNumberDAO) {
    	this.config = config;
    	this.smsService = new InBoundSMSService(config, accountDAO, phoneNumberDAO);
    	this.accountDAO = accountDAO;
    }

    @POST
    @Path("/sms")
    public Response sms(@Valid SMSRequest smsRequest) {
    	
    	//If POST json is not provided
    	if(smsRequest == null)
    	{
    		return Response.ok(new SMSResponse("", "Invalid Input")).build();
    	}
    	
    	//If User is not validated
    	boolean validUser = AuthService.validateUser(this.accountDAO, smsRequest.getUsername(), smsRequest.getAuth_id());
    	if(!validUser){
    		return Response.status(Response.Status.FORBIDDEN).build(); 
    	}
    	
    	//Else Serve the request
        return Response.ok(smsService.sms(smsRequest)).build();
    }
    
}