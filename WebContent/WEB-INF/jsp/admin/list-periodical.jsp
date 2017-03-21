<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/pintuer.js"></script>
</head>
<body>
<form method="post" action="" id="listform">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder">&nbsp;刊物信息管理</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px; ">
        <li> <a class="button border-main icon-plus-square-o" href="toAddPeriod.do">添加刊物</a> </li>
        <!-- <li>
            <a class="button border-red" href="javascript:void(0)" onclick="">
              <span class="icon-trash-o"></span>批量删除
            </a>
          </li> -->
       </ul>
    </div>
    <table class="table table-hover text-center" style="width:100%;border:1px solid #ccc;">
      <tr>
        <th>刊物名称</th>
        <th>刊号</th>
        <th>类别</th>
        <th>通信地址</th>
        <th>联系电话</th>
        <th>电子邮件</th>
        <th width="">操作</th>
      </tr>
      <c:forEach items="${list }" var="item" varStatus="row">
      <tr id="row_${row.index }">
        <td>${item.periodicalName }</td>
        <td>${item.isbn }</td>
        <td>${item.periodicalType.typeName }</td>
        <td>${item.address }</td>
        <td>${item.tel }</td>
        <td>${item.email }</td>
        <td>
        	<div class="button-group">
        		<a class="button border-main" href="toModifyPeriod.do?pid=${item.periodicalId }">
	    			<span class="icon-edit"></span>修改
	    		</a>
				<a class="button border-red" href="javascript:void(0)" onclick="return deleteInfo(${item.periodicalId},${row.index });"><span class="icon-edit"></span>删除</a>
			</div>
		</td>
      </tr>
      </c:forEach>
    </table>
  </div>
</form>
  <input type="hidden" id="step" value="${step }">
<script type="text/javascript">
$(function(){
	if($("#step").val()=="99"){
		window.location.href="periodMgr.do?step=1";
	}
});
function deleteInfo(bid,rowid){
	if(confirm("确定删除该刊物吗？")){
		$.get("deletePeriod.do", {"pid":bid}, function(data){
			if(data == "0"){
				$("#row_"+rowid).remove();
			}
		});
	}
}

</script>
</body>
</html>