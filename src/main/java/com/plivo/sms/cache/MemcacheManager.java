package com.plivo.sms.cache;

import java.io.IOException;
import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

public class MemcacheManager implements CacheManager{

	MemcachedClient client;
	
	public MemcacheManager(String hostname, int port) throws Exception{
		try{
			this.client = new MemcachedClient(new InetSocketAddress(hostname, port));
		} catch(IOException iex){
			throw iex;
		}
		

    }
	
	
	public void set(String k, int exp,  Object v) throws Exception {
		client.set(k, exp, v);
		
	}

	public  Object get(String k) throws Exception {
		Object myObject= client.get(k);
		return myObject;
	}

}
