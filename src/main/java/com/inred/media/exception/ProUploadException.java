package com.inred.media.exception;


public class ProUploadException extends org.springframework.web.multipart.MultipartException {


	private static final long serialVersionUID = -3645364017481717150L;

	/**
	 * 错误信息，可选
	 */
	public ProUploadException(String errorCode) {
		super(errorCode);

	}

	// 业务异常，无需关注堆栈信息
	@Override
	public Throwable fillInStackTrace() {
		return this;
	}


	
	
}
