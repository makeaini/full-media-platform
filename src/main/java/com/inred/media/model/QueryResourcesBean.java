package com.inred.media.model;

public class QueryResourcesBean {
	/**
	 * 页数
	 */
	private Integer pageNo = 1;
	/**
	 * 条数
	 */
	private Integer pageSize = 10;

	/**
	 * ASC DESC
	 */
	private String sortType;
	/**
	 * 排序的列
	 */
	private String sortCol = null; //排序
	private String resources = null; //资源分类
	private String resourcesType = null;//资源类型
	private String grade = null;//年级
	private String subject = null;//课程
	private String year = null;//年级
	
	private String school=null;//学校
	
	private String name=null;//资源名称
	
	
	
	public String getName() {
		return urlStringEnc(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchool() {
		return urlStringEnc(school);
	}

	public void setSchool(String school) {
		this.school = school;
		
	}

	public Integer getPageNo() {
		return Integer.parseInt(urlStringEnc(pageNo.toString()));
//		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortType() {
		return urlStringEnc(sortType);
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

	public String getResources() {
		return urlStringEnc(resources);
	}

	public void setResources(String resources) {
		this.resources = resources;
	}

	public String getResourcesType() {
		return urlStringEnc(resourcesType);
	}

	public void setResourcesType(String resourcesType) {
		this.resourcesType = resourcesType;
	}

	public String getGrade() {
		return urlStringEnc(grade);
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSubject() {
		return urlStringEnc(subject);
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getYear() {
		return urlStringEnc(year);
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String urlStringEnc(String resources) {
		if (resources != null && !resources.equals("")) {
			if ("ALL".equalsIgnoreCase(resources)) {
				return null;
			}
			if (resources.indexOf(",") != -1) {
				
				
				String checkALL=resources.substring(resources.lastIndexOf(",") + 1,
						resources.length());
				if ("ALL".equalsIgnoreCase(checkALL)) {
					return null;
				}
				return checkALL;
				
			}
			return resources;

		}
		return null;

	}

	@Override
	public String toString() {
		return "QueryResourcesBean [pageNo=" + pageNo + ", pageSize="
				+ pageSize + ", sortType=" + sortType + ", sortCol=" + sortCol
				+ ", resources=" + resources + ", resourcesType="
				+ resourcesType + ", grade=" + grade + ", subject=" + subject
				+ ", year=" + year + ", school=" + school + ", name=" + name
				+ "]";
	}	

}
