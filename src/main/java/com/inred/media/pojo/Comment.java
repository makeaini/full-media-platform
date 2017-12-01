package com.inred.media.pojo;

import java.util.Date;

import com.inred.media.model.CommentAudit;


/**
 * 上传的某个资料组，进行评论。
 * @author mac
 *
 */
public class Comment extends IdEntity {
	private static final long serialVersionUID = 7958164327349348782L;
	private String name;
	private String message;
	private CommentAudit audit;//审核
	private Date commentdate;
	public Date getCommentdate() {
		return commentdate;
	}
	public void setCommentdate(Date commentdate) {
		this.commentdate = commentdate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public CommentAudit getAudit() {
		return audit;
	}
	public void setAudit(CommentAudit audit) {
		this.audit = audit;
	}

	
}
