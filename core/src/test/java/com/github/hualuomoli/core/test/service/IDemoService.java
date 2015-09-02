package com.github.hualuomoli.core.test.service;

import java.util.List;

import com.github.hualuomoli.core.entity.Page;
import com.github.hualuomoli.core.test.entity.Demo;

/**
 * test service
 * @author hualuomoli
 *
 */
public interface IDemoService {

	// insert
	int insert(Demo demo);

	// find list by param
	List<Demo> findList(Demo demo);

	// 查询分页
	Page findPage(Demo demo, Integer pageNo, Integer pageSize);

	// 查询分页
	Page findPage(Demo demo, Page page);

}
