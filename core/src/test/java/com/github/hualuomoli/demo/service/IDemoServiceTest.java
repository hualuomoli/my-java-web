package com.github.hualuomoli.demo.service;

import java.util.List;
import java.util.UUID;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.hualuomoli.core.entity.Page;
import com.github.hualuomoli.core.junit4.JUnit4Runner;
import com.github.hualuomoli.demo.entity.Demo;

// JUnit test
public class IDemoServiceTest extends JUnit4Runner {

	@Autowired
	private IDemoService demoService;

	@Test
	@Ignore
	public void testInsert() {
		Demo demo = new Demo();
		demo.setId(UUID.randomUUID().toString().replaceAll("[-]", ""));
		demo.setEmail("test@126.com");
		demo.setAge(23);
		demo.setSex('F');
		demoService.insert(demo);
	}

	@Test
	@Ignore
	public void testFindList() {
		Demo demo = new Demo();
		// demo.setId("b2428f3c3dfe431898d10eb969bfd68f");
		demo.setEmail("test@126.com");
		demo.setAge(23);
		List<Demo> list = demoService.findList(demo);
		logger.debug("list:{}", list);
	}

	@Test
	@Ignore
	public void testFindPageDemoIntegerInteger() {
		Demo demo = new Demo();
		demo.setEmail("test@126.com");
		demo.setAge(23);
		Page page = demoService.findPage(demo, 2, 3);
		logger.debug("page {} {} {}", page.getCount(), page.getPageNumber(), page.getList());
	}

	@Test
	@Ignore
	public void testFindPageDemoPage() {
		Demo demo = new Demo();
		demo.setEmail("test@126.com");
		demo.setAge(23);
		Page page = demoService.findPage(demo, new Page(3, 2));
		logger.debug("page {} {} {}", page.getCount(), page.getPageNumber(), page.getList());
	}
}
