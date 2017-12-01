package com.inred.media.aoplog;


import org.aspectj.lang.JoinPoint;

public interface ILogService {

	/**
	 * 调用方法前记录日志
	 * 
	 * @param point
	 */
	public void logBeforeArg(JoinPoint point);

	/**
	 * 正常方法执行后返回时记录日志
	 * 
	 * @param point
	 * @param returnObj
	 */
	public void logAfterReturning(JoinPoint point, Object returnObj);

	/**
	 * 方法执行抛出异常后返回时记录日志
	 * 
	 * @param point
	 * @param e
	 */
	public void logAfterThrowing(JoinPoint point, Throwable e);
}