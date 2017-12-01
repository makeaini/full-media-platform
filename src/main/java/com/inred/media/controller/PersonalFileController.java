package com.inred.media.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.inred.media.exception.ProException;
import com.inred.media.exception.ProParamException;
import com.inred.media.exception.ProValiException;
import com.inred.media.model.FileType;
import com.inred.media.model.FileUploadResultBean;
import com.inred.media.model.Pagination;
import com.inred.media.model.PathResultBean;
import com.inred.media.model.QueryPersonalFileBean;
import com.inred.media.model.ReturnBean;
import com.inred.media.model.VideoDecoding;
import com.inred.media.pojo.PersonalFile;
import com.inred.media.pojo.User;
import com.inred.media.service.PersonalFileServiceI;
import com.inred.media.util.ConfigFileUtil;
import com.inred.media.util.Constant;
import com.inred.media.util.DocConverter;
import com.inred.media.util.FileOperationUtil;
import com.inred.media.util.ImageTool;
import com.inred.media.util.PathUtil;
import com.inred.media.util.RandomUtil;
import com.inred.media.util.Task;
import com.inred.media.util.ThreadPool;
import com.inred.media.util.VideoEncodingUtil;

@Controller
@RequestMapping("/personalfile")
public class PersonalFileController {
	@Autowired
	private PersonalFileServiceI personalFileServiceI;
	private static final Logger LOG = LoggerFactory
			.getLogger(PersonalFileController.class);

	/**
	 * 创建一个文件夹
	 * 
	 * @param personalfile
	 * @return
	 */
	@RequestMapping("/createfolder")
	@ResponseBody
	public ReturnBean createFolderByPersonalFile(PersonalFile personalfile,
			HttpServletRequest request,
			@RequestParam(required = false) String parentid) {
		ReturnBean returnBean = ReturnBean.returnBeanBuild();
		User user = (User) request.getSession().getAttribute(
				Constant.LOGIN_USR_AUTH);
		// 将文件名进行修改
		String folderName = RandomUtil.getUUID();
		PathResultBean pathResult = null;
		// 如果当前parentId不为空，表示有父级,否则根目录建目录
		if (!StringUtils.isEmpty(parentid)) {
			// 先找到父级的路径
			PersonalFile personalfolder = personalFileServiceI
					.getPersonalFileById(parentid);
			if (personalfolder != null) {
				pathResult = PathUtil.getFolderPath(user.getId(),
						user.getSchoolId(), personalfolder.getOriginalPath(),
						folderName);
				personalfile.setParentId(parentid);
			}
		} else {
			personalfile.setParentId(Constant.ROOT_PATH);
			pathResult = PathUtil.getFolderPath(user.getId(),
					user.getSchoolId(), null, folderName);
		}
		// 创建本地文件夹
		if (FileOperationUtil.createLocalFolder(pathResult.getOriginalPath())) {
			personalfile.setOriginalPath(pathResult.getRelativelyPath());
			personalfile.setGenerateName(folderName);
			personalfile.setUserId(user.getId());
			personalfile.setFileSize(Constant.FOLDER_SIZE);
			personalfile.setFileSizeStr(Constant.FOLDER_SIZE_STR);

			personalFileServiceI.createFolder(personalfile);
			returnBean.setReturnCode(Constant.SUCCESS);
			returnBean.setReturnMsg("创建文件夹成功");
			return returnBean;
		}
		returnBean.setReturnCode(Constant.ERROR);
		returnBean.setReturnMsg("创建文件夹成功失败");
		return returnBean;
	}

