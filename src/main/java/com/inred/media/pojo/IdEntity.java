package com.inred.media.pojo;

import org.springframework.data.annotation.Id;

public class IdEntity implements java.io.Serializable {
	/**
	 * 	
	 */
	private static final long serialVersionUID = 4140037446867323255L;
	@Id
	private String id;
	private String schoolId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	


	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	

}
