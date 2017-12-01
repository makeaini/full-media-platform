package com.inred.media.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.portable.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.inred.media.controller.base.BaseController;
import com.inred.media.exception.ProException;
import com.inred.media.exception.ProParamException;
import com.inred.media.exception.ProValiException;
import com.inred.media.model.FileType;
import com.inred.media.model.FileUploadResultBean;
import com.inred.media.model.Pagination;
import com.inred.media.model.PathResultBean;
import com.inred.media.model.ResourcesRole;
import com.inred.media.model.ReturnBean;
import com.inred.media.model.RolePermissions;
import com.inred.media.model.VideoDecoding;
import com.inred.media.pojo.Grade;
import com.inred.media.pojo.MyFile;
import com.inred.media.pojo.Resources;
import com.inred.media.pojo.ResourcesType;
import com.inred.media.pojo.Subject;
import com.inred.media.pojo.User;
import com.inred.media.service.GradeServiceI;
import com.inred.media.service.ResourcesServiceI;
import com.inred.media.service.ResourcesTypeServiceI;
import com.inred.media.service.SubjectServiceI;
import com.inred.media.util.ConfigFileUtil;
import com.inred.media.util.Constant;
import com.inred.media.util.DateUtil;
import com.inred.media.util.DocConverter;
import com.inred.media.util.FileOperationUtil;
import com.inred.media.util.ImageTool;
import com.inred.media.util.PathUtil;
import com.inred.media.util.RandomUtil;
import com.inred.media.util.Task;
import com.inred.media.util.ThreadPool;
import com.inred.media.util.VideoEncodingUtil;
import com.inred.media.util.ZipCompressor;

@Controller
@RequestMapping("/resources")
public class ResourcesController extends BaseController<Resources> {
	@Autowired
	private ResourcesServiceI resourcesServiceI; // 全媒体资源中心类

	@Autowired
	private SubjectServiceI subjectServiceI;// 科目

	@Autowired
	private GradeServiceI gradeServiceI;// 年级

	@Autowired
	private ResourcesTypeServiceI resourcesTypeServiceI;// 资源类型

	private static final Logger LOG = LoggerFactory
			.getLogger(ResourcesController.class);
	
