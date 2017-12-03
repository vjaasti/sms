package com.plivo.sms.services;

import com.plivo.sms.db.dao.AccountDAO;
import com.plivo.sms.db.model.Account;

public class AuthService {

	private AccountDAO accountDAO;
	public static boolean validateUser(AccountDAO accountDAO, String username, String auth_id){
		
		Account account = accountDAO.findAccountByUserAndAuthID(username, auth_id);
		
		//No account with this combination
		if(account == null)
			return false;
		
		//user Validated
		return true; 
	}
}
