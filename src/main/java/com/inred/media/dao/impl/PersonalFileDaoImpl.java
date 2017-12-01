package com.inred.media.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.inred.media.dao.BaseDaoI;
import com.inred.media.dao.PersonalFileDaoI;
import com.inred.media.model.Pagination;
import com.inred.media.model.VideoDecoding;
import com.inred.media.pojo.PersonalFile;

@Repository
public class PersonalFileDaoImpl implements PersonalFileDaoI {
	@Autowired
	private BaseDaoI<PersonalFile> baseDaoI;

	@Override
	public void createFolder(PersonalFile personalFile) {
		baseDaoI.insert(personalFile);
	}

	@Override
	public void uploadFile(PersonalFile personalFile) {
		baseDaoI.insert(personalFile);
	}
	
	

	@Override
	public Pagination<PersonalFile> selectFilesSortFolder(
			Map<String, Object> params, Integer pageSize, Integer pageNo,
			String sortColum, String sortType) {
		Query query = null;
		if (params != null && !params.isEmpty()) {
			query = new Query();
			for (Entry<String, Object> entry : params.entrySet()) {
				query.addCriteria(Criteria.where(entry.getKey()).is(
						entry.getValue()));
			}
		}
		if (!StringUtils.isEmpty(sortColum) && !StringUtils.isEmpty(sortType)) {
			query.with(new Sort(Sort.Direction.valueOf(sortType), sortColum));
		} else {
			query.with(new Sort(Sort.Direction.ASC, "fileType"));
		}
		return baseDaoI.findPaginationByQuery(query, pageNo, pageSize,
				PersonalFile.class);
	}

	@Override
	public PersonalFile updatefileNameById(String id, String fileName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		return baseDaoI.findAndModify(query,
				Update.update("originalName", fileName), PersonalFile.class);
	}

	@Override
	public PersonalFile deletePersonalFileById(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		return baseDaoI.findAndRemove(query, PersonalFile.class);

	}

	@Override
	public PersonalFile findPersonalFileById(String id) {
		return baseDaoI.findOneById(id, PersonalFile.class);
	}
	@Override
	public PersonalFile findPersonalFileByIdAndUserId(String id,String userId) {
		Query query=new Query();
		query.addCriteria(Criteria.where("id").is(id).andOperator(Criteria.where("userId").is(userId)));
		return baseDaoI.findOneByQuery(query,PersonalFile.class);
	}

	@Override
	public Integer findPersonalFileCountByParentId(Map<String,Object> params) {
		Query query = new Query();
		if (params != null && !params.isEmpty()) {
			for (Entry<String, Object> entry : params.entrySet()) {
				query.addCriteria(Criteria.where(entry.getKey()).is(
						entry.getValue()));
			}
		}
		return baseDaoI.findCountByQuery(query, PersonalFile.class);
	}

	@Override
	public boolean findExistsPersonalFileByParentId(String parentId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("parentId").is(parentId));
		return baseDaoI.exists(query, PersonalFile.class);
	}

	@Override
	public void updatePersonalFileDecding(PersonalFile personalFile) {
		baseDaoI.save(personalFile);
		
	}

	@Override
	public List<PersonalFile> queryPlayMusicByDecingAnduserId(
			Map<String,Object> params) {
		Query query=new Query();
		if (params != null && !params.isEmpty()) {
			for (Entry<String, Object> entry : params.entrySet()) {
				query.addCriteria(Criteria.where(entry.getKey()).is(
						entry.getValue()));
			}
		}
		
		return baseDaoI.find(query, PersonalFile.class);
	}

	@Override
	public List<PersonalFile> find(Query query, Class<PersonalFile> classes) {
		// TODO Auto-generated method stub
		return this.baseDaoI.find(query, classes);
	}

}
