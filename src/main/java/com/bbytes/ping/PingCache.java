package com.bbytes.ping;



import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;


@Component
public class PingCache extends Assert{
   
	public String ping(String ip, String port) throws Exception{
		int ports = Integer.parseInt(port);
	Jedis jedis = new Jedis(ip, ports);
	/* jedis.configSet("timeout", "1");
	 Thread.sleep(2000);*/
	/* jedis.hmget("foobar", "foo");*/
	 assertEquals("PONG", jedis.ping());
	 return "PONG";
	}
}
