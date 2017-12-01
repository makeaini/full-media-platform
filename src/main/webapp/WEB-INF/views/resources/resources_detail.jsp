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
  <title>全媒体源云库中心－详细</title>

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
   
 <link href="${ctx}/assets/quote_user/tancuchen/css/style.css" rel="stylesheet" type="text/css" />

<%-- <script type="${ctx}/assets/quote_user/tancuchen/text/javascript" src="js/jquery-1.9.1.min.js"></script> --%>
   
</head>
<body>
<div class="modal-shiftfix">
<%@include file="../public/top.jsp" %>
<!-- 内容 -->
   <!-- End Navigation -->
    <div class="container-fluid main-content">
        <!-- Gallery with captions -->
        <div class="row"> 
        <div class="col-lg-8">
          <ul class="breadcrumb">
          全媒体资源中心 》 
            </ul>
            <div class="widget-container fluid-height">
            
              <div class="heading">
		 		
              </div>
           
              <div class="widget-content padded">
               <div class="col-lg-12">
                <ul class="clearfix " id="links" style="list-style-type:none;  ">
                <c:if test="${not empty resources}">
                <c:forEach items="${resources.myFiles}" var="file">
                <c:choose>
                 <c:when test="${file.fileType =='PICTURE'}">
                   <li style="width:150px;height: 180px; float: left;">
                   <div class="text-center">
                   	 <div style="width:120px;height: 120px; float: left;overflow:hidden; margin:0px 15px 15px 0px ">
	                     <a  class="linksa" href="${pageContext.request.contextPath}/file/getbusinessOriginalImage?fileId=${file.id}&resourceId=${param.resourceId}" title="${file.originalName}" >
	                      <img src="${ctx}/file/getBusinessThumbnailsImage?fileId=${file.id}&resourceId=${param.resourceId}" class="links"/>
	                      </a>
                     </div>
                      <div style="size: 12px" title="${file.originalName}">
                       <a href="${ctx}/file/downloadBusinessFile?fileId=${file.id}&resourceId=${param.resourceId}">
                          <c:choose>
                       <c:when test="${fn:length(file.originalName) > 8 }">${fn:substring(file.originalName, 0, 7)}...</c:when>
                       <c:otherwise>
                        ${file.originalName}
                       </c:otherwise>
                       </c:choose>
                     </a> 
                      </div>
                      </div>
                  </li>
                  </c:when>
                      <c:when test="${file.fileType=='DOCUMENT'}">
                	  <li style="width:150px;height: 180px; float: left;">
                    <div class="text-center">
                    	 <a target="_blank" href="${ctx}/file/resourcesVideoPlay?fileId=${file.id}&resourceId=${param.resourceId}">
                      <img   src="${pageContext.request.contextPath}/assets/images/document.png" />
                      	</a>
                       <div style="size: 12px" title="${file.originalName}">
                       <a href="${ctx}/file/downloadBusinessFile?fileId=${file.id}&resourceId=${param.resourceId}">
                          <c:choose>
                       <c:when test="${fn:length(file.originalName) > 8 }">${fn:substring(file.originalName, 0, 7)}...</c:when>
                       <c:otherwise>
                        ${file.originalName}
                       </c:otherwise>
                       </c:choose>
                     </a> 
                      </div>
                    </div>
                  </li>
                  </c:when>
                       <c:when test="${file.fileType=='OTHER'}">
                	   <li style="width:150px;height: 180px; float: left;">
                    <div class="text-center">
                      <img   src="${pageContext.request.contextPath}/assets/images/other.png" />
                         <div style="size: 12px" title="${file.originalName}">
                       <a href="${ctx}/file/downloadBusinessFile?fileId=${file.id}&resourceId=${param.resourceId}">
                          <c:choose>
                       <c:when test="${fn:length(file.originalName) > 8 }">${fn:substring(file.originalName, 0, 7)}...</c:when>
                       <c:otherwise>
                        ${file.originalName}
                       </c:otherwise>
                       </c:choose>
                     </a> 
                      </div>
                    </div>
                  </li>
                  </c:when>
                         <c:when test="${file.fileType=='COMPRESSION'}">
                	  <li style="width:150px;height: 180px; float: left;">
                    <div class="text-center">
                      <img   src="${pageContext.request.contextPath}/assets/images/compression.png" />
                      <div style="size: 12px" title="${file.originalName}">
                       <a href="${ctx}/file/downloadBusinessFile?fileId=${file.id}&resourceId=${param.resourceId}">
                          <c:choose>
                       <c:when test="${fn:length(file.originalName) > 8 }">${fn:substring(file.originalName, 0, 7)}...</c:when>
                       <c:otherwise>
                        ${file.originalName}
                       </c:otherwise>
                       </c:choose>
                     </a> 
                      </div>
                    </div>
                  </li>
                  </c:when>
                        <c:when test="${file.fileType=='MUSIC'}">
                	  <li style="width:150px;height: 180px; float: left;">
                    <div class="text-center">
                      <img src="${pageContext.request.contextPath}/assets/images/music.png" />
                      <div style="size: 12px" title="${file.originalName}">
                       <a href="${ctx}/file/downloadBusinessFile?fileId=${file.id}&resourceId=${param.resourceId}">
                          <c:choose>
                       <c:when test="${fn:length(file.originalName) > 8 }">${fn:substring(file.originalName, 0, 7)}...</c:when>
                       <c:otherwise>
                        ${file.originalName}
                       </c:otherwise>
                       </c:choose>
                     </a> 
                      </div>
                    </div>
                  </li>
                  </c:when>
                  <c:when test="${file.fileType=='VIDEO'}">
                	  <li style="width:150px;height: 180px; float: left;">
                    <div class="text-center">
                    <c:choose>
                    <c:when test="${file.videoDec=='DECODING'}">
                    <a target="_target" href="${pageContext.request.contextPath}/resourcesCenter/playVideoView?fileId=${file.id}&resourceId=${param.resourceId}">
                     <img src="${pageContext.request.contextPath}/assets/images/video.png" />  
                     </a>   
                     </c:when> 
                     <c:otherwise>
                     <div class="caption gallery-grid">
                        <img src="${pageContext.request.contextPath}/assets/images/video.png" title="正在转码,请稍候"/>  
                      </div>
                     </c:otherwise>  
                     </c:choose>         
                     <div style="size: 12px" title="${file.originalName}">
                       <a href="${ctx}/file/downloadBusinessFile?fileId=${file.id}&resourceId=${param.resourceId}">
                          <c:choose>
                       <c:when test="${fn:length(file.originalName) > 8 }">${fn:substring(file.originalName, 0, 7)}...</c:when>
                       <c:otherwise>
                        ${file.originalName}
                       </c:otherwise>
                       </c:choose>
                     </a> 
                      </div>
                    </div>
                  </li>
                  </c:when>
                  </c:choose>
     		 </c:forEach>
     		 </c:if>
                </ul>
              </div>
              </div>
            </div>
        </div>

        
          <div class="col-lg-4 hidden-sm hidden-xs">
          <div class="widget-container fluid-height">
              <div class="widget-content">
                <div class="panel-group" id="accordion">
                  <div class="panel">
                    <div class="panel-heading">
                      <div class="panel-title">
                        <a class="accordion-toggle" data-parent="#accordion" data-toggle="collapse" href="#collapseTwo">
                          <span>资源相关信息</span></a>
                      </div>
                    </div>
                
                    <div class="panel-collapse collapse in" id="collapseTwo">
                      <div class="panel-body">
                      	<div class="row">
					              <div class="col-md-6">
					              
					              <p class="">
					               资源名称：${resources.name}
					              </p>
					               <p class="">
					               年级：${resources.grade.name}
					              </p>
					               <p class="">
					               科目：${resources.subject.name}
					              </p>
					              <p class="">
					               日期：<fmt:formatDate value="${resources.createTime}" type="both"/>
					              </p>
					              </div>
					              <div class="col-md-6">
					              <p class="">
					              资源类别：${resources.resourcesType.name}
					              </p>
					               <p class="">
					               浏览资数：${resources.browseNumber}
					              </p>
					               <p class="">
					               下载量：${resources.downloadsNumber}
					              </p>
					              <p class="">
					              
					               文件数量：${fn:length(resources.myFiles) }
					              </p>
					              </div>
					    </div>
					    <!-- 下载按扭 -->
					    <div class="row">
					    <center >
					    <button onclick="window.location.href='${ctx}/resources/downloadResource?id=${param.resourceId}'" style="width: 200px;" class="btn btn-info-outline"><i class="icon-cloud-download"></i>Download</button>
					    </center>
					    </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
      
            </div>
           
          </div>
            
          
          <!-- 文件内容 -->       
        <!-- end Gallery with captions -->
      </div>
      
      
      <!-- 资源分享 -->
      
            <div class="row">
              <div class="col-lg-12">
                <div class="widget-container fluid-height">
                  <div class="heading tabs">
                    <i class="icon-sitemap"></i>CORED 精心为您推选  
                    <ul class="nav nav-tabs pull-right" data-tabs="tabs" id="tabs">
                      <li class="active">
                        <a data-toggle="tab" href="#tab1"><i class="icon-paper-clip"></i><span>相关资源</span></a>
                      </li>
                      <li>
                        <a data-toggle="tab" href="#tab2"><i class="icon-user"></i><span>作者相关资源</span></a>
                      </li>
                      <li>
                        <a data-toggle="tab" href="#tab3"><i class="icon-paper-clip"></i><span>猜您喜欢的资源</span></a>
                      </li>
                    </ul>
                  </div>
                  <div class="tab-content padded" id="my-tab-content">
                    <div class="tab-pane active" id="tab1">
                <div class="widget-content padded">
                <ul class="gallery-grid clearfix">
                <c:forEach items="${typeResources.datas}" var="items">
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
                    </div>
                    <div class="tab-pane" id="tab2">
                       <div class="widget-content padded">
                <ul class="gallery-grid clearfix">
                <c:forEach items="${peopleResource.datas}" var="items">
                <a href="${ctx}/resourcesCenter/getResourceDetail?resourceId=${items.id}">
                <li style="width: 200px;overflow:hidden;margin-left: 30px;">
                    <figure style="height: 180px">
                    <c:choose>
                    <c:when test="${fn:contains(items.fileSuperType,'PICTURE')}"><!-- 包含 PICTURE 表示些资源中有图片，则展示图片 -->
	                    <c:forEach items="${items.myFiles}" var="fileImg">
	                    <c:choose >
	                    <c:when test="${fileImg.fileType =='PICTURE'}">
		                     		<c:set var="img_check2" value="${fileImg.id}"></c:set>
		                     		</c:when>
		                     		<c:otherwise>
		                     		
		                     		</c:otherwise>
		                     	</c:choose>
	                    </c:forEach>
	                     <c:choose >
	                   		<c:when test="${not empty img_check2 }">
	                   			<img width="200" src="${pageContext.request.contextPath}/file/getBusinessThumbnailsImage?fileId=${img_check2}&resourceId=${items.id}" />
	                   		</c:when>
	                   		<c:otherwise>
	                   			<img height="180" src="${ctx}/assets/images/r_other.png" />
	                   		</c:otherwise>
	                   </c:choose>
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
                    </div>
                    <div class="tab-pane" id="tab3">
                       <div class="widget-content padded">
                <ul class="gallery-grid clearfix">
                <c:forEach items="${newResources.datas}" var="items">
                <a href="${ctx}/resourcesCenter/getResourceDetail?resourceId=${items.id}">
                <li style="width: 200px;overflow:hidden;margin-left: 30px;">
                    <figure style="height: 180px">
                      <c:choose>
                    <c:when test="${fn:contains(items.fileSuperType,'PICTURE')}"><!-- 包含 PICTURE 表示些资源中有图片，则展示图片 -->
	                    <c:forEach items="${items.myFiles}" var="fileImg">
	                    <c:choose >
	                    <c:when test="${fileImg.fileType =='PICTURE'}">
		                     		<c:set var="img_check3" value="${fileImg.id}"></c:set>
		                     		</c:when>
		                     		<c:otherwise>
		                     		
		                     		</c:otherwise>
		                     	</c:choose>
	                    </c:forEach>
	                     <c:choose >
	                   		<c:when test="${not empty img_check3 }">
	                   			<img width="200" src="${pageContext.request.contextPath}/file/getBusinessThumbnailsImage?fileId=${img_check3}&resourceId=${items.id}" />
	                   		</c:when>
	                   		<c:otherwise>
	                   			<img height="180" src="${ctx}/assets/images/r_other.png" />
	                   		</c:otherwise>
	                   </c:choose>
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
                    </div>
                  </div>
                </div>
              </div>
            </div>
      	<!-- 评论 -->
           <div class="row">
              <div class="col-lg-12">
                <div class="widget-container fluid-height">
                  <div class="heading tabs">
                    <i class="icon-keyboard"></i>分享您的心德 
                    <ul class="nav nav-tabs pull-right" data-tabs="tabs" id="tabs">
                      <li class="active">
                        <a data-toggle="tab" href="#tab1-tl"><i class="icon-list-alt"></i><span>我要评论</span></a>
                      </li>
                      <li>
                        <a data-toggle="tab" href="#tab2-tl"><i class="icon-paste"></i><span>历史评论</span></a>
                      </li>
                    </ul>
                  </div>
                  <div class="tab-content padded" id="my-tab-content">
                    <div class="tab-pane active" id="tab1-tl">
                 <div class="row">
				  <div class="col-lg-12">
				  <div class="widget-content padded">
                    <form action="${ctx}/resourcesCenter/saveComment" method="post">
                     	<div class="form-group">
				            <label class="control-label col-md-1">姓名：</label>
				            <div class="col-md-11">
				              <label class="checkbox-inline">
				              <input checked="checked" name="type" type="radio" value="1"><span>匿名</span>
				              </label>
				              <label class="checkbox-inline">
				              <input name="type" type="radio" value="2"><span>真实姓名</span>
				              </label>
				            </div>
				          </div>
				          <div class="form-group">
				            <label class="control-label col-md-1">内容</label>
				            <div class="col-md-11">
				              <textarea class="form-control" rows="3" name="message"></textarea>
				            </div>
				          </div>
				          <input type="hidden" value="${param.resourceId}" name="resourceId">
				            <div class="form-group text-center">
				              <button style="margin-top: 15px;" class="btn btn-primary" type="submit">提交评论信息</button>
				            </div>
				        </form>
				       </div>
                    </div>
				    </div>  
                    </div>
                    <div class="tab-pane" id="tab2-tl">
                     <div class="row">
                     <div class="col-lg-12">
			            <div class=" scrollable list rating-widget">
			              <div class="heading">
			                <i class="icon-comment"></i>参与评论
			              </div>
			                
			              <div class="widget-content">
			                <ul>
			             
			                 <c:if test="${not empty resources}">
               				 <c:forEach items="${resources.comments}" var="comments">
			               <c:if test="${comments.audit=='AUDIT'}"> 
			                  <li>
			                    <div class="reviewer-info">
			                      <a href="javascript:;"> ${comments.name}</a><em>
								<fmt:formatDate value="${comments.commentdate}" type="both"/>
								</em>
			                    </div>
			                    <div class="review-text">
			                      <p>
			                      ${comments.message}
			                      </p>
			                    </div>
			                  </li>
			               </c:if> 
			                  </c:forEach>
			                  </c:if>
			                </ul>
			              </div>
			            </div>
			          </div>
                     </div>
                    </div>
                  </div>
                </div>
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



