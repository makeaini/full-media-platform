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
  <title>全媒体源云库中心－上传</title>

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
<body>
<div class="modal-shiftfix">
<%@include file="../public/top.jsp" %>
<!-- 内容 -->
   <!-- End Navigation -->
    <div class="container-fluid main-content">
        <div class="page-title">
          <h1>
            全媒体资源上传中心
          </h1>
        </div>
        <div class="row">
          <div class="col-lg-12">
            <div class="widget-container fluid-height">
              <div class="heading">
                <i class="icon-edit"></i>资源上传
              </div>
              <div class="widget-content">
                <div class="wizard" id="wizard">
                  <div class="padded">
                    <ul class="wizard-nav">
                      <li>
                        <a data-toggle="tab" href="#tab1">1</a>
                      </li>
                      <li>
                        <a data-toggle="tab" href="#tab2">2 </a>
                      </li>
<!--                       <li> -->
<!--                         <a data-toggle="tab" href="#tab3">3</a> -->
<!--                       </li> -->
                    </ul>
                    <div class="progress progress-striped active">
                      <div aria-valuemax="100" aria-valuemin="0" aria-valuenow="0" class="progress-bar" role="progressbar"></div>
                    </div>
                    <div class="tab-content">
                      <div class="tab-pane" id="tab1">
                        <h2>
                          请添加您要上传的一组资源文件
                        </h2>
                        <form role="form">
                        <div class="form-group">
                            <label for="email"></label>
                            <input class="form-control" id="name" placeholder="" type="hidden" value=" ${sessionScope.usr_auth.username}">
                          </div>
                          <div class="form-group">
                            <div id="uploader">
							<p>Your browser doesn't have Flash, Silverlight or HTML5 support.</p>
						</div>
                          </div>
                        </form>
                      </div>
                      <div class="tab-pane" id="tab2">
                        <h2>
                          请填完善资源信息
                        </h2>
       					 <div class="widget-content padded">
					          <form id="validate-form" role="form" action="${ctx}/resources/resource-save" method="post">
					           <fieldset>
					            <div class="row">
					              <div class="col-md-6">
					                <div class="form-group">
					                  <label for="firstname">资源类型</label>
					                   <select class="select2able" name="typeId">
					                    <c:forEach items="${listResourcesTypeServiceI}" var="items" varStatus="status">
						              	<option value="${items.id}">${items.name}</option>
						              	</c:forEach>
						              </select>
					                </div>
					                <div class="form-group">
					                  <label for="username">年级</label>
					                  <select class="select2able" name="gradeId">
						              	 <c:forEach items="${listGrade}" var="items" varStatus="status">
						              	<option value="${items.id}">${items.name}</option>
						              	</c:forEach>
									   </select>
					                </div>
					                <div class="form-group">
					                  <label for="password">科目</label>
					                   <select class="select2able" name="subjectsId">
						                <c:forEach items="${listSubject}" var="items" varStatus="status">
						              	<option value="${items.id}">${items.name}</option>
						              	</c:forEach>
									   </select>
					                </div>
					              </div>
					              
					              	<div class="col-md-6">
					                <div class="form-group">
					                  <label for="lastname">名称</label>
					                  <input class="form-control" id="label" name="name" type="text">
					                </div>
					              
					                <div class="form-group">
					                  <label for="lastname">关键字</label>
					                  <input class="form-control" id="label" name="label" type="text">
					                </div>
					                <div class="form-group">
					                   <label class="control-label col-md-3">是否公开</label>
							            <div class="col-md-7">
							              <label class="radio" >
							              <input  name="rolePermissions" type="radio" value="WEBSHOW" checked="checked"><span>公开</span></label>
							              <label class="radio">
							              <input  name="rolePermissions" type="radio" value="NOWEBSHOW"><span>校内私有</span></label>
							            </div>
					                </div>
					          
					                
					              </div>
					              </div>
					               <div class="row">
					               <div class="col-md-12">
					                <div class="form-group">
					                  <label for="lastname">备注</label>
					                  <textarea class="form-control" rows="3" name="remark"></textarea>
					                </div>
					               </div>
					              </div>
					              <center>
					                 <button class="btn btn-lg btn-success"  type="button" id="submit-resources" >确认并且提交资源</button>
					 			  </center>
					 				</fieldset>
					 			</form>
					      </div>
                      </div>
<!--                       <div class="tab-pane" id="tab3"> -->
<!--                         <h2> -->
<!--                          	 请确认您上传的资源信息 -->
<!--                         </h2> -->
                        
<!--                         <form role="form"> -->
<!--                           <div class="form-group" id="uploadfilelist"> -->
                         
                     
<!--                           </div> -->
<!--                           <button class="btn btn-lg btn-success" type="submit" >确认并且提交资源</button> -->
<!--                         </form> -->
<!--                       </div> -->
                    </div>
                 
                  </div>
                  
                  <div class="pager">
                    <div class="btn btn-default-outline btn-previous">
                      <i class="icon-long-arrow-left"></i>返回
                    </div>
                    <div class="btn btn-primary-outline btn-next" id="btn-next">
                      下一步<i class="icon-long-arrow-right"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
</div>

</body>

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
<script type="text/javascript">

$(function() {
	var url='${ctx}/resources/resource-upload';
	var uploader = $("#uploader").plupload({
		runtimes : 'html5,flash,silverlight,html4',
		url : url,
		max_file_count : 10,
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
			max_file_size : '2g',
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
	                 up.setOption('multipart_params', {"uid": file.id});
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
	            	var currentfileId=null;
	            	for(i=0;i<files.length;i++){
	            		currentfileId=files[i].id;
	            	}
	            	$.post("${ctx}/resources/remove-myfiles",{"uid":currentfileId},function(data){
	            		if(data.returnCode=="success"){
	            			console.info(data.returnMsg)
	            		}
	            	})
	                 
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
	$("#submit-resources").click(function(){
		$.post("${ctx}/resources/find-myfiles",{},function(data){
			if(data.returnCode=="success"){
				$("#validate-form").submit();
			}else{
				alert("请选至少上传一个文件，或者请点击上传");
			}
			
		});
		
	});

})


</script>
                
</html>

