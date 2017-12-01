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
  <title>全媒体资源中心-用户管理</title>
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
           用户管理
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
                      用户名
                    </th>
                     <th class="hidden-xs">
                      邮箱
                    </th>
                    <th class="hidden-xs">
                      帐号类型
                    </th>
                     <th class="hidden-xs">
                     备注
                    </th>
                    <!-- 操作 -->
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
                       <td class="hidden-xs">
                        ${items.username }
                      </td>
                       <td class="hidden-xs">
                        ${items.email }
                      </td>
                       <td class="hidden-xs">
                       <c:choose>
                       	<c:when test="${items.type =='SCHOOLMANAGER' }">
                       		学校管理员
                       	</c:when>
                       	<c:when test="${items.type =='TEACHERUSER' }">
                       		学校老师
                       	</c:when>
                       	<c:otherwise>
                       		其他类型
                       	</c:otherwise>
                       </c:choose>
                        
                      </td>
                      <td class="hidden-xs">
                        ${items.remark }
                      </td>
                      <td class="actions">
                        <div class="action-buttons">
                          
                           <a class="fancybox" href="#fancybox-example-update" 
                           onclick="update('${items.id}','${items.name}','${items.username}','${items.password}','${items.email}','${items.remark}')">
                          <i class="icon-pencil"></i></a>
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
       	<!-- 新建－弹出层 -->
           <div id="fancybox-example" style="display: none;width:600px;">
                  <h4>添加 </h4>
               <form action="createOrUpdateUserToFind" method="post">
          <fieldset>
            <div class="row" >
              <div class="col-md-6">
                <div class="form-group">
                  <label for="firstname">姓名</label><input class="form-control" id="name" name="name" type="text">
                </div>
                <div class="form-group">
                  <label for="username">用户名</label><input class="form-control" id="username" name="username" type="text">
                </div>
                <div class="form-group">
                  <label for="password">密码</label><input class="form-control" id="password" name="password" type="password">
                </div>
              </div>
              <div class="col-md-6">
                
                <div class="form-group">
                  <label for="email">邮箱</label><input class="form-control" id="email" name="email" type="email">
                </div>
                <div class="form-group">
                  <label for="lastname">资源权限</label><input class="form-control" id="lastname" name="" type="text">
                </div>
                <div class="form-group">
                  <label for="confirm_password">密码确认</label><input class="form-control" id="confirm_password" name="" type="password">
                </div>
              </div>
            </div>
             <div class="row">
             	<div class="form-group">
		            <label class="control-label col-md-1">备注</label>
		            <div class="col-md-11">
		              <textarea name="remark" class="form-control" rows="3"></textarea>
		            </div>
		      </div>
             </div>
            <input class="btn btn-primary" type="submit" value="保存">
          </fieldset>
        </form>
             </div>

<!-- 更新－弹出层 -->
    <div id="fancybox-example-update" style="display: none;width:600px;">
                  <h4>修改 </h4>
       	 <form action="createOrUpdateUserToFind" method="post">
          <fieldset>
            <div class="row" >
              <div class="col-md-6">
                <div class="form-group">
                  <label for="firstname">姓名</label><input class="form-control" id="update_name" name="name" type="text">
                </div>
                <div class="form-group">
                  <label for="username">用户名</label><input class="form-control" id="update_username" name="username" type="text">
                </div>
                <div class="form-group">
                  <label for="password">密码</label><input class="form-control" id="update_password" name="password" type="password">
                </div>
              </div>
              <div class="col-md-6">
                
                <div class="form-group">
                  <label for="email">邮箱</label><input class="form-control" id="update_email" name="email" type="email">
                </div>
<!--                 <div class="form-group"> -->
                  <label for="lastname">资源权限</label><input class="form-control" id="update_id" name="id" type="hidden">
<!--                 </div> -->
<!--                 <div class="form-group"> -->
<!--                   <label for="confirm_password">密码确认</label><input class="form-control" id="confirm_password" name="" type="password"> -->
<!--                 </div> -->
              </div>
            </div>
             <div class="row">
             	<div class="form-group">
		            <label class="control-label col-md-1">备注</label>
		            <div class="col-md-11">
		              <textarea id="update_remark" name="remark" class="form-control" rows="3"></textarea>
		            </div>
		      </div>
             </div>
            <input class="btn btn-primary" type="submit" value="保存">
          </fieldset>
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
    	function update(id,name,username,password,email,remark){
			$("#update_id").val(id);
    		$("#update_name").val(name);
    		$("#update_username").val(username);
    		$("#update_password").val(password);
    		$("#update_email").val(email);
    		$("#update_remark").val(remark);
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