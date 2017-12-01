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
  <title>个人网盘</title>

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
            个人网盘
          </h1>
        </div>
        <!-- Gallery with captions -->
        <div class="row"> 
          <div class="col-sm-3 hidden-sm hidden-md hidden-xs">
          <div class="widget-container fluid-height">
              <div class="widget-content">
                <div class="panel-group" id="accordion">
                  <div class="panel">
                    <div class="panel-heading">
                      <div class="panel-title">
                        <a class="accordion-toggle" data-parent="#accordion" data-toggle="collapse" href="#collapseTwo">
                          <span>我的私人资料</span></a>
                      </div>
                    </div>
                
                    <div class="panel-collapse collapse in" id="collapseTwo">
                      <div class="panel-body">
                         <!-- active -->
              <a class="list-group-item active" href="javascript:;">
                <p>
                  <span class="badge" style="background-color: #4282E2">目录</span> <i class="icon-user" style="padding-right: 10px;"> </i> 全部文件
                </p>
              </a><a class="list-group-item" href="${ctx}/personalfile/querypersonbytype?fileType=VIDEO">
                <p>
                  <span class="badge" style="background-color: #4282E2">${fileCount.VIDEO}</span> <i class="icon-film" style="padding-right: 10px;"></i>视频
                </p>
              </a>
              <a class="list-group-item" href="${ctx}/personalfile/querypersonbytype?fileType=DOCUMENT">
                <p>
                  <span class="badge" style="background-color: #4282E2">${fileCount.DOCUMENT}</span><i class="icon-file-text" style="padding-right: 10px;"></i>文档
                </p>
              </a><a class="list-group-item" href="${ctx}/personalfile/querypersonbytype?fileType=PICTURE">
                <p>
                  <span class="badge" style="background-color: #4282E2">${fileCount.PICTURE}</span><i class="icon-camera-retro" style="padding-right: 10px;"></i>图片
                </p>
              </a><a class="list-group-item" href="${ctx}/personalfile/querypersonbytype?fileType=MUSIC">
                <p>
                  <span class="badge" style="background-color: #4282E2">${fileCount.MUSIC}</span><i class="icon-music" style="padding-right: 10px;"></i>音频
                </p>
              </a><a class="list-group-item" href="${ctx}/personalfile/querypersonbytype?fileType=OTHER">
                <p>
                  <span class="badge" style="background-color: #4282E2">${fileCount.OTHER}</span><i class="icon-hdd" style="padding-right: 10px;"></i>其他
                </p>
              </a>
                </a><a class="list-group-item" href="${ctx}/personalfile/querypersonbytype?fileType=COMPRESSION">
                <p>
                <span class="badge" style="background-color: #4282E2">${fileCount.COMPRESSION}</span><i class="icon-book" style="padding-right: 10px;"></i>压缩包
                </p>
              </a>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- 文件内容 -->
          <div class="col-lg-9">
          <ul class="breadcrumb">
          <fileTag:path fileIds="${param.pathIds}" filePath="${param.filePath}"/>
            </ul>
            <div class="widget-container fluid-height">
            
              <div class="heading">
              	 <a class="fancybox" href="#fancybox-example">
                    <button class="btn btn-primary"><i class="icon-folder-open-alt"></i>新建文件夹</button>
                 </a>
				 <a data-toggle="modal" href="#myModal">
                    <button class="btn btn-info"><i class="icon-cloud-upload"></i>上传文件</button>
                 </a>
              
                      <c:if test="${not empty pagination.datas}">
                    <button class="btn btn-info"><i class="icon-cloud-download"></i>下载</button>
                   	<a  class="btn btn-danger fancybox" href="#fancybox-example-delete" onclick="batchDelete()">删除</a>
                     <div class="check-header hidden-xs" style="float: right;">
                     <label ><input id="checkAll" name="checkAll" type="checkbox"><span>全选</span></label>
                     </div>