<!-- 侧面弹出层 -->
<div id="rightArrow" style="display: none"><a href="javascript:;" title="在线客户"></a></div>
<div id="floatDivBoxs" style="display: none">
	<div class="floatDtt">在线音乐</div>
    <div class="floatShadow">
        <div class="col-lg-12 hidden-sm hidden-xs" id="musicplay">
          <div class="widget-container fluid-height">
              <div class="widget-content">
                <div class="panel-group" id="musicpanal">
                  <div class="panel">
					<div class="music-player" style="margin: 0 auto; ">
					<!-- 音乐播放 歌名 -->
					  <div class="info" >
					    <div class="left">
					    </div>
					    <div class="center">
					    <div class="jp-playlist">
					      <ul>
					        <li></li>
					      </ul>
					    </div>
					
					    </div>
					    <div class="right">
					    </div>
					
					    <div class="progress jp-seek-bar">
					      <span class="jp-play-bar" style="width: 0%"></span>
					    </div>
					  </div>
					
					  <div class="controls">
					    <div class="current jp-current-time" >00:00</div>
					    <div class="play-controls">
					      <a href="javascript:;" class="icon-previous jp-previous" title="previous"></a>
					      <a href="javascript:;" class="icon-play jp-play" title="play"></a>
					      <a href="javascript:;" class="icon-pause jp-pause" title="pause"></a>
					      <a href="javascript:;" class="icon-next jp-next" title="next"></a>
					    </div>
					    <div class="volume-level jp-volume-bar">
					      <span class="jp-volume-bar-value" style="width: 0%"></span>
					      <a href="javascript:;" class="icon-volume-up jp-volume-max" title="max volume"></a>
					      <a href="javascript:;" class="icon-volume-down jp-mute" title="mute"></a>
					    </div>
					  </div>
					  <div id="jquery_jplayer" class="jp-jplayer"></div>
					</div>
					  
                      </div>
                      
                     
                    </div>
                   
                  </div>
                </div>
              </div>
    </div>
