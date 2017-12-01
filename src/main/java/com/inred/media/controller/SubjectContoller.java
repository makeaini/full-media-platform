package com.inred.media.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inred.media.controller.base.BaseController;
import com.inred.media.pojo.Subject;
import com.inred.media.service.SubjectServiceI;

@Controller
@RequestMapping("/subject")
public class SubjectContoller extends BaseController<Subject> {
	private static final Logger log = LoggerFactory
			.getLogger(SubjectContoller.class);
	@Autowired
	private SubjectServiceI subjectServiceI;
	
	
	//毕需重构父类中的 （updateClasses）信息
	SubjectContoller(){
		super.updateClasses(Subject.class,"admin/subject","subject",100);
	}
	
	
}
