package com.inred.media.service;

import com.inred.media.exception.ProParamException;
import com.inred.media.pojo.User;

public interface UserServiceI extends BaseServiceI<User> {
	/**
	 * 添加用户
	 * @param user
	 */
	public void addUser(User user);
	/**
	 * 用户登录
	 * @param userId
	 */
	public User userLogin(String username,String schoolId)throws ProParamException;
	

}
