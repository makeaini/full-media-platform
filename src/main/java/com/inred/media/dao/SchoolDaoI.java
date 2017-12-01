package com.inred.media.dao;

import com.inred.media.pojo.School;

public interface SchoolDaoI {
	/**
	 * 添加一个学校
	 * @param school
	 */
	public void insertSchool(School school);
	
	/**
	 * 通过schoolds查询当前的school
	 * @param schoolIds 当前学校的编码
	 */
	public School findSchoolBySchoolIds(String schoolIds);
}
