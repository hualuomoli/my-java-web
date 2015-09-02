package com.github.hualuomoli.core.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.hualuomoli.core.entity.Page;
import com.github.hualuomoli.core.test.entity.Demo;
import com.github.hualuomoli.core.test.mapper.IDemoMapper;
import com.github.hualuomoli.core.test.service.IDemoService;

@Service(value = "com.github.hualuomoli.core.test.service.impl.DemoServiceImpl")
public class DemoServiceImpl implements IDemoService {

	@Autowired
	private IDemoMapper demoMapper;

	@Override
	public int insert(Demo demo) {
		return demoMapper.insert(demo);
	}

	@Override
	public List<Demo> findList(Demo demo) {
		return demoMapper.findList(demo);
	}

	@Override
	public Page findPage(Demo demo, Integer pageNo, Integer pageSize) {
		return this.findPage(demo, new Page(pageNo, pageSize));
	}

	@Override
	public Page findPage(Demo demo, Page page) {
		demo.setPage(page);
		page.setList(demoMapper.findList(demo));
		return page;
	}

}
