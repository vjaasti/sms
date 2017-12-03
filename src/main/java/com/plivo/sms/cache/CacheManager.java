package com.plivo.sms.cache;

public interface CacheManager {

	public void set(String k, int exp, Object v) throws Exception;
	public Object get(String k) throws Exception;
}