<!--                      <label  class="checkbox"><input type="checkbox"><span></span></label> -->
                    </c:if>
                    
              </div>
           
              <div class="widget-content padded" id="context-panal">
               <div class="col-lg-12">
                <input type="hidden" value="${param.filePath}" id="filePath" name="filePath">
                <input type="hidden" value="${param.pathIds}" id="pathIds" name="pathIds">
                <ul class="clearfix " id="links" style="list-style-type:none;  ">
                <c:if test="${not empty pagination.datas}">
                <c:forEach items="${pagination.datas}" var="file">
                <c:choose>
                 <c:when test="${file.fileType =='PICTURE'}">
                   <li class="col-lg-3 col-sm-4 col-xs-6 col-md-3">
                  <div class="text-center" onmousemove="showBDD('${file.id}')" onmouseout="hideBDD('${file.id}')">
<!-- 		              <label style="width: 10px;" class="checkbox"><input type="checkbox"><span></span></label> -->
		              <div class="check hidden-xs">
                        <label><input  type="checkbox" name="checkbox" class="checkboxClass" value="${file.id}"><span></span></label>
                      </div>
		              <center>
			          	 <div class="text-center" style="width:128px;height: 128px;overflow:hidden;">
			          	 <a class="linksa" href="${pageContext.request.contextPath}/file/getOriginalImage/${file.id}" title="${file.originalName}" >
	                      <img src="${pageContext.request.contextPath}/file/getThumbnailsImage/${file.id}"  class="links" />
	                     </a>
	                      </div>
                        </center>
                      <div id="text_${file.id}"  style="font-size: 14px;padding-top: 5px;" title="${file.originalName}" >
	                     <a href="${ctx}/file/downloadFile/${file.id}">
	                       <c:choose>
		                       <c:when test="${fn:length(file.originalName) > 8 }">${fn:substring(file.originalName, 0, 7)}...</c:when>
		                       <c:otherwise>
		                        ${file.originalName}
		                       </c:otherwise>
	                       </c:choose>
	                    </a>
                      </div>
                      <div class="text-center" style="font-size: 14px;padding-top: 5px;display: none" id="bdd_${file.id}" >
	                   		<span><a href="${ctx}/file/downloadFile/${file.id}">下载</a></span>
	                   		<span style="padding-left: 30px;">
	                   			<a  class="fancybox" href="#fancybox-example-delete"  onclick="deletes('${file.id}')">删除</a>
