package com.github.hualuomoli.core.exception;

/**
 * 版本冲突异常，用于修改
 * @see 
 * @author hualuomoli
 *
 */
public class VersionConflictException extends RuntimeException {

	private static final long serialVersionUID = 49802882951266728L;

	// 默认编码和消息
	private static final Integer DEFAULT_CODE = 99999999;
	private static final String DEFAULT_MESSAGE = "版本冲突，请重新获取数据!";

	private Integer code;
	private String message;

	public VersionConflictException() {
		super();
	}

	public VersionConflictException(String message) {
		super(message);
		this.message = message;
	}

	public VersionConflictException(Throwable cause) {
		super(cause);
	}

	public VersionConflictException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	public VersionConflictException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.message = message;
	}

	public Integer getCode() {
		return code == null ? DEFAULT_CODE : code;
	}

	@Override
	public String getMessage() {
		return message == null ? DEFAULT_MESSAGE : message;
	}
}
