package com.inred.media.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.query.Query;

import com.inred.media.model.Pagination;
import com.inred.media.model.VideoDecoding;
import com.inred.media.pojo.PersonalFile;

public interface PersonalFileDaoI{
	
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
	 * 上传文件
	 * @param personalFile 
	 */
	public void uploadFile(PersonalFile personalFile);
	
	/**
	 * 查询文件带条件分页，并按文件夹排序
	 * @param params 条件
	 * @param pageNo  第几页
	 * @param pageSize 查询多少条
	 */
	public Pagination<PersonalFile> selectFilesSortFolder(Map<String,Object> params,Integer pageSize,Integer pageNo
			,String sortColum, String sortType);

	/**
	 * 修改文件的名称
	 * @param id 修改文件的夹的id
	 * @param folderName 修改文件的名称
	 */
	public PersonalFile updatefileNameById(String id,String folderName);
	

	
	/**
	 * 删除文件
	 * @param id 删除的文件id
	 */
	public PersonalFile deletePersonalFileById(String id);
	
	/**
	 * 通过Id找到对应的personfile
	 * @param id
	 * @return
	 */
	public PersonalFile findPersonalFileById(String id);
	
	/**
	 * 查询当前用户parentid的PersonalFile各文件类型的条数
	 * @param parentId 父文件夹Id
	 * @param userId 用户Id
	 * @param fileType 文件类型 
	 * 
	 */
	public Integer findPersonalFileCountByParentId(Map<String,Object> params);
	
	/**
	 * 查询是否存在parentid的PersonalFile
	 * @param parentId  父文件夹Id
	 * @return
	 */
	public boolean findExistsPersonalFileByParentId(String parentId);
	
	/**
	 * 根据用户Id和文件id查询
	 */
	public PersonalFile  findPersonalFileByIdAndUserId(String id,String userId);
	
	public void updatePersonalFileDecding(PersonalFile personalFile);
	public List<PersonalFile> queryPlayMusicByDecingAnduserId(
			Map<String,Object> params);

}
