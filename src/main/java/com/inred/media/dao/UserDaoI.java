package com.inred.media.dao;

import java.util.Map;

import com.inred.media.model.Pagination;
import com.inred.media.pojo.User;

public interface UserDaoI {
	
	/**
	 * 添加用户
	 * @param user
	 */
	public void insertUser(User user);
	
	/**
	 *删除用户 
	 */
	public User deleteUser(String  userId);
	
	/**
	 * 查询用户byId
	 */
	public User findUserByParams(String username,String schoolId);
	
	/**
	 * 查询所有的用户并分页
	 */
     public Pagination<User> findUserByParams(Map<String, Object> params,Integer pageNo,Integer pageSize);
	
	
	
	

}
