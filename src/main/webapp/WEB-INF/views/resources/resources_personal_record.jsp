<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib uri="page" prefix="page" %>
<%@ taglib uri="path" prefix="fileTag" %>
<%@page isELIgnored="false"%> 
<!doctype html>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>个人－资源记录</title>

  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description" content="">
  <meta name="keywords" content="index">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <meta name="apple-mobile-web-app-title" content="WeChat" />
  <link rel="shortcut icon" href="${ctx}/assets/images/logo_title.png" 
   type="image/x-icon" />

   <link rel="stylesheet" href="${ctx}/assets/Gallery-2.16.0/css/blueimp-gallery.min.css">   
<script src="${ctx}/assets/Gallery-2.16.0/js/blueimp-gallery.min.js"></script>
   
   
</head>
<body>
<div class="modal-shiftfix">
<%@include file="../public/top.jsp" %>
<!-- 内容 -->
   <!-- End Navigation -->
    <div class="container-fluid main-content">
        <div class="page-title">
          <h1>
            时间轴－个人
          </h1>
        </div>
        <ul class="timeline animated" id="links">
         <c:forEach items="${listResources}" var="items">
          <li>
            <div class="timeline-time">
<!--               <strong>Oct 3</strong>4:53 PM -->
		<fmt:formatDate value="${items.createTime}" type="both"/>
            </div>
            <div class="timeline-icon">
              <div class="bg-primary">
                <i class="icon-pencil"></i>
              </div>
            </div>
            <div class="timeline-content">
              <h2>
               <span> ${items.name} - ${items.resourcesType.name}</span>
               <span style="float: right;font-size: 14px;"> 作者：${items.udpateName} </span>
              </h2>
              <!-- 详细信息－备注 -->
                
              <div class="row">
              <c:forEach items="${items.myFiles}" var="myfile">
               <c:choose>
                 <c:when test="${myfile.fileType =='PICTURE'}">
              <div style="width:120px;height: 120px; float: left;overflow:hidden; margin:0px 15px 15px 0px ">
              <a href="${pageContext.request.contextPath}/file/getbusinessOriginalImage?fileId=${myfile.id}&resourceId=${items.id}" title="${file.originalName}" >
              	<img width="120" src="${ctx}/file/getBusinessThumbnailsImage?fileId=${myfile.id}&resourceId=${items.id}" class="links">
              	</a>
              </div>
              </c:when>
               <c:when test="${myfile.fileType =='OTHER'}">
              <div style="width:120px;height: 120px; float: left;overflow:hidden; margin:0px 15px 15px 0px ">
                <img  width="120" src="${pageContext.request.contextPath}/assets/images/other.png" />
              </div>
              </c:when>
                <c:when test="${myfile.fileType =='DOCUMENT'}">
              <div style="width:120px;height: 120px; float: left;overflow:hidden; margin:0px 15px 15px 0px ">
                <img  width="120" src="${pageContext.request.contextPath}/assets/images/document.png" />
              </div>
              </c:when>
            <c:when test="${myfile.fileType =='COMPRESSION'}">
              <div style="width:120px;height: 120px; float: left;overflow:hidden; margin:0px 15px 15px 0px ">
                <img  width="120" src="${pageContext.request.contextPath}/assets/images/compression.png" />
              </div>
              </c:when>
             <c:when test="${myfile.fileType =='MUSIC'}">
              <div style="width:120px;height: 120px; float: left;overflow:hidden; margin:0px 15px 15px 0px ">
                <img  width="120" src="${pageContext.request.contextPath}/assets/images/music.png" />
              </div>
              </c:when>
              <c:when test="${myfile.fileType =='VIDEO'}">
              <div style="width:120px;height: 120px; float: left;overflow:hidden; margin:0px 15px 15px 0px ">
                <img  width="120" src="${pageContext.request.contextPath}/assets/images/video.png" />
              </div>
              </c:when>
              </c:choose>
              </c:forEach>
              </div>
              <p>
                ${items.remark} 
              </p>
            </div>
          </li>
          </c:forEach>
          
        </ul>
      </div>
    </div>
  
  <div id="blueimp-gallery" class="blueimp-gallery blueimp-gallery-controls">
    <div class="slides"></div>
    <h3 class="title"></h3>
    <a class="prev">‹</a>
    <a class="next">›</a>
    <a class="close">×</a>
    <a class="play-pause"></a>
    <ol class="indicator"></ol>
</div>

</body>
<script type="text/javascript">
	$(".links").click(function(event){
	    event = event || window.event;
	    var target = event.target || event.srcElement,
	        link = target.src ? target.parentNode : target,
	        options = {index: link, event: event, slideshowInterval: 3000},
	        links = document.getElementById("links").getElementsByTagName('a');
	   		blueimp.Gallery(links, options);
	});
</script>
</html>