<%-- 	                   		<a href="${ctx}/personalfile/batchdeletefile?fileIds=${file.id}">删除</a> --%>
	                   		</span> 
			           </div>
                    </div>
                  </li>
                  </c:when>
                   <c:when test="${file.fileType=='FOLDER'}">
                	 <li class="col-lg-3 col-sm-4 col-xs-6 col-md-3">
                    <div class="text-center" onmousemove="showBDD('${file.id}')"
		                    	onmouseout="hideBDD('${file.id}')">
		              <label style="width: 10px;" class="checkbox"><input type="checkbox" name="checkbox" class="checkboxClass" value="${file.id}"><span></span></label>
                    <a href="javascript:;" onclick="clickFolder('${file.id}','${file.originalName}')">
                      <img title="${file.originalName}" src="${pageContext.request.contextPath}/assets/images/folder.png" />
                      </a>
                      <div id="text_${file.id}" style="font-size: 14px;" title="${file.originalName}">
	                      <a href="${ctx}/file/downloadFile/${file.id}">
	                       <c:choose>
	                       <c:when test="${fn:length(file.originalName) > 8 }">
	                       ${fn:substring(file.originalName, 0, 7)}...</c:when>
	                       <c:otherwise>
	                         ${file.originalName}
	                       </c:otherwise>
	                       </c:choose>
	                     </a>
                     </div>
                     <div class="text-center" style="font-size: 14px;display: none" id="bdd_${file.id}" >
                   		<a href="${ctx}/file/downloadFile/${file.id}"><span>下载</span>  </a>
                   		<span style="padding-left: 30px;">
                   		<a  class="fancybox" href="#fancybox-example-delete"  onclick="deletes('${file.id}')">删除</a>
                   		</span>
                   		<%-- <a href="${ctx}/personalfile/batchdeletefile?fileIds=${file.id}"><span style="padding-left: 30px;">删除</span></a> --%>
			          </div>
                    </div>
                  </li>
                  </c:when>
                      <c:when test="${file.fileType=='DOCUMENT'}">
	                	 <li class="col-lg-3 col-sm-4 col-xs-6 col-md-3">
		                    <div class="text-center" onmousemove="showBDD('${file.id}')"
		                    	onmouseout="hideBDD('${file.id}')">
		                    	<label style="width: 10px;" class="checkbox"><input type="checkbox" name="checkbox" class="checkboxClass" value="${file.id}"><span></span></label>
			                      <a target="_blank" href="${ctx}/file/onlineBrowse/${file.id}">
			                      <img title="${file.originalName}"   src="${pageContext.request.contextPath}/assets/images/document.png" />
			                      </a>
			                    <div id="text_${file.id}" style="font-size: 14px" title="${file.originalName}">
			                        <a href="${ctx}/file/downloadFile/${file.id}">
				                       <c:choose>
				                       <c:when test="${fn:length(file.originalName) > 8 }">${fn:substring(file.originalName, 0, 7)}...</c:when>
				                       <c:otherwise>
				                    	 ${file.originalName}
				                       </c:otherwise>
				                       </c:choose>
			                       </a>
			                   	</div>
			                   	<div class="text-center" style="font-size: 14px;display: none" id="bdd_${file.id}" >
			                   		<span><a href="${ctx}/file/downloadFile/${file.id}">下载</a></span>
			                   		 <span style="padding-left: 15px;"> <a target="_blank" href="${ctx}/file/onlineBrowse/${file.id}">预览 </a></span>
			                   		<span style="padding-left: 15px;">
			                   	    <a  class="fancybox" href="#fancybox-example-delete"  onclick="deletes('${file.id}')">删除</a>
			                   	<%-- 	<a href="${ctx}/personalfile/batchdeletefile?fileIds=${file.id}">删除</a> --%>
			                   		</span> 
			                   	</div>
		                    </div>
	                  	</li>
                  </c:when>
                       <c:when test="${file.fileType=='OTHER'}">
                	 <li class="col-lg-3 col-sm-4 col-xs-6 col-md-3">
                   <div class="text-center" onmousemove="showBDD('${file.id}')" onmouseout="hideBDD('${file.id}')">
		              <label style="width: 10px;" class="checkbox"><input type="checkbox" name="checkbox" class="checkboxClass" value="${file.id}"><span></span></label>
                      <img   src="${pageContext.request.contextPath}/assets/images/other.png" />
                      <div id="text_${file.id}"  style="font-size: 14px" title="${file.originalName}">
	                       <a href="${ctx}/file/downloadFile/${file.id}">
                           	<c:choose>
		                       <c:when test="${fn:length(file.originalName) > 8 }">${fn:substring(file.originalName, 0, 7)}...</c:when>
		                       <c:otherwise>
		                        ${file.originalName}
		                       </c:otherwise>
	                      	 </c:choose>
	                       </a>
                       </div>
                       	<div class="text-center" style="font-size: 14px;display: none" id="bdd_${file.id}" >
	                   		<span><a href="${ctx}/file/downloadFile/${file.id}">下载</a></span>
	                   		<span style="padding-left: 30px;">
	                   		 <a  class="fancybox" href="#fancybox-example-delete"  onclick="deletes('${file.id}')">删除</a>
	                   		<%-- <a href="${ctx}/personalfile/batchdeletefile?fileIds=${file.id}">删除</a> --%>
	                   		</span> 
			             </div>
                    </div>
                  </li>
                  </c:when>
                  
                  <c:when test="${file.fileType=='COMPRESSION'}">
                	 <li class="col-lg-3 col-sm-4 col-xs-6 col-md-3">
                     <div class="text-center" onmousemove="showBDD('${file.id}')" onmouseout="hideBDD('${file.id}')">
		              <label style="width: 10px;" class="checkbox"><input type="checkbox" name="checkbox" class="checkboxClass" value="${file.id}"><span></span></label>
                      <img   src="${pageContext.request.contextPath}/assets/images/compression.png" />
                        <div id="text_${file.id}" style="font-size: 14px" title="${file.originalName}">
                        <a href="${ctx}/file/downloadFile/${file.id}">
						     <c:choose>
                       <c:when test="${fn:length(file.originalName) > 8 }">${fn:substring(file.originalName, 0, 7)}...</c:when>
                       <c:otherwise>
                        ${file.originalName}
                       </c:otherwise>
                       </c:choose>
                       </a>
					</div>
					<div class="text-center"  style="font-size: 14px;display: none" id="bdd_${file.id}" >
                   		<span><a href="${ctx}/file/downloadFile/${file.id}">下载</a></span>
                   		<span style="padding-left: 30px;"><a href="${ctx}/personalfile/batchdeletefile?fileIds=${file.id}">删除</a></span> 
			        </div>
                    </div>
                  </li>
                  </c:when>
                        <c:when test="${file.fileType=='MUSIC'}">
                	 <li class="col-lg-3 col-sm-4 col-xs-6 col-md-3">
                    <div class="text-center" onmousemove="showBDD('${file.id}')" onmouseout="hideBDD('${file.id}')">
		              <label style="width: 10px;" class="checkbox"><input type="checkbox" name="checkbox" class="checkboxClass" value="${file.id}"><span></span></label>
                      <img   src="${pageContext.request.contextPath}/assets/images/music.png" />
                      <div id="text_${file.id}" style="font-size: 14px" title="${file.originalName}">
                        <a href="${ctx}/file/downloadFile/${file.id}">
							<c:choose>
		                       <c:when test="${fn:length(file.originalName) > 8 }">${fn:substring(file.originalName, 0, 7)}...</c:when>
		                       <c:otherwise>
		                        ${file.originalName}
		                       </c:otherwise>
	                       </c:choose>
						</a>
					</div>
					<div class="text-center" style="font-size: 14px;display: none" id="bdd_${file.id}" >
                   		<span><a href="${ctx}/file/downloadFile/${file.id}">下载</a></span>
                   		<%--  <span style="padding-left: 15px;"> <a target="_blank" href="${ctx}/file/onlineBrowse/${file.id}">播放 </a></span> --%>
                   		<span style="padding-left: 15px;">
                   		<a  class="fancybox" href="#fancybox-example-delete"  onclick="deletes('${file.id}')">删除</a>
                   	<%-- 	<a href="${ctx}/personalfile/batchdeletefile?fileIds=${file.id}">删除</a> --%>
                   		</span> 
			         </div>
                    </div>
                  </li>
                  </c:when>
                  
                  <c:when test="${file.fileType=='VIDEO'}">
                	 <li class="col-lg-3 col-sm-4 col-xs-6 col-md-3">
                    <div class="text-center" onmousemove="showBDD('${file.id}')" onmouseout="hideBDD('${file.id}')">
		              <label style="width: 10px;" class="checkbox"><input type="checkbox" name="checkbox" class="checkboxClass" value="${file.id}"><span></span></label>
		              <img   src="${pageContext.request.contextPath}/assets/images/video.png" /> 
		              <div id="text_${file.id}" style="font-size: 14px" title="${file.originalName}">
	                        <a href="${ctx}/file/downloadFile/${file.id}">
	                        <c:choose>
	                       <c:when test="${fn:length(file.originalName) > 8 }">${fn:substring(file.originalName, 0, 7)}...</c:when>
	                       <c:otherwise>
	                        ${file.originalName}
	                       </c:otherwise>
	                       </c:choose>
	                       </a>
                       </div>
                       <div class="text-center" style="font-size: 14px;display: none" id="bdd_${file.id}" >
	                   		<span><a href="${ctx}/file/downloadFile/${file.id}">下载</a></span>
	                   		 <span style="padding-left: 15px;">
	                   		 <c:if test="${file.videoDec=='DECODING'}">
	                   		   <a target="_blank" href="${ctx}/personalfile/videoPlayview/${file.id}">播放 </a>
	                   		   </c:if>
	                   		   </span>
	                   		<span style="padding-left: 15px;">
	                   		<a  class="fancybox" href="#fancybox-example-delete"  onclick="deletes('${file.id}')">删除</a>
	                   		<%-- <a href="${ctx}/personalfile/batchdeletefile?fileIds=${file.id}">删除</a> --%>
	                   		</span> 
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
            <!-- 分页 -->
            <c:if test="${not empty pagination.datas}">
                <div class="widget-container fluid-height">
                  <div class="widget-content padded text-center">
                    <ul class="pagination">
                        <page:pagination pageination="${pagination}"/>
                    </ul>
                  </div>
         
         <!-- 分页 结束-->
            
          </div>
           </c:if>
        </div>
        <!-- end Gallery with captions -->
    
      </div>
    </div>
    
			<!-- 新建文件夹－弹出层 -->
           <div id="fancybox-example" style="display: none">
                  <h4>新建文件夹 </h4>
       
                  <div class="row"> 
          <div class="form-horizontal">
            <label class="control-label col-md-4">文件夹名</label>
            <div class="col-md-8">
              <input class="form-control" id="originalName" name="originalName" placeholder="文件夹名" type="text">
              <input name="originalName" name="parentId"  id="parentId" type="hidden" value="${param.parentId}">
        	   <input name="pageNo"  id="pageNo" type="hidden" value="${param.pageNo}">
            </div>
          </div>
   
          </div>
        
 			<div class="row"> 
   					 <div class="text-right">
                        <button class="btn btn-primary" id="btn-createfolder" type="button">保存</button>
