package com.github.hualuomoli.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.hualuomoli.core.entity.Page;
import com.github.hualuomoli.core.exception.VersionConflictException;
import com.github.hualuomoli.demo.entity.Demo;
import com.github.hualuomoli.demo.mapper.IDemoMapper;
import com.github.hualuomoli.demo.service.IDemoService;

@Service
public class DemoServiceImpl implements IDemoService {

	@Autowired
	private IDemoMapper demoMapper;

	@Override
	public void insert(Demo demo) {
		demoMapper.insert(demo);
	}

	@Override
	public void update(Demo demo) throws VersionConflictException {
		demoMapper.update(demo);
	}

	@Override
	public void delete(Demo demo) {
		demoMapper.delete(demo);
	}

	@Override
	public Demo get(String id) {
		Demo demo = new Demo();
		demo.setId(id);
		return demoMapper.get(demo);
	}

	@Override
	public Demo get(Demo demo) {
		return demoMapper.get(demo);
	}

	@Override
	public List<Demo> findList(Demo demo) {
		return demoMapper.findList(demo);
	}

	@Override
	public Page findPage(Demo demo, Page page) {
		demo.setPage(page);
		page.setList(demoMapper.findList(demo));
		return page;
	}

	@Override
	public List<Demo> findListByEmail(String email) {
		return demoMapper.findListByEmail(email);
	}

}
