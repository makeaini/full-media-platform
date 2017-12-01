package com.inred.media.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.inred.media.controller.base.BaseController;
import com.inred.media.model.Pagination;
import com.inred.media.pojo.SchoolNotification;
import com.inred.media.pojo.User;
import com.inred.media.service.SchoolNotificationI;
import com.inred.media.util.Constant;

/**
 * 学校通知，学校资源通知
 * @author mac
 *
 */
@Controller
@RequestMapping("/schoolNotification")
public class SchoolNotificationController extends BaseController<SchoolNotification> {
	private static final Logger log = LoggerFactory
			.getLogger(SchoolNotificationController.class);
	@Autowired
	private SchoolNotificationI schoolNotificationI;
	
	
	//毕需重构父类中的 （updateClasses）信息
	SchoolNotificationController(){
		super.updateClasses(SchoolNotification.class,"admin/schoolNotification","schoolNotification",1000);
	}
	
	
	
	@RequestMapping("/list")
	public ModelAndView findlist(
			@RequestParam(value = "pid", defaultValue = "0") String pid,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "1000") Integer pageSize,
			HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/schoolNotification/list");
		// 分页查询数据
		User user=(User) session.getAttribute(Constant.LOGIN_USR_AUTH);//get session
		Pagination<SchoolNotification> pagination = schoolNotificationI.
				findPaginationByQuery(craeteQueryWhere("schoolId",user.getSchoolId()), pageNo, pageSize, SchoolNotification.class);
		System.out.println(pagination.getDatas().size());
		if(pagination==null)
			pagination = new Pagination<SchoolNotification>();
		modelAndView.addObject("pageList", pagination);

		return modelAndView;
	}
	
	
	@RequestMapping("/personalNotifcation")
	public ModelAndView personalNotifcation(
			@RequestParam(value = "pid", defaultValue = "0") String pid,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "1000") Integer pageSize,
			HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("personalNotifcation");
		// 分页查询数据
		User user=(User) session.getAttribute(Constant.LOGIN_USR_AUTH);//get session
		Pagination<SchoolNotification> pagination = schoolNotificationI.
				findPaginationByQuery(craeteQueryWhere("schoolId",user.getSchoolId()), pageNo, pageSize, SchoolNotification.class);
		System.out.println(pagination.getDatas().size());
		if(pagination==null)
			pagination = new Pagination<SchoolNotification>();
		modelAndView.addObject("pageList", pagination);

		return modelAndView;
	}

}
