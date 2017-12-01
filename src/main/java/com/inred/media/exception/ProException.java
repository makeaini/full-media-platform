package com.inred.media.exception;

import com.inred.media.util.Constant;

public class ProException extends Exception {
	
	private static final long serialVersionUID = -4720455406482839391L;
	/**
	 * 错误码
	 */
	private String errorCode=Constant.OK;
	/**
	 * 错误信息，可选
	 */
	private String errorMsg=Constant.ERROR;
	
	public ProException() {
		super();
	}

	public ProException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProException(String message) {
		super(message);
	}

	public ProException(Throwable cause) {
		super(cause);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	protected void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	protected void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public String toString() {
		return this.getClass().getName()+" [errorCode=" + errorCode + ", errorMsg="
				+ errorMsg + "]";
	}

}
