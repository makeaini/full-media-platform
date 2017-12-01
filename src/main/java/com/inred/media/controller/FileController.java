package com.inred.media.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inred.media.exception.ProValiException;
import com.inred.media.model.FileType;
import com.inred.media.model.ReturnBean;
import com.inred.media.model.VideoDecoding;
import com.inred.media.pojo.MyFile;
import com.inred.media.pojo.PersonalFile;
import com.inred.media.pojo.Resources;
import com.inred.media.pojo.User;
import com.inred.media.service.MyFilesServiceI;
import com.inred.media.service.PersonalFileServiceI;
import com.inred.media.service.ResourcesServiceI;
import com.inred.media.util.ConfigFileUtil;
import com.inred.media.util.Constant;
import com.inred.media.util.FileOperationUtil;
import com.inred.media.util.ResponseTools;
import com.inred.media.util.ZipCompressor;

@Controller
@RequestMapping("/file")
public class FileController {
	@Autowired
	private PersonalFileServiceI personalFileServiceI;
	@Autowired
	private ResourcesServiceI resourcesServiceI;
	@Autowired
	private MyFilesServiceI filesServiceI;

	private static final Logger LOG = LoggerFactory
			.getLogger(FileController.class);



	/**
	 * 取當前的缩略图片
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping("/getThumbnailsImage/{id}")
	public void getThumbnailsImage(@PathVariable(value = "id") String id,
			HttpServletResponse response, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(
				Constant.LOGIN_USR_AUTH);
		try {
			PersonalFile personalFile = personalFileServiceI
					.queryPersonalFileByIdAndUserId(id, user.getId());
			if (personalFile == null) {
				throw new ProValiException(Constant.ERROR, "数据错误");
			}
			if (personalFile.getFileType().equals(FileType.PICTURE)) {
				String personalPath = new String(ConfigFileUtil
						.getPersonalPath().getBytes("ISO8859-1"), "utf-8");
				StringBuffer buffer = new StringBuffer(personalPath);
				if (!StringUtils.isEmpty(personalFile.getOriginalPath())) {
					buffer.append(personalFile.getOriginalPath()).append(
							File.separator);
				}
				buffer.append(personalFile.getCompressPicName());
				LOG.info("访问缩略图片的路径：{}", buffer.toString());
				File file = new File(buffer.toString());
				FileInputStream is = new FileInputStream(file);
				ResponseTools.responsePicture(response, is);
			}
		} catch (ProValiException e) {
			LOG.error(e.getErrorCode(), e.getErrorMsg());
		} catch (FileNotFoundException e) {
			LOG.error("当前的文件不存在!");
			LOG.error(e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			LOG.error(e.getMessage(), e);
		}

	}

	/**
	 * 獲取原始圖片
	 * 
	 * @param id
	 *            當前圖片的id
	 * @param response
	 */
	@RequestMapping("/getOriginalImage/{id}")
	public void getOriginalImage(@PathVariable(value = "id") String id,
			HttpServletResponse response, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(
				Constant.LOGIN_USR_AUTH);
		try {
			PersonalFile personalFile = personalFileServiceI
					.queryPersonalFileByIdAndUserId(id, user.getId());
			if (personalFile == null) {
				throw new ProValiException(Constant.ERROR, "数据错误");
			}
			if (personalFile.getFileType().equals(FileType.PICTURE)) {
				String personalPath = new String(ConfigFileUtil
						.getPersonalPath().getBytes("ISO8859-1"), "utf-8");
				StringBuffer buffer = new StringBuffer(personalPath);
				if (!StringUtils.isEmpty(personalFile.getOriginalPath())) {
					buffer.append(personalFile.getOriginalPath()).append(
							File.separator);
				}
				buffer.append(personalFile.getGenerateName());
				LOG.info("访问原图片的路径：{}", buffer.toString());
				File file = new File(buffer.toString());
				FileInputStream is = new FileInputStream(file);
				ResponseTools.responsePicture(response, is);
			}
		} catch (ProValiException e) {
			LOG.error(e.getErrorCode(), e.getErrorMsg());
		} catch (FileNotFoundException e) {
			LOG.error("当前的文件不存在!");
			LOG.error(e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	/**
	 * 企业獲取原始圖片
	 * 
	 * @param id
	 *            當前圖片的id
	 * @param response
	 */
	@RequestMapping("/getbusinessOriginalImage")
	public void getBsinessOriginalImage(String resourceId, String fileId,
			HttpServletResponse response, HttpServletRequest request) {
		try {
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
				throw new ProValiException(Constant.ERROR, "数据错误");
			}
			if (myFile.getFileType().equals(FileType.PICTURE)) {
				String personalPath = new String(
						ConfigFileUtil.getEnterprisePath());
				StringBuffer buffer = new StringBuffer(personalPath);
				if (!StringUtils.isEmpty(myFile.getOriginalPath())) {
					buffer.append(myFile.getOriginalPath()).append(
							File.separator);
				}
				buffer.append(myFile.getGenerateName());
				LOG.info("访问原图片的路径：{}", buffer.toString());
				File file = new File(buffer.toString());
				FileInputStream is = new FileInputStream(file);
				ResponseTools.responsePicture(response, is);
			}
		} catch (ProValiException e) {
			LOG.error(e.getErrorCode(), e.getErrorMsg());
		} catch (FileNotFoundException e) {
			LOG.error("当前的文件不存在!");
			LOG.error(e.getMessage(), e);
		}
	}

	/**
	 * 取當前的缩略图片
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping("/getBusinessThumbnailsImage")
	public void getBussinessThumbnailsImage(String resourceId, String fileId,
			HttpServletResponse response, HttpServletRequest request) {

		try {
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
				throw new ProValiException(Constant.ERROR, "数据错误");
			}
			if (myFile.getFileType().equals(FileType.PICTURE)) {
				String personalPath = new String(
						ConfigFileUtil.getEnterprisePath());
				StringBuffer buffer = new StringBuffer(personalPath);
				if (!StringUtils.isEmpty(myFile.getOriginalPath())) {
					buffer.append(myFile.getOriginalPath());
				}
				buffer.append(myFile.getCompressPicName());
				LOG.info("访问缩略图片的路径：{}", buffer.toString());
				File file = new File(buffer.toString());
				FileInputStream is = new FileInputStream(file);
				ResponseTools.responsePicture(response, is);
			}
		} catch (ProValiException e) {
			LOG.error(e.getErrorCode(), e.getErrorMsg());
		} catch (FileNotFoundException e) {
			LOG.error("当前的文件不存在!");
			LOG.error(e.getMessage(), e);
		}

	}

	/**
	 * 下载企业文件单个文件
	 * 
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping("/downloadBusinessFile")
	public void dowloadBusinessFile(String resourceId, String fileId,
			HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("multipart/form-data");
		try {
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
				throw new ProValiException(Constant.ERROR, "数据错误");
			}
			String personalPath = new String(ConfigFileUtil.getEnterprisePath());
			StringBuffer buffer = new StringBuffer(personalPath)
					.append(myFile.getOriginalPath()).append(File.separator)
					.append(myFile.getGenerateName());
			System.out.println(myFile.getOriginalName());
			LOG.info("下载文件的路径：{}", buffer.toString());
			File file = FileOperationUtil.downloadFile(response,
					myFile.getOriginalName(), buffer.toString());
			if (file != null) {
				file = null;
			}
		} catch (ProValiException e) {
			LOG.error(e.getErrorCode(), e.getErrorMsg());
		} catch (Exception e) {
			LOG.error("下载文件夹时压缩异常!");
			LOG.error(e.getMessage(), e);

		}
	}
	
	
	
	/**
	 * 在线浏览
	 * 
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping("/onlineBrowse/{id}")
	public void onlineBrowse(@PathVariable(value = "id") String id,
			HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("multipart/form-data");
		User user = (User) request.getSession().getAttribute(
				Constant.LOGIN_USR_AUTH);
		try {
			//在数据库中查找到当前的数据信息
			PersonalFile personalFile = personalFileServiceI
					.queryPersonalFileByIdAndUserId(id, user.getId());
			if (personalFile == null) {
				throw new ProValiException(Constant.ERROR, "数据错误");
			}
			LOG.info(personalFile.toString());
			
			PersonalFile SPFile=new PersonalFile();
			
		
			if(personalFile!=null && !personalFile.getParentId().equals("rootPath")){
				//找到父ID的地址
				SPFile = personalFileServiceI.getPersonalFileById(personalFile.getParentId());
			}
		
			String personalPath = new String(ConfigFileUtil.getPersonalPath()
					.getBytes("ISO8859-1"), "utf-8");
			

			response.setContentType("application/pdf;charset=UTF-8");
			String filename=personalFile.getGenerateName().substring(0,personalFile.getGenerateName().lastIndexOf("."))+".pdf";	
			
			String pathtemp="";
			if(SPFile.getOriginalPath().equals(null))
				pathtemp=personalFile.getOriginalPath();
			else
				pathtemp=SPFile.getOriginalPath();
			
			
			StringBuffer buffer = new StringBuffer(personalPath+File.separator)
			.append(pathtemp).append(File.separator)
			.append(filename);
			File file = FileOperationUtil.downloadFile(response,
					filename, buffer.toString(),true);
			if (file != null) {
				file = null;
			}
			return;
		
			
			
		} catch (ProValiException e) {
			LOG.error(e.getErrorCode(), e.getErrorMsg());
		} catch (Exception e) {
			LOG.error("下载文件夹时压缩异常!");
			LOG.error(e.getMessage(), e);

		}
	}

	/**
	 * 下载文件或文件夹里面的文件
	 * 
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping("/downloadFile/{id}")
	public void dowloadFile(@PathVariable(value = "id") String id,
			HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("multipart/form-data");
		User user = (User) request.getSession().getAttribute(
				Constant.LOGIN_USR_AUTH);
		try {
			PersonalFile personalFile = personalFileServiceI
					.queryPersonalFileByIdAndUserId(id, user.getId());
			if (personalFile == null) {
				throw new ProValiException(Constant.ERROR, "数据错误");
			}
			String personalPath = new String(ConfigFileUtil.getPersonalPath()
					.getBytes("ISO8859-1"), "utf-8");
			if (personalFile.getFileType().equals(FileType.FOLDER)) {
				StringBuffer folderPath = new StringBuffer(personalPath)
						.append(personalFile.getOriginalPath());
				// 压缩文件到临时目录下面
				String tempPath = new String(ConfigFileUtil
						.getTempCompressorPath().getBytes("iso8859-1"), "utf-8");
				LOG.info("当前的压缩文件的路径:{}", folderPath);
				String zipFile = tempPath + File.separator
						+ personalFile.getOriginalName() + ZipCompressor.EXT;
				ZipCompressor.compress(folderPath.toString(), zipFile);
				// 下载临时文件夹下面的压缩包
				File file = FileOperationUtil.downloadFile(response,
						personalFile.getOriginalName() + ZipCompressor.EXT,
						zipFile);
				// 删除临时目录下面的文件
				if (file != null) {
					file.delete();
					file = null;
				}
			} else {
				StringBuffer buffer = new StringBuffer(personalPath)
						.append(personalFile.getOriginalPath())
						.append(File.separator)
						.append(personalFile.getGenerateName());
				LOG.info("下载文件的路径：{}", buffer.toString());
				File file = FileOperationUtil.downloadFile(response,
						personalFile.getOriginalName(), buffer.toString());
				if (file != null) {
					file = null;
				}
			}
		} catch (ProValiException e) {
			LOG.error(e.getErrorCode(), e.getErrorMsg());
		} catch (Exception e) {
			LOG.error("下载文件夹时压缩异常!");
			LOG.error(e.getMessage(), e);

		}
	}

	@RequestMapping("/personalVideoPlay/{id}")
	public void videoPlay(@PathVariable(value = "id") String id,
			HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute(
				Constant.LOGIN_USR_AUTH);
		try {
			PersonalFile personalFile = personalFileServiceI
					.queryPersonalFileByIdAndUserId(id, user.getId());
			if (personalFile == null) {
				throw new ProValiException(Constant.ERROR, "数据错误");
			}
			String personalPath = new String(ConfigFileUtil.getPersonalPath()
					.getBytes("ISO8859-1"), "utf-8");
			
			if (personalFile.getFileType().equals(FileType.VIDEO)) {
				if (personalFile.getVideoDec().equals(VideoDecoding.NONE)) {
					ReturnBean returnBean = ReturnBean.returnBeanBuild()
							.setReturnCode(Constant.ERROR)
							.setReturnMsg("当前视频还没有转码完成不能进播放");
					response.getWriter().println(returnBean);
					response.getWriter().close();
					return;
				}
			}else if (personalFile.getFileType().equals(FileType.MUSIC)) {
					if (personalFile.getVideoDec().equals(VideoDecoding.NONE)) {
						ReturnBean returnBean = ReturnBean.returnBeanBuild()
								.setReturnCode(Constant.ERROR)
								.setReturnMsg("当前音乐还没有转码完成不能进播放");
						response.getWriter().println(returnBean);
						response.getWriter().close();
						return;
					}
				}
			
			String filename=null;
			if(personalFile.getFileType().equals(FileType.MUSIC)){
				response.setContentType("audio/mpeg");
				filename=Constant.VIDEO_DECODING+personalFile.getGenerateName().substring(0,personalFile.getGenerateName().lastIndexOf("."))+Constant.DECODING_EXT_MP3;	
			}else if(personalFile.getFileType().equals(FileType.VIDEO)) {
				response.setContentType("video/mpeg");
				filename=Constant.VIDEO_DECODING+personalFile.getGenerateName().substring(0,personalFile.getGenerateName().lastIndexOf("."))+Constant.DECODING_EXT_FLV;	
			}
				StringBuffer buffer = new StringBuffer(personalPath)
						.append(personalFile.getOriginalPath())
						.append(File.separator)
						.append(filename);
				LOG.info("下载文件的路径：{}", buffer.toString());
				File file = FileOperationUtil.downloadFile(response,
						personalFile.getOriginalName(), buffer.toString());
				if (file != null) {
					file = null;
			}
		} catch (ProValiException e) {
			LOG.error(e.getErrorCode(), e.getErrorMsg());
		} catch (Exception e) {
			LOG.error("播放视频或者音乐时异常");
			LOG.error(e.getMessage(), e);
		}
	}

	@RequestMapping("/resourcesVideoPlay")
	public void resourcesVideoPlay(String resourceId, String fileId,
			HttpServletRequest request, HttpServletResponse response) {
		try {
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
				throw new ProValiException(Constant.ERROR, "数据错误");
			}
			String personalPath = new String(ConfigFileUtil.getEnterprisePath());

			if (myFile.getFileType().equals(FileType.VIDEO)) {
				if (myFile.getVideoDec().equals(VideoDecoding.NONE)) {
					ReturnBean returnBean = ReturnBean.returnBeanBuild()
							.setReturnCode(Constant.ERROR)
							.setReturnMsg("当前视频还没有转码完成不能进播放");
					response.getWriter().println(returnBean);
					response.getWriter().close();
					return;
				}
			}else if (myFile.getFileType().equals(FileType.MUSIC)) {
					if (myFile.getVideoDec().equals(VideoDecoding.NONE)) {
						ReturnBean returnBean = ReturnBean.returnBeanBuild()
								.setReturnCode(Constant.ERROR)
								.setReturnMsg("当前音乐还没有转码完成不能进播放");
						response.getWriter().println(returnBean);
						response.getWriter().close();
						return;
					}
				}
				String filename=null;
				LOG.info("文件类型："+myFile.getFileType());
				if(myFile.getFileType().equals(FileType.MUSIC)){
					response.setContentType("audio/mpeg");
					filename=Constant.VIDEO_DECODING+myFile.getGenerateName().substring(0,myFile.getGenerateName().lastIndexOf("."))+Constant.DECODING_EXT_MP3;	
				}else if(myFile.getFileType().equals(FileType.VIDEO)) {
					response.setContentType("video/mpeg");
					filename=Constant.VIDEO_DECODING+myFile.getGenerateName().substring(0,myFile.getGenerateName().lastIndexOf("."))+Constant.DECODING_EXT_FLV;	
				}else if(myFile.getFileType().equals(FileType.DOCUMENT)){
					response.setContentType("application/pdf;charset=UTF-8");
					filename=myFile.getGenerateName().substring(0,myFile.getGenerateName().lastIndexOf("."))+".pdf";	
					
					StringBuffer buffer = new StringBuffer(personalPath)
					.append(myFile.getOriginalPath())
					.append(filename);
					File file = FileOperationUtil.downloadFile(response,
							filename, buffer.toString(),true);
					if (file != null) {
						file = null;
					}
					return;
				}
				StringBuffer buffer = new StringBuffer(personalPath)
						.append(myFile.getOriginalPath())
						
						.append(filename);
				LOG.info("文件播放的路径：{}", buffer.toString());
				
				File file = FileOperationUtil.downloadFile(response,
						myFile.getOriginalName(), buffer.toString());
				if (file != null) {
					file = null;
				}
		} catch (ProValiException e) {
			LOG.error(e.getErrorCode(), e.getErrorMsg());
		} catch (Exception e) {
			LOG.error("查看视频或者音乐时异常!");
			LOG.error(e.getMessage(), e);
		}
	}

	/*
	 * private HttpServletResponse setHttpContextType(HttpServletResponse
	 * response,String ext){ switch (ext) { case ".wmv":
	 * response.setContentType(type); break;
	 * 
	 * default: break; }
	 * 
	 * return null; }
	 */

}
