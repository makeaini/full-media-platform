package com.inred.media.pojo;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 学校内部通知公告(主要针对学校资源信息通知这一块)
 * 
 * @author mac
 *
 */
@Document(collection = "schoolNotification")
public class SchoolNotification extends IdEntity {

	private String title;// 标题
	private String content;// 内容
	private Date sendDate=new Date();// 发送日期
	private String type;// 1:资源公知，2.校园系统通知

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
