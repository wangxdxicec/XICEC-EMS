package com.zhenhappy.exception;

import com.zhenhappy.dto.ErrorCode;

/**
 * User: Haijian Liang Date: 13-11-19 Time: 下午10:00 Function:
 */
public class ReturnException extends RuntimeException {

	private Integer errorCode;

	private ErrorCode errorCodeObject;

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public ReturnException() {
	}

	public ReturnException(ErrorCode errorCode) {
		super(errorCode.getMsg());
		this.errorCode = errorCode.getCode();
		this.errorCodeObject = errorCode;
	}

	public ReturnException(Integer errorCode, String des) {
		super(des);
		this.errorCode = errorCode;
	}

	public ReturnException(String message) {
		super(message);
	}

	public ReturnException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReturnException(Throwable cause) {
		super(cause);
	}

	public ErrorCode getErrorCodeObject() {
		return errorCodeObject;
	}

	public void setErrorCodeObject(ErrorCode errorCodeObject) {
		this.errorCodeObject = errorCodeObject;
	}

}
