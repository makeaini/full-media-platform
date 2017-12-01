package com.inred.media.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.inred.media.controller.base.BaseController;
import com.inred.media.exception.ProException;
import com.inred.media.exception.ProParamException;
import com.inred.media.exception.ProValiException;
import com.inred.media.model.Pagination;
import com.inred.media.model.ResourcesRole;
import com.inred.media.model.UserType;
import com.inred.media.pojo.Resources;
import com.inred.media.pojo.School;
import com.inred.media.pojo.SchoolNotification;
import com.inred.media.pojo.User;
import com.inred.media.service.ResourcesServiceI;
import com.inred.media.service.SchoolNotificationI;
import com.inred.media.service.SchoolServiceI;
import com.inred.media.service.UserServiceI;
import com.inred.media.util.Constant;
import com.inred.media.util.FileHashUtil;

@Controller
public class UserController extends BaseController<User> {
	private static final Logger LOG = LoggerFactory
			.getLogger(UserController.class);
	@Autowired
	private UserServiceI userServiceI;
	@Autowired
	private SchoolServiceI schoolServiceI;
	
	
	@Autowired
	private ResourcesServiceI resourcesServiceI; // 全媒体资源中心类
	@Autowired
	private SchoolNotificationI schoolNotificationI;//通知公告
	/**
	 * 添加与更新
	 * 
	 
	 * @return
	 */
	@RequestMapping(value = "/createOrUpdateUserToFind", method = RequestMethod.POST)
	public ModelAndView createOrUpdateUserToFind(User webUser,HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("redirect:/list");
			User sessionUser=(User)session.getAttribute(Constant.LOGIN_USR_AUTH);
			
			webUser.setSchoolId(sessionUser.getSchoolId());
			webUser.setSchooldIds(sessionUser.getSchooldIds());
			webUser.setType(UserType.TEACHERUSER);
			webUser.setResourcesRole(ResourcesRole.NOROLE);//创建的用户，初始化是没有权限的。
			webUser.setPassword(FileHashUtil.stringMD5(webUser.getPassword()));//密码加
		User user = (User)webUser;
		LOG.info(user.toString());
		
		try {
			if (user.getId() == null) {// 添加信息
				userServiceI.insert(user);//添加记录
			} else {// 更新信息
				User updateInfo = userServiceI.findOneById(user.getId(), User.class);// 查找到当前记录.
					updateInfo=user;
					userServiceI.save(updateInfo);// 更新数据信息
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	/**
	 * 通用的 List信息
	 * @param 
	 */
	@RequestMapping("/list")
	public ModelAndView findlist(
			@RequestParam(value = "pid", defaultValue = "0") String pid,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/user/list");
		// 分页查询数据
		User user=(User) session.getAttribute(Constant.LOGIN_USR_AUTH);//get session
		Pagination<User> pagination = userServiceI.findPaginationByQuery(craeteQueryWhere("schoolId",user.getSchoolId()), pageNo, pageSize, User.class);
		if(pagination==null)
			pagination = new Pagination<User>();
		modelAndView.addObject("pageList", pagination);

		return modelAndView;
	}

	// 毕需重构父类中的 （updateClasses）信息
	UserController() {
		super.updateClasses(User.class, "admin/user", "", 100);
	}

	/**
	 * 个人中心
	 */
	@RequestMapping("user/personalCenter")
	public ModelAndView personalCenter(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("personal");// 去登录
		User user=(User) session.getAttribute(Constant.LOGIN_USR_AUTH);
		
		//加载上传的文件数量
		//加载视频的数量
		Integer video=loadResourceNumber("VIDEO",user.getId());
		//加载图片数量
		Integer picture=loadResourceNumber("PICTURE",user.getId());
		//加载文档数量
		Integer document=loadResourceNumber("DOCUMENT",user.getId());
		//加载有几条个人通知信息
		Integer notifcation=this.schoolNotificationI.findCountByQuery(super.craeteQueryWhere("schoolId",user.getSchoolId()), SchoolNotification.class);
		
		mav.addObject("video",video);
		mav.addObject("picture",picture);
		mav.addObject("document",document);
		mav.addObject("notifcation",notifcation);
		return mav;

	}

	/**
	 * 访问首页
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index() {
		//加载视频的数量
		Integer video=loadResourceNumber("VIDEO");
		//加载图片数量
		Integer picture=loadResourceNumber("PICTURE");
		//加载文档数量
		Integer document=loadResourceNumber("DOCUMENT");
		//加载其他数量
		Integer other=loadResourceNumber("OTHER");
		//加载公告通知
		List<SchoolNotification> listNotifcation=this.schoolNotificationI.find(new Query(), SchoolNotification.class);
		
		//加载入信平台的学校
		List<School> listSchool=this.schoolServiceI.find(new Query(), School.class);
		//加载最新上传的资源10条
		Pagination<Resources> listNewResources= this.resourcesServiceI.findPaginationByQuery(new Query(), 1, 10, Resources.class);
		
		
		//返回数据到前台
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		modelAndView.addObject("video",video);
		modelAndView.addObject("picture",picture);
		modelAndView.addObject("document",document);
		modelAndView.addObject("other",other);
		
		modelAndView.addObject("listNotifcation",listNotifcation);
		modelAndView.addObject("listSchool",listSchool);
		modelAndView.addObject("listNewResources",listNewResources);
		return modelAndView;// 返回登录页面
	}
	
	public Integer loadResourceNumber(String type){
		Query query = new Query();
		query.addCriteria(Criteria.where("fileSuperType").regex(type));
		return this.resourcesServiceI.findCountByQuery(query, Resources.class);
	}
	
	public Integer loadResourceNumber(String type,String userid){
		Query query = new Query();
		query.addCriteria(Criteria.where("fileSuperType").regex(type));
		query.addCriteria(Criteria.where("userid").regex(userid));
		
		return this.resourcesServiceI.findCountByQuery(query, Resources.class);
	}
	

	/**
	 * 用户登录
	 * 
	 * @return
	 */
	@RequestMapping("/login")
	public String loginView() {
		return "loginForTeacher";// 返回登录页面
	}
	@RequestMapping("/")
	public String loginView2(){
		return "loginForTeacher";// 返回登录页面
	}
	

	/**
	 * 切换到登录
	 * 
	 * @return
	 */
	@RequestMapping("/Switchlogin")
	public String Switchlogin(String type) {
		if (type.equals("login"))
			return "loginForTeacher";
		else if (type.equals("loginForTeacher"))
			return "login";
		else
			return "errorpage/404";

	}

	/**
	 * 注销登录，返回到登录页面
	 * 
	 * @return
	 */
	@RequestMapping("/loginout")
	public String loginout(HttpSession session) {
		session.invalidate();
		return "redirect:resourcesCenter/center";// 返回登录页面
	}
	/**
	 * 老师帐号登录
	 * 
	 * @param username
	 * @param request
	 * @param schoolIds
	 * @param password
	 * @return
	 */
	@RequestMapping("/loginTeacherinfo")
	public ModelAndView loginInfo(String username, HttpServletRequest request,
			String schoolIds, String password) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("loginForTeacher");
		try {
			if(StringUtils.isEmpty(username)){
				throw new ProParamException("用户名不能为空");
			}
			if (StringUtils.isEmpty(password)) {
				throw new ProParamException("密码不能为空");
			}
			if(StringUtils.isEmpty(schoolIds)){
				throw new ProParamException("学校的编号不能为空");
			}
			String md5Pass = FileHashUtil.stringMD5(password);
			School school = schoolServiceI.findSchoolBySchoolIds(schoolIds);
			if (StringUtils.isEmpty(school)) {
				throw new ProValiException(Constant.ERROR, "没有对应的学校");
			}
			User user = userServiceI.userLogin(username, school.getId());
			if (user != null && md5Pass.equals(user.getPassword())) {
				request.getSession()
						.setAttribute(Constant.LOGIN_USR_AUTH, user);

				RedirectView view = new RedirectView(
						"user/personalCenter");
				modelAndView.setView(view);
				return modelAndView;
			}
		} catch (ProException e) {
			LOG.error(e.getErrorCode(), e.getErrorMsg());
			modelAndView.addObject("loginerror", e.getErrorMsg());
			return modelAndView;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			modelAndView.addObject("loginerror", "系统异常");
			return modelAndView;
		}
	
		modelAndView.setViewName("loginForTeacher");
		modelAndView.addObject("loginerror", "用户名密码不匹配");
		return modelAndView;
	}

		
	/**
	 * 加载 个人资料
	 * @param 
	 */
	@RequestMapping("user/personalData")
	public ModelAndView personalDate(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("personalData");
		//用户信息
		User user=(User) session.getAttribute(Constant.LOGIN_USR_AUTH);
		//学校信息
		School school=this.schoolServiceI.findOneById(user.getSchoolId(), School.class);
		User webUser=this.userServiceI.findOneById(user.getId(), User.class);
		modelAndView.addObject("school", school);
		modelAndView.addObject("user", webUser);

		return modelAndView;
	}

	/**
	 * 用户自己修改－个人信息
	 * 
	 * @return
	 */
	@RequestMapping("user/personalUpdate")
	public ModelAndView personalUpdate(String id,
			String username,String name,String remark,
			String resourcesRole,HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/user/personalData");
		User udpateUser=this.userServiceI.findOneById(id, User.class);
		udpateUser.setUsername(username);
		udpateUser.setName(name);
		udpateUser.setRemark(remark);
		udpateUser.setResourcesRole(ResourcesRole.valueOf(resourcesRole));
		
		this.userServiceI.save(udpateUser);
		
		return modelAndView;

	}
	
	
	/**
	 * 加载 个人密码
	 * @param 
	 */
	@RequestMapping("user/personalLoadPassword")
	public ModelAndView personalLoadPassword(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("personalPassword");
		return modelAndView;
	}
	
	/**
	 * 修改 个人密码
	 * @param 
	 */
	@RequestMapping("user/personalUpdatePassword")
	public ModelAndView personalUpdatePassword(HttpSession session,String id,
			String password,String oldPassword) {
		ModelAndView modelAndView = new ModelAndView();
		
		User user=this.userServiceI.findOneById(id, User.class);
		if(user.getPassword().equals(FileHashUtil.stringMD5(oldPassword))){//验证原始密码是否相等
			modelAndView.setViewName("redirect:/user/personalLoadPassword");
			user.setPassword(FileHashUtil.stringMD5(password));
			this.userServiceI.save(user);//更新个人密码
			modelAndView.addObject("passwordSuccess", "恭喜您,修改密码成功!");
		}else{
			modelAndView.setViewName("personalPassword");
			modelAndView.addObject("passwordError", "原始密码输入出错!");
		}
		
		return modelAndView;
	}

}
