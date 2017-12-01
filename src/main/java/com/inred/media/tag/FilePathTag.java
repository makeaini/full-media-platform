package com.inred.media.tag;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.inred.media.util.UrlEncUtil;

public class FilePathTag extends TagSupport {
	private static final Logger LOG = LoggerFactory
			.getLogger(FilePathTag.class);
	private static final long serialVersionUID = 6973328024404240594L;
	private String filePath;
	private String fileIds;

	public String getFileIds() {
		return fileIds;
	}

	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setPageContext(PageContext pageContext) {
		this.pageContext = pageContext;
	}

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		StringBuffer buffer = new StringBuffer();
		buffer.append("<li><i class='icon-home'></i></li>");
		if (StringUtils.isEmpty(filePath)) {
			buffer.append("<li class='active'>全部文件</li>");
		} else {
			StringBuffer returnPath=new StringBuffer();
			StringBuffer returnIds=new StringBuffer();
			buffer.append("<li><a href='?'>全部文件</a></li>");
			String  filePathEnc= UrlEncUtil.decode(filePath);
			String[] paths = filePathEnc.split("_");
			String[] fileId= fileIds.split("_");
			for (int i = 1; i < paths.length; i++) {
				// TODO: 思考面包条需要怎么写
				if(i!=(paths.length-1)){
				returnPath.append("_").append(paths[i]);
				returnIds.append("_").append(fileId[i]);
				buffer.append("<li>").append("<a href='?parentId=").
				append(fileId[i]).append("&filePath=").append(returnPath)
				.append("&pathIds=").append(returnIds)
				.append("'>").
				append(paths[i]).append("</a></li>");
				}else{
					buffer.append("<li class='active'>").
					append(paths[i]).append("</li>");
				}
			}
		}
		try {
			out.print(buffer.toString());
		} catch (IOException e) {
			LOG.error("自定义标签面包条IO异常");
			LOG.error(e.getMessage(),e);
		}
		 return super.doEndTag();
	}

}
