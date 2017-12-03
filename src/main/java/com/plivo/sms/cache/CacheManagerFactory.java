package com.plivo.sms.cache;

/**
 * Factory class to create cache Manager
 * TODO read cache type from config and build proper cache manager
 * @author vinay
 *
 */
public class CacheManagerFactory {

	public static CacheManager cacheManager;
	public static void createCacheManager(String cacheType, String host, int port) throws Exception{
		if(cacheType == "memcache"){
			cacheManager = new MemcacheManager(host, port);
			
		}
	}
		
	public static CacheManager getCacheManager()
	{
		return cacheManager;
	}
	
		
	
}
