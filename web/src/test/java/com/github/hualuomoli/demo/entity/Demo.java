package com.github.hualuomoli.demo.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.github.hualuomoli.core.entity.Page;

/**
 * test entity
 * @author hualuomoli
 *
 */
public class Demo {

	private String id;
	private String email;
	private Integer age;
	private char sex;

	private Integer version; // 当前数据版本

	private Page page;

	public Demo() {
	}

	public Demo(String id, String email, Integer age, char sex) {
		this.id = id;
		this.email = email;
		this.age = age;
		this.sex = sex;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
