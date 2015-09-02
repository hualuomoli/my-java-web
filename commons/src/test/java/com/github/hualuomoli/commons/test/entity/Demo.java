package com.github.hualuomoli.commons.test.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
