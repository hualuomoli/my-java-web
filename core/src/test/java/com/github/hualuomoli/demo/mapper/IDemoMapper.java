package com.github.hualuomoli.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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
	@Insert("insert into demo(id,email,age,sex) values(#{id},#{email},#{age},#{sex})")
	int insert(Demo demo);

	// update
	int update(Demo demo);

	// delete
	@Delete("delete from demo t where t.id=#{id}")
	int delete(Demo demo);

	// find list by param
	List<Demo> findList(Demo demo);

}
