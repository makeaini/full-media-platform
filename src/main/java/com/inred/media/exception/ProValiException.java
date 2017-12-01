package com.inred.media.exception;


public class ProValiException extends ProException {

	private static final long serialVersionUID = -7368646284079457427L;

	public ProValiException(String errorCode) {
		super();
		super.setErrorCode(errorCode);
		super.setErrorMsg(errorCode);
	}
	public ProValiException(String errorCode,String errorMsg) {
		super();
		super.setErrorCode(errorCode);
		super.setErrorMsg(errorMsg);
	}

	// 业务异常，无需关注堆栈信息
	@Override
	public Throwable fillInStackTrace() {
		return this;
	}
	

}
