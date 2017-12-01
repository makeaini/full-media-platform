<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@page isELIgnored="false"%> 
<!doctype html>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>登录</title>

  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description" content="">
  <meta name="keywords" content="index">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <meta name="apple-mobile-web-app-title" content="WeChat" />
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/logo_title.png" 
   type="image/x-icon" />
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
    <script src="${pageContext.request.contextPath}/assets/quote_user/jquery-ui.js" type="text/javascript"></script>
    
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
    <script src="${pageContext.request.contextPath}/assets/javascripts/datatable-editable.js" type="text/javascript"></script>
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


 <body class="login1">

    <!-- Login Screen -->
    <div class="login-wrapper">
      <div class="login-container" style="height: 411px;">
       <h3> <a href="javascript:;">全媒体云库中心</a></h3>
  	 	<div style="color: red"> ${loginerror}&nbsp;</div>
       <form action="loginTeacherinfo" method="post">
        <div class="form-group">
            <input name="schoolIds" class="form-control" placeholder="学校编号" type="text">
          </div>
          <div class="form-group">
            <input  name="username"  class="form-control" placeholder=" 用 户 名" type="text">
          </div>
          <div class="form-group">
            <input name="password" class="form-control" placeholder="密 码" type="password">
            <input type="submit" value="&#xf054;">
          </div>
          <div class="form-options clearfix">
            <a class="pull-right" href="#">忘记 密码?</a>
            <!-- <div class="text-left">
              <label class="checkbox"><input type="checkbox"><span> 记住我</span></label>
            </div> -->
          </div>
        </form>
    <!--    <div class="social-login clearfix">
          <a class="btn btn-primary  facebook" href="loginTeacherinfo">
          <i class="icon-retweet"></i>登录</a>
           <a class="btn btn-primary pull-right twitter" href="index-2.html"> -->
<!--           <i class="icon-twitter"></i>Twitter login</a>
        </div> -->
<!--         <p class="signup">
          没有一个帐户了吗?<a href="signup1.html">现在就注册</a>
        </p> -->
      </div>
    </div>
    <!-- End Login Screen -->
  </body>
</html>
