package com.inred.media.model;

import org.springframework.util.StringUtils;

public class QueryPersonalFileBean extends BaseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4637693166692461465L;
	/**
	 * 页数
	 */
	private Integer pageNo=1;
	/**
	 * 条数
	 */
	private Integer pageSize=16;
	
	/**
	 * ASC DESC
	 */
	private String sortType;
	/**
	 * 排序的列
	 */
	private String sortCol;
	/**
	 * 文件类型
	 */
	private FileType fileType;
	/**
	 * 用户Id
	 */
	private String userId;
	/**
	 * 父的id
	 */
	private String parentId;
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public FileType getFileType() {
		return fileType;
	}
	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}
	public Integer getPageNo() {
		
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		if(StringUtils.isEmpty(pageNo)){
			this.pageNo=1;
		}
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	public String getSortCol() {
		return sortCol;
	}
	public void setSortCol(String sortCol) {
		this.sortCol = sortCol;
	}
	@Override
	public String toString() {
		return "QueryPersonalFileBean [pageNo=" + pageNo + ", pageSize="
				+ pageSize + ", sortType=" + sortType + ", sortCol=" + sortCol
				+ "]";
	}
	
	

}
