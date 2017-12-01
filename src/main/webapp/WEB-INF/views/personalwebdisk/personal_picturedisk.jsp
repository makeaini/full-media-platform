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
               <a class="list-group-item" href="${ctx}/personalfile/querypersonfile">
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
              </a><a class="list-group-item active" href="javascript:;">
                <p>
                  <span class="badge" style="background-color: #4282E2">${fileCount.PICTURE}</span><i class="icon-camera-retro" style="padding-right: 10px;"></i>图片
                </p>
              </a><a class="list-group-item" href="${ctx}/personalfile/querypersonbytype?fileType=MUSIC">
                <p>
                  <span class="badge" style="background-color: #4282E2">${fileCount.MUSIC}</span><i class="icon-music" style="padding-right: 10px;"></i>音频
                </p>
              </a><a class="list-group-item " href="${ctx}/personalfile/querypersonbytype?fileType=OTHER">
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
            <div class="widget-container fluid-height" id="context-panal">
             <input name="pageNo"  id="pageNo" type="hidden" value="${param.pageNo}">
              <div class="heading">
<!--                  <div class="row"> -->
		<!-- 	 <a data-toggle="modal" href="#myModal">
                    <button class="btn btn-default"><i class="icon-home"></i>上传文件</button>
                    </a> -->
                      <c:if test="${not empty pagination.datas}">
                     <a  class="btn btn-danger fancybox" href="#fancybox-example-delete" onclick="batchDelete()">删除</a>
                    <button class="btn btn-info"><i class="icon-cloud-download"></i>下载</button>
                     <div class="check-header hidden-xs" style="float: right;">
                     <label ><input id="checkAll" name="checkAll" type="checkbox"><span>全选</span></label>
                     </div>
                    </c:if>
<!--                   </div> -->
              </div>
           
              <div class="widget-content padded">
               <div class="col-lg-12">
                <ul class="clearfix" id="links"  style="list-style-type:none;  ">
                <c:if test="${not empty pagination.datas}">
                <c:forEach items="${pagination.datas}" var="file">
                <c:choose>
                  <c:when test="${file.fileType=='PICTURE'}">
                	 <li class="col-lg-3 col-sm-4 col-xs-6 col-md-3">
                  <div class="text-center" onmousemove="showBDD('${file.id}')" onmouseout="hideBDD('${file.id}')">
<!-- 		              <label style="width: 10px;" class="checkbox"><input type="checkbox"><span></span></label> -->
		              <div class="check hidden-xs">
                        <label>
                         <input type="checkbox" value="${file.id}" class="checkboxClass"><span></span></label>
                      </div>
		              <center>
			          	 <div class="text-center" style="width:128px;height: 128px;overflow:hidden;">
	                     <a class="linksa" href="${pageContext.request.contextPath}/file/getOriginalImage/${file.id}" title="${file.originalName}" >
                    		<img src="${pageContext.request.contextPath}/file/getThumbnailsImage/${file.id}" class="links" />
                    	</a>
	                      </div>
                        </center>
                      <div id="text_${file.id}"  style="font-size: 14px;padding-top: 7px;" title="${file.originalName}" >
	                     <a href="${ctx}/file/downloadFile/${file.id}">
	                       <c:choose>
		                       <c:when test="${fn:length(file.originalName) > 8 }">${fn:substring(file.originalName, 0, 7)}...</c:when>
		                       <c:otherwise>
		                        ${file.originalName}
		                       </c:otherwise>
	                       </c:choose>
	                    </a>
                      </div>
                      <div class="text-center" style="font-size: 14px;padding-top: 7px;display: none" id="bdd_${file.id}" >
	                   		<span><a href="${ctx}/file/downloadFile/${file.id}">下载</a></span>
	                   		<span style="padding-left: 30px;">
	                   			<a  class="fancybox" href="#fancybox-example-delete"  onclick="deletes('${file.id}')">删除</a></span> 
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
          </div>
           </c:if>
        </div>
        <!-- end Gallery with captions -->
    
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
</body>
<script type="text/javascript">
	$(".links").click(function(event){
	    event = event || window.event;
	    var target = event.target || event.srcElement,
	        link = target.src ? target.parentNode : target,
	        options = {index: link, event: event, slideshowInterval: 3000},
	        links = $(".linksa");
	   		blueimp.Gallery(links, options);
	});
</script>
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
	
	var pageNo=$("#pageNo").val();
	$(function(){
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
		
	});
	
	function deleteById(){
		if(deleteIds==null||deleteIds==""){
			alert("请至少选中一个进行删除");
			return;
		}
		var reposne_url='${ctx}/personalfile/querypersonbytype';
		$.post("${ctx}/personalfile/batchdeletefile",{"fileIds":deleteIds},function(data){
			if(data.returnCode=="success"){
				if(pageNo==null||pageNo==""){
					pageNo=1;
				}
				reposne_url+="?pageNo="+pageNo+"&fileType=PICTURE";
				window.location.href=reposne_url;
			}else{
				alert("删除的目录下面可能有子目录");
			}
		});
		
		
	}
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
	var deleteIds=null;
	function deletes(ids){
		deleteIds=ids;
	}
	
</script>

</html>

