package com.inred.media.model;

import com.alibaba.fastjson.JSON;
import com.inred.media.util.Constant;


public class ReturnBean extends BaseBean {
	/** serialVersionUID */
	private static final long serialVersionUID = 5761386302316325878L;

	/** returnCode 返回码 */
	private String returnCode=Constant.OK;

	/** ReturnMsg 错误描述 */
	private String returnMsg="成功";

	/** data 返回值 */
	private String data;

	private ReturnBean() {
	}
	
	public ReturnBean(String returnCode, String data) {
		super();
		this.returnCode = returnCode;
		this.data = data;
	}

	public ReturnBean(String returnCode, Object data) {
		super();
		this.returnCode = returnCode;
		this.data = JSON.toJSONString(data);
	}

	public ReturnBean(String returnCode, String ReturnMsg, String data) {
		super();
		this.returnCode = returnCode;
		this.returnMsg = ReturnMsg;
		this.data = data;
	}
	
	public ReturnBean(String returnCode, String ReturnMsg, Object data) {
		super();
		this.returnCode = returnCode;
		this.returnMsg = ReturnMsg;
		this.data = data==null?null:JSON.toJSONString(data);
	}
	
	

	public String getReturnCode() {
		return returnCode;
	}

	public ReturnBean setReturnCode(String returnCode) {
		this.returnCode = returnCode;
		return this;
	}

	public String getData() {
		return data;
	}

	public ReturnBean setData(String data) {
		this.data = data;
		return this;
	}
	
	public ReturnBean setData(Object data) {
		this.data = data==null?null:JSON.toJSONString(data);
		return this;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public ReturnBean setReturnMsg(String ReturnMsg) {
		this.returnMsg = ReturnMsg;
		return this;
	}
	
	public static ReturnBean returnBeanBuild(){
		ReturnBean bean=new ReturnBean();
		return bean;
	}
	
	
	

	@Override
	public String toString() {
		return "ReturnBean [returnCode=" + returnCode + ", returnMsg="
				+ returnMsg + ", data=" + data + "]";
	}

}