	//毕需重构父类中的 （updateClasses）信息
	ResourcesController(){
		super.updateClasses(Resources.class,"admin/resources","resources",100);
	}
	
	
	/**
	 * 通用的 updateRolePermissions信息
	 * 审核与修改资源的状态。
	 * @param 
	 */
	@RequestMapping("/updateRolePermissions")
	public ModelAndView findlist(String id,String rolePermissions,
			HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:../resources/list");
		Resources resource=this.resourcesServiceI.findOneById(id, Resources.class);
		if(resource!=null){
			resource.setRolePermissions(RolePermissions.valueOf(rolePermissions));
			this.resourcesServiceI.save(resource);
		}
		return modelAndView;
	}
	
	
	/**
	 * 一个一个的上传文件
	 * @param request
	 * @param uid
	 * @param multipartFiles
	 * @return
	 */
	@RequestMapping("/resource-upload")
	@ResponseBody
	public ReturnBean resourcesUpload(
			HttpServletRequest request,
			String uid,
			@RequestParam(value = "file", required = false) MultipartFile[] multipartFiles) {
		ReturnBean returnBean = ReturnBean.returnBeanBuild();
		User user = (User) request.getSession().getAttribute(
				Constant.LOGIN_USR_AUTH);
		// 上传文件的
		MyFile myfile = new MyFile();
		PathResultBean pathResult = null;
		Map<String, MyFile> myfiles = null;
		FileUploadResultBean resultBean = null;
		pathResult = PathUtil.getentErprisePath(user.getSchoolId());
		myfile.setOriginalPath(pathResult.getRelativelyPath());
		LOG.info("当前上传文件到学校目录,上传的绝对路径为:{}", pathResult.getOriginalPath());
		LOG.info("当前上传文件到学校目录,上传的相对路径为:{}", pathResult.getRelativelyPath());
		try {
			resultBean = FileOperationUtil.uploadFile(
					pathResult.getOriginalPath(), multipartFiles);
			if (resultBean != null) {
				if (resultBean.getFileType().equals(FileType.PICTURE)) {
					myfile.setCompressPicName(Constant.PIC_MIN
							+ resultBean.getTargetName());
					ImageTool tool = new ImageTool();
					// 创建缩略图片
					tool.compressPic(pathResult.getOriginalPath()
							+ File.separator, pathResult.getOriginalPath()
							+ File.separator, resultBean.getTargetName(),
							Constant.PIC_MIN + resultBean.getTargetName(), 190,
							140, true);
					
					LOG.info("图片缩略图的路径:{},缩略图的名称:{}",
							pathResult.getOriginalPath(),
							myfile.getCompressPicName());
				}else if(resultBean.getFileType().equals(FileType.VIDEO)||resultBean.getFileType().equals(FileType.MUSIC)){
					myfile.setVideoDec(VideoDecoding.NONE);
				}else if(resultBean.getFileType().equals(FileType.DOCUMENT)){//当上传的附件是文件时，转化为PDF
					//转化为PDF
					DocConverter dc=new DocConverter();
					StringBuffer path=new StringBuffer(pathResult.getOriginalPath());
					path.append(resultBean.getTargetName());
					LOG.info("path:"+path);
					dc.doc2pdf(path.toString());
				}
				myfile.setId(RandomUtil.getUUID());
				myfile.setGenerateName(resultBean.getTargetName());
				myfile.setOriginalName(resultBean.getSourceName());
				myfile.setExtension(resultBean.getExtension());
				myfile.setFileType(resultBean.getFileType());
				myfile.setSchoolId(user.getSchoolId());
				// personalFileServiceI.uploadfile(personalFile);
				myfiles = (Map<String, MyFile>) request.getSession()
						.getAttribute(Constant.TMP_FILE_UPLOAD);
				if (myfiles == null) {
					myfiles = new HashMap<String, MyFile>();
					myfiles.put(uid, myfile);
					request.getSession().setAttribute(Constant.TMP_FILE_UPLOAD,
							myfiles);
				} else {
					myfiles.put(uid, myfile);
					request.getSession().setAttribute(Constant.TMP_FILE_UPLOAD,
							myfiles);
				}

			}
		} catch (IllegalStateException | IOException e) {
			LOG.error("上传文件IO错误");
			LOG.error(e.getMessage(), e);
			returnBean.setReturnCode(Constant.ERROR);
			returnBean.setReturnMsg("上传文件时IO异常");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			returnBean.setReturnCode(Constant.ERROR);
			returnBean.setReturnMsg("上传图片系统异常");
		}
		returnBean.setReturnCode(Constant.SUCCESS);
		returnBean.setReturnMsg("上传图片成功");
		returnBean.setData(myfiles);
		return returnBean;
	}

	@RequestMapping("/remove-myfiles")
	@ResponseBody
	public ReturnBean removeMyfiles(String uid, HttpServletRequest request) {
		ReturnBean returnBean = ReturnBean.returnBeanBuild();
		try {
			if (StringUtils.isEmpty(uid)) {
				throw new ProParamException("移除文件时uid为空");
			}
			Map<String, MyFile> myfiles = (Map<String, MyFile>) request
					.getSession().getAttribute(Constant.TMP_FILE_UPLOAD);
			if (myfiles != null && !myfiles.isEmpty()) {
				LOG.info("删除一个key uid为:{}的文件", uid);
				MyFile mFile = myfiles.remove(uid);
				if (mFile != null) {
					returnBean.setReturnCode(Constant.SUCCESS).setReturnMsg(
							"移除session里面的值成功");
				} else {
					returnBean.setReturnCode(Constant.SUCCESS).setReturnMsg(
							"移除session里面的值,但是没有找到对应的key");
				}

				return returnBean;
			}
		} catch (ProParamException e) {
			LOG.error(e.getErrorCode(), e.getErrorMsg());
			return returnBean.setReturnCode(e.getErrorCode()).setReturnMsg(
					e.getErrorMsg());
		}
		returnBean.setReturnCode(Constant.SUCCESS).setReturnMsg(
				"当前session里面没有任何值所以不用移除");
		return returnBean;
	}

