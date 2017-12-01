<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false"%> 
<!doctype html>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>全媒体资源中心</title>
  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <meta name="keywords" content="index">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="WeChat" />

<!-- <link href="http://fonts.googleapis.com/css?family=Lato:100,300,400,700" -->
<!-- 	media="all" rel="stylesheet" type="text/css" />  -->
    <link href="${pageContext.request.contextPath}/assets/quote_user/googleapis.css" media="all" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/stylesheets/bootstrap.min.css" media="all" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/stylesheets/font-awesome.css" media="all" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/stylesheets/se7en-font.css" media="all" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/stylesheets/isotope.css" media="all" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/stylesheets/jquery.fancybox.css" media="all" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/stylesheets/fullcalendar.css" media="all" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/stylesheets/wizard.css" media="all" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/stylesheets/select2.css" media="all" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/stylesheets/morris.css" media="all" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/stylesheets/datatables.css" media="all" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/stylesheets/datepicker.css" media="all" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/stylesheets/timepicker.css" media="all" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/stylesheets/colorpicker.css" media="all" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/stylesheets/bootstrap-switch.css" media="all" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/stylesheets/daterange-picker.css" media="all" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/stylesheets/typeahead.css" media="all" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/stylesheets/summernote.css" media="all" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/stylesheets/pygments.css" media="all" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/stylesheets/style.css" media="all" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/stylesheets/color/green.css" media="all" rel="alternate stylesheet" title="green-theme" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/stylesheets/color/orange.css" media="all" rel="alternate stylesheet" title="orange-theme" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/stylesheets/color/magenta.css" media="all" rel="alternate stylesheet" title="magenta-theme" type="text/css" />
    <link href="${pageContext.request.contextPath}/assets/stylesheets/color/gray.css" media="all" rel="alternate stylesheet" title="gray-theme" type="text/css" />
    
    <script src="${pageContext.request.contextPath}/assets/quote_user/jquery-1.10.2.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
    
<!--     <script src="http://code.jquery.com/jquery-1.10.2.min.js" -->
<!-- 	type="text/javascript"></script> -->
<!-- <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js" -->
<!-- 	type="text/javascript"></script> -->
    
    <script src="${pageContext.request.contextPath}/assets/javascripts/bootstrap.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/raphael.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/selectivizr-min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/jquery.mousewheel.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/jquery.vmap.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/jquery.vmap.sampledata.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/jquery.vmap.world.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/jquery.bootstrap.wizard.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/fullcalendar.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/gcal.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/jquery.dataTables.min.js" type="text/javascript"></script>

    <script src="${pageContext.request.contextPath}/assets/javascripts/jquery.easy-pie-chart.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/excanvas.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/jquery.isotope.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/isotope_extras.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/modernizr.custom.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/jquery.fancybox.pack.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/select2.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/styleswitcher.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/wysiwyg.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/summernote.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/jquery.inputmask.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/jquery.validate.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/bootstrap-fileupload.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/bootstrap-datepicker.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/bootstrap-timepicker.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/bootstrap-colorpicker.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/bootstrap-switch.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/typeahead.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/daterange-picker.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/date.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/morris.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/skycons.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/fitvids.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/jquery.sparkline.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/main.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/javascripts/respond.js" type="text/javascript"></script>
</head>
<body>
 
 <!-- Navigation -->
      <div class="navbar navbar-fixed-top scroll-hide">
        <div class="container-fluid top-bar">
          <div class="pull-right">
            <c:choose>
              <c:when test="${not empty sessionScope}">
            <ul class="nav navbar-nav pull-right">
           
              <li class="dropdown notifications hidden-xs">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#"><span aria-hidden="true" class="se7en-flag"></span>
                  <div class="sr-only">
                    Notifications
                  </div>
                  <p class="counter">
                    1
                  </p>
                </a>
                <ul class="dropdown-menu">
                  <li><a href="#">
                    <div class="notifications label label-info">
                      New
                    </div>
                    <p>
                      New user added: Jane Smith
                    </p></a>
                  </li>
                </ul>
              </li>
              <li class="dropdown messages hidden-xs">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#"><span aria-hidden="true" class="se7en-envelope"></span>
                  <div class="sr-only">
                    Messages
                  </div>
                  <p class="counter">
                    1
                  </p>
                </a>
                <ul class="dropdown-menu messages">
                  <li><a href="#">
                    <img width="34" height="34" src="${ctx}/assets/images/avatar-male2.png" />Could we meet today? I wanted...</a>
                  </li>
                 
                </ul>
              </li>
            
              
               <li class="dropdown user hidden-xs"><a data-toggle="dropdown" class="dropdown-toggle" href="#">
                <img width="34" height="34" src="${ctx}/assets/images/avatar-male.jpg" />
                 ${sessionScope.usr_auth.username}<b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="#">
                    <i class="icon-user"></i>时光轴</a>
                  </li>
                  <li><a href="#">
                    <i class="icon-gear"></i>帐号设置</a>
                  </li>
                  <li><a href="${pageContext.request.contextPath}/loginout">
                    <i class="icon-signout"></i>退出登录</a>
                  </li>
                </ul>
              </li>
         
            </ul>
              </c:when>
             <c:otherwise>
              <li class="dropdown user hidden-xs">
              <div>
              <a href="${pageContext.request.contextPath}/login">亲，请登录 </a>
               免费注册手
              </div>
             	
             	</li>
             </c:otherwise>
             </c:choose>  
          </div>
          <button class="navbar-toggle"><span class="icon-bar"></span><span class="icon-bar">
          </span><span class="icon-bar"></span></button>
          <div class="text-center" style="float:left;padding-top: 5px;">
          	<h3>
          	<span style="color: red;"><strong>CORED 中赤</strong></span> <span style="font-size: 15px;">全媒体资源中心</span>
          	
          	</h3>
          <h4>
          		
          	</h4>
          </div>
