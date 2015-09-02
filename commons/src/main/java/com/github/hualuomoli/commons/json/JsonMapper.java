package com.github.hualuomoli.commons.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.hualuomoli.commons.json.style.JsonMapperStyle;

/**
 * 简单封装Jackson，实现JSON String<->Java Object的Mapper.
 * @author hualuomoli
 *
 */
public class JsonMapper extends ObjectMapper implements JsonMapperStyle {

	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(JsonMapper.class);

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");// 默认日期格式

	private static JsonMapper defaultMapper = new JsonMapper(); // 默认mapper

	private JsonMapper() {
		this.setInclusion(Include.ALWAYS);
		format(this);
	}

	// 指定风格
	private JsonMapper(Include include) {
		this.setInclusion(include);
		format(this);
	}

	private JsonMapper(Include include, JsonMapperStyle style) {
		this.setInclusion(include);
		// 设置输出样式
		style.format(this);
	}

	@Override
	public void format(JsonMapper mapper) {
		// 允许单引号、允许不带引号的字段名称
		this.configure(Feature.ALLOW_SINGLE_QUOTES, true);
		this.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		// 空值处理为空串
		this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
			@Override
			public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
					JsonProcessingException {
				jgen.writeString("");
			}
		});
		// 统一默认Date类型转换格式。如果设置，Bean中的@JsonFormat将无效
		this.registerModule(new SimpleModule().addSerializer(Date.class, new JsonSerializer<Date>() {
			@Override
			public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
					JsonProcessingException {
				if (value != null) {
					jgen.writeString(sdf.format(value));
				}
			}
		}));
		// 进行HTML解码。
		this.registerModule(new SimpleModule().addSerializer(String.class, new JsonSerializer<String>() {
			@Override
			public void serialize(String value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
					JsonProcessingException {
				if (value != null) {
					jgen.writeString(StringEscapeUtils.unescapeHtml4(value));
				}
			}
		}));		// 设置时区
		this.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
	}

	// 设置输出风格
	private void setInclusion(Include include) {
		// 设置输出时包含属性的风格
		if (include != null) {
			this.setSerializationInclusion(include);
		}
	}

	/**
	 * 转换成字符串
	 * @param obj 对象
	 * @return JSON字符串
	 */
	public String toJson(Object obj) {
		try {
			return this.writeValueAsString(obj);
		} catch (Exception e) {
			logger.error("toJsonString error param {} ,error {}", obj, e);
		}
		return StringUtils.EMPTY;
	}

	/**
	 * 转换json
	 * @param json JSON数据
	 * @param clazz 转换后的类型
	 * @return 实体类
	 */
	public <T> T parseJsonString(String json, Class<T> clazz) {
		try {
			return this.readValue(json, clazz);
		} catch (Exception e) {
			logger.error("parse Json {},class {}, error {}", json, clazz, e);
		}
		return null;
	}

	/**
	 * 转换json
	 * @see ArrayList<MyBean>, 则调用parseJson(json,ArrayList.class,MyBean.class)
	 * @see HashMap<String,MyBean>, 则调用parseJson(json,HashMap.class,String.class, MyBean.class)
	 * @param json JSON数据
	 * @param collectionClass 集合类型
	 * @param elementClasses 元素类型
	 * @return 集合
	 */
	public <T> T parseJson(String json, Class<?> collectionClass, Class<?>... elementClasses) {
		return this.parseJson(json, this.createCollectionType(collectionClass, elementClasses));
	}

	/**
	 * 构造泛型的Collection Type如:
	 * ArrayList<MyBean>, 则调用constructCollectionType(ArrayList.class,MyBean.class)
	 * HashMap<String,MyBean>, 则调用(HashMap.class,String.class, MyBean.class)
	 */
	private JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return this.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}

	/**
	 * 反序列化复杂Collection如List<Bean>, 先使用函数createCollectionType构造类型,然后调用本函数.
	 * @see #createCollectionType(Class, Class...)
	 */
	@SuppressWarnings("unchecked")
	private <T> T parseJson(String jsonString, JavaType javaType) {
		try {
			return (T) this.readValue(jsonString, javaType);
		} catch (Exception e) {
			logger.error("parse json string '{}' error {}" + jsonString, e);
		}
		return null;
	}

	// 获取默认的实例
	public JsonMapper getInstance() {
		return defaultMapper;
	}

	// 指定输出风格(如空值是否输出)
	public JsonMapper getInstance(Include include) {
		return new JsonMapper(include);
	}

	// 指定输出风格(如空值是否输出)和输出样式(日期输出格式)
	public JsonMapper getInstance(Include include, JsonMapperStyle style) {
		return new JsonMapper(include, style);
	}

	/**
	 * POJO转JSON
	 * @param obj Object
	 * @return JSON
	 */
	public static String toJsonString(Object obj) {
		return defaultMapper.toJson(obj);
	}

	/**
	 * JSON转POJO
	 * @param json JSON 
	 * @param clazz Object's Class
	 * @return Object
	 */
	public static <T> T parseJson(String json, Class<T> clazz) {
		return defaultMapper.parseJsonString(json, clazz);
	}

	/**
	* JSON转List<POJO>
	* @param json JSON 
	* @param clazz Object's Class
	* @return List<POJO>
	*/
	public static <T> List<T> parseJsonList(String json, Class<T> clazz) {
		return defaultMapper.parseJson(json, List.class, clazz);
	}

	/**
	* JSON转Map<String,POJO>
	* @param json JSON 
	* @param clazz Object's Class
	* @return Map<String,POJO>
	*/
	public static <T> Map<String, T> parseJsonMap(String json, Class<T> clazz) {
		return defaultMapper.parseJson(json, Map.class, String.class, clazz);
	}

	/**
	* JSON转自定义集合
	* @param json JSON 
	* @param collectionClass collection's Class
	* @param elementClasses  element classes
	* @return Object
	*/
	@SuppressWarnings("rawtypes")
	public static <T> T parseJsonMap(String json, Class collectionClass, Class... elementClasses) {
		return defaultMapper.parseJson(json, defaultMapper.createCollectionType(collectionClass, elementClasses));
	}
}
