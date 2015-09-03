package com.github.hualuomoli.core.util;

import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

/**
 * Properties工具
 * @author hualuomoli
 *
 */
public class PropUtil {

	/**
	 * 获取字符串
	 * @param props 资源 
	 * @param key 键
	 * @return 值，如果没有配置，返回null
	 */
	public static String getProperty(Properties props, String key) {
		return props.getProperty(key);
	}

	/**
	 * 获取字符串
	 * @param props 资源 
	 * @param key 键
	 * @return 值，如果没有配置，返回空字符串
	 */
	public static String getString(Properties props, String key) {
		return getString(props, key, StringUtils.EMPTY);
	}

	/**
	 * 获取字符串
	 * @param props 资源 
	 * @param key 键
	 * @param defaultValue 默认值
	 * @return 值，如果没有配置，返回defaultValue
	 */
	public static String getString(Properties props, String key, String defaultValue) {
		String value = getProperty(props, key);
		return value == null ? defaultValue : value;
	}

	/**
	 * 获取整数
	 * @param props 资源 
	 * @param key 键
	 * @return 值，如果没有配置，返回0
	 */
	public static Integer getInteger(Properties props, String key) {
		return getInteger(props, key, 0);
	}

	/**
	* 获取整数
	* @param props 资源 
	* @param key 键
	* @param defaultValue 默认值
	* @return 值，如果没有配置，返回defaultValue
	*/
	public static Integer getInteger(Properties props, String key, Integer defaultValue) {
		String value = getProperty(props, key);
		return value == null ? defaultValue : Integer.parseInt(value);
	}

	/**
	 * 获取长整数
	 * @param props 资源 
	 * @param key 键
	 * @return 值，如果没有配置，返回0
	 */
	public static Long getLong(Properties props, String key) {
		return getLong(props, key, 0L);
	}

	/**
	* 获长整数
	* @param props 资源 
	* @param key 键
	* @param defaultValue 默认值
	* @return 值，如果没有配置，返回defaultValue
	*/
	public static Long getLong(Properties props, String key, Long defaultValue) {
		String value = getProperty(props, key);
		return value == null ? defaultValue : Long.parseLong(value);
	}

	/**
	 * 获取浮点数
	 * @param props 资源 
	 * @param key 键
	 * @return 值，如果没有配置，返回0
	 */
	public static Float getFloat(Properties props, String key) {
		return getFloat(props, key, 0F);
	}

	/**
	* 获长浮点数
	* @param props 资源 
	* @param key 键
	* @param defaultValue 默认值
	* @return 值，如果没有配置，返回defaultValue
	*/
	public static Float getFloat(Properties props, String key, Float defaultValue) {
		String value = getProperty(props, key);
		return value == null ? defaultValue : Float.parseFloat(value);
	}

	/**
	 * 获取双精度
	 * @param props 资源 
	 * @param key 键
	 * @return 值，如果没有配置，返回0
	 */
	public static Double getDouble(Properties props, String key) {
		return getDouble(props, key, 0D);
	}

	/**
	* 获取双精度
	* @param props 资源 
	* @param key 键
	* @param defaultValue 默认值
	* @return 值，如果没有配置，返回defaultValue
	*/
	public static Double getDouble(Properties props, String key, Double defaultValue) {
		String value = getProperty(props, key);
		return value == null ? defaultValue : Double.parseDouble(value);
	}

	/**
	 * 获取日期
	 * @param props 资源 
	 * @param key 键
	 * @param defaultValue 默认值
	 * @return 值，如果没有配置，返回null
	 */
	public static Date getDate(Properties props, String key) {
		return getDate(props, key, null);
	}

	/**
	* 获取日期
	* @param props 资源 
	* @param key 键
	* @param defaultValue 默认值
	* @return 值，如果没有配置，返回defaultValue
	*/
	public static Date getDate(Properties props, String key, Date defaultValue) {
		String value = getProperty(props, key);
		return value == null ? defaultValue : DateUtil.parse(value);
	}

	/**
	 * 获取布尔
	 * @param props 资源 
	 * @param key 键
	 * @return 值，如果没有配置，返回false
	 */
	public static Boolean getBoolean(Properties props, String key) {
		String value = getProperty(props, key);
		return value != null && //
				(value.equalsIgnoreCase("t") //
						|| value.equalsIgnoreCase("true") //
						|| value.equalsIgnoreCase("yes") //
						|| value.equalsIgnoreCase("y") //
						|| value.equals("1") //
						|| value.equalsIgnoreCase("on") //
				|| value.equalsIgnoreCase("open")//
				);
	}
}
