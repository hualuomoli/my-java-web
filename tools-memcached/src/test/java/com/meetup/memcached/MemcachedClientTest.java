package com.meetup.memcached;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.LoggerFactory;

public class MemcachedClientTest {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MemcachedClientTest.class);
	private static final String poolName = "hualuomoli";
	private static MemcachedClient client = null;

	@BeforeClass
	public static void beforeClass() {
		client = new MemcachedClient(poolName);

		String[] servers = { "localhost:11211" };
		Integer[] weights = { 1 };
		SockIOPool pool = SockIOPool.getInstance(poolName);
		pool.setServers(servers);
		pool.setWeights(weights);

		pool.setInitConn(10);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaxIdle(1000 * 60 * 60 * 6);

		pool.setMaintSleep(30);
		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setSocketConnectTO(0);

		pool.initialize();

		client.setCompressEnable(true);
		client.setCompressThreshold(64 * 1024);
	}

	@Test
	public void test() {
		client.set("foo", "This is a test String");
		String foo = (String) client.get("foo");
		logger.debug("foo from cache {}", foo);
	}

}
