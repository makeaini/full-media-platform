<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@page isELIgnored="false"%> 
<!doctype html>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>个人中心</title>

  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description" content="">
  <meta name="keywords" content="index">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <meta name="apple-mobile-web-app-title" content="WeChat" />
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/images/logo_title.png" 
   type="image/x-icon" />
</head>


<body>
<div class="modal-shiftfix">
<%@include file="public/top.jsp" %>
<!-- 内容 -->
   <!-- End Navigation -->
      <div class="container-fluid main-content">
        <div class="page-title">
          <h1>
            个人中心
          </h1>
        </div>
        <div class="row">
          <div class="col-sm-3">
      
            <div class="widget-container fluid-height">
              <div class="widget-content">
                <div class="panel-group" id="accordion">
                
                
                  <div class="panel">
                    <div class="panel-heading">
                      <div class="panel-title">
                        <a class="accordion-toggle" data-parent="#accordion" data-toggle="collapse" href="#collapseOne">
                          <div class="caret pull-right"></div>
                          资源管理</a>
                      </div>
                    </div>
                    <div class="panel-collapse collapse in" id="collapseOne">
                      <div class="panel-body">
                      
                       <a href="${pageContext.request.contextPath}/resources/resource-upload-views">
                          <label class="btn btn-block btn-info-outline action ">
                          <i class="icon-stethoscope"></i>上传资源
                          </label>
                      </a>
                       <a href="${pageContext.request.contextPath}/personalfile/querypersonfile">
                          <label class="btn btn-block btn-info-outline "> 
                          <i class="icon-stethoscope"></i>个人网盘
                          </label>
                         </a>
                      </div>
                    </div>
                  </div>
                  <div class="panel">
                    <div class="panel-heading">
                      <div class="panel-title">
                        <a class="accordion-toggle" data-parent="#accordion" data-toggle="collapse" href="#collapseTwo">
                          <div class="caret pull-right"></div>
                          <span>我的云库</span></a>
                      </div>
                    </div>
                    <div class="panel-collapse collapse in" id="collapseTwo">
                      <div class="panel-body">
                         <!-- active -->
              <a class="list-group-item " href="${pageContext.request.contextPath}/resourcesCenter/getPersionalRecord">
                <p>
                  <span class="badge"></span>资源记录
                </p>
              </a><a class="list-group-item" href="${pageContext.request.contextPath}/personalfile/getPersionalRecord">
                <p>
                  <span class="badge"></span>网盘记录
                </p>
              </a><a class="list-group-item active" href="#">
                <p>
                  <span class="badge" style="background-color: #4282E2">${notifcation}</span>通知
                </p>
              </a>
<!--               <a class="list-group-item" href="#"> -->
<!--                 <p> -->
<!--                   <span class="badge">18</span>我的粉思 -->
<!--                 </p> -->
<!--               </a><a class="list-group-item" href="#"> -->
<!--                 <p> -->
<!--                   <span class="badge">7</span>我的关注 -->
<!--                 </p> -->
<!--               </a> -->
<!--               <a class="list-group-item" href="#"> -->
<!--                 <p> -->
<!--                   <span class="badge"></span>我的留言 -->
<!--                 </p> -->
<!--               </a> -->
                      </div>
                    </div>
                  </div>
                  <div class="panel filter-categories">
                    <div class="panel-heading">
                      <div class="panel-title">
                        <a class="accordion-toggle" data-parent="#accordion" data-toggle="collapse" href="#collapseThree">
                          <div class="caret pull-right"></div>
                          设置</a>
                      </div>
                    </div>
                    <div class="panel-collapse collapse in" id="collapseThree">
                      <div class="panel-body">
                         <!-- active -->
              <a class="list-group-item " href="#">
                <p>
                  <span class="badge"></span>个人资料
                </p>
              </a><a class="list-group-item" href="${pageContext.request.contextPath}/user/personalLoadPassword">
                <p>
                  <span class="badge"></span>密码修改
                </p>
              </a><a class="list-group-item" href="#">
                <p>
                  <span class="badge"></span>安全设置
                </p>
              </a>
              </a>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-sm-9">
          
            <div class="widget-container scrollable list rating-widget">
              <div class="heading">
                <i class="icon-comment"></i>公告通知
              </div>
              <div class="widget-content">
                <ul>
                <!-- 公告通知 -->
               <c:forEach items="${pageList.datas}" var="items" varStatus="status">
              
                  <li>
                    <div class="reviewer-info">
                      <div class="star-rating pull-right">
                       ${items.title}
                      </div>
                      <img width="30" height="30" src="${pageContext.request.contextPath}/assets/images/avatar-male.jpg" />
                      <a href="#">管理员</a><em> ${items.sendDate}</em>
                    </div>
                    <div class="review-text">
                      <p>
                       ${items.content}
                      </p>
                    </div>
                  </li>
                   </c:forEach>
                </ul>
              </div>
               
          </div>
        </div>
      </div>
    </div>
</body>
</html>
