package com.plivo.sms.cache;

public interface CacheManager {

	/**
	 * Sets the object in cache
	 * @param k Cache Key
	 * @param exp Object Expiry
	 * @param v Cache Value for given key
	 * @throws Exception
	 */
	public void set(String k, int exp, Object v) throws Exception;
	
	
	/**
	 * Gets object from cache
	 * @param k Cache Key
	 * @return Object from Cache, Null if no object present
	 * @throws Exception
	 */
	public Object get(String k) throws Exception;
}
