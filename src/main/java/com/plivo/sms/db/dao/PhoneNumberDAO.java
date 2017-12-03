package com.plivo.sms.db.dao;

import com.plivo.sms.db.mapper.PhoneNumberMapper;
import com.plivo.sms.db.model.Account;
import com.plivo.sms.db.model.PhoneNumber;

import java.util.List;
import java.util.Set;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlBatch;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.BatchChunkSize;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;
import org.skife.jdbi.v2.unstable.BindIn;


@RegisterMapper(PhoneNumberMapper.class)
@UseStringTemplate3StatementLocator
public interface PhoneNumberDAO {
	
	@SqlQuery("select * from phone_number where account_id = :account_id AND number = :number LIMIT 1")
	public PhoneNumber findByAccountANDNumber(@Bind("account_id") int account_id, @Bind("number") String number);
}
