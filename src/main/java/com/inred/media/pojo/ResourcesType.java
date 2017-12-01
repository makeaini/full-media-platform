package com.inred.media.pojo;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 资源类型： 教案 教学课件 作业试题 课堂实录 教学参考 素材 错误 公开课 课题分享 其他 个人随笔
 * 
 * @author mac
 *
 */
@Document(collection="resourcesType")
public class ResourcesType extends IdEntity{
	private static final long serialVersionUID = 4391861850444666874L;
	private String name;// 资源类型名字
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
