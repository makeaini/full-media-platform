package com.inred.media.pojo;

import org.springframework.data.mongodb.core.mapping.Document;

import com.inred.media.model.SchoolState;


/**
 * 学校基本信息
 * （基于云平台架构）
 * @author mac
 *
 */
@Document(collection="school")
public class School extends IdEntity {

	private static final long serialVersionUID = -3950920819588301240L;
	private String name;// 学校名称
	private String englishName;// 学校英文名
	private String shorts;// 学校简称
	private String address;// 学校地址
	private String telPhone;// 学校电话
	private String email;// 邮箱
	private String remark;// 备注
	private String schoolCreateTime;// 学校创办时间
	private String coredIds;// 学校编号
	private String area;// 学校区域。
	private SchoolState state;// 状态，//试用到期停止，正常使用，永久不过期
	private String schoolproduct;// 学校有哪些产品.数组，表示学校有可能有多个产品.



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getShorts() {
		return shorts;
	}

	public void setShorts(String shorts) {
		this.shorts = shorts;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSchoolCreateTime() {
		return schoolCreateTime;
	}

	public void setSchoolCreateTime(String schoolCreateTime) {
		this.schoolCreateTime = schoolCreateTime;
	}

	public String getCoredIds() {
		return coredIds;
	}

	public void setCoredIds(String coredIds) {
		this.coredIds = coredIds;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public SchoolState getState() {
		return state;
	}

	public void setState(SchoolState state) {
		this.state = state;
	}

	public String getSchoolproduct() {
		return schoolproduct;
	}

	public void setSchoolproduct(String schoolproduct) {
		this.schoolproduct = schoolproduct;
	}

	
}
