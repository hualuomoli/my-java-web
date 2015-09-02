package com.github.hualuomoli.commons.prop;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.google.common.collect.Maps;

/**
 * Properties文件载入工具类
 * @author Dean
 *
 */
public class PropertiesLoader {

	private static Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);
	private static ResourceLoader resourceLoader = new DefaultResourceLoader();

	/**
	 * 加载Properties配置文件，转换成Map
	 * @param resources 资源文件
	 * @return map
	 */
	public static Map<String, String> loadProperties2Map(String... resources) {
		return converProp2MapString(loadProperties(resources));
	}

	/**
	 * 加载Properties配置文件
	 * @param resources 资源文件
	 * @return 加载source资源文件的资源
	 */
	public static Properties loadProperties(String... resources) {
		return loadProperties(null, resources);
	}

	/**
	 * 加载Properties配置文件
	 * @param source 资源文件
	 * @param props 资源Properties
	 * @return 加载source资源文件的资源
	 */
	public static Properties loadProperties(Properties props, String... resources) {

		if (props == null) {
			props = new Properties();
		}

		for (String location : resources) {
			InputStream is = null;
			try {
				Resource resource = resourceLoader.getResource(location);
				is = resource.getInputStream();
				props.load(is);
			} catch (IOException ex) {
				logger.warn("Could not load properties from path:" + location + ", " + ex.getMessage());
			} finally {
				IOUtils.closeQuietly(is);
			}
		}
		return props;
	}

	/**
	 * 转换Prop为Map
	 * @param prop 资源
	 * @return map
	 */
	public static Map<String, String> converProp2MapString(Properties prop) {
		Map<String, String> map = Maps.newHashMap();
		Set<Object> set = prop.keySet();
		for (Object k : set) {
			String key = String.valueOf(k);
			map.put(key, prop.getProperty(key));
		}
		return map;
	}

	/**
	* 转换Prop为Map
	* @param prop 资源
	* @return map
	*/
	public static Map<Object, Object> converProp2Map(Properties prop) {
		Map<Object, Object> map = Maps.newHashMap();
		for (Object key : prop.keySet()) {
			map.put(key, prop.get(key));
		}
		return map;
	}

	/**
	 * 资源文件添加其他资源文件
	 * @param dest 目的资源文件
	 * @param datas 要添加的资源文件
	 */
	public static void putAll(Properties dest, Properties... datas) {
		for (Properties properties : datas) {
			dest.putAll(converProp2Map(properties));
		}
	}
}