<!--                         <button id="btn-close-createfolder" class="btn btn-default-outline" type="button">关闭</button> -->
                      </div>
                      </div>
             </div>
             
             
                <!-- 上传文件－弹出层 -->
          <div class="modal fade" id="myModal">
                  <div class="modal-dialog">
                    <div class="modal-content">
                      <div class="modal-header">
                        <button aria-hidden="true" class="close" data-dismiss="modal" type="button">&times;</button>
                        <h4 class="modal-title">
                          &nbsp;
                        </h4>
                      </div>
                      <div class="modal-body">
                    	<div id="uploader">
							<p>Your browser doesn't have Flash, Silverlight or HTML5 support.</p>
						</div>
                      </div>
                     <!--  <div class="modal-footer">
                        <button class="btn btn-primary" type="button">Save Changes</button><button class="btn btn-default-outline" data-dismiss="modal" type="button">Close</button>
                      </div> -->
                    </div>
                  </div>
                </div>
                </div>
                
                  <!-- 删除与批量删除 -->
   		<div id="fancybox-example-delete" style="display: none">
                  <h4>删除提示</h4>
       		<div  class="row">
       			<div class="text-center">
       			您确认要删除吗?删除后将无法恢复数据!
       			</div>
       		
       		</div>
        
 			<div class="row"> 
   				 <div class="text-right">
                        <button class="btn btn-primary" id="btn-createfolder" type="button"
                        onclick="deleteById()">删除</button>
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
</body>
<!-- 删除下载按据 -->
<script type="text/javascript">
	//显示预览，下载，删除按据
	function showBDD(id){
		 $("#bdd_"+id).css("display","block");
		 $("#text_"+id).css("display","none");
	}
	function hideBDD(id){
		 $("#bdd_"+id).css("display","none");
		 $("#text_"+id).css("display","block");
	}
