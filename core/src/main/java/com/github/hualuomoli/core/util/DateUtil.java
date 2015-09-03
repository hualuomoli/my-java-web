package com.github.hualuomoli.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期工具类
 * @author hualuomoli
 *
 */
public class DateUtil {

	private static final SimpleDateFormat SDF1 = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
	private static final SimpleDateFormat SDF2 = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat SDF3 = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
	private static final SimpleDateFormat SDF4 = new SimpleDateFormat("yyyy/MM/dd");
	private static final SimpleDateFormat SDF5 = new SimpleDateFormat("yyyyMMddkkmmss");
	private static final SimpleDateFormat SDF6 = new SimpleDateFormat("yyyyMMdd");

	private static final String REGEX1 = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$";
	private static final String REGEX2 = "^\\d{4}-\\d{2}-\\d{2}$";
	private static final String REGEX3 = "^\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}$";
	private static final String REGEX4 = "^\\d{4}/\\d{2}/\\d{2}$";
	private static final String REGEX5 = "^\\d{14}$";
	private static final String REGEX6 = "^\\d{8}$";

	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	/**
	 * 格式化日期
	 * @param source 日期字符串
	 * @return 日期，如果日期字符串为空或日期格式不正确，返回null
	 */
	public static Date parse(String source) {
		if (StringUtils.isEmpty(source)) {
			return null;
		}
		// yyyy.MM.dd处理成yyyy-MM-dd
		source = source.replaceAll("[.]", "-");

		try {
			return parseDate(source);
		} catch (ParseException e) {
			logger.error("parse date {} error.{}", source, e);
		}

		return null;
	}

	/**
	 * 格式化日期
	 * @param source 日期字符串
	 * @return 日期，如果日期字符串为空或日期格式不正确，返回null
	 * @throws ParseException 字符串转日期错误，该处不会出现这个错误
	 */
	private static Date parseDate(String source) throws ParseException {
		// yyyy-MM-dd kk:mm:ss
		if (source.matches(REGEX1)) {
			return SDF1.parse(source);
		}
		// yyyy-MM-dd
		if (source.matches(REGEX2)) {
			return SDF2.parse(source);
		}

		// yyyy/MM/dd kk:mm:ss
		if (source.matches(REGEX3)) {
			return SDF3.parse(source);
		}
		// yyyy/MM/dd
		if (source.matches(REGEX4)) {
			return SDF4.parse(source);
		}

		// yyyyMMddkkmmss
		if (source.matches(REGEX5)) {
			return SDF5.parse(source);
		}
		// yyyyMMdd
		if (source.matches(REGEX6)) {
			return SDF6.parse(source);
		}

		logger.warn("Illegal date format {}", source);

		return null;
	}

}
