package com.inred.media.pojo;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 年级： 一年级，二年级等
 * 
 * @author mac
 *
 */
@Document(collection="grade")
public class Grade extends IdEntity{

	private static final long serialVersionUID = 1844776415946038217L;
	public String name;// 年级名字
	public String oreder;// 排序ID
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public String getOreder() {
		return oreder;
	}

	public void setOreder(String oreder) {
		this.oreder = oreder;
	}

	@Override
	public String toString() {
		return "Grade [name=" + name + ", oreder=" + oreder + "]";
	}

	

}