	/**
	 * 批量删除文件
	 * 
	 * @param fileIds
	 *            删除的文件Ids
	 * @return
	 */
	@RequestMapping("/batchdeletefile")
	@ResponseBody
	public ReturnBean batchDeleteFile(String fileIds) {
		ReturnBean returnBean = ReturnBean.returnBeanBuild();
		try {
			if (StringUtils.isEmpty(fileIds)) {
				throw new ProParamException("删除文件时fileIds参数为空!");
			}
			String localPath = new String(ConfigFileUtil.getPersonalPath()
					.getBytes("iso8859-1"), "utf-8");
			
			String[] ids = fileIds.split(",");
			for (String id : ids) {
				StringBuffer originalPath = new StringBuffer(localPath);
				try {
					PersonalFile personalFile = personalFileServiceI
							.getPersonalFileById(id);
					if (personalFile == null) {
						throw new ProValiException(Constant.ERROR, "删除文件时数据错误!");
					}
						if (personalFile.getFileType().equals(FileType.FOLDER)) {
							// 查询当前文件夹下面是否存在文件夹，或者文件
							boolean exists = personalFileServiceI
									.getExistsPersonalFilebyParentId(personalFile.getId());
							if (exists) {
								returnBean.setReturnCode(Constant.HAS_FILE_ERROR)
										.setReturnMsg("该文件夹下面有子项目，请删除子项目重新操作!");
								return returnBean;
							}
							originalPath.append(personalFile.getOriginalPath());
							LOG.info("当前是删除的文件夹,绝对路径为:{}", originalPath.toString());
							FileOperationUtil
									.deleteLocalFolder(originalPath.toString());
								personalFileServiceI.deletePersonalFilebyId(id);
								returnBean.setReturnCode(Constant.SUCCESS).setReturnMsg(
										"删除成功");
								return returnBean;
						} 
					if (personalFile.getFileType().equals(FileType.PICTURE)) {
						originalPath.append(personalFile.getOriginalPath()).append(File.separator)

						.append(personalFile.getGenerateName());
						LOG.info("删除的原图,绝对路径为:{}", originalPath);
						StringBuffer originalPicMinPath = new StringBuffer(
								localPath)
								.append(personalFile.getOriginalPath()).append(File.separator)
								.append(Constant.PIC_MIN)
								.append(personalFile.getGenerateName());
						LOG.info("删除的缩略图,绝对路径为:{}",
								originalPicMinPath.toString());
						FileOperationUtil.deleteLocalFile(originalPath
								.toString()) ;
						FileOperationUtil.deleteLocalFile(originalPicMinPath
								.toString());
						personalFileServiceI.deletePersonalFilebyId(id);
						
					}else{
					originalPath.append(personalFile.getOriginalPath())
							.append(File.separator)
							.append(personalFile.getGenerateName());
					LOG.info("删除的是文件,绝对路径为:{}", originalPath);
					FileOperationUtil.deleteLocalFile(originalPath
							.toString()) ;
				
						personalFileServiceI.deletePersonalFilebyId(id);
					}
				} catch (ProValiException e) {
					LOG.error(e.getErrorCode(), e.getMessage());
				}
			}
		} catch (ProParamException e) {
			LOG.error(e.getErrorCode(), e.getErrorMsg());
			returnBean.setReturnCode(e.getErrorCode()).setReturnMsg(
					e.getErrorMsg());
			return returnBean;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			returnBean.setReturnCode(Constant.ERROR).setReturnMsg("系统错误");
			return returnBean;
		}
		returnBean.setReturnCode(Constant.SUCCESS);
		returnBean.setReturnMsg("批量删作除文件成功");
		return returnBean;

	}

