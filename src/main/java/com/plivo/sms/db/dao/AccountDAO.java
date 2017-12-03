package com.plivo.sms.db.dao;


import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;

import com.plivo.sms.db.mapper.AccountMapper;
import com.plivo.sms.db.model.Account;


@RegisterMapper(AccountMapper.class)
@UseStringTemplate3StatementLocator
public interface AccountDAO {

	@SqlQuery("select * from account where username = :username")
	public Account findByUsername(@Bind("username") String username);
	
	@SqlQuery("select * from account where username = :username and auth_id= :auth_id")
	public Account findAccountByUserAndAuthID(@Bind("username") String username, @Bind("auth_id") String auth_id);
	
	
}
