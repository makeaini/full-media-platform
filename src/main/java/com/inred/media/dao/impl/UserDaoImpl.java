package com.inred.media.dao.impl;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.inred.media.dao.BaseDaoI;
import com.inred.media.dao.UserDaoI;
import com.inred.media.model.Pagination;
import com.inred.media.pojo.User;
import com.inred.media.util.StringUtil;
@Repository
public class UserDaoImpl implements UserDaoI{
	@Autowired
	private BaseDaoI<User> baseDaoI;

	@Override
	public void insertUser(User user) {
		baseDaoI.save(user);
		
	}

	@Override
	public User deleteUser(String userId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(userId));
		return baseDaoI.findAndRemove(query, User.class);
		
	}

	@Override
	public User findUserByParams(String username,String schoolId) {
		Query query = new Query();
        if(!StringUtils.isEmpty(username)){
        	query.addCriteria(Criteria.where("username").is(username));
        }		
        if(!StringUtils.isEmpty(schoolId)){
        	query.addCriteria(Criteria.where("schoolId").is(schoolId));
        }
		return baseDaoI.findOneByQuery(query, User.class);
	}

	@Override
	public Pagination<User> findUserByParams(Map<String, Object> params,Integer pageNo,Integer pageSize) {
			Query query = new Query();
			if (params != null && !params.isEmpty()) {
				for (Entry<String, Object> entry : params.entrySet()) {
					query.addCriteria(Criteria.where(entry.getKey()).is(
							entry.getValue()));
				}
			
		}
			
		return baseDaoI.findPaginationByQuery(query, pageNo, pageSize, User.class);
	}

}
