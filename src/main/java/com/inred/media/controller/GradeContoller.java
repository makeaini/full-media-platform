package com.inred.media.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.inred.media.controller.base.BaseController;
import com.inred.media.model.SchoolState;
import com.inred.media.pojo.Grade;
import com.inred.media.service.GradeServiceI;

@Controller
@RequestMapping("/grade")
public class GradeContoller extends BaseController<Grade> {
	private static final Logger log = LoggerFactory
			.getLogger(GradeContoller.class);
	@Autowired
	private GradeServiceI gradeServiceI;
	
	
	//毕需重构父类中的 （updateClasses）信息
	GradeContoller(){
		super.updateClasses(Grade.class,"admin/grade","grade",100);
	}
		
	/**
	 * 添加与修改
	 * @param gradeInfo
	 * @return
	 */
	@RequestMapping(value = "/ajaxcreateOrUpdateToFind",method = RequestMethod.POST)
	@ResponseBody
	public String ajaxcreateOrUpdateToFind(@ModelAttribute( " gradeInfo " ) Grade gradeInfo) {

		
		Grade grade = (Grade)gradeInfo;
		log.info("年级信息"+grade.toString());
		try {
			if (grade.getId() == null) {// 添加信息
				gradeServiceI.insert(grade);//添加记录
			} else {// 更新信息
				Grade updateInfo = gradeServiceI.findOneById(grade.getId(), Grade.class);// 查找到当前记录.
					updateInfo=grade;
					gradeServiceI.save(updateInfo);// 更新数据信息
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	
	@RequestMapping(value = "/deletegrade",method = RequestMethod.POST)
	@ResponseBody
	public String deletegrade( Grade gradeInfo) {
		
		Grade grade = (Grade)gradeInfo;
		log.info("删除"+grade.toString());
		
		if(grade!=null){
			Query query =new Query();
			query.addCriteria(Criteria.where("schoolId").is(grade.getSchoolId()))
			.addCriteria(Criteria.where("name").is(grade.getName()));
			log.info(query.toString());
			this.gradeServiceI.findAndRemove(query, Grade.class);
//			this.gradeServiceI.remove(grade2);
		}
		
		
		return "success";
	}
	
	
}