	/**
	 * 删除文件
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deletefolerorfile")
	@ResponseBody
	public ReturnBean deleteFolderOrFile(String id) {
		ReturnBean returnBean = ReturnBean.returnBeanBuild();
		try {
			if (StringUtils.isEmpty(id)) {
				throw new ProParamException("删除文件时id参数为空!");
			}
			PersonalFile personalFile = personalFileServiceI
					.getPersonalFileById(id);
			if (personalFile == null) {
				throw new ProValiException(Constant.ERROR, "删除文件时数据错误!");
			}
			String localPath = new String(ConfigFileUtil.getPersonalPath()
					.getBytes("iso8859-1"), "utf-8");
			// 文件的绝对路径
			StringBuffer originalPath = new StringBuffer(localPath);
			// 如果是文件夹
			if (personalFile.getFileType().equals(FileType.FOLDER)) {
				// 查询当前文件夹下面是否存在文件夹，或者文件
				boolean exists = personalFileServiceI
						.getExistsPersonalFilebyParentId(personalFile.getId());
				if (exists) {
					returnBean.setReturnCode(Constant.HAS_FILE_ERROR)
							.setReturnMsg("该文件夹下面有子项目，请删除子项目重新操作!");
					return returnBean;
				}
				originalPath.append(personalFile.getOriginalPath());
				LOG.info("当前是删除的文件夹,绝对路径为:{}", originalPath.toString());
				if (FileOperationUtil
						.deleteLocalFolder(originalPath.toString())) {
					personalFileServiceI.deletePersonalFilebyId(id);
					returnBean.setReturnCode(Constant.SUCCESS).setReturnMsg(
							"删除成功");
					return returnBean;
				}
				// 如果是图片
			} else if (personalFile.getFileType().equals(FileType.PICTURE)) {
				originalPath.append(personalFile.getOriginalPath()).append(
						personalFile.getGenerateName());
				LOG.info("删除的原图,绝对路径为:{}", originalPath);
				StringBuffer originalPicMinPath = new StringBuffer(localPath)
						.append(personalFile.getOriginalPath())
						.append(Constant.PIC_MIN)
						.append(personalFile.getGenerateName());

				LOG.info("删除的缩略图,绝对路径为:{}", originalPicMinPath.toString());
				if (FileOperationUtil.deleteLocalFile(originalPath.toString())
						&& FileOperationUtil.deleteLocalFile(originalPicMinPath
								.toString())) {
					personalFileServiceI.deletePersonalFilebyId(id);
					returnBean.setReturnCode(Constant.SUCCESS).setReturnMsg(
							"删除成功");
					return returnBean;
				}
			}else if(personalFile.getFileType().equals(FileType.MUSIC)){
				originalPath.append(personalFile.getOriginalPath()).append(
						personalFile.getGenerateName());
				LOG.info("删除的文件,绝对路径为:{}", originalPath);
			String	filename = personalFile.getGenerateName().substring(0,
					personalFile.getGenerateName().lastIndexOf("."))
						+ Constant.DECODING_EXT_MP3;
				StringBuffer originalOrgPath = new StringBuffer(localPath)
						.append(personalFile.getOriginalPath())
						.append(Constant.VIDEO_DECODING)
						.append(filename);

				LOG.info("删除编码后的文件,绝对路径为:{}", originalOrgPath.toString());
				if (FileOperationUtil.deleteLocalFile(originalPath.toString())
						&& FileOperationUtil.deleteLocalFile(originalOrgPath
								.toString())) {
					personalFileServiceI.deletePersonalFilebyId(id);
					returnBean.setReturnCode(Constant.SUCCESS).setReturnMsg(
							"删除成功");
					return returnBean;
				}
				
				
			}else if(personalFile.getFileType().equals(FileType.VIDEO)){
				originalPath.append(personalFile.getOriginalPath()).append(
						personalFile.getGenerateName());
				LOG.info("删除的文件,绝对路径为:{}", originalPath);
			     String	filename = personalFile.getGenerateName().substring(0,
					personalFile.getGenerateName().lastIndexOf("."))
						+ Constant.DECODING_EXT_FLV;
				StringBuffer originalOrgPath = new StringBuffer(localPath)
						.append(personalFile.getOriginalPath())
						.append(Constant.VIDEO_DECODING)
						.append(filename);
				LOG.info("删除编码后的文件,绝对路径为:{}", originalOrgPath.toString());
				if (FileOperationUtil.deleteLocalFile(originalPath.toString())
						&& FileOperationUtil.deleteLocalFile(originalOrgPath
								.toString())) {
					personalFileServiceI.deletePersonalFilebyId(id);
					returnBean.setReturnCode(Constant.SUCCESS).setReturnMsg(
							"删除成功");
					return returnBean;
			}
		}
				
			originalPath.append(personalFile.getOriginalPath())
					.append(File.separator)
					.append(personalFile.getGenerateName());
			LOG.info("删除的是文件,绝对路径为:{}", originalPath);
			if (FileOperationUtil.deleteLocalFile(originalPath.toString())) {
				personalFileServiceI.deletePersonalFilebyId(id);
				returnBean.setReturnCode(Constant.SUCCESS).setReturnMsg("删除成功");
				return returnBean;
			}
		} catch (ProException e) {
			LOG.error(e.getErrorCode(), e.getErrorMsg());
			returnBean.setReturnCode(e.getErrorCode()).setReturnMsg(
					e.getErrorMsg());
			return returnBean;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			returnBean.setReturnCode(Constant.ERROR).setReturnMsg("系统错误!");
			return returnBean;
		}
		returnBean.setReturnCode(Constant.ERROR).setReturnMsg("删除失败!");
		return returnBean;
	}

	/**
	 * 修改文件的名称
	 * 
	 * @param name
	 * @param id
	 * @return
	 */
	@RequestMapping("/modifypersonalfile")
	@ResponseBody
	public ReturnBean modifyPersonalFileById(String name, String id) {
		ReturnBean returnBean = ReturnBean.returnBeanBuild();
		try {
			if (StringUtils.isEmpty(id)) {
				throw new ProParamException("修改的文件id参数为空!");
			}
			personalFileServiceI.modifyPersonalFileNamebyId(id, name);
		} catch (ProException e) {
			LOG.error(e.getErrorCode(), e.getErrorMsg());
			returnBean.setReturnCode(e.getErrorCode()).setReturnMsg(
					e.getErrorMsg());
			return returnBean;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			returnBean.setReturnCode(Constant.ERROR).setReturnMsg("系统错误!");
			return returnBean;
		}
		returnBean.setReturnCode(Constant.SUCCESS).setReturnMsg("修改成功!");
		return returnBean;
	}

