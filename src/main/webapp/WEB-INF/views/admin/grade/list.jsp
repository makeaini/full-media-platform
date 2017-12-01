<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" /> 
<c:set var="schoolId" value="${sessionScope.usr_auth.schoolId}" /> 
<%@page isELIgnored="false"%> 
<!doctype html>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>全媒体资源中心-年级</title>
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
  <div class="container-fluid main-content">
        <div class="page-title">
          <h1>
           年级
          </h1>
        </div>
        <!-- DataTables Example -->
        <div class="row">
          <div class="col-lg-12">
            <div class="widget-container fluid-height clearfix">
              <div class="heading">
                <i class="icon-table"></i>基本信息管理
                <a class="btn btn-sm btn-primary-outline pull-right fancybox" href="#fancybox-example">
                <i ></i>添加</a>
              </div>
                          
              <div class="widget-content padded clearfix">
                <table class="table table-bordered table-striped" id="dataTable1">
                  <thead>
                    <th class="check-header hidden-xs">
                      <label><input id="checkAll" name="checkAll" type="checkbox"><span></span>
                      </label>
                    </th>
                   
                    <th class="hidden-xs">
                       名称
                    </th>
                    <th class="hidden-xs">
                      排序
                    </th>
                    <th></th>
                  </thead>
                  <tbody>
                   <c:forEach items="${pageList.datas}" var="items" varStatus="status">
                    <tr>
                      <td class="check hidden-xs">
                        <label><input name="optionsRadios1" type="checkbox" value="option1"><span></span></label>
                      </td>
                      
                      <td class="hidden-xs">
                        ${items.name }
                      </td>
                      </td>
                      <td class="hidden-xs">
                         ${items.oreder }
                      </td>
                      <td class="actions">
                        <div class="action-buttons">
                          
                           <a class="fancybox" href="#fancybox-example-update" onclick="update('${items.id}','${items.name}','${items.oreder}')">
                          <i class="icon-pencil"></i></a>
                          <%-- delete?id=${items.id} --%>
                          <a  class="fancybox" href="#fancybox-example-delete"  onclick="deletes('${items.id}')">
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

			<!-- 新建－弹出层 -->
           <div id="fancybox-example" style="display: none">
                  <h4>添加 </h4>
       	<form action="createOrUpdateToFind" method="post">
                  <div class="row"> 
          <div class="form-horizontal">
            <label class="control-label col-md-4">名称</label>
            <div class="col-md-8">
              <input class="form-control" id="name" name="name" placeholder="名称" type="text">
            </div>
          </div>
          </div>
           <div class="row">
          <div class="form-horizontal">
            <label class="control-label col-md-4">排序</label>
            <div class="col-md-8">
              <input class="form-control" id="oreder" name="oreder" placeholder="排序" type="text">
<%--               <input class="form-control" id="schoolId" name="schoolId" value="${sessionScope.usr_auth.schoolId}" type="hidden"> --%>
            </div>
          </div>
          
   
          </div>
        
 			<div class="row"> 
   					 <div class="text-right">
                        <button class="btn btn-primary" id="btn-createfolder" type="submit">保存</button>
                      </div>
                      </div>
                      
               </form>
             </div>

<!-- 更新－弹出层 -->
    <div id="fancybox-example-update" style="display: none">
                  <h4>修改 </h4>
       	<form action="createOrUpdateToFind" method="post">
                  <div class="row"> 
          <div class="form-horizontal">
            <label class="control-label col-md-4">名称</label>
            <div class="col-md-8">
              <input class="form-control" id="update_name" name="name" placeholder="名称" type="text">
            </div>
          </div>
          </div>
           <div class="row">
          <div class="form-horizontal">
            <label class="control-label col-md-4">排序</label>
            <div class="col-md-8">
              <input class="form-control" id="update_oreder" name="oreder" placeholder="排序" type="text">
              <input class="form-control" id="update_id" name="id" placeholder="排序" type="hidden">
<%--                <input class="form-control" id="update_schoolId" name="schoolId" value="${sessionScope.usr_auth.schoolId}" type="hidden"> --%>
            </div>
          </div>
          
   
          </div>
        
 			<div class="row"> 
   					 <div class="text-right">
                        <button class="btn btn-primary" id="btn-createfolder" type="submit">保存</button>
                      </div>
                      </div>
                      
               </form>
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

    <script type="text/javascript">
    	function update(id,name,order){
    		$("#update_name").val(name);
    		$("#update_id").val(id);
    		$("#update_oreder").val(order);
    	}
    	var delete_id = "";
    	function deletes(id){
    		delete_id=id;
    	}
    	
    	function deleteById() {
    		if (delete_id != "") {
    			window.location.href = "delete?id="+delete_id;
    		}else {
    			var arrChk=$("input[name='optionsRadios1']:checked");
    			var str="";
    		    $(arrChk).each(function(){
    		      str+=this.value+",";                        
    		    });
    		    if(str!=""){
    		    	  var ids=str.substring(0,str.length-1);
    		    	  window.location.href = "delete?&ids="+ids;
    		    }else{
    		    	window.location.href=document.URL;
    		    }
    		  
    			
    		}
    	}
    </script>   
</body>
</html>
