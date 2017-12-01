package com.inred.media.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.inred.media.controller.base.BaseController;
import com.inred.media.exception.ProException;
import com.inred.media.exception.ProParamException;
import com.inred.media.exception.ProValiException;
import com.inred.media.model.CommentAudit;
import com.inred.media.model.FileType;
import com.inred.media.model.Pagination;
import com.inred.media.model.QueryResourcesBean;
import com.inred.media.model.ReturnBean;
import com.inred.media.model.RolePermissions;
import com.inred.media.model.VideoDecoding;
import com.inred.media.pojo.Comment;
import com.inred.media.pojo.Grade;
import com.inred.media.pojo.MyFile;
import com.inred.media.pojo.Resources;
import com.inred.media.pojo.ResourcesType;
import com.inred.media.pojo.School;
import com.inred.media.pojo.Subject;
import com.inred.media.pojo.User;
import com.inred.media.service.GradeServiceI;
import com.inred.media.service.ResourcesServiceI;
import com.inred.media.service.ResourcesTypeServiceI;
import com.inred.media.service.SchoolServiceI;
import com.inred.media.service.SubjectServiceI;
import com.inred.media.util.ConfigFileUtil;
import com.inred.media.util.Constant;

/**
 * 此类做全媒体资源中心（查找）功能
 * 
 * @author mac
 *
 */
@Controller
@RequestMapping("/resourcesCenter")
public class ResourcesCenterController extends BaseController<Resources> {

	private static final Logger LOG = LoggerFactory
			.getLogger(ResourcesCenterController.class);
	@Autowired
	private ResourcesServiceI resourcesServiceI; // 全媒体资源中心类

	@Autowired
	private SubjectServiceI subjectServiceI;// 科目

	@Autowired
	private GradeServiceI gradeServiceI;// 年级

	@Autowired
	private ResourcesTypeServiceI resourcesTypeServiceI;// 资源类型

	@Autowired
	private SchoolServiceI schoolServiceI;// 学校

	// 上传全媒体资源中心界面
	@RequestMapping("/center")
	public ModelAndView resourceUploadViews(HttpServletRequest request,
			QueryResourcesBean queryResourceBean) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("resources/resources_center_select");
		

		// 学校
		mav.addObject("listSchool",
				schoolServiceI.find(new Query(), School.class));
		// 年级
		mav.addObject("listGrade", gradeServiceI.find(new Query(), Grade.class));
		// 科目
		mav.addObject("listSubject",
				subjectServiceI.find(new Query(), Subject.class));
		// 资源类别
		mav.addObject("listResourcesType",
				resourcesTypeServiceI.find(new Query(), ResourcesType.class));
		// 资源类型
		mav.addObject("listType", getResourcesType());
		// 日期
		mav.addObject("listYear", getResourcesYear());

		LOG.info("value:" + queryResourceBean.getResources());

		Query query = new Query();
		// query.addCriteria(Criteria.where("schoolId").is(user.getSchoolId()));
		LOG.info(queryResourceBean.toString());

		if (!StringUtils.isEmpty(request.getSession().getAttribute(
				Constant.LOGIN_USR_AUTH))) {// 当用户的登录信息不为空时
			// 查看公开资源
			User user = (User) request.getSession().getAttribute(
					Constant.LOGIN_USR_AUTH);// get session
			// 登录成功的用户可以查看学校非公开的内容，如果查看的不是自已学校只能查看非公开的内容。
			if (!StringUtils.isEmpty(queryResourceBean.getSchool())) {// 学校不等于空
				query.addCriteria(Criteria.where("schoolId").is(
						queryResourceBean.getSchool()));
			} else {// 如果学校等于空 (全部)
				Criteria criteria = new Criteria();
				criteria.orOperator(
						Criteria.where("schoolId")
								.ne(user.getSchoolId())
								.andOperator(
										Criteria.where("rolePermissions").is(
												RolePermissions.WEBSHOW)),
						Criteria.where("schoolId").is(user.getSchoolId()));
				query.addCriteria(criteria);
			}

			if (!StringUtils.isEmpty(queryResourceBean.getSchool())
					&& !queryResourceBean.getSchool()
							.equals(user.getSchoolId())) {
				query.addCriteria(Criteria.where("rolePermissions").is(
						RolePermissions.WEBSHOW));
			}
		} else {
			// 当用户没有登录时，只能查看所有学校的公开资源

			query.addCriteria(Criteria.where("rolePermissions").is(
					RolePermissions.WEBSHOW));
			if (!StringUtils.isEmpty(queryResourceBean.getSchool()))
				query.addCriteria(Criteria.where("schoolId").is(
						queryResourceBean.getSchool()));
		}

		if (!StringUtils.isEmpty(queryResourceBean.getResources()))
			query.addCriteria(Criteria.where("typeId").is(
					queryResourceBean.getResources()));

