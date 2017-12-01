<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib uri="page" prefix="page" %>
<%@page isELIgnored="false"%> 
<!doctype html>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>全媒体源云库中心</title>

  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description" content="">
  <meta name="keywords" content="index">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <meta name="apple-mobile-web-app-title" content="WeChat" />
  <link rel="shortcut icon" href="${ctx}/assets/images/logo_title.png" 
   type="image/x-icon" />
</head>
<script type="text/javascript">
	function select(){
		var name=$("#name").val();
		var url="${url}";
		
		if(name!=""){
			url=url+"&name="+name
		}
		window.location.href='${ctx}/resourcesCenter/center?pageNo=1'+url
	}
</script>
<body>
<div class="modal-shiftfix">
<%@include file="../public/top.jsp" %>
<!-- 内容 -->
   <!-- End Navigation -->
    <div class="container-fluid main-content">
     <!-- end Basic Table --><!-- Bordered Table -->
          <div class="col-lg-12">
            <div class="widget-container fluid-height clearfix">
              <div class="heading">
                <i class="icon-table"></i>全媒体资源中心 》分类查找
                <span style="float: right;">
                
                 <input id="name" name="name" style="width:200px;float:left" class="form-control"
                 value="${testInput}" type="text">
                 	<button onclick="select();" style="float:left;" class="btn btn-default" type="button">搜索</button>
                 </input>
                </span>
              </div>
              <div class="widget-content padded clearfix">
                <table class="table table-bordered">
                
                  <tbody class="">
                   <tr>
                      <th class="col-lg-1  col-sm-2 col-xs-2 col-md-2">
                      	学校分类：
                   	  </th>
	                    <th>
	                    
	                    <c:forEach items="${listSchool}" var="items" varStatus="status">
	                     	<c:choose >
	                     	
	                     		<c:when test="${fn:contains(url,items.id)}">
	                     		<c:set var="schoolall" value="ALL"></c:set>
	                     		</c:when>
	                     		<c:otherwise>
	                     		
	                     		</c:otherwise>
	                     	</c:choose>
	                     		
	                   		 </c:forEach>
	                    
	                    <c:choose >
	                     	
	                     		<c:when test="${not empty schoolall}">
	                     		<button onclick="window.location.href='${ctx}/resourcesCenter/center?pageNo=1${url}&school=ALL'" 
	                    	 style="margin: 0 10px 1px 0;" class="btn btn-xs btn-default-outline">全部</button>
	                     		</c:when>
	                     		<c:otherwise>
	                     		<button onclick="window.location.href='${ctx}/resourcesCenter/center?pageNo=1${url}&school=ALL'" 
	                    	 style="margin: 0 10px 1px 0;" class="btn btn-xs btn-primary-outline">全部</button>
	                     		</c:otherwise>
	                     	</c:choose>
	    				
	                    	 
	                     	<c:forEach items="${listSchool}" var="items" varStatus="status">
	                     	<c:choose >
	                     	
	                     		<c:when test="${fn:contains(url,items.id)}">
	                     		<button onclick="window.location.href='${ctx}/resourcesCenter/center?pageNo=1${url}&school=${items.id}'" 
	                     		style="margin: 0 10px 1px 0;" class="btn btn-xs btn-primary-outline">${items.name}</button>
	                     		</c:when>
	                     		<c:otherwise>
	                     		<button onclick="window.location.href='${ctx}/resourcesCenter/center?pageNo=1${url}&school=${items.id}'" 
	                     		style="margin: 0 10px 1px 0;" class="btn btn-xs btn-default-outline">${items.name}</button>
	                     		</c:otherwise>
	                     	</c:choose>
	                     		
	                   		 </c:forEach>
	                    </th>
					 </tr>
                  
                  
                    <tr>
                      <th class="col-lg-1  col-sm-2 col-xs-2 col-md-2">
                      	资源分类：
                   	  </th>
	                    <th>
	                    
	                    <c:forEach items="${listResourcesType}" var="items" varStatus="status">
	                     	<c:choose >
	                     	
	                     		<c:when test="${fn:contains(url,items.id)}">
	                     		<c:set var="all" value="ALL"></c:set>
	                     		</c:when>
	                     		<c:otherwise>
	                     		
	                     		</c:otherwise>
	                     	</c:choose>
	                     		
	                   		 </c:forEach>
	                    
	                    <c:choose >
	                     	
	                     		<c:when test="${not empty all}">
	                     		<button onclick="window.location.href='${ctx}/resourcesCenter/center?pageNo=1${url}&resources=ALL'" 
	                    	 style="margin: 0 10px 1px 0;" class="btn btn-xs btn-default-outline">全部</button>
	                     		</c:when>
	                     		<c:otherwise>
	                     		<button onclick="window.location.href='${ctx}/resourcesCenter/center?pageNo=1${url}&resources=ALL'" 
	                    	 style="margin: 0 10px 1px 0;" class="btn btn-xs btn-primary-outline">全部</button>
	                     		</c:otherwise>
	                     	</c:choose>
	    				
	                    	 
	                     	<c:forEach items="${listResourcesType}" var="items" varStatus="status">
	                     	<c:choose >
	                     	
	                     		<c:when test="${fn:contains(url,items.id)}">
	                     		<button onclick="window.location.href='${ctx}/resourcesCenter/center?pageNo=1${url}&resources=${items.id}'" 
	                     		style="margin: 0 10px 1px 0;" class="btn btn-xs btn-primary-outline">${items.name}</button>
	                     		</c:when>
	                     		<c:otherwise>
	                     		<button onclick="window.location.href='${ctx}/resourcesCenter/center?pageNo=1${url}&resources=${items.id}'" 
	                     		style="margin: 0 10px 1px 0;" class="btn btn-xs btn-default-outline">${items.name}</button>
	                     		</c:otherwise>
	                     	</c:choose>
	                     		
	                   		 </c:forEach>
	                    </th>
					 </tr>
                    <tr>
                      <th>
                      	资源类型：
                   	  </th>
	                    <th >
	                     <c:forEach items="${listType}" var="items" varStatus="status">
	                     	<c:choose >
	                     	
	                     		<c:when test="${fn:contains(url,items.key)}">
	                     		<c:set var="allresourcesType" value="ALL"></c:set>
	                     		</c:when>
	                     		<c:otherwise>
	                     		
	                     		</c:otherwise>
	                     	</c:choose>
	                     		
	                   		 </c:forEach>
	                    
	                    <c:choose >
	                     	
	                     		<c:when test="${not empty allresourcesType}">
	                     		<button onclick="window.location.href='${ctx}/resourcesCenter/center?pageNo=1${url}&resourcesType=ALL'" 
	                     style="margin: 0 10px 1px 0;" class="btn btn-xs btn-default-outline">全部</button>
	                     		</c:when>
	                     		<c:otherwise>
	                     		<button onclick="window.location.href='${ctx}/resourcesCenter/center?pageNo=1${url}&resourcesType=ALL'" 
	                     style="margin: 0 10px 1px 0;" class="btn btn-xs btn-primary-outline">全部</button>
	                     		</c:otherwise>
	                     	</c:choose>
	                    
	                    
	                    
	                     <c:forEach items="${listType}" var="items" varStatus="status">
	                     <c:choose >
	                     	
	                     		<c:when test="${fn:contains(url,items.key)}">
	                     		<button onclick="window.location.href='${ctx}/resourcesCenter/center?pageNo=1${url}&resourcesType=${items.key}'" 
	                      style="margin: 0 10px 1px 0;" class="btn btn-xs btn-primary-outline">${items.value}</button>
	                     		</c:when>
	                     		<c:otherwise>
	                     		<button onclick="window.location.href='${ctx}/resourcesCenter/center?pageNo=1${url}&resourcesType=${items.key}'" 
	                      style="margin: 0 10px 1px 0;" class="btn btn-xs btn-default-outline">${items.value}</button>
	                     		</c:otherwise>
	                     	</c:choose>
	                     
	                     
	                    </c:forEach>
	                    </th>
                    </tr>
                    <tr>
                      <th>
                      	年级：
                   	  </th>
	                    <th>
	                          <c:forEach items="${listGrade}" var="items" varStatus="status">
	                     	<c:choose >
	                     	
	                     		<c:when test="${fn:contains(url,items.id)}">
	                     		<c:set var="allgrade" value="ALL"></c:set>
	                     		</c:when>
	                     		<c:otherwise>
	                     		
	                     		</c:otherwise>
	                     	</c:choose>
	                     		
	                   		 </c:forEach>
	                    
	                    <c:choose >
	                     	
	                     		<c:when test="${not empty allgrade}">
	                     		
	                    <button onclick="window.location.href='${ctx}/resourcesCenter/center?pageNo=1${url}&grade=ALL'" 
	                     style="margin: 0 10px 1px 0;" class="btn btn-xs btn-default-outline">全部</button>
	                     		</c:when>
	                     		<c:otherwise>
	                     		
	                    <button onclick="window.location.href='${ctx}/resourcesCenter/center?pageNo=1${url}&grade=ALL'" 
	                     style="margin: 0 10px 1px 0;" class="btn btn-xs btn-primary-outline">全部</button>
	                     		</c:otherwise>
	                     	</c:choose>
	                    
	                    
	                     <c:forEach items="${listGrade}" var="items" varStatus="status">
	                      <c:choose >
	                     	
	                     		<c:when test="${fn:contains(url,items.id)}">
	                     		 <button onclick="window.location.href='${ctx}/resourcesCenter/center?pageNo=1${url}&grade=${items.id}'" 
	                     style="margin: 0 10px 1px 0;" class="btn btn-xs btn-primary-outline">${items.name}</button>
	                     		</c:when>
	                     		<c:otherwise>
	                     		 <button onclick="window.location.href='${ctx}/resourcesCenter/center?pageNo=1${url}&grade=${items.id}'" 
	                     style="margin: 0 10px 1px 0;" class="btn btn-xs btn-default-outline">${items.name}</button>
	                     		</c:otherwise>
	                     	</c:choose>
	                     
	                    
	                    </c:forEach>
	                    </th>
                    </tr>
                    <tr>
                      <th>
                      	学科：
                   	  </th>
	                    <th>
	                      <c:forEach items="${listSubject}" var="items" varStatus="status">
	                     	<c:choose >
	                     	
	                     		<c:when test="${fn:contains(url,items.id)}">
	                     		<c:set var="allsub" value="ALL"></c:set>
	                     		</c:when>
	                     		<c:otherwise>
	                     		
	                     		</c:otherwise>
	                     	</c:choose>
	                     		
	                   		 </c:forEach>
	                    
	                    <c:choose >
	                     	
	                     		<c:when test="${not empty allsub}">
	                     		
	                  <button onclick="window.location.href='${ctx}/resourcesCenter/center?pageNo=1${url}&subject=ALL'" 
	                     style="margin: 0 10px 1px 0;" class="btn btn-xs btn-default-outline">全部</button>
	                     		</c:when>
	                     		<c:otherwise>
	                     		
	                    <button onclick="window.location.href='${ctx}/resourcesCenter/center?pageNo=1${url}&subject=ALL'" 
	                     style="margin: 0 10px 1px 0;" class="btn btn-xs btn-primary-outline">全部</button>
	                     		</c:otherwise>
	                     	</c:choose>
	                    
	                    
	                     <c:forEach items="${listSubject}" var="items" varStatus="status">
	                      <c:choose >
	                     	
	                     		<c:when test="${fn:contains(url,items.id)}">
	                     		<button onclick="window.location.href='${ctx}/resourcesCenter/center?pageNo=1${url}&subject=${items.id}'" 
	                     style="margin: 0 10px 1px 0;" class="btn btn-xs btn-primary-outline">${items.name}</button>
	                     		</c:when>
	                     		<c:otherwise>
	                     		<button onclick="window.location.href='${ctx}/resourcesCenter/center?pageNo=1${url}&subject=${items.id}'" 
	                     style="margin: 0 10px 1px 0;" class="btn btn-xs btn-default-outline">${items.name}</button>
	                     		</c:otherwise>
	                     	</c:choose>
	                     
	                     
	                    </c:forEach>
	                    </th>
	               </tr>
	               <tr> 
                      <th>
                      	年份：
                   	  </th>
	                    <th>
	                     <c:forEach items="${listYear}" var="items" varStatus="status">
	                     	<c:choose >
	                     	
	                     		<c:when test="${fn:contains(url,items.key)}">
	                     		<c:set var="allyear" value="ALL"></c:set>
	                     		</c:when>
	                     		<c:otherwise>
	                     		
	                     		</c:otherwise>
	                     	</c:choose>
	                     		
	                   		 </c:forEach>
	                    
	                    <c:choose >
	                     	
	                     		<c:when test="${not empty allyear}">
	                     		
	                 <button onclick="window.location.href='${ctx}/resourcesCenter/center?pageNo=1${url}&year=ALL'" 
	                     style="margin: 0 10px 1px 0;" class="btn btn-xs btn-default-outline">全部</button>
	                     		</c:when>
	                     		<c:otherwise>
	                     		
	                   <button onclick="window.location.href='${ctx}/resourcesCenter/center?pageNo=1${url}&year=ALL'" 
	                     style="margin: 0 10px 1px 0;" class="btn btn-xs btn-primary-outline">全部</button>
	                     		</c:otherwise>
	                     	</c:choose>
	                    
	                    
	                     <c:forEach items="${listYear}" var="items" varStatus="status">
	                     
	                      <c:choose >
	                     	
	                     		<c:when test="${fn:contains(url,items.key)}">
	                     		 <button onclick="window.location.href='${ctx}/resourcesCenter/center?pageNo=1${url}&year=${items.key}'" 
	                      style="margin: 0 10px 1px 0;" class="btn btn-xs btn-primary-outline" >${items.value}</button>
	                     		</c:when>
	                     		<c:otherwise>
	                     		 <button onclick="window.location.href='${ctx}/resourcesCenter/center?pageNo=1${url}&year=${items.key}'" 
	                      style="margin: 0 10px 1px 0;" class="btn btn-xs btn-default-outline" >${items.value}</button>
	                     		</c:otherwise>
	                     	</c:choose>
	                     
	                    
	                    </c:forEach>
	                    </th>
                    </tr>
                  </tbody>
                </table>
              </div>
              
              <!-- 明细信息 -->
                <div class="row">
		           <div class="form-group">
		            <label class="control-label col-md-1 col-lg-1">排序：</label>
		            <div class="col-md-11">
		           		 <c:choose >
                    		<c:when test="${fn:contains(url,'browseNumberASE')}">
                    			<label style="padding-top: 0px;" class="radio-inline">
			              <input onclick="window.location.href='${ctx}/resourcesCenter/center?pageNo=1${url}&sortCol=browseNumberASE'" 
			               checked="" name="optionsRadios2" type="radio" value="option1"><span>热门</span></label>
                    		</c:when>
                    		<c:otherwise>
                    			<label style="padding-top: 0px;" class="radio-inline">
			              <input onclick="window.location.href='${ctx}/resourcesCenter/center?pageNo=1${url}&sortCol=browseNumberASE'" 
			                name="optionsRadios2" type="radio" value="option1"><span>热门</span></label>
                    		</c:otherwise>
                    	</c:choose>
                    	
                    	
                    	 <c:choose >
                    		<c:when test="${fn:contains(url,'downloadsNumberASE')}">
                    			<label style="padding-top: 0px;" class="radio-inline">
			              <input onclick="window.location.href='${ctx}/resourcesCenter/center?pageNo=1${url}&sortCol=downloadsNumberASE'" 
			               checked="" name="optionsRadios2" type="radio" value="option2"><span>下载量</span> </label>
                    		</c:when>
                    		<c:otherwise>
                    		<label style="padding-top: 0px;" class="radio-inline">
			              <input onclick="window.location.href='${ctx}/resourcesCenter/center?pageNo=1${url}&sortCol=downloadsNumberASE'" 
			                name="optionsRadios2" type="radio" value="option2"><span>下载量</span> </label>
                    		</c:otherwise>
                    	</c:choose>
                    	
                    	
                    	 <c:choose >
                    		<c:when test="${fn:contains(url,'uploadTimeASE')}">
                    			<label style="padding-top: 0px;" class="radio-inline">
			              <input onclick="window.location.href='${ctx}/resourcesCenter/center?pageNo=1${url}&sortCol=uploadTimeASE'" 
			              checked="" name="optionsRadios2" type="radio" value="option3"><span>按时间</span></label>
                    		</c:when>
                    		<c:otherwise>
                    			<label style="padding-top: 0px;" class="radio-inline">
			              <input onclick="window.location.href='${ctx}/resourcesCenter/center?pageNo=1${url}&sortCol=uploadTimeASE'" 
			               name="optionsRadios2" type="radio" value="option3"><span>按时间</span></label>
                    		</c:otherwise>
                    	</c:choose>
			              
			               
		              	
			               
		              	
          		</div>
                </div>
               
              </div>
              <!-- 资源详细 -->
              <div class="row">
	              	<div class="col-lg-12">
              <div class="widget-content padded">
                <ul class="gallery-grid clearfix">
                <c:forEach items="${pageList.datas}" var="items">
                <a href="${ctx}/resourcesCenter/getResourceDetail?resourceId=${items.id}">
                <li style="width: 200px;overflow:hidden;margin-left: 30px;">
                    <figure style="height: 180px">
                    <c:set var="img_check" value=""></c:set>
                    <c:if test="${fn:contains(items.fileSuperType,'PICTURE')}"><!-- 包含 PICTURE 表示些资源中有图片，则展示图片 -->
	                    <c:forEach items="${items.myFiles}" var="fileImg">
	                    <c:choose >
	                    <c:when test="${fileImg.fileType =='PICTURE'}">
		                     		<c:set var="img_check" value="${fileImg.id}"></c:set>
		                     		</c:when>
		                     		<c:otherwise>
		                     		
		                     		</c:otherwise>
		                     	</c:choose>
	                    </c:forEach>
	                    </c:if>
	                    
	                     <c:choose >
	                   <c:when test="${not empty img_check }">
	                   <img width="200" src="${pageContext.request.contextPath}/file/getBusinessThumbnailsImage?fileId=${img_check}&resourceId=${items.id}
	                   " />
	                   </c:when>
	                   <c:otherwise>
	                   <img height="180" src="${ctx}/assets/images/r_other.png" />
	                   </c:otherwise>
	                   </c:choose>
                      
                      <div class="caption" style="font-size: 10px;">
                      <span>
                      	<strong>
                       	 	资源中格式有：<br>
                       	 </strong>
                       	 </span>
                        <span>
                        <c:forEach items="${items.myFiles}" var="files">
                        ${files.extension}
                        </c:forEach>
						</span>
                      </div>
                    </figure>
                    <div style="font-size:10px;">
                    <div style="width:100%;">
                   		标题:${items.name}
                   	</div>
                   <div style="width:100%">
                   <div style="float: left;">作者：${items.udpateName }</div> 
                   <div style="float: right;">${items.grade.name} ${items.subject.name}
                   </div>
                   </div>
                  	<br>
                   时间：<fmt:formatDate value="${items.createTime}" type="both"/>
                    </div>
                   
                  </li>
                   </a>
                  </c:forEach>
                </ul>
              </div>
              
                                    <!-- 分页 -->
            <c:if test="${not empty pageList.datas}">
                <div class="widget-container fluid-height">
                  <div class="widget-content padded text-center">
                    <ul class="pagination">
                        <page:pagination pageination="${pageList}"/>
                    </ul>
                  </div>
         <!-- 分页 结束-->
          </div>
           </c:if>
          </div>
   
              </div>
      
            </div>
       
     
          </div>
     
          <!-- end Bordered Table -->
     
      </div>

                
</html>
