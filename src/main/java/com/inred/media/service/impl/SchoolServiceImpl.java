package com.inred.media.service.impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.inred.media.exception.ProParamException;
import com.inred.media.model.SchoolState;
import com.inred.media.pojo.School;
import com.inred.media.service.SchoolServiceI;
@Service
public class SchoolServiceImpl extends BaseServiceImpl<School> implements SchoolServiceI {
	

//	@Override
//	public void addSchool(School school) {
//		super.
//		
//	}
//
	@Override
	public School findSchoolBySchoolIds(String schoolIds) throws ProParamException {
		if(StringUtils.isEmpty(schoolIds)){
			throw new ProParamException("查询学校时schoolIds为空");
		}
		Query query =new Query();
		query.addCriteria(Criteria.where("coredIds").is(schoolIds))
		.addCriteria(Criteria.where("state").is(SchoolState.NORMALRUN));
		return baseDaoI.findOneByQuery(query, School.class);
	}

}
