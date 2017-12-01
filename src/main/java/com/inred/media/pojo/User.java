package com.inred.media.pojo;

import org.springframework.data.mongodb.core.mapping.Document;

import com.inred.media.model.ResourcesRole;
import com.inred.media.model.UserType;

/**
 * 用户信息，登录。
 * 
 * @author mac
 *
 */
@Document(collection = "user")
public class User extends IdEntity {

	private static final long serialVersionUID = -3253305464329595954L;
	private String name;// 性名
	private String username;// 用户名
	private String password;// 密码
	private String headImage;// 头像
	private String email;// 邮箱
	private UserType type;// 帐号内型:学生，老师，学校管理员，其他.（二期学校也可以提供作品展）
	private String schooldIds;// 学校编号.(用于：用户于登录时，验证是哪个学校。)

	private String weChatId;// 微信ID;(二期开发)

	private ResourcesRole resourcesRole;// 资源权限（）
	private String remark;// 备注

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public String getSchooldIds() {
		return schooldIds;
	}

	public void setSchooldIds(String schooldIds) {
		this.schooldIds = schooldIds;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getWeChatId() {
		return weChatId;
	}

	public void setWeChatId(String weChatId) {
		this.weChatId = weChatId;
	}

	public ResourcesRole getResourcesRole() {
		return resourcesRole;
	}

	public void setResourcesRole(ResourcesRole resourcesRole) {
		this.resourcesRole = resourcesRole;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", username=" + username + ", password="
				+ password + ", headImage=" + headImage + ", email=" + email
				+ ", type=" + type + ", schooldIds=" + schooldIds
				+ ", weChatId=" + weChatId + ", resourcesRole=" + resourcesRole
				+ ", remark=" + remark + "]";
	}

}
