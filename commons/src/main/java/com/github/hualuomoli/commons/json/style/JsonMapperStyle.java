package com.github.hualuomoli.commons.json.style;

import com.github.hualuomoli.commons.json.JsonMapper;

/**
 * JsonMapper输出样式格式化
 * @author hualuomoli
 *
 */
public interface JsonMapperStyle {

	// 输出格式化
	void format(JsonMapper mapper);

}
