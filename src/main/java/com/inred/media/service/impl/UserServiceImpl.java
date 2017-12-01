package com.inred.media.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.inred.media.dao.UserDaoI;
import com.inred.media.exception.ProParamException;
import com.inred.media.pojo.User;
import com.inred.media.service.UserServiceI;
@Service
public class UserServiceImpl extends BaseServiceImpl<User>  implements UserServiceI  {
	@Autowired
	private UserDaoI userDaoI;

	@Override
	public void addUser(User user) {
		userDaoI.insertUser(user);
	}

	@Override
	public User userLogin(String username, String schoolId) throws ProParamException{
		if(StringUtils.isEmpty(username)){
			throw new ProParamException("用户名不能为空");
		}
		return userDaoI.findUserByParams(username, schoolId);
	}

}
