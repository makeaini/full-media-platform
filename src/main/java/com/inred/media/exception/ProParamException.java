package com.inred.media.exception;

import com.inred.media.util.Constant;


public class ProParamException extends ProException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2634813585698684406L;
	public ProParamException() {
		super();
		super.setErrorCode(Constant.PARAM);
		super.setErrorMsg("请求参数不正确");
	}
	public ProParamException(String errorMsg) {
		super();
		super.setErrorCode(Constant.PARAM);
		super.setErrorMsg(errorMsg);
	}

	// 业务异常，无需关注堆栈信息
	@Override
	public Throwable fillInStackTrace() {
		return this;
	}
	

}