</script>




   <link rel="stylesheet" href="${ctx}/assets/jquery-ui/jquery-ui.css"
	type="text/css" />
<link rel="stylesheet"
	href="${ctx}/assets/plupload-2.1.8/jquery.ui.plupload/css/jquery.ui.plupload.css"
	type="text/css" />
<%-- <script type="text/javascript"
	src="${ctx}/assets/jquery-ui/external/jquery.js"></script> --%>
<script type="text/javascript"
	src="${ctx}/assets/jquery-ui/jquery-ui.min.js"></script>

<!-- production -->

<script type="text/javascript"
	src="${ctx}/assets/plupload-2.1.8/plupload.full.min.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/plupload-2.1.8/jquery.ui.plupload/jquery.ui.plupload.js"></script>
<script type="text/javascript"
	src="${ctx}/assets/plupload-2.1.8/i18n/zh_CN.js"></script>
<%-- 	<script type="text/javascript"
	src="${ctx}/assets/jquery-ui/external/jquery.js"></script> --%>
<script type="text/javascript">


$(".links").click(function(event){
    event = event || window.event;
    var target = event.target || event.srcElement,
        link = target.src ? target.parentNode : target,
        options = {index: link, event: event, slideshowInterval: 3000},
        links = $(".linksa");
   		blueimp.Gallery(links, options);
});
var parentId=$("#parentId").val();
var filePath=$("#filePath").val();
var pathIds=$("#pathIds").val();
var pageNo=$("#pageNo").val();
$(function() {
	var url=null;
	if(parentId==null||parentId==""){
		url='${ctx}/personalfile/uploadpersonalfile';
	}else{
		url='${ctx}/personalfile/uploadpersonalfile?parentId='+parentId;
	}
	
	var uploader = $("#uploader").plupload({
		runtimes : 'html5,flash,silverlight,html4',
		url : url,
		// User can upload no more then 20 files in one go (sets multiple_queues to false)
		max_file_count : 50,
		// Resize images on clientside if we can
		/* 	resize : {
				width : 200, 
				height : 200, 
				quality : 90,
				crop: true // crop to exact dimensions
			},
		 */
		filters : {
			// Maximum file size
			max_file_size : '1g',
		// Specify what files to browse for
/* 			 mime_types : [ //只允许上传图片和zip文件
			                { title : "Image files", extensions : "jpg,jpeg,png,bmp" }, 
			                { title : "Zip files", extensions : "zip,rar" },
			                { title : "Doc files", extensions : "doc,docx,xls,xlsx,ppt,pptx,pdf" },
			                { title : "video files", extensions : "avi,mp4,ogg,webm,m4v,mpg,3gp,swf,wmv" },
			                {title:   "music files",extensions:"wav,mp3"}
			              ] */

		},

		// Rename files by clicking on their titles
		rename : true,
		// Sort files
		sortable : true,

		// Enable ability to drag'n'drop files onto the widget (currently only HTML5 supports that)
		dragdrop : false,

		// Views to activate
		views : {
			list : true,
			/* thumbs: true,  */// Show thumbs
			active : 'thumbs'
		},
		// Flash settings
		flash_swf_url : '../../js/Moxie.swf',
		// Silverlight settings
		silverlight_xap_url : '../../js/Moxie.xap',
		
		    // PreInit events, bound before any internal events
	        preinit : {
	            Init: function(up, info) {
	            },
	            UploadFile: function(up, file) {
	                // You can override settings before the file is uploaded
	                // up.setOption('url', 'upload.php?id=' + file.id);
	                // up.setOption('multipart_params', {param1 : 'value1', param2 : 'value2'});
	            }
	        },
	 
	        // Post init events, bound after the internal events
	        init : {
	 
	            Refresh: function(up) {
	            },
	  
	            StateChanged: function(up) {
	                
	            },
	  
	            QueueChanged: function(up) {
	               
	            },
	 
	            OptionChanged: function(up, name, value, oldValue) {
	             
	            },
	 
	            BeforeUpload: function(up, file) {
	            },
	  
	            UploadProgress: function(up, file) {
	               
	            },
	 
	            FileFiltered: function(up, file) {
	            
	            },
	  
	            FilesAdded: function(up, files) {

	            },
	  
	            FilesRemoved: function(up, files) {
	            	uploader.each(files, function(file) {
	                  
	                });
	            },
	  
	            FileUploaded: function(up, file, info) {
	            
	         
	            },
	  
	            ChunkUploaded: function(up, file, info) {
	                
	            },
	 
	            UploadComplete: function(up, files) {
	              
	            },
	 
	            Destroy: function(up) {
	            	
	            },
	  
	            Error: function(up, args) {
	             
	            }
	        }
		
	});
	

	
	//创建文件夹
	$("#btn-createfolder").click(function(){
		var folderName=$("#originalName").val();
		var reposne_url='${ctx}/personalfile/querypersonfile?1=1';
		var data={};
		if(parentId!=null&&parentId!=""){
			data={"parentid":parentId,"originalName":folderName};
			reposne_url+="&parentId="+parentId+"&filePath="+filePath+"&pathIds="+pathIds;
		}else{
			data={"originalName":folderName};
		}
		if(pageNo!=null&&pageNo!=""){
			reposne_url+="&pageNo="+pageNo;
		}
		var request_url='${ctx}/personalfile/createfolder';
		$.post(request_url,data,function(data){
			if(data.returnCode==="success"){
				window.location.href=reposne_url;
			}
		});
		
	});
	$('#myModal').on('hidden.bs.modal', function (e) {
		var reposne_url='${ctx}/personalfile/querypersonfile';
		if(parentId!=null||parentId!=""){
			if(pageNo==null||pageNo==""){
				pageNo=1;
			}
			reposne_url+="?parentId="+parentId+"&filePath="+filePath+"&pathIds="+pathIds+"&pageNo="+pageNo;
		}
	  window.location.href=reposne_url;
	})
		$("#checkAll").click(function(){
		
		
		 if(this.checked){    
		        $("#context-panal :checkbox").prop("checked", true);   
		        $("#context-panal :checkbox").attr("checked", true);   
		    }else{    
		        $("#context-panal :checkbox").prop("checked", false); 
		        $("#context-panal :checkbox").attr("checked", false);   
		    }  
	});
	$(".checkboxClass").click(function(){
		 if(this.checked){    
		$(this).attr("checked", true);   
		 }else{
				$(this).attr("checked", false);   
			 }
		
	});
})

