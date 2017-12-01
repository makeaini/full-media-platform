<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<c:set var="ctx" value="${pageContext.request.contextPath}" /> 
<c:set var="schoolId" value="${sessionScope.usr_auth.schoolId}" /> 
<%@page isELIgnored="false"%> 
<!doctype html>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>全媒体资源中心-资源</title>
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
           资源管理
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
                       资源名称
                    </th>
                    <th class="hidden-xs">
                      上传人
                    </th>
                     <th class="hidden-xs">
                      资源信息
                    </th>
                    <th class="hidden-xs">
                      附件内容
                    </th>
                     <th class="hidden-xs">
                     状态
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
                        ${items.udpateName }
                      </td>
                       <td class="hidden-xs">
                        ${items.grade.name }-
                        ${items.subject.name }-
                        ${items.resourcesType.name }
                      </td>
                       <td class="hidden-xs">
                     
		                    <c:if test="${fn:contains(items.fileSuperType,'PICTURE')}">
			                     <span class="label label-warning pull-right">
			                        图片
			                      </span>
			                </c:if>
			                <c:if test="${fn:contains(items.fileSuperType,'DOCUMENT')}">
			                     <span class="label label-success pull-right">
			                        文档
			                      </span>
			                </c:if>
			                <c:if test="${fn:contains(items.fileSuperType,'OTHER')}">
			                     <span class="label label-success pull-right">
			                        其他
			                      </span>
			                </c:if>
			                <c:if test="${fn:contains(items.fileSuperType,'VIDEO')}">
			                     <span class="label label-warning pull-right">
			                        视频
			                      </span>
			                </c:if>
		                     		
                        
                      </td>
                      <td class="hidden-xs">
                        <c:choose>
                        	<c:when test="${items.rolePermissions =='WEBSHOW'}">
                        		<span style="color: #419E15">对外共享</span>
                        	</c:when>
                        	<c:when test="${items.rolePermissions =='NOWEBSHOW'}">
                        		<span >校内私有</span>
                        	</c:when>
                        	<c:when test="${items.rolePermissions =='EXAMINE'}">
                        		<span style="color: red">等待审核</span>
                        	</c:when>
                        	<c:otherwise>
                        		异常
                        	</c:otherwise>
                        </c:choose>
                      </td>
                      <td class="actions">
                        <div class="action-buttons">
                         <a class="table-actions" href="${ctx}/resourcesCenter/getResourceDetail?resourceId=5613c5ef8b6b7321f20e291f">
                         <i class="icon-eye-open" title="查看资源详细"></i></a>
                        
                           <a class="fancybox" href="#fancybox-example-update" 
                           onclick="update('${items.id}','${items.rolePermissions}')" title="审核资源">
                          <i class="icon-pencil"></i></a>
                          
                          <a style="padding-left: 8px;" class="fancybox" 
                          href="#fancybox-example-delete"  onclick="deletes('${items.id}')">
                          <i class="icon-trash"  title="删除资源"></i></a>
                          
                          
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

<!-- 更新－弹出层 -->
    <div id="fancybox-example-update" style="display: none;width:600px;">
                  <h4>资源审核</h4>
       	 <form action="updateRolePermissions" method="post">
          <fieldset>
            <div class="row" >
            	<div class="col-lg-12">
	            	 <div class="form-group">
			            <div class="col-md-12">
			              <label class="radio-inline"><input name="rolePermissions" type="radio" value="NOWEBSHOW"><span>校内私有</span></label>
			              <label class="radio-inline"><input name="rolePermissions" type="radio" value="WEBSHOW"><span>对外共享</span></label>
			              <label class="radio-inline"><input name="rolePermissions" type="radio" value="EXAMINE"><span>等待审核</span></label>
			            </div>
			          </div>
				  </div>
              </div>
              <input class="form-control" id="update_id" name="id" type="hidden">
            <input class="btn btn-primary" type="submit" value="提交审核">
            
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
    	function update(id,rolePermissions){
			$("#update_id").val(id);
			//$("#rolePermissions").attr("checked","checked");
			
		 	if(rolePermissions=="NOWEBSHOW"){
		 		$("input:radio[name='rolePermissions']").eq(0).attr("checked",true);
			}
		 	if(rolePermissions=="WEBSHOW"){
				$("input:radio[name='rolePermissions']").eq(1).attr("checked",true);
			}
			if(rolePermissions=="EXAMINE"){
				$("input:radio[name='rolePermissions']").eq(2).attr("checked",true);
			} 
			
			
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