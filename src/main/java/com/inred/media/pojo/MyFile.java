package com.inred.media.pojo;

import java.util.Date;

import com.inred.media.model.FileType;
import com.inred.media.model.VideoDecoding;


/**
 * 我上传的文件。
 * @author mac
 *
 */
public class MyFile extends IdEntity{

	private static final long serialVersionUID = -3728422286884833936L;
	private String originalName;//原文件的名字
	private String generateName;//生成的文件名称
	private String originalPath;//文件的原地址
	private String compressPicName;//文件的压缩地址（图片使用）
	private String extension;//文件扩展名
	private FileType fileType;//文件夹，图片，文档等
	private VideoDecoding videoDec;
	public VideoDecoding getVideoDec() {
		return videoDec;
	}
	public void setVideoDec(VideoDecoding videoDec) {
		this.videoDec = videoDec;
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
	public FileType getFileType() {
		return fileType;
	}
	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}
	@Override
	public String toString() {
		return "MyFile [originalName=" + originalName + ", generateName="
				+ generateName + ", originalPath=" + originalPath
				+ ", compressPicName=" + compressPicName + ", extension="
				+ extension + ", fileType=" + fileType + ", videoDec="
				+ videoDec + "]";
	}
	
	
}