	@RequestMapping("/find-myfiles")
	@ResponseBody
	public ReturnBean findfileValue(HttpServletRequest request) {
		ReturnBean returnBean = ReturnBean.returnBeanBuild();
		Map<String, MyFile> myfiles = (Map<String, MyFile>) request
				.getSession().getAttribute(Constant.TMP_FILE_UPLOAD);
		if (myfiles != null && !myfiles.isEmpty()) {
			return returnBean.setReturnCode(Constant.SUCCESS).setReturnMsg(
					"session里面有值");
		}
		return returnBean.setReturnCode(Constant.ERROR).setReturnMsg(
				"session里面没有值");
	}

	/**
	 * 上传资源
	 * @param resources
	 * @param request
	 * @return
	 */
	@RequestMapping("/resource-save")
	public ModelAndView saveResouces(Resources resources,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, MyFile> myfiles = (Map<String, MyFile>) request
				.getSession().getAttribute(Constant.TMP_FILE_UPLOAD);
		User user = (User) request.getSession().getAttribute(
				Constant.LOGIN_USR_AUTH);
		try {
			if (myfiles == null) {
				throw new ProValiException("session里面的文件值为空，数据错误!");
			}
			if (myfiles.isEmpty()) {
				throw new ProValiException("没有一个文件进行上传!");
			}
			StringBuffer superfileType = new StringBuffer();
			List<MyFile> files = new ArrayList<MyFile>();
			List<MyFile> videomusicfiles=new ArrayList<MyFile>();
			for (Map.Entry<String, MyFile> myfile : myfiles.entrySet()) {
				if(myfile.getValue().getFileType().equals(FileType.VIDEO)||myfile.getValue().getFileType().equals(FileType.MUSIC)){
					videomusicfiles.add(myfile.getValue());
				}
				files.add(myfile.getValue());
				FileType type = PathUtil.getFileType(myfile.getValue()
						.getOriginalName());
				superfileType.append(type.toString()).append(",");
			}
			// 如果没有权限并且选择的是公开，就把状态改成审核状态
			if (user.getResourcesRole().equals(ResourcesRole.NOROLE)
					&& resources.getRolePermissions().equals(
							RolePermissions.WEBSHOW)) {
				resources.setRolePermissions(RolePermissions.EXAMINE);

			}
			
			
			resources.setBrowseNumber(0);
			resources.setDownloadsNumber(0);
			resources.setMyFiles(files);
			resources.setUserid(user.getId());
			resources.setUdpateName(user.getName());
			resources.setFileSuperType(superfileType.toString());
			resources.setYear(String.valueOf(DateUtil.getYear(new Date())));
			resources.setCreateTime(new Date());
			resources.setGrade(this.gradeServiceI.findOneById(resources.getGradeId(),Grade.class));
			resources.setSubject(this.subjectServiceI.findOneById(resources.getSubjectsId(), Subject.class));
			resources.setResourcesType(this.resourcesTypeServiceI.findOneById(resources.getTypeId(), ResourcesType.class));
			resources.setSchoolId(user.getSchoolId());
			resourcesServiceI.save(resources);
			request.getSession().removeAttribute(Constant.TMP_FILE_UPLOAD);
			RedirectView view = new RedirectView("../user/personalCenter");
			modelAndView.setView(view);
			//将视频进行转码操作
			for (MyFile myFile : videomusicfiles) {
				decingVideoAndMusic(myFile,resources.getId());
			}
			return modelAndView;

		} catch (ProValiException e) {
			LOG.error(e.getErrorCode(), e.getErrorMsg());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		RedirectView view = new RedirectView("../user/personalCenter");
		modelAndView.setView(view);
		return modelAndView;
	}
	
	
	private void decingVideoAndMusic(final MyFile myfile, final String resourceId){
		
	          ThreadPool.getInstance().addTask(new Task() {
	            @Override
	            public String info() {
	              LOG.info("当前视频文件开始解码,名称:{}",myfile.getOriginalName());
	              return null;
	            }

	            @Override
	            public void run() {
	            	 String enterprisePath=ConfigFileUtil.getEnterprisePath();
		              String sourcepath=enterprisePath+myfile.getOriginalPath()+myfile.getGenerateName();
		              String filename=null;
		              String targetPath=null;
	               if(PathUtil.getFileType(sourcepath).equals(FileType.VIDEO)&&myfile.getVideoDec().equals(VideoDecoding.NONE)){
	            	   filename=myfile.getGenerateName().substring(0,myfile.getGenerateName().lastIndexOf("."))+Constant.DECODING_EXT_FLV;
	            	   targetPath=enterprisePath+myfile.getOriginalPath()+Constant.VIDEO_DECODING+filename;
	            	   VideoEncodingUtil.encodingToFlv(sourcepath, targetPath);
	               }else if(PathUtil.getFileType(sourcepath).equals(FileType.MUSIC)&&myfile.getVideoDec().equals(VideoDecoding.NONE)){
	            	   filename=myfile.getGenerateName().substring(0,myfile.getGenerateName().lastIndexOf("."))+Constant.DECODING_EXT_MP3;
	            	   targetPath=enterprisePath+myfile.getOriginalPath()+Constant.VIDEO_DECODING+filename;
	            	   VideoEncodingUtil.encodingToMp3(sourcepath, targetPath);
	               }
	                Resources resources=   resourcesServiceI.findOneById(resourceId, Resources.class);
	                if(resources.getMyFiles()!=null&&!resources.getMyFiles().isEmpty()){
	                	for (MyFile file : resources.getMyFiles()) {
	                		if(file.getId().equals(myfile.getId())){
	                		 	Update update = new Update();
	    	                	Query query = new Query();
	    	                	Criteria criteria = Criteria.where("id").is(resourceId);
	    	                	query.addCriteria(criteria);
	    	                	criteria = Criteria.where("myFiles.id").is(myfile.getId());
	    	                	query.addCriteria(criteria);
	    	                	update.set("myFiles.$.videoDec",VideoDecoding.DECODING);
	    	                	resourcesServiceI.updateFirst(query, update, Resources.class);
	                			break;
	                		}
						}
	                }
	              }
	          });
	        }

	// 上传全媒体资源中心界面
	@RequestMapping("/resource-upload-views")
	public ModelAndView resourceUploadViews(HttpServletRequest request) {
		Map<String, MyFile> myfiles = (Map<String, MyFile>) request
				.getSession().getAttribute(Constant.TMP_FILE_UPLOAD);
		if (myfiles != null) {
			request.getSession().removeAttribute(Constant.TMP_FILE_UPLOAD);
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("resources/resources_upload");
		User user = (User) request.getSession().getAttribute(
				Constant.LOGIN_USR_AUTH);// get session
		mav.addObject("listGrade", gradeServiceI.find(
				new Query(), Grade.class));
		mav.addObject("listSubject",
				subjectServiceI.find(
						new Query(),
						Subject.class));
		mav.addObject("listResourcesTypeServiceI", resourcesTypeServiceI.find(
				new Query(),
				ResourcesType.class));
		return mav;
	}

	@RequestMapping("/downloadResource")
	public void downloadResource(String id,HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(id)) {
				throw new ProParamException("下载时参数不能为空");
			}
			Resources resources = resourcesServiceI.findOneById(id,
					Resources.class);
			if (resources == null) {
				throw new ProValiException("下载时，数据错误!");
			}
			String personalPath = ConfigFileUtil.getEnterprisePath();
			int number = resources.getDownloadsNumber()+1;
			resources.setDownloadsNumber(number);
			List<File> files=new ArrayList<File>();
			String fileCommpessPath=ConfigFileUtil.getTempCompressorPath()+File.separator+resources.getName()+ZipCompressor.EXT;
			if (resources.getMyFiles() != null
					&& !resources.getMyFiles().isEmpty()) {
				for (MyFile myfile : resources.getMyFiles()) {
					StringBuffer folderPath = new StringBuffer(personalPath)
							.append(myfile.getOriginalPath()).append(myfile.getGenerateName());
					LOG.info("当前的压缩文件的路径:{}", folderPath);
					File file=new File(folderPath.toString());
					if(file.exists()){
						files.add(file);
					}
					
				}
				ZipCompressor.compessFiles(files,fileCommpessPath);
				// 下载临时文件夹下面的压缩包
				File file = FileOperationUtil.downloadFile(response,
						resources.getName()+ZipCompressor.EXT,
						fileCommpessPath);
				// 删除临时目录下面的文件
				if (file != null) {
					file.delete();
					file = null;
				}
			}
			resourcesServiceI.save(resources);
		} catch (ProException e) {
			LOG.error(e.getErrorCode(), e.getErrorMsg());
		}catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}

	}

}
