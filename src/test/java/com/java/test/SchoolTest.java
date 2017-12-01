/*package com.java.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inred.media.model.SchoolState;
import com.inred.media.pojo.School;
import com.inred.media.service.SchoolServiceI;
import com.inred.media.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-config.xml")
public class SchoolTest {
	@Autowired
	private SchoolServiceI schoolServiceI;
    @Test
	public void addSchoolTest() {
		School school = new School();
		school.setAddress("上海头桥");
		school.setCoredIds("10001");
		school.setArea("上海头桥");
		school.setEmail("cored@163.com");
		school.setEnglishName("cored school");
		school.setName("中赤互联网学校");
		school.setRemark("我们是一家互联网学校");
		String createTime=DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss");
		school.setSchoolCreateTime(createTime);
		school.setShorts("中赤");
		school.setTelPhone("15021917166");
		school.setState(SchoolState.NORMALRUN);
		schoolServiceI.save(school);
	}

}
*/