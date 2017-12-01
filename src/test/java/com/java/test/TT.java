package com.java.test;

import jdk.nashorn.internal.ir.annotations.Ignore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inred.media.pojo.Grade;
import com.inred.media.pojo.Resources;
import com.inred.media.pojo.ResourcesType;
import com.inred.media.pojo.Subject;
import com.inred.media.service.GradeServiceI;
import com.inred.media.service.ResourcesTypeServiceI;
import com.inred.media.service.SubjectServiceI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-config.xml")
public class TT {
	@Autowired
	private ResourcesTypeServiceI resourcesTypeServiceI;

	@Autowired
	private SubjectServiceI subjectServiceI;
	@Autowired
	private GradeServiceI gradeServiceI;

	@Test
	public void resourceTest() {
	/*	ResourcesType resourcesType = new ResourcesType();
		resourcesType.setName("教案");*/
		ResourcesType resourcesType2 = new ResourcesType();
		resourcesType2.setName("文学");
		resourcesTypeServiceI.save(resourcesType2);
	}

	@Test
	@Ignore
	public void subjectTest() {
		Subject subject = new Subject();
		subject.setName("语文");
		Subject subject1 = new Subject();
		subject1.setName("数学");
		subjectServiceI.save(subject);
		subjectServiceI.save(subject1);

	}

	@Test
	@Ignore
	public void gradeTest() {
		Grade subject = new Grade();
		subject.setName("一年级");
		Grade subject1 = new Grade();
		subject1.setName("二年级");
		gradeServiceI.save(subject);
		gradeServiceI.save(subject1);

	}

}
