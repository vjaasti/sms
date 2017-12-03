package com.plivo.sms.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.DateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.plivo.sms.db.model.Account;


public class AccountMapper implements ResultSetMapper<Account> {

	@Override
	public Account map(int index, ResultSet r, StatementContext ctx) throws SQLException {

		Account account = new Account();
		account.setId(r.getInt("id"));
		account.setAuthID(r.getString("auth_id"));
		account.setUserName(r.getString("username"));
		
		return account;
	}

}
