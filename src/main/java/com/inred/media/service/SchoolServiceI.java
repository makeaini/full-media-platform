package com.inred.media.service;

import com.inred.media.exception.ProParamException;
import com.inred.media.pojo.School;

public interface SchoolServiceI  extends BaseServiceI<School> {
//	public void addSchool(School school);
	/**
	 * 通过schoolIds查询school
	 * @param schoolIds
	 * @return
	 */
	public School findSchoolBySchoolIds(String schoolIds)throws ProParamException;

}