		if (!StringUtils.isEmpty(queryResourceBean.getGrade()))
			query.addCriteria(Criteria.where("gradeId").is(
					queryResourceBean.getGrade()));

		if (!StringUtils.isEmpty(queryResourceBean.getSubject()))
			query.addCriteria(Criteria.where("subjectsId").is(
					queryResourceBean.getSubject()));

		if (!StringUtils.isEmpty(queryResourceBean.getYear()))
			query.addCriteria(Criteria.where("year").is(
					queryResourceBean.getYear()));

		if (!StringUtils.isEmpty(queryResourceBean.getResourcesType()))
			query.addCriteria(Criteria.where("fileSuperType").regex(
					queryResourceBean.getResourcesType().toUpperCase()));

		// 排序
		if (!StringUtils.isEmpty(queryResourceBean.getSortCol())) {
			if (queryResourceBean.getSortCol().equals("downloadsNumberASE"))
				query.with(new Sort(Sort.Direction.DESC, "downloadsNumber"));
			if (queryResourceBean.getSortCol().equals("browseNumberASE"))// 按热门
				query.with(new Sort(Sort.Direction.DESC, "browseNumber"));
			if (queryResourceBean.getSortCol().equals("uploadTimeASE"))// 按上传时间
				query.with(new Sort(Sort.Direction.DESC, "createTime"));
		}
		//按名字搜索
		if(!StringUtils.isEmpty(queryResourceBean.getName())){
			LOG.info(queryResourceBean.getName());
			Criteria criteriaName = new Criteria();
			criteriaName.orOperator(
					Criteria.where("name").regex(queryResourceBean.getName()),
					Criteria.where("label").regex(queryResourceBean.getName()));
			
			query.addCriteria(criteriaName);
			mav.addObject("testInput", queryResourceBean.getName());
		}

		LOG.info("query:" + query.toString());
		Pagination<Resources> pagination = resourcesServiceI
				.findPaginationByQuery(query, queryResourceBean.getPageNo(),
						queryResourceBean.getPageSize(), Resources.class);
		mav.addObject("pageList", pagination);

		mav.addObject("url", urlEnc(request.getParameterMap()));

