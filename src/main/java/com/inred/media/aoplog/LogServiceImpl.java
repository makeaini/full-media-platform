package com.inred.media.aoplog;



import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogServiceImpl implements ILogService {
	private static final Logger LOG = LoggerFactory
			.getLogger(LogServiceImpl.class);
	
	@Override
	public void logBeforeArg(JoinPoint point) {
		HttpServletRequest request=null;
		StringBuilder argStr=new StringBuilder();
		for (Object arg : point.getArgs()) {
			if(arg instanceof HttpServletRequest){
				request=(HttpServletRequest) arg;
				LOG.info("----------------------------");
				LOG.info("request params:{}",request.getParameterMap());
				LOG.info("----------------------------");
			}else{
				argStr.append(arg).append(",");
			}
		}
		if(request!=null){
			LOG.info("请求地址："+request.getRequestURL());
		}else{
			LOG.info("请求地址："+point.getSignature());
		}
		LOG.info(point.getSignature().getName()+"请求参数："+argStr.toString());
	}

	@Override
	public void logAfterReturning(JoinPoint point, Object returnObj) {
		LOG.info(point.getSignature().getName()+"执行结果："+returnObj);
	}

	@Override
	public void logAfterThrowing(JoinPoint point, Throwable e) {
		LOG.info(point.getSignature().getName()+"抛出异常："+e);
		LOG.error(point.getSignature().getName()+e, e);
	}


}