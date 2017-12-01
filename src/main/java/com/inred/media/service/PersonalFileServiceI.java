package com.inred.media.service;


import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.query.Query;

import com.inred.media.model.Pagination;
import com.inred.media.model.QueryPersonalFileBean;
import com.inred.media.model.VideoDecoding;
import com.inred.media.pojo.PersonalFile;

public interface PersonalFileServiceI {
	
	/**
	 * 通过条件查询所有的记录
	 * @param query
	 * @return
	 */
	public List<PersonalFile> find(Query query,Class<PersonalFile> classes);
	

	/**
	 * 创建一个文件夹
	 * @param personalFile
	 */
	public void createFolder(PersonalFile personalFile);
	
	/**
	 * 通过Id查询PersonalFile
	 * @param id 
	 * @return
	 */
	public PersonalFile getPersonalFileById(String id);
	
	/**
	 * 返回当前用户的PersonalFile为parentId的各文件类型条数
	 * @param parentId 父id
	 * @param userid 用户的Id
	 * @return 
	 */
	public Map<String,Integer> getPersonalFileTypeCountByPid(QueryPersonalFileBean personalFileBean);
	
	/**
	 * 查询是否存在一个parentId的PersonalFile
	 * @param parentId
	 * @return
	 */
	public boolean getExistsPersonalFilebyParentId(String parentId);
	
	/**
	 * 删除当前文件件
	 */
	public PersonalFile deletePersonalFilebyId(String id);
	
	/**
	 * 修改当前文件的名称
	 */
	public PersonalFile modifyPersonalFileNamebyId(String id,String name);
	
	
	/**
	 * 上传文件时需要添加记录
	 * @param personalFile
	 */
	public void uploadfile(PersonalFile personalFile);
	
	/**
	 * 查询并分页带条件 
	 * @param personalFileBean
	 * @return
	 */
	public Pagination<PersonalFile> queryPersonalFileByBean(QueryPersonalFileBean personalFileBean);
	/**
	 * 
	 * @param id 文件Id
	 * @param userId 用户Id
	 * @return
	 */
	public PersonalFile  queryPersonalFileByIdAndUserId(String id,String userId);
	
	
	public void updatePersonalFileDecoding(PersonalFile personalFile);
	
	public List<PersonalFile> queryPlayMusicByDecingAnduserId(VideoDecoding videoDecoding,String userid);
}