		return mav;
	}

	private String urlEnc(Map<String, String[]> httpUrl) {
		StringBuffer urlparams = new StringBuffer("");

		Map<String, String> mapUrl = new HashMap<String, String>();

		if (httpUrl != null && !httpUrl.isEmpty()) {
			for (Entry<String, String[]> entry : httpUrl.entrySet()) {
				if (entry.getKey().equals("1")) {
					continue;
				}
				// 存MAP
				for (String obj : entry.getValue()) {
					mapUrl.put(entry.getKey(), obj);
				}
			}
		}
		// 地址处理
		for (Entry<String, String> map : mapUrl.entrySet()) {
			urlparams.append("&").append(map.getKey() + "=")
					.append(map.getValue());
		}

		LOG.info("参数URL：" + urlparams);
		return urlparams.toString();
	}

	/**
	 * 查看某件资源的详细
	 * 
	 * @return
	 */
	@RequestMapping("/getResourceDetail")
	public ModelAndView getResourceDetail(String resourceId) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			if (resourceId == null) {
				throw new ProParamException("查询资源详细时resourceId为空");
			}
			Resources resources = resourcesServiceI.findOneById(resourceId,
					Resources.class);
			if (resources == null) {
				throw new ProValiException("查看资源详细时数据错误");
			}
			int browseNumber = resources.getBrowseNumber() + 1;
			resources.setBrowseNumber(browseNumber);
			resourcesServiceI.save(resources);
			// 跟这个人有关的
			Pagination<Resources> peopleResource = null;
			Query query = new Query();
			query.addCriteria(Criteria.where("userid")
					.is(resources.getUserid()));
			query.with(new Sort(Sort.Direction.DESC, "createTime"));
			peopleResource = resourcesServiceI.findPaginationByQuery(query, 1,
					5, Resources.class);
			modelAndView.addObject("peopleResource", peopleResource);
			// 按资源类型查询
			Pagination<Resources> typeResources = null;
			query = new Query();
			query.addCriteria(Criteria.where("typeId")
					.is(resources.getTypeId()));
			query.with(new Sort(Sort.Direction.DESC, "createTime"));
			typeResources = resourcesServiceI.findPaginationByQuery(query, 1,
					5, Resources.class);
			modelAndView.addObject("typeResources", typeResources);
			// 最新资源
			Pagination<Resources> newResources = null;
			query = new Query();
			query.with(new Sort(Sort.Direction.DESC, "createTime"));
			newResources = resourcesServiceI.findPaginationByQuery(query, 1, 5,
					Resources.class);
			modelAndView.addObject("newResources", newResources);
			// 当前这个资源的详细
			modelAndView.addObject("resources", resources);
			// 查询所有的评论
		} catch (ProParamException e) {
			LOG.error(e.getErrorCode(), e.getErrorMsg());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		modelAndView.setViewName("resources/resources_detail");
		return modelAndView;
	}

	/**
	 * 添加评论
	 * 
	 * @param resourcesId
	 * @param message
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveComment")
	public ModelAndView saveComment(String resourceId, String type,
			String message, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		User user = (User) request.getSession().getAttribute(
				Constant.LOGIN_USR_AUTH);
		try {
			if (resourceId == null) {
				throw new ProParamException("添加评论时,resourceId为空");
			}
			Resources resources = resourcesServiceI.findOneById(resourceId,
					Resources.class);
			if (resources == null) {
				throw new ProValiException("查看资源详细时数据错误");
			}
			String uername = Constant.NIKE_NAME;
			if (user == null) {
				uername = Constant.NIKE_NAME;
			} else {
				if ("2".equals(type)) {
					uername = user.getName();
				}
			}
			List<Comment> comments = resources.getComments();
			if (comments == null) {
				comments = new ArrayList<Comment>();
			}
			Comment comment = new Comment();
			comment.setAudit(CommentAudit.NOAUDIT);
			comment.setId(String.valueOf(System.currentTimeMillis()));
			comment.setCommentdate(new Date());
			comment.setName(uername);
			comment.setMessage(message);
			comments.add(comment);
			resources.setComments(comments);
			resourcesServiceI.save(resources);
		} catch (ProException e) {
			LOG.error(e.getErrorCode(), e.getErrorMsg());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		RedirectView view = new RedirectView(
				"../resourcesCenter/getResourceDetail?resourceId=" + resourceId);
		modelAndView.setView(view);
		return modelAndView;
	}
	
	/**
	 * 个人上传的历史资源记录信息
	 * @return
	 */
	@RequestMapping("/getPersionalRecord")
	public ModelAndView getResourceDetail(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			User user=(User) request.getSession().getAttribute(Constant.LOGIN_USR_AUTH);//get session
			List<Resources> list=this.resourcesServiceI.find(craeteQueryWhere("userid",user.getId()), Resources.class);
			
			modelAndView.addObject("listResources",list);
		}catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
		modelAndView.setViewName("resources/resources_personal_record");
		return modelAndView;
	}

	// 自动添加各类文件类型：文件，视频
	public Map<String, String> getResourcesType() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("video", "视频");
		map.put("picture", "图片");
		map.put("document", "文档");
		map.put("music", "音频");
		map.put("compression", "压缩文档");
		map.put("other", "其他");
		return map;
	}

	// 自动添加各类文件类型：文件，视频
	public static Map<Integer, String> getResourcesYear() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		Calendar cl = Calendar.getInstance();
		int year = cl.get(Calendar.YEAR);
		for (int i = 0; i <= 3; i++) {
			int key = year--;
			map.put(key, key + "年");
		}
		return map;
	}
	
	@RequestMapping("/playVideoView")
	public ModelAndView playVideoView(String resourceId,String fileId){
		ModelAndView modelAndView=new ModelAndView();
		Query query = new Query(Criteria.where("id").is(resourceId));
		Resources resources = resourcesServiceI.findOneByQuery(query,
				Resources.class);
		MyFile myFile = null;
		for (MyFile file : resources.getMyFiles()) {
			if (file.getId().equals(fileId)) {
				myFile = file;
				break;
			}
		}
		if (myFile == null) {
			try {
				throw new ProValiException(Constant.ERROR, "数据错误");
			} catch (ProValiException e) {
				LOG.error(e.getMessage(),e);
			}
		}
		modelAndView.addObject("file",myFile);
		modelAndView.addObject("resourceId",resourceId);
		modelAndView.setViewName("resources/resource_videoplay");
		return modelAndView;
	}
	
	@RequestMapping("/playmusiclist")
	@ResponseBody
	public ReturnBean playMusicList(String resourceId){
		ReturnBean returnBean=ReturnBean.returnBeanBuild();
		Query query = new Query(Criteria.where("id").is(resourceId));
		Resources resources = resourcesServiceI.findOneByQuery(query,
				Resources.class);
		List<MyFile> musicfiles=new ArrayList<MyFile>();
         if(resources!=null){
    		if(resources.getMyFiles()!=null&&!resources.getMyFiles().isEmpty()){
    			for (MyFile myFile2 : resources.getMyFiles()) {
    				if(myFile2.getFileType().equals(FileType.MUSIC)&&myFile2.getVideoDec().equals(VideoDecoding.DECODING)){
    					musicfiles.add(myFile2);
    				}
    			}
    		} 
         }
		returnBean.setData(musicfiles);
		return returnBean;
	}
   
	
	

}
