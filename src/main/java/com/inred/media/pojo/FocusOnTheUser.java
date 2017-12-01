package com.inred.media.pojo;


/**
 * 关注表
 * 学生可以通过平台测试帐号，关注学校的某些老师。
 * 那么关注老师的学生可以查看到这位老师发布的独立资源。
 * @author mac
 *
 */
public class FocusOnTheUser {
	
	private String userId;//用户id;
	private String userName;//用户名字；
	private String focusOnPeopleId;//关注人（我关注的人）
	private String focusOnPeroleUserName;//关注人姓名
	
	private String schoolId;//学校ID

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFocusOnPeopleId() {
		return focusOnPeopleId;
	}

	public void setFocusOnPeopleId(String focusOnPeopleId) {
		this.focusOnPeopleId = focusOnPeopleId;
	}

	public String getFocusOnPeroleUserName() {
		return focusOnPeroleUserName;
	}

	public void setFocusOnPeroleUserName(String focusOnPeroleUserName) {
		this.focusOnPeroleUserName = focusOnPeroleUserName;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	
	
	

	

}
