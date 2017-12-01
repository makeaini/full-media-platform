package com.inred.media.model;

public class FileUploadResultBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4799684304017944541L;
	/**
	 * 原文件名称
	 */
	private String sourceName;
	/**
	 * 上传文件类型
	 */
	private FileType fileType;
	/**
	 * 当前的文件名称
	 */
	private String targetName;
	/**
	 * 后缀名
	 */
	private String extension;
	
	private long fileSize;
	private String fileSizeStr;
	
	private String generateName;//没有后zui名
	
	
	public String getGenerateName() {
		return generateName;
	}
	public void setGenerateName(String generateName) {
		this.generateName = generateName;
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
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	

	public FileType getFileType() {
		return fileType;
	}
	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}
	public String getTargetName() {
		return targetName;
	}
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
	
	

}
