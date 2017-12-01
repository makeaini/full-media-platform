package com.inred.media.pojo;


import java.util.Date;
import java.util.List;

import com.inred.media.model.RolePermissions;
import com.inred.media.model.Stauts;

/**
 * 资源上传（上传一组文件或图片或视频等）
 * @author mac
 *
 */
public class Resources extends IdEntity {

	private static final long serialVersionUID = -7630149021452101702L;
	private String name;//资源名称（语文课－古诗三百首）
	private String userid;//用户id
	private Date createTime; //上传时间
	private String udpateName;//上传人姓名
	//发布权限 (webshow:上传的资源可以址接显示在网页中，nowebshow：不能直接显示在网页中，需要审核（user表状态为：haveRole都可以审核）。)
	//如果有从显示状态 到 关闭状态。那么需要管理员操作。
	private RolePermissions rolePermissions;
	
	private String gradeId;//年级(如：高一，高二)
	private Grade grade;//
	private String subjectsId;//科目（如：语文，地理）
	private Subject subject;
	private String typeId;//类型（如：教案，课件）
	private ResourcesType resourcesType;
	



	private String fileSuperType;//文件类型:	music,video,picture,document,other	
	private int downloadsNumber;//资源下载次数
	private int browseNumber;//浏览次数
	private String year;
	
	
	private List<MyFile> myFiles;//文件组（原因：有可能上传多个文件，如果是个人网盘：那么只有一件记录）
	private List<Comment> comments;//评论组
	private String remark;//摘要
	private String label;//标签
	
	

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}



	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUdpateName() {
		return udpateName;
	}

	public void setUdpateName(String udpateName) {
		this.udpateName = udpateName;
	}



	public RolePermissions getRolePermissions() {
		return rolePermissions;
	}

	public void setRolePermissions(RolePermissions rolePermissions) {
		this.rolePermissions = rolePermissions;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getSubjectsId() {
		return subjectsId;
	}

	public void setSubjectsId(String subjectsId) {
		this.subjectsId = subjectsId;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getFileSuperType() {
		return fileSuperType;
	}

	public void setFileSuperType(String fileSuperType) {
		this.fileSuperType = fileSuperType;
	}




	public int getDownloadsNumber() {
		return downloadsNumber;
	}

	public void setDownloadsNumber(int downloadsNumber) {
		this.downloadsNumber = downloadsNumber;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getBrowseNumber() {
		return browseNumber;
	}

	public void setBrowseNumber(int browseNumber) {
		this.browseNumber = browseNumber;
	}

	public List<MyFile> getMyFiles() {
		return myFiles;
	}

	public void setMyFiles(List<MyFile> myFiles) {
		this.myFiles = myFiles;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}


	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public ResourcesType getResourcesType() {
		return resourcesType;
	}

	public void setResourcesType(ResourcesType resourcesType) {
		this.resourcesType = resourcesType;
	}
	

}
