package com.inred.media.tag;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inred.media.model.Pagination;


public class PaginationTag extends TagSupport {

	private static final long serialVersionUID = 4869314267978863903L;
	private static final Logger LOG = LoggerFactory
			.getLogger(PaginationTag.class);
	public void setPageContext(PageContext pageContext) {
		this.pageContext = pageContext;
	}
	private Pagination<?> pageination;

	public Pagination<?> getPageination() {
		return pageination;
	}

	public void setPageination(Pagination<?> pageination) {
		this.pageination = pageination;
	}

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		StringBuffer buffer = new StringBuffer();
		StringBuffer paramsStr=new StringBuffer();
		HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
		Map<String,String[]> params=request.getParameterMap();
		if(params!=null&&!params.isEmpty()){
			for (Entry<String, String[]> entry : params.entrySet()) {
				if(entry.getKey().equals("pageNo")||entry.getKey().equals("pageSize")){
					continue;
				}
				for (String obj : entry.getValue()) {
					paramsStr.append("&").append(entry.getKey()).append("=").append(obj);
				}
			}
		}
		if(pageination!=null){
			if(pageination.isHasPreviousPage()){
				buffer.append("<li><a class='icon' href='?pageNo=").append(pageination.getUpPage()).append(paramsStr).append("'><i class='icon-long-arrow-left'></i></a>");
			
			}
			for (int nav : pageination.getNavigatepageNums()) {
				if(nav==pageination.getPageNo()){
					buffer.append("<li class='active'><a href='javascript:;'>").append(nav).append("</a></li>");
				}else{
					buffer.append("<li><a href='?pageNo=").append(nav).append(paramsStr).append("'>").append(nav).append("</a></li>");
				}
			}
			if(pageination.isHasNextPage()){
				 
				buffer.append("<li><a class='icon' href='?pageNo=").append(pageination.getNextPage()).append(paramsStr).append("'> <i class='icon-long-arrow-right'></i></a>");
			}
		}
		try {
			out.print(buffer.toString());
		} catch (IOException e) {
			LOG.error("自定义标签分页IO异常");
			LOG.error(e.getMessage(),e);
		}
		return super.doEndTag();
	}

}
