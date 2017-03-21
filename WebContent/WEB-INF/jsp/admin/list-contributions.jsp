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
    <div class="panel-head"><strong class="icon-reorder">&nbsp;来稿管理</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
    <table class="table table-hover text-center" style="width:100%;border:1px solid #ccc;">
      <tr>
        <th>序号</th>
        <th>征稿栏目</th>
        <th>征稿启示</th>
        <th>投稿日期</th>
        <th>投稿人</th>
        <th>稿件标题</th>
        <th>处理情况</th>
        <th width="">操作</th>
      </tr>
      <c:forEach items="${page.records }" var="item" varStatus="row">
      <tr>
        <td>${row.count }</td>
        <td>${item.solicitContributions.columnType }</td>
        <td>${item.solicitContributions.title }</td>
        <td><fmt:formatDate value="${item.publishTime }" type="date" pattern="yyyy-MM-dd"/></td>
        <td>${item.user.realName }</td>
        <td><a href="contributionDetail.do?cid=${item.contributionsId }">${item.title }</a></td>
        <td id="row_${row.index }">
			<c:if test="${item.status eq 0 }">未处理</c:if>
			<c:if test="${item.status eq 1 }">已采用</c:if>
			<c:if test="${item.status eq -1 }">已退稿</c:if>
		</td>
        <td id="opt_${row.index }">
        	<c:if test="${item.status eq 0 }">
        	<div class="button-group">
        		<a class="button border-main" href="javascript:void(0);" onclick="adopt(${item.contributionsId },${row.index });">
	    			<span class="icon-edit"></span>采用
	    		</a>
				<a class="button border-red" href="javascript:void(0)" onclick="deleteInfo(${item.contributionsId },${row.index });"><span class="icon-edit"></span>退稿</a>
			</div>
			</c:if>
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
function adopt(bid,rowid){
	if(confirm("确定采用吗？")){
		$.get("adopt.do", {"cid":bid}, function(data){
			if(data == "0"){
				$("#row_"+rowid).html("已采用");
				$("#opt_"+rowid).remove();
			}
		});
	}
}
function deleteInfo(bid,rowid){
	if(confirm("确定退稿吗？")){
		$.get("refuse.do", {"cid":bid}, function(data){
			if(data == "0"){
				$("#row_"+rowid).html("已退稿");
				$("#opt_"+rowid).remove();
			}
		});
	}
}

</script>
</body>
</html>