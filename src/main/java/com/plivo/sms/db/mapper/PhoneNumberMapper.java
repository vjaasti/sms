package com.plivo.sms.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.DateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.plivo.sms.db.model.PhoneNumber;


public class PhoneNumberMapper implements ResultSetMapper<PhoneNumber> {

	@Override
	public PhoneNumber map(int index, ResultSet r, StatementContext ctx) throws SQLException {

		PhoneNumber phoneNumber = new PhoneNumber();
		phoneNumber.setId(r.getInt("id"));
		phoneNumber.setNumber(r.getString("number"));
		phoneNumber.setAccountID(r.getInt("account_id"));
		
		return phoneNumber;
	}

}
