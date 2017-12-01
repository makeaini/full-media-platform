<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" /> 
<%@page isELIgnored="false"%> 
<!doctype html>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>首页－全媒体资源中心</title>
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
<%@include file="public/top.jsp" %>
<!-- 内容 -->
  <div class="container-fluid main-content">
        <!-- Statistics -->
        <div class="row">
          <div class="col-lg-12">
            <div class="widget-container stats-container">
              <div class="col-md-3">
              <a href="${ctx}/resourcesCenter/center?resourcesType=video">
                <div class="number">
                  <div class="icon-facetime-video"></div>
                  ${video}<small></small>
                </div>
                <div class="text">
                 视频 数量
                </div>
                </a>
              </div>
              <div class="col-md-3">
              <a href="${ctx}/resourcesCenter/center?resourcesType=picture">
                <div class="number">
                  <div class="icon-camera-retro"></div>
                  ${picture}
                </div>
                <div class="text">
                  图片 数量
                </div>
                </a>
              </div>
              <div class="col-md-3">
              <a href="${ctx}/resourcesCenter/center?resourcesType=document">
                <div class="number">
                  <div class="icon-file"></div>
                  <small></small>${document}
                </div>
                <div class="text">
                  文档 数量
                </div>
                </a>
              </div>
              <div class="col-md-3">
              <a href="${ctx}/resourcesCenter/center?resourcesType=other">
                <div class="number">
                  <div class="icon-hdd"></div>
                  ${other}
                </div>
                <div class="text">
                  其 他
                </div>
                </a>
              </div>
            </div>
          </div>
        </div>
        <!-- End Statistics -->
        <div class="row">
          <!-- Weather -->
          <div class="col-md-8">
            <div class="widget-container weather">
              <div class="widget-content padded">
                <div class="row text-center">
                  <div class="col-sm-6 col-md-6 col-lg-4 today">
                    <p>
                      TODAY
                    </p>
                    <canvas class="skycons-element" data-skycons="RAIN" height="100" id="rain" width="100"></canvas>
                    <div class="number">
                      72<small>&deg;</small>
                    </div>
                    <p class="location">
                      Washington, D.C.
                    </p>
                  </div>
                  <div class="col-sm-2 hidden-xs">
                    <p>
                      MON
                    </p>
                    <canvas class="skycons-element" data-skycons="CLEAR_DAY" height="60" id="clear-day" width="60"></canvas>
                    <div class="number">
                      86<small>&deg;</small>
                    </div>
                  </div>
                  <div class="col-sm-2 hidden-xs">
                    <p>
                      TUE
                    </p>
                    <canvas class="skycons-element" data-skycons="RAIN" height="60" id="cloudy" width="60"></canvas>
                    <div class="number">
                      75<small>&deg;</small>
                    </div>
                  </div>
                  <div class="col-sm-2 hidden-xs">
                    <p>
                      WED
                    </p>
                    <canvas class="skycons-element" data-skycons="PARTLY_CLOUDY_DAY" height="60" id="partly-cloudy-day" width="60"></canvas>
                    <div class="number">
                      82<small>&deg;</small>
                    </div>
                  </div>
                  <div class="col-sm-2 hidden-md hidden-sm hidden-xs">
                    <p>
                      THU
                    </p>
                    <canvas class="skycons-element" data-skycons="SLEET" height="60" id="sleet" width="60"></canvas>
                    <div class="number">
                      64<small>&deg;</small>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- end Weather --><!-- Bar Graph -->
          <div class="col-md-4">
            <div class="widget-container small">
              <div class="heading">
                <i class="icon-signal"></i>最近上传记录<i class="icon-list pull-right"></i><i class="icon-refresh pull-right"></i>
              </div>
              <div class="widget-content padded">
                <div class="bar-chart-widget">
                  <div class="chart-graph">
                    <div id="barcharts">
                      Loading...
                    </div>
                    <ul class="chart-text-axis">
                      <li> 1</li>
                      <li>2</li>
                      <li> 3</li>
                      <li>
                        4
                      </li>
                      <li>
                        5
                      </li>
                      <li>
                        6
                      </li>
                      <li>
                        7
                      </li>
                      <li>
                        8
                      </li>
                      <li>
                        9
                      </li>
                      <li>
                        10
                      </li>
                      <li>
                        11
                      </li>
                      <li>
                        12
                      </li>
                      <li>
                        13
                      </li>
                      <li>
                        14
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- End Bar Graph -->
        </div>
        
        
        
                <div class="row">
          <!-- Task List -->
          <div class="col-lg-6">
            <div class="widget-container scrollable list task-widget">
              <div class="heading">
                <i class="icon-list"></i>最新上传资源
              </div>
              <div class="widget-content">
                <ul>
                <c:forEach items="${listNewResources.datas}" var="items" varStatus="status">
                 <a href="${ctx}/resourcesCenter/getResourceDetail?resourceId=${items.id}">
                  <li>
                    <label>
                      <c:forEach items="${items.myFiles}" var="fileImg" varStatus="status">
                       <c:choose >
		                    <c:when test="${fileImg.fileType =='PICTURE'}">
			                     <div class="label label-warning pull-right">
			                        图片
			                      </div>
			                </c:when>
			                <c:when test="${fileImg.fileType =='DOCUMENT'}">
			                     <div class="label label-success pull-right">
			                        文档
			                      </div>
			                </c:when>
			                <c:when test="${fileImg.fileType =='OTHER'}">
			                     <div class="label label-success pull-right">
			                        其他
			                      </div>
			                </c:when>
			                <c:when test="${fileImg.fileType =='VIDEO'}">
			                     <div class="label label-warning pull-right">
			                        视频
			                      </div>
			                </c:when>
		                     		
		               </c:choose>
                     </c:forEach>
                      
                    ${items.name}-
                    ${items.resourcesType.name} 
                    </label>
                  </li>
                  </a>
                  </c:forEach>
                 
                </ul>
              </div>
            </div>
          </div>
          <!-- End Task List --><!-- Ratings -->
          <div class="col-lg-6">
            <div class="widget-container scrollable list rating-widget">
              <div class="heading">
                <i class="icon-comment"></i>公告通知
              </div>
              <div class="widget-content">
                <ul>
                <!-- 公告通知 -->
               <c:forEach items="${listNotifcation}" var="items" varStatus="status">
              
                  <li>
                    <div class="reviewer-info">
                      <div class="star-rating pull-right">
                       ${items.title}
                      </div>
                      <img width="30" height="30" src="assets/images/avatar-male.jpg" />
                      <a href="#">aaron</a><em> ${items.sendDate}</em>
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
          <!-- End Ratings -->
        </div>
        
        
        <div class="row">
          <!-- Pie Graph 1 -->
          <div class="col-lg-5">
            <div class="widget-container fluid-height">
              <div class="heading">
                <i class="icon-bar-chart"></i>性能比
              </div>
              <div class="widget-content clearfix">
                <div class="col-sm-6">
                  <div class="pie-chart1 pie-chart pie-number" data-percent="87">
                    87%
                  </div>
                </div>
                <div class="col-sm-6">
                  <div class="pie-chart2 pie-chart pie-number" data-percent="26">
                    26%
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-7">
            <div class="widget-container fluid-height">
              <!-- Table -->
              <table class="table table-filters">
                <tbody>
                 <c:forEach items="${listSchool}" var="items" varStatus="status">
                 
                  <tr>
                    <td class="filter-category blue">
                      <div class="arrow-left"></div>
                      <i class="icon-stethoscope"></i>
                    </td>
                    <td>
                    <a href="${ctx}/resourcesCenter/center?pageNo=1&school=${items.id}">
                       ${items.name}
                      </a>
                    </td>
                    <td>
                      3
                    </td>
                    <td class="hidden-xs">
                      <div class="sparkslim">
                        50,55,60,40,30,35,30,20,25,30,40,20,15
                      </div>
                    </td>
                    <td>
                      <div class="danger">
                        4%
                      </div>
                    </td>
                  </tr>
                  
                  </c:forEach>
                  
                 
                </tbody>
              </table>
            </div>
          </div>
          <!-- End Pie Graph 1 -->
        </div>
      </div>
</div>
</body>
</html>
