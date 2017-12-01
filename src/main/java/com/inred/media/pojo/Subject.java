package com.inred.media.pojo;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 学科: 语文，数学，历史，化学等。
 * 
 * @author mac
 *
 */
@Document(collection="subject")
public class Subject extends IdEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3926662821271775169L;
	private String name;// 科目名字
	private String oreder;// 排序ID


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

}
