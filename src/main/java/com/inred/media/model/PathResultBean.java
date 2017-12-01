package com.inred.media.model;

public class PathResultBean extends BaseBean{

	private static final long serialVersionUID = -6003387968680628518L;
	private String originalPath;//绝对路径
	private String relativelyPath;//相对路径
	public String getOriginalPath() {
		return originalPath;
	}
	public void setOriginalPath(String originalPath) {
		this.originalPath = originalPath;
	}
	public String getRelativelyPath() {
		return relativelyPath;
	}
	public void setRelativelyPath(String relativelyPath) {
		this.relativelyPath = relativelyPath;
	}
	@Override
	public String toString() {
		return "PathResultBean [originalPath=" + originalPath
				+ ", relativelyPath=" + relativelyPath + "]";
	}

}
