package com.github.hualuomoli.core.test.entity;

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

	private Page page;

	public Demo() {
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
