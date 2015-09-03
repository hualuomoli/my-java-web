package com.github.hualuomoli.demo.service;

import java.util.List;

import com.github.hualuomoli.core.entity.Page;
import com.github.hualuomoli.core.exception.VersionConflictException;
import com.github.hualuomoli.demo.entity.Demo;

/**
 * test service
 * @author hualuomoli
 *
 */
public interface IDemoService {

	// insert
	void insert(Demo demo);

	// update
	void update(Demo demo) throws VersionConflictException;

	// delete
	void delete(Demo demo);

	// get
	Demo get(String id);

	// get
	Demo get(Demo demo);

	// find list by param
	List<Demo> findList(Demo demo);

	// find page by param
	Page findPage(Demo demo, Page page);

	// set param name
	List<Demo> findListByEmail(String email);

}
