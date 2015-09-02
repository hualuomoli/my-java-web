package com.github.hualuomoli.commons.json;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.hualuomoli.demo.entity.Demo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class JsonMapperTest {

	private static Logger logger = LoggerFactory.getLogger(JsonMapperTest.class);

	@Test
	@Ignore
	public void testToJsonString() {
		Demo demo1 = new Demo(UUID.randomUUID().toString().replaceAll("[-]", ""), "test@qq.com", 23, 'F');
		Demo demo2 = new Demo(UUID.randomUUID().toString().replaceAll("[-]", ""), "hualuomoli@163.com", 21, 'M');

		// pojo
		logger.debug(JsonMapper.toJsonString(demo1));

		// list
		List<Demo> list = Lists.newArrayList();
		list.add(demo1);
		list.add(demo2);
		logger.debug(JsonMapper.toJsonString(list));

		// map
		Map<String, Demo> map = Maps.newHashMap();
		map.put("test1", demo1);
		map.put("test2", demo2);
		logger.debug(JsonMapper.toJsonString(map));
	}

	@Test
	@Ignore
	public void testParseJson() {
		String json = "{\"id\":\"11298510cafa4a028b6f81320b6dcb58\",\"email\":\"test@qq.com\",\"age\":23,\"sex\":\"F\"}";
		Demo demo = JsonMapper.parseJson(json, Demo.class);
		logger.debug("demo id {},email {},age {},sex {}", demo.getId(), demo.getEmail(), demo.getAge(), demo.getSex());
	}

	@Test
	@Ignore
	public void testParseJsonList() {
		String json = "[{\"id\":\"556e79122f1a4de99e32b3f57f6b1af7\",\"email\":\"test@qq.com\",\"age\":23,\"sex\":\"F\"},{\"id\":\"cbda0dc2d8704a11bfb7419bf996b576\",\"email\":\"hualuomoli@163.com\",\"age\":21,\"sex\":\"M\"}]";
		List<Demo> demoList = JsonMapper.parseJsonList(json, Demo.class);
		for (Demo demo : demoList) {
			logger.debug("demo id {},email {},age {},sex {}", demo.getId(), demo.getEmail(), demo.getAge(),
					demo.getSex());
		}
	}

	@Test
	@Ignore
	public void testParseJsonMap() {
		String json = "{\"test1\":{\"id\":\"0a4bd8992bcb46eea1f3cbc3c36aea15\",\"email\":\"test@qq.com\",\"age\":23,\"sex\":\"F\"},\"test2\":{\"id\":\"3c6d110a7d2e46bd90d96818c2e0028e\",\"email\":\"hualuomoli@163.com\",\"age\":21,\"sex\":\"M\"}}";
		Map<String, Demo> map = JsonMapper.parseJsonMap(json, Demo.class);
		for (String key : map.keySet()) {
			logger.debug("key {}", key);
			Demo demo = map.get(key);
			logger.debug("demo id {},email {},age {},sex {}", demo.getId(), demo.getEmail(), demo.getAge(),
					demo.getSex());
		}
	}

}