</div>

</body>
<%-- <link rel="stylesheet" href="${ctx}/assets/Mp3Player/css/reset.css"> --%>
<link rel="stylesheet" href="${ctx}/assets/Mp3Player/css/style.css" media="screen" type="text/css" />
<script src="${ctx}/assets/Mp3Player/js/jquery.jplayer.min.js"></script>
<script src="${ctx}/assets/Mp3Player/js/jplayer.playlist.min.js"></script>

<%-- <script src="${ctx}/assets/Mp3Player/js/index.js"></script> --%>

<script type="text/javascript"></script>

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
<script src="${ctx}/assets/jquery-ui/external/jquery.js"></script>
<script type="text/javascript">
var resourceId='${param.resourceId}';
$(document).ready(function(){
	var playlist = new Array();
	$.post("${ctx}/resourcesCenter/playmusiclist",{"resourceId":'${param.resourceId}'},function(data){
		if(data!=null){
			var music=eval(data.data);
			console.info(music.length);
			if(music.length==0){
				//$("#musicpanal").css("display","none");
				//当音乐文件＝0时， 
				$("#rightArrow").css("display","none");
				$("#floatDivBoxs").css("display","none");
			}else{
				//有音乐文件是，显示音乐播放器 display: block;
				$("#rightArrow").css("display","block");
				$("#floatDivBoxs").css("display","block");
			}
			for(i=0;i<music.length;i++){
				playlist.push({
					title:music[i].originalName,
				    mp3:"http://localhost:8080/full-media-platform/file/resourcesVideoPlay?fileId="+music[i].id+"&resourceId="+resourceId,
				});
				
			}
			  var cssSelector = {
					    jPlayer: "#jquery_jplayer",
					    cssSelectorAncestor: ".music-player"
					  };
					  
					  var options = {
					    swfPath: "Jplayer.swf",
					    supplied: "ogv, m4v, oga, mp3"
					  };
					  console.info(playlist);
					  var myPlaylist = new jPlayerPlaylist(cssSelector, playlist, options);
			
		}
	});
});



</script>



<!-- 右边的弹出层播放器 -->
<script type="text/javascript">
var flag=1;
$('#rightArrow').click(function(){
	if(flag==1){
		$("#floatDivBoxs").animate({right: '-375px'},300);
		$(this).animate({right: '-5px'},300);
		$(this).css('background-position','-50px 0');
		flag=0;
	}else{
		$("#floatDivBoxs").animate({right: '0'},300);
		$(this).animate({right: '370px'},300);
		$(this).css('background-position','0px 0');
		flag=1;
	}
});
</script>


</html>

