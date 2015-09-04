package com.github.hualuomoli.demo.service;

import java.util.List;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.hualuomoli.core.entity.Page;
import com.github.hualuomoli.core.exception.VersionConflictException;
import com.github.hualuomoli.core.junit4.JUnit4Runner;
import com.github.hualuomoli.demo.entity.Demo;
import com.google.common.collect.Lists;

@FixMethodOrder(MethodSorters.CUSTOM)
public class IDemoServiceTest extends JUnit4Runner {

	@Autowired
	private IDemoService demoService;

	private static final String id = "abcdefghijklmnopqrstuvwxyz";

	@Test(sort = 1)
	public void testInsert() {
		Demo demo = new Demo();
		demo.setId(id);
		demo.setEmail("test@126.com");
		demo.setAge(1);
		demo.setSex('M');
		demoService.insert(demo);
	}

	@Test(sort = 2)
	public void testGetString() {
		Demo demo = demoService.get(id);
		logger.debug("demo {}", demo);
	}

	@Test(sort = 3)
	public void testUpdate() {
		Demo demo = demoService.get(id);
		demo.setAge(2);
		demoService.update(demo);
	}

	@Test(sort = 4)
	public void testGetDemo() {
		Demo demo = new Demo();
		demo.setId(id);
		demoService.get(demo);
		logger.debug("demo {}", demo);
	}

	@Test(sort = 5)
	public void testUpdateVersionConflict() {

		try {
			Demo demo = demoService.get(id);

			demo.setAge(3);
			demoService.update(demo);

			// 修改已经被修改过的数据
			demo.setSex('F');
			demoService.update(demo);
		} catch (VersionConflictException e) {
			logger.debug("conflict data exception {}", e.getMessage());
		}
	}

	@Test(sort = 6)
	public void testDelete() {
		Demo demo = new Demo();
		demo.setId(id);
		demoService.delete(demo);
	}

	@Test(sort = 21)
	public void testBatchInsert() {
		List<Demo> demoList = Lists.newArrayList();
		for (int i = 0; i < 100; i++) {
			Demo demo = new Demo();
			demo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			demo.setEmail("testme@qq.com");
			demo.setAge(i + 20);
			demo.setSex(i % 2 == 0 ? 'F' : 'M');
			demoList.add(demo);
		}
		demoService.batchInsert(demoList);
	}

	@Test(sort = 22)
	public void testFindList() {
		Demo demo = new Demo();
		demo.setSex('F');
		List<Demo> list = demoService.findList(demo);
		logger.debug("list {}", list.size());
	}

	@Test(sort = 23)
	public void testFindPage() {
		Demo demo = new Demo();
		demo.setEmail("testme@qq.com");
		Page page = new Page(5, 6);
		page = demoService.findPage(demo, page);
		logger.debug("pageNo {} , pageSize {} , count {} , number {}", page.getPageNo(), page.getPageSize(),
				page.getCount(), page.getPageNumber());
	}

	@Test(sort = 24)
	public void testFindListByEmail() {
		List<Demo> list = demoService.findListByEmail("testme@qq.com");
		logger.debug("list {}", list.size());
	}

	@Test(sort = 25)
	public void testClear() {
		demoService.clear();
	}

}
