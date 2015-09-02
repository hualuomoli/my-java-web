package com.github.hualuomoli.core.prop;

import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import com.github.hualuomoli.commons.prop.PropertiesLoader;

/**
 * 自定义加载器
 * @author hualuomoli
 *
 */
public class PropertyPlaceholderConfigurer extends
		org.springframework.beans.factory.config.PropertyPlaceholderConfigurer {

	private static Logger logger = LoggerFactory.getLogger(PropertyPlaceholderConfigurer.class);
	private static final String PRE_KEY_CLASS = "property.pre.class";

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {

		Properties properties = new Properties();

		// 获取要读取的配置文件
		Map<String, String> map = PropertiesLoader.converProp2MapString(props);
		for (String key : map.keySet()) {
			String resource = map.get(key);
			if (logger.isDebugEnabled()) {
				logger.debug("load properties {}", resource);
			}
			// 移除该属性
			properties.remove(PRE_KEY_CLASS);
			// 加载配置文件
			PropertiesLoader.loadProperties(properties, resource);
			// 如果需要与处理，与处理
			if (properties.containsKey(PRE_KEY_CLASS)) {
				try {
					PropertyPreDealWith dealWith = (PropertyPreDealWith) Class.forName(
							properties.getProperty(PRE_KEY_CLASS)).newInstance();
					dealWith.deal(properties);
				} catch (Exception e) {
					logger.error("get pre class instance error.{}", e);
				}
			}
		}
		props = properties;

		if (logger.isDebugEnabled()) {
			logger.debug("properties message {}", PropertiesLoader.converProp2MapString(props));
		}

		super.processProperties(beanFactoryToProcess, props);
	}
}
