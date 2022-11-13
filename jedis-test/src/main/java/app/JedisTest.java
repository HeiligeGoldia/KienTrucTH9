package app;

import java.util.Scanner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisTest {
	
	public static void main(String[] args) {
	    JedisPool jedisPool = new JedisPool("127.0.0.1");
	    try {
	      Jedis jedis = jedisPool.getResource();
	      String result = jedis.get("mykey");
	      System.out.println(result);

	      jedis.set("msg_java", "Hello Java");
	      jedisPool.returnResource(jedis);

	      String key, value;

	      Scanner sc = new Scanner(System.in);
	      do {
	        System.out.println("Key1");
	        key = sc.next();

	        System.out.println("Value1");
	        value = sc.next();

	        jedis.set(key, value);
	        jedisPool.returnResource(jedis);

	        System.out.println(key + " " + value);
	        System.out.println(key + " " + jedis.get(key));

	      } while (!key.equals(""));

	      sc.close();

	    } finally {
	      jedisPool.destroy();
	    }
	}

}
