package com.inred.media.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.inred.media.dao.PersonalFileDaoI;
import com.inred.media.model.FileType;
import com.inred.media.model.Pagination;
import com.inred.media.model.QueryPersonalFileBean;
import com.inred.media.model.VideoDecoding;
import com.inred.media.pojo.PersonalFile;
import com.inred.media.service.PersonalFileServiceI;

@Service
public class PersonalFileServiceImpl implements PersonalFileServiceI {
	@Autowired
	private PersonalFileDaoI personalFileDaoI;

	@Override
	public void createFolder(PersonalFile personalFile) {
		personalFile.setCreateTime(new Date());
		personalFile.setFileType(FileType.FOLDER);
		personalFileDaoI.createFolder(personalFile);
	}

	@Override
	public PersonalFile getPersonalFileById(String id) {
		return personalFileDaoI.findPersonalFileById(id);
	}

	@Override
	public Map<String,Integer> getPersonalFileTypeCountByPid(QueryPersonalFileBean personalFileBean) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(personalFileBean.getUserId())){
			params.put("userId", personalFileBean.getUserId());
		}
/*		if(!StringUtils.isEmpty(personalFileBean.getParentId())){
			params.put("parentId", personalFileBean.getParentId());
		}*/
		params.put("fileType", FileType.VIDEO.toString());
		Integer videoCount = personalFileDaoI.findPersonalFileCountByParentId(params);
		params.put("fileType", FileType.DOCUMENT.toString());
		Integer documentCount = personalFileDaoI.findPersonalFileCountByParentId(params);
		params.put("fileType", FileType.COMPRESSION.toString());
		Integer compressionCount = personalFileDaoI.findPersonalFileCountByParentId(params);
		params.put("fileType", FileType.MUSIC.toString());
		Integer musicCount = personalFileDaoI.findPersonalFileCountByParentId(params);
		params.put("fileType", FileType.PICTURE.toString());
		Integer pictureCount = personalFileDaoI.findPersonalFileCountByParentId(params);
		params.put("fileType", FileType.OTHER.toString());
		Integer otherCount = personalFileDaoI.findPersonalFileCountByParentId(params);
		Map<String ,Integer> personalfileCount=new HashMap<String, Integer>();
		personalfileCount.put(FileType.VIDEO.toString(), videoCount);
		personalfileCount.put(FileType.DOCUMENT.toString(), documentCount);
		personalfileCount.put(FileType.COMPRESSION.toString(), compressionCount);
		personalfileCount.put(FileType.MUSIC.toString(), musicCount);
		personalfileCount.put(FileType.PICTURE.toString(), pictureCount);
		personalfileCount.put(FileType.OTHER.toString(), otherCount);
		return personalfileCount;
	}

	@Override
	public boolean getExistsPersonalFilebyParentId(String parentId) {
		return personalFileDaoI.findExistsPersonalFileByParentId(parentId);
	}

	@Override
	public PersonalFile deletePersonalFilebyId(String id) {
		return personalFileDaoI.deletePersonalFileById(id);
	}

	@Override
	public PersonalFile modifyPersonalFileNamebyId(String id, String filename) {
		return personalFileDaoI.updatefileNameById(id, filename);
	}

	@Override
	public void uploadfile(PersonalFile personalFile) {
		personalFileDaoI.uploadFile(personalFile);

	}

	@Override
	public Pagination<PersonalFile> queryPersonalFileByBean(
			QueryPersonalFileBean personalFileBean) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(personalFileBean.getUserId())){
			params.put("userId", personalFileBean.getUserId());
		}
		if(!StringUtils.isEmpty(personalFileBean.getFileType())){
			params.put("fileType", personalFileBean.getFileType().toString());
		}
		if(!StringUtils.isEmpty(personalFileBean.getParentId())){
			params.put("parentId", personalFileBean.getParentId());
		}
		return personalFileDaoI.selectFilesSortFolder(params,
				personalFileBean.getPageSize(), personalFileBean.getPageNo(),
				personalFileBean.getSortCol(), personalFileBean.getSortType());
	}
	
	/**
	 * 根据用户Id和文件id查询
	 */
	public PersonalFile  queryPersonalFileByIdAndUserId(String id,String userId){
		return personalFileDaoI.findPersonalFileByIdAndUserId(id, userId);
	}

	@Override
	public void updatePersonalFileDecoding(PersonalFile personalFile) {
		personalFileDaoI.updatePersonalFileDecding(personalFile);
		
	}

	@Override
	public List<PersonalFile> queryPlayMusicByDecingAnduserId(
			VideoDecoding videoDecoding, String userid) {
		Map<String,Object> params=new HashMap<String, Object>();
		if(!StringUtils.isEmpty(userid)){
			params.put("userId",userid);
		}
		if(!StringUtils.isEmpty(videoDecoding)){
			params.put("videoDec", videoDecoding.toString());
		}
		return personalFileDaoI.queryPlayMusicByDecingAnduserId(params);
	}

	public List<PersonalFile> find(Query query, Class<PersonalFile> classes) {
		return this.personalFileDaoI.find(query, classes);
	}
}