	/**
	 * 上傳圖片
	 * 
	 * @param multipartFiles
	 * @param response
	 * @param request
	 * @param parentId
	 *            是否有父目录
	 * @return
	 */
	@RequestMapping("/uploadpersonalfile")
	@ResponseBody
	public ReturnBean uploadfile(
			@RequestParam(value = "file", required = false) MultipartFile[] multipartFiles,
			HttpServletResponse response, HttpServletRequest request,
			@RequestParam(required = false) String parentId) {
		ReturnBean returnBean = ReturnBean.returnBeanBuild();
		User user = (User) request.getSession().getAttribute(
				Constant.LOGIN_USR_AUTH);
		// 上传文件的
		PersonalFile personalFile = new PersonalFile();
		PathResultBean pathResult = null;
		FileUploadResultBean resultBean = null;
		List<PersonalFile> personalFiles = new ArrayList<PersonalFile>();
		if (!StringUtils.isEmpty(parentId)) {
			// 先找到父级的路径
			PersonalFile personalfolder = personalFileServiceI
					.getPersonalFileById(parentId);
			if (personalfolder != null) {
				pathResult = PathUtil.getFilePath(user.getId(),
						user.getSchoolId(), personalfolder.getOriginalPath());
				personalFile.setOriginalPath(pathResult.getRelativelyPath());
				
				personalFile.setParentId(parentId);
				LOG.info("当前上传文件时有父目录,上传的绝对路径为:{}",
						pathResult.getOriginalPath());
				LOG.info("当前上传文件时有父目录,上传的相对路径为:{}",
						pathResult.getRelativelyPath());
			}
		} else {
			pathResult = PathUtil.getFilePath(user.getId(), user.getSchoolId(),
					null);
			personalFile.setParentId(Constant.ROOT_PATH);
			personalFile.setOriginalPath(pathResult.getRelativelyPath());
			LOG.info("当前上传文件时没有父目录,上传的绝对路径为:{}", pathResult.getOriginalPath());
			LOG.info("当前上传文件时没有父目录,上传的相对路径为:{}", pathResult.getRelativelyPath());
		}
		try {
			resultBean = FileOperationUtil.uploadFile(
					pathResult.getOriginalPath(), multipartFiles);
			if (resultBean != null) {
				if (resultBean.getFileType().equals(FileType.PICTURE)) {
					personalFile.setCompressPicName(Constant.PIC_MIN
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
							personalFile.getCompressPicName());
				} else if (resultBean.getFileType().equals(FileType.VIDEO)
						|| resultBean.getFileType().equals(FileType.MUSIC)) {//如果视频或音乐文件，进行转码
					personalFile.setVideoDec(VideoDecoding.NONE);
					personalFiles.add(personalFile);
				}else if(resultBean.getFileType().equals(FileType.DOCUMENT)){//当上传的附件是文件时，转化为PDF
					//转化为PDF
					DocConverter dc=new DocConverter();
					StringBuffer path=new StringBuffer(pathResult.getOriginalPath()).append(File.separator);
					path.append(resultBean.getTargetName());
					LOG.info("path:"+path);
					dc.doc2pdf(path.toString());
				}
				System.out.println(personalFile);
				personalFile.setUserId(user.getId());
				personalFile.setGenerateName(resultBean.getTargetName());
				personalFile.setCreateTime(new Date());
				personalFile.setOriginalName(resultBean.getSourceName());
				personalFile.setExtension(resultBean.getExtension());
				personalFile.setFileType(resultBean.getFileType());
				personalFile.setFileSize(resultBean.getFileSize());
				personalFile.setFileSizeStr(resultBean.getFileSizeStr());
				personalFileServiceI.uploadfile(personalFile);
				// 将文件进行转码
				for (PersonalFile personalFile2 : personalFiles) {
					this.decingVideoAndMusic(personalFile2, user.getId());
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
		return returnBean;
	}

	private void decingVideoAndMusic(final PersonalFile myfile,
			final String userId) {

		ThreadPool.getInstance().addTask(new Task() {
			@Override
			public String info() {
				LOG.info("当前视频文件开始解码,名称:{}", myfile.getOriginalName());
				return null;
			}

			@Override
			public void run() {
				String personalPath = ConfigFileUtil.getPersonalPath();
				try {
					personalPath = new String(personalPath
							.getBytes("iso8859-1"), "utf-8");
				} catch (UnsupportedEncodingException e) {
					LOG.error(e.getMessage(), e);
				}
				String sourcepath = personalPath + myfile.getOriginalPath()+File.separator
						+ myfile.getGenerateName();
				String filename = null;
				String targetPath = null;
				if (PathUtil.getFileType(sourcepath).equals(FileType.VIDEO)
						&& myfile.getVideoDec().equals(VideoDecoding.NONE)) {
					filename = myfile.getGenerateName().substring(0,
							myfile.getGenerateName().lastIndexOf("."))
							+ Constant.DECODING_EXT_FLV;
					targetPath = personalPath + myfile.getOriginalPath()+File.separator
							+ Constant.VIDEO_DECODING + filename;
					VideoEncodingUtil.encodingToFlv(sourcepath, targetPath);
				} else if (PathUtil.getFileType(sourcepath).equals(
						FileType.MUSIC)
						&& myfile.getVideoDec().equals(VideoDecoding.NONE)) {
					filename = myfile.getGenerateName().substring(0,
							myfile.getGenerateName().lastIndexOf("."))
							+ Constant.DECODING_EXT_MP3;
					targetPath = personalPath + myfile.getOriginalPath()+File.separator
							+ Constant.VIDEO_DECODING + filename;
					VideoEncodingUtil.encodingToMp3(sourcepath, targetPath);
				}
				PersonalFile personal = personalFileServiceI
						.queryPersonalFileByIdAndUserId(myfile.getId(), userId);
				if (personal != null) {
					personal.setVideoDec(VideoDecoding.DECODING);
					personalFileServiceI.updatePersonalFileDecoding(personal);
				}
			}
		});
	}

	/**
	 * 查询个人网盘分页
	 * 
	 * @param querybean
	 *            查询的bean
	 * @param request
	 * @return
	 */
	@RequestMapping("/querypersonfile")
	public ModelAndView queryPersonFile(QueryPersonalFileBean querybean,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("personalwebdisk/personal_networkdisk");
		User user = (User) request.getSession().getAttribute(
				Constant.LOGIN_USR_AUTH);
		querybean.setUserId(user.getId());
		try {
			if (StringUtils.isEmpty(querybean.getParentId())) {
				querybean.setParentId(Constant.ROOT_PATH);
			}
			Pagination<PersonalFile> pagination = personalFileServiceI
					.queryPersonalFileByBean(querybean);
			Map<String, Integer> fileCount = personalFileServiceI
					.getPersonalFileTypeCountByPid(querybean);
			modelAndView.addObject("fileCount", fileCount);
			modelAndView.addObject("pagination", pagination);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return modelAndView;
	}

	/**
	 * 查询个人的视频
	 * 
	 * @param querybean
	 *            查询的bean
	 * @param request
	 * @return
	 */
	@RequestMapping("/querypersonbytype")
	public ModelAndView queryPersonVideo(QueryPersonalFileBean querybean,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();

		User user = (User) request.getSession().getAttribute(
				Constant.LOGIN_USR_AUTH);
		querybean.setUserId(user.getId());
		try {
			if (querybean.getFileType().equals(FileType.COMPRESSION)) {
				modelAndView
						.setViewName("personalwebdisk/personal_compressiondisk");
			} else if (querybean.getFileType().equals(FileType.VIDEO)) {
				modelAndView.setViewName("personalwebdisk/personal_videodisk");
			} else if (querybean.getFileType().equals(FileType.DOCUMENT)) {
				modelAndView
						.setViewName("personalwebdisk/personal_documentdisk");
			} else if (querybean.getFileType().equals(FileType.MUSIC)) {
				modelAndView.setViewName("personalwebdisk/personal_musicdisk");
			} else if (querybean.getFileType().equals(FileType.OTHER)) {
				modelAndView.setViewName("personalwebdisk/personal_otherdisk");
			} else if (querybean.getFileType().equals(FileType.PICTURE)) {
				modelAndView
						.setViewName("personalwebdisk/personal_picturedisk");
			}
			Pagination<PersonalFile> pagination = personalFileServiceI
					.queryPersonalFileByBean(querybean);
			Map<String, Integer> fileCount = personalFileServiceI
					.getPersonalFileTypeCountByPid(querybean);
			modelAndView.addObject("fileCount", fileCount);
			modelAndView.addObject("pagination", pagination);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return modelAndView;
	}

	@RequestMapping("/videoPlayview/{id}")
	public ModelAndView videoPlayView(@PathVariable(value = "id") String id,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("personalwebdisk/personal_videoplay");
		try {
			if (StringUtils.isEmpty(id)) {
				throw new ProParamException("播放视频时，id为空");
			}
			User user = (User) request.getSession().getAttribute(
					Constant.LOGIN_USR_AUTH);
			PersonalFile personalFile = personalFileServiceI
					.queryPersonalFileByIdAndUserId(id, user.getId());
			if (personalFile == null) {
				throw new ProValiException("当前用户没有数据这条记录");
			}
			modelAndView.addObject("personalFile", personalFile);
		} catch (ProException e) {
			LOG.error(e.getErrorCode(), e.getErrorMsg());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return modelAndView;

	}

	@RequestMapping("/playmusiclist")
	@ResponseBody
	public ReturnBean playMusicList(HttpServletRequest request) {
		ReturnBean returnBean = ReturnBean.returnBeanBuild();
		User user = (User) request.getSession().getAttribute(
				Constant.LOGIN_USR_AUTH);
		List<PersonalFile> musicfiles = new ArrayList<PersonalFile>();
		musicfiles = personalFileServiceI.queryPlayMusicByDecingAnduserId(
				VideoDecoding.DECODING, user.getId());
		returnBean.setData(musicfiles);
		return returnBean;
	}
	
	
	
	/**
	 * 个人网盘上传的历史资源记录信息
	 * @return
	 */
	@RequestMapping("/getPersionalRecord")
	public ModelAndView getResourceDetail(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			User user=(User) request.getSession().getAttribute(Constant.LOGIN_USR_AUTH);//get session
			Query q=new Query();
			q.addCriteria(Criteria.where("userId").is(user.getId()));
			List<PersonalFile> list=this.personalFileServiceI.find(q, PersonalFile.class);
			
			modelAndView.addObject("listPersonalFile",list);
		}catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
		modelAndView.setViewName("personalwebdisk/personal_webdisk_record");
		return modelAndView;
	}

}
