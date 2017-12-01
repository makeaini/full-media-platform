package com.inred.media.pojo;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.inred.media.model.FileType;
import com.inred.media.model.VideoDecoding;
@Document(collection="personal_file")
public class PersonalFile extends IdEntity {

	private static final long serialVersionUID = -5658032741235792006L;
	private String originalName;//原文件的名字
	private String generateName;//生成的文件名称
	private String originalPath;//文件的原地址
	private String compressPicName;//文件的压缩地址（图片使用）
	private String extension;//文件扩展名
	private Date createTime;//创建时间
	private String userId;//属于的用户
	private FileType fileType;//文件夹，图片，文档等
	private String parentId; //文件夹的父Id
	private long fileSize;//文件大小
	private String fileSizeStr;//文件大小的字符串
	private VideoDecoding videoDec;//是否解码（视频使用）
	public VideoDecoding getVideoDec() {
		return videoDec;
	}
	public void setVideoDec(VideoDecoding videoDec) {
		this.videoDec = videoDec;
	}
   	public String getFileSizeStr() {
		return fileSizeStr;
	}
	public void setFileSizeStr(String fileSizeStr) {
		this.fileSizeStr = fileSizeStr;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public String getGenerateName() {
		return generateName;
	}
	public void setGenerateName(String generateName) {
		this.generateName = generateName;
	}
	public String getOriginalPath() {
		return originalPath;
	}
	public void setOriginalPath(String originalPath) {
		this.originalPath = originalPath;
	}
	
	public String getCompressPicName() {
		return compressPicName;
	}
	public void setCompressPicName(String compressPicName) {
		this.compressPicName = compressPicName;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public FileType getFileType() {
		return fileType;
	}
	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}
	@Override
	public String toString() {
		return "PersonalFile [originalName=" + originalName + ", generateName="
				+ generateName + ", originalPath=" + originalPath
				+ ", compressPicName=" + compressPicName + ", extension="
				+ extension + ", createTime=" + createTime + ", userId="
				+ userId + ", fileType=" + fileType + ", parentId=" + parentId
				+ ", fileSize=" + fileSize + ", fileSizeStr=" + fileSizeStr
				+ "]";
	}




}