<!--           <form class="navbar-form form-inline col-lg-2 hidden-xs"> -->
<!--             <input class="form-control" placeholder="Search" type="text"> -->
<!--           </form> -->
        </div>
        
        <!--菜单栏-->
        <div class="container-fluid main-nav clearfix">
          <div class="nav-collapse">
            <ul class="nav">
              <li>
                <a class="current" href="${pageContext.request.contextPath}/index"><span aria-hidden="true" class="se7en-home"></span>首页</a>
              </li>
              
              <li class="dropdown">
              <a data-toggle="dropdown" href="${pageContext.request.contextPath}/resourcesCenter/center">
                <span aria-hidden="true" class="se7en-star"></span>校园云库中心<b class="caret"></b></a>
                <ul class="dropdown-menu">
                 <li>
                    <a href="${pageContext.request.contextPath}/resourcesCenter/center?&resourcesType=ALL">全部</a>
                  </li>
                  <li>
                    <a href="${pageContext.request.contextPath}/resourcesCenter/center?resourcesType=video">视频</a>
                  </li>
                  <li>
                     <a href="${pageContext.request.contextPath}/resourcesCenter/center?resourcesType=document">文档</a>
                  </li>
                  <li>
                  <a href="${pageContext.request.contextPath}/resourcesCenter/center?resourcesType=picture">
                    <span class="notifications label label-warning">图片</span>
                    <p>图片</p></a>
                    
                  </li>
                  <li>
                   <a href="${pageContext.request.contextPath}/resourcesCenter/center?pageNo=1&resourcesType=music">音频</a>
                  </li>
                  <li>
                     <a href="${pageContext.request.contextPath}/resourcesCenter/center?pageNo=1&resourcesType=other">其他</a>
                  </li>
                 
                </ul>
              </li>
              
              <li><a href="${pageContext.request.contextPath}/user/personalCenter">
                <span aria-hidden="true" class="se7en-feed"></span>个人中心</a>
              </li>
					<c:if test="${sessionScope.usr_auth.type =='SCHOOLMANAGER'}">
						<li class="dropdown">
						<a data-toggle="dropdown" href="#">
								<span aria-hidden="true" class="se7en-forms"></span>系统设置<b
								class="caret"></b>
						</a>
							<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath}/list">用户管理</a></li>
								
								<li><a href="${pageContext.request.contextPath}/resources/list">资源管理</a></li>
								
								<li><a href="${pageContext.request.contextPath}/schoolNotification/list">
								 <span
										class="notifications label label-warning">New</span> 通知
								</a></li>
							</ul></li>
							</c:if>
						<c:if test="${sessionScope.usr_auth.type =='SUPERMANAGER'}">
						<li class="dropdown"><a data-toggle="dropdown" href="#">
								<span aria-hidden="true" class="se7en-tables"></span>基础配置<b
								class="caret"></b>
						</a>
							<ul class="dropdown-menu">
								<li><a href="${pageContext.request.contextPath}/school/list"><p>学校信息</p>
								</a></li>
								<li><a href="${pageContext.request.contextPath}/grade/list">年级配置</a></li>
								<li><a href="${pageContext.request.contextPath}/subject/list">科目配置</a></li>
								<li><a href="${pageContext.request.contextPath}/resourcesType/list">
								<div class="notifications label label-warning">New</div>类型配置
								</a></li>
								
							</ul></li>
					</c:if>



				</ul>
          </div>
        </div>
      </div>

</body>
</html>
