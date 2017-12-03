package com.plivo.sms.resources;

import com.plivo.sms.PlivoConfiguration;
import com.plivo.sms.api.SMSRequest;
import com.plivo.sms.db.dao.AccountDAO;
import com.plivo.sms.db.dao.PhoneNumberDAO;
import com.plivo.sms.services.AuthService;
import com.plivo.sms.services.InBoundSMSService;
import com.plivo.sms.services.OutBoundSMSService;
import com.codahale.metrics.annotation.Timed;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/outbound")
@Produces(MediaType.APPLICATION_JSON)
public class OutBoundSMSResource {
    
  
    PlivoConfiguration config;
    OutBoundSMSService smsService;
    public OutBoundSMSResource(PlivoConfiguration config, AccountDAO accountDAO, PhoneNumberDAO phoneNumberDAO) {
    	this.config = config;
    	this.smsService = new OutBoundSMSService(config, accountDAO, phoneNumberDAO);
    }

    @POST
    @Path("/sms")
    public Response sms(@Valid SMSRequest smsRequest) {
    	boolean validUser = AuthService.validateUser(smsRequest.getUsername(), smsRequest.getAuth_id());
    	if(!validUser){
    		return Response.status(Response.Status.FORBIDDEN).build(); 
    	}
    	
    	
        return Response.ok(smsService.sms(smsRequest)).build();
    }
}