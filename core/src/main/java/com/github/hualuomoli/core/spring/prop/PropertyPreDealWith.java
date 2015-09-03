package com.github.hualuomoli.core.spring.prop;

import java.util.Properties;

/**
 * 配置文件预处理，如果配置文件需要对内容处理，需要实现该接口
 * @see 如数据库密码加密，需要指定处理类的class全路径property.pre.class=....
 * @author hualuomoli
 *
 */
public interface PropertyPreDealWith {

	// 预处理，将处理的key放回原props
	void deal(Properties props);

}
