<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" /> 
<%@page isELIgnored="false"%> 
<!doctype html>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>全媒体资源中心-学校管理</title>
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
<%@include file="../../public/top.jsp" %>
<!-- 内容 -->
 <!-- End Navigation -->
      <div class="container-fluid main-content">
        <div class="page-title">
          <h1>
            学校管理
          </h1>
        </div>
        <!-- DataTables Example -->
        <div class="row">
          <div class="col-lg-12">
            <div class="widget-container fluid-height clearfix">
              <div class="heading">
                <i class="icon-table"></i>DataTable with Sorting
              </div>
              <div class="widget-content padded clearfix">
                <table class="table table-bordered table-striped" id="dataTable1">
                  <thead>
                    <th class="check-header hidden-xs">
                      <label><input id="checkAll" name="checkAll" type="checkbox"><span></span>
                      </label>
                    </th>
                    <th>
                      学校名称
                    </th>
                    <th>
                      学校编号
                    </th>
                    <th class="hidden-xs">
                       学校邮箱
                    </th>
                    <th class="hidden-xs">
                      学校地址
                    </th>
                    <th class="hidden-xs">
                      状态
                    </th>
                    <th></th>
                  </thead>
                  <tbody>
                   <c:forEach items="${pageList.datas}" var="items" varStatus="status">
                    <tr>
                      <td class="check hidden-xs">
                        <label><input name="optionsRadios1" type="checkbox" value="option1"><span></span></label>
                      </td>
                      <td>
                        ${items.name }
                      </td>
                      <td>
                         ${items.coredIds }
                      </td>
                      <td class="hidden-xs">
                        ${items.email }
                      </td>
                      </td>
                      <td class="hidden-xs">
                        ${items.address }
                      </td>
                      </td>
                      <td class="hidden-xs">
                        <span class="label label-success">
                         ${items.state }</span>
                      </td>
                      <td class="actions">
                        <div class="action-buttons">
                          <a class="table-actions" href="#">
                          <i class="icon-eye-open"></i></a>
                          <a class="table-actions" href="#">
                          <i class="icon-pencil"></i></a>
                          <a class="table-actions" href="#">
                          <i class="icon-trash"></i></a>
                        </div>
                      </td>
                    </tr>
                    </c:forEach>

                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
        <!-- end DataTables Example -->
      </div>
</div>
</body>
</html>
