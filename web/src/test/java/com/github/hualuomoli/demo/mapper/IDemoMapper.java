package com.github.hualuomoli.demo.mapper;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.github.hualuomoli.demo.entity.Demo;

/**
 * test mapper
 * @author hualuomoli
 *
 */
@Repository
public interface IDemoMapper {

	// insert
	@Insert("insert into demo(id,email,age,sex,version) values(#{id},#{email},#{age},#{sex},1)")
	int insert(Demo demo);

	// batch insert
	int batchInsert(@Param(value = "demoList") Collection<Demo> demoList);

	// update
	int update(Demo demo);

	// delete
	@Delete("delete from demo where id=#{id}")
	int delete(Demo demo);

	// clear
	@Delete("delete from demo")
	int clear();

	// get
	Demo get(Demo demo);

	// find list by param
	List<Demo> findList(Demo demo);

	// set param name
	List<Demo> findListByEmail(String email);

}
