package com.github.hualuomoli.core.global;

import java.util.Date;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.hualuomoli.commons.prop.PropertiesLoader;
import com.github.hualuomoli.core.util.PropUtil;

/**
 * 
 * @author hualuomoli
 *
 */
public class GlobalProp {

	private static final Logger logger = LoggerFactory.getLogger(GlobalProp.class);
	// 全局的prop配置文件
	private static final String GLOBAL_PROP_NAME = "classpath:props/props.properties";
	// 全局的资源
	private static Properties props = null;

	// 设置资源，如spring加载完成后，设置该 资源
	public static void setProps(Properties props) {
		if (GlobalProp.props == null) {
			GlobalProp.props = props;
		} else {
			PropertiesLoader.putAll(GlobalProp.props, props);
		}
	}

	// 获取资源
	private static Properties getProps() {
		if (props == null) {
			initProps();
		}
		return props;
	}

	// 初始化
	private static void initProps() {

		System.out.println("使用GlobalProperties初始化......");

		props = PropertiesLoader.loadProperties(GLOBAL_PROP_NAME);

		// 获取要读取的配置文件
		Map<String, String> map = PropertiesLoader.converProp2MapString(props);
		for (String key : map.keySet()) {
			String resource = map.get(key);
			if (logger.isDebugEnabled()) {
				logger.debug("load properties {}", resource);
			}
			PropertiesLoader.loadProperties(props, resource);
		}
	}

	/**
	 * 获取字符串
	 * @param key 键
	 * @return 值，如果没有配置，返回空字符串
	 */
	public static String getString(String key) {
		return PropUtil.getString(getProps(), key);
	}

	/**
	 * 获取字符串
	 * @param key 键
	 * @param defaultValue 默认值
	 * @return 值，如果没有配置，返回defaultValue
	 */
	public static String getString(String key, String defaultValue) {
		return PropUtil.getString(getProps(), key, defaultValue);
	}

	/**
	 * 获取整数
	 * @param key 键
	 * @return 值，如果没有配置，返回0
	 */
	public static Integer getInteger(String key) {
		return PropUtil.getInteger(getProps(), key);
	}

	/**
	* 获取整数
	* @param key 键
	* @param defaultValue 默认值
	* @return 值，如果没有配置，返回defaultValue
	*/
	public static Integer getInteger(String key, Integer defaultValue) {
		return PropUtil.getInteger(getProps(), key, defaultValue);
	}

	/**
	 * 获取长整数
	 * @param key 键
	 * @return 值，如果没有配置，返回0
	 */
	public static Long getLong(String key) {
		return PropUtil.getLong(getProps(), key);
	}

	/**
	* 获长整数
	* @param key 键
	* @param defaultValue 默认值
	* @return 值，如果没有配置，返回defaultValue
	*/
	public static Long getLong(String key, Long defaultValue) {
		return PropUtil.getLong(getProps(), key, defaultValue);
	}

	/**
	 * 获取浮点数
	 * @param key 键
	 * @return 值，如果没有配置，返回0
	 */
	public static Float getFloat(String key) {
		return PropUtil.getFloat(getProps(), key);
	}

	/**
	* 获长浮点数
	* @param key 键
	* @param defaultValue 默认值
	* @return 值，如果没有配置，返回defaultValue
	*/
	public static Float getFloat(String key, Float defaultValue) {
		return PropUtil.getFloat(getProps(), key, defaultValue);
	}

	/**
	 * 获取双精度
	 * @param key 键
	 * @return 值，如果没有配置，返回0
	 */
	public static Double getDouble(String key) {
		return PropUtil.getDouble(getProps(), key);
	}

	/**
	* 获取双精度
	* @param key 键
	* @param defaultValue 默认值
	* @return 值，如果没有配置，返回defaultValue
	*/
	public static Double getDouble(String key, Double defaultValue) {
		return PropUtil.getDouble(getProps(), key, defaultValue);
	}

	/**
	 * 获取日期
	 * @param key 键
	 * @param defaultValue 默认值
	 * @return 值，如果没有配置，返回null
	 */
	public static Date getDate(String key) {
		return PropUtil.getDate(getProps(), key);
	}

	/**
	* 获取日期
	* @param key 键
	* @param defaultValue 默认值
	* @return 值，如果没有配置，返回defaultValue
	*/
	public static Date getDate(String key, Date defaultValue) {
		return PropUtil.getDate(getProps(), key, defaultValue);
	}

	/**
	 * 获取布尔
	 * @param key 键
	 * @return 值，如果没有配置，返回false
	 */
	public static Boolean getBoolean(String key) {
		return PropUtil.getBoolean(getProps(), key);
	}

}
