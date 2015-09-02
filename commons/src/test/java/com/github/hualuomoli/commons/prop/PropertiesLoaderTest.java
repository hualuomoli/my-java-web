package com.github.hualuomoli.commons.prop;

import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

public class PropertiesLoaderTest {

	@Test
	@Ignore
	public void testloadProperties2MapStringArray() {
		Map<String, String> map = PropertiesLoader.loadProperties2Map("classpath:/props/props.properties");
		for (String key : map.keySet()) {
			String location = map.get(key);
			System.out.println(location);
			Map<String, String> p = PropertiesLoader.loadProperties2Map(location);
			System.out.println(p);
		}
	}

}