function clickFolder(parentId,filename){
	window.location.href=encodeURI("?parentId="+parentId+"&filePath="+filePath+"_"+filename+"&pathIds="+pathIds+"_"+parentId);
}

function deleteById(){
	if(deleteIds==null||deleteIds==""){
		alert("请至少选中一个进行删除");
		return;
	}
	var reposne_url='${ctx}/personalfile/querypersonfile';
	$.post("${ctx}/personalfile/batchdeletefile",{"fileIds":deleteIds},function(data){
		if(data.returnCode=="success"){
			if(pageNo==null||pageNo==""){
				pageNo=1;
			}
			reposne_url+="?parentId="+parentId+"&filePath="+filePath+"&pathIds="+pathIds+"&pageNo="+pageNo;
			window.location.href=reposne_url;
		}else{
			alert("删除的目录下面可能有子目录");
		}
	});
	
	
}
var deleteIds=null;
function batchDelete(ids){
	 if(ids==null||ids==undefined){
		  var valArr = new Array; 
		    $("#context-panal :checkbox[checked]").each(function(i){ 
		        valArr[i] = $(this).val(); 
		    }); 
		    var vals = valArr.join(',');//转换为逗号隔开的字符串 
		    deleteIds=vals;
}
}




function deletes(ids){
	deleteIds=ids;
}



</script>
</html>

