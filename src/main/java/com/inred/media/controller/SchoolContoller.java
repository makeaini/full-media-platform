package com.inred.media.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.inred.media.controller.base.BaseController;
import com.inred.media.model.Pagination;
import com.inred.media.pojo.School;
import com.inred.media.service.SchoolServiceI;
import com.inred.media.service.UserServiceI;

@Controller
@RequestMapping("/school")
public class SchoolContoller extends BaseController {
	private static final Logger LOG = LoggerFactory
			.getLogger(SchoolContoller.class);
	@Autowired
	private SchoolServiceI schoolServiceI;
	
	
	/**
	 * 添加一个学校信息
	 * @param school
	 */
	public String addSchool(School school,HttpServletRequest request){
	   
		
		return null;
	}
	//毕需重构父类中的 （updateClasses）信息
	SchoolContoller(){
		super.updateClasses(School.class,"admin/school","school");
	}
	
	/**
	 * 查询学校信息
	 * @param school
	 */
//	@RequestMapping("/list")
//	public ModelAndView findlist(
//			@RequestParam(value = "pid", defaultValue = "0") String pid,
//			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
//			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
//			HttpSession session) {
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("admin/school/list");
//		// 分页查询数据
//		Pagination<School> pagination = schoolServiceI.findPaginationByQuery(new Query(), pageNo, pageSize, School.class);
//		System.out.println(pagination.getDatas().size());
//		if(pagination==null)
//			pagination = new Pagination<School>();
//		modelAndView.addObject("pageList", pagination);
//
//		return modelAndView;
//	}
	
	
	/**
	 * 删除与批量删除
	 * 
	 * @param id
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public ModelAndView delete(
			@RequestParam(value = "id", defaultValue = "0") String id,
			@RequestParam(value = "ids", defaultValue = "0") String ids) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/classes/list");
		try {
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	
	@RequestMapping("/updateOrCreate")
	public ModelAndView updateOrCreate(
			@RequestParam(value = "id", defaultValue = "0") String id,
			HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("app-school/classes/editor");
		try {} catch (Exception e) {
			e.printStackTrace();
		}// 以ID查找当前用户信息

		return modelAndView;

	}
	
}
