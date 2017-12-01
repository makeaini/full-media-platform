package com.inred.media.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.inred.media.dao.BaseDaoI;
import com.inred.media.dao.SchoolDaoI;
import com.inred.media.model.SchoolState;
import com.inred.media.pojo.School;
@Repository
public class SchoolDaoImpl implements SchoolDaoI{
	@Autowired
	private BaseDaoI<School> baseDaoI;

	@Override
	public void insertSchool(School school) {
		baseDaoI.save(school);
	}

	@Override
	public School findSchoolBySchoolIds(String schoolIds) {
		Query query =new Query();
		query.addCriteria(Criteria.where("coredIds").is(schoolIds)).addCriteria(Criteria.where("state").is(SchoolState.NORMALRUN));
		return baseDaoI.findOneByQuery(query, School.class);
	}

}
