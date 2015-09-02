/**
 * Copyright &copy; 2013-2015 山东易科德软件有限公司 All rights reserved.
 */
package com.github.hualuomoli.core.interceptor;

import java.io.Serializable;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.plugin.Interceptor;

import com.github.hualuomoli.commons.reflect.Reflections;
import com.github.hualuomoli.core.dialect.Dialect;
import com.github.hualuomoli.core.dialect.db.MySQLDialect;
import com.github.hualuomoli.core.entity.Page;
import com.google.common.collect.Maps;

/**
 * Mybatis拦截器基类
 * @author ThinkGem
 * @version 2015-1-14
 */
public abstract class BaseInterceptor implements Interceptor, Serializable {

	private static final long serialVersionUID = 1L;

	protected Log log = LogFactory.getLog(this.getClass());

	protected static final String PAGE = "page";

	protected static final String DELEGATE = "delegate";

	protected static final String MAPPED_STATEMENT = "mappedStatement";

	protected static final Map<String, Dialect> dialectMap = Maps.newHashMap();

	/**
	 * 对参数进行转换和检查
	 * @param parameterObject 参数对象
	 * @param page            分页对象
	 * @return 分页对象
	 * @throws NoSuchFieldException 无法找到参数
	 */
	@SuppressWarnings({ "rawtypes" })
	protected static Page convertParameter(Object parameterObject) {
		try {
			if (parameterObject instanceof Map) {
				return (Page) ((Map) parameterObject).get(PAGE);
			} else {
				return (Page) Reflections.getFieldValue(parameterObject, PAGE);
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 初始化属性
	 * @param p
	 */
	protected void initProperties(Properties p) {

	}

	/**
	 * 获取自定义方言，数据库类型，插件支持的数据库
	 */
	public static Dialect getDialect() {
		// TODO
		return new MySQLDialect();
	}

}
