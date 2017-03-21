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
    <div class="panel-head"><strong class="icon-reorder">&nbsp;征稿启示管理</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px; ">
        <li> <a class="button border-main icon-plus-square-o" href="toAddSolicitCont.do">发布征稿启示</a> </li>
        <!-- <li>
            <a class="button border-red" href="javascript:void(0)" onclick="">
              <span class="icon-trash-o"></span>批量删除
            </a>
          </li> -->
       </ul>
    </div>
    <table class="table table-hover text-center" style="width:100%;border:1px solid #ccc;">
      <tr>
        <th>序号</th>
        <th>发布日期</th>
        <th>栏目名称</th>
        <th>标题</th>
        <th>截稿日期</th>
        <th>投稿数量</th>
        <th width="">操作</th>
      </tr>
      <c:forEach items="${page.records }" var="item" varStatus="row">
      <tr>
        <td>${row.count }</td>
        <td><fmt:formatDate value="${item.publishTime }" type="date" pattern="yyyy-MM-dd"/></td>
        <td>${item.columnType }</td>
        <td>${item.title }</td>
        <td>${item.deadLine }</td>
        <td>${item.contributionsCount }</td>
        <td id="row_${row.index }">
        	<c:if test="${item.deadLine ge today }">
        	<div class="button-group">
        		<a class="button border-main" href="toModifySolicitCon.do?scid=${item.solicitContributionsId }">
	    			<span class="icon-edit"></span>修改
	    		</a>
				<a class="button border-red" href="javascript:void(0)" onclick="return deleteInfo(${item.solicitContributionsId},${row.index });"><span class="icon-edit"></span>截止征稿</a>
			</div>
			</c:if>
			<c:if test="${item.deadLine lt today }">已截稿</c:if>
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
		window.location.href="scMgr.do?step=1";
	}
});
function deleteInfo(bid,rowid){
	if(confirm("确定停止征稿吗？")){
		$.get("stopSc.do", {"scid":bid}, function(data){
			if(data == "0"){
				$("#row_"+rowid).html("已截稿");
			}
		});
	}
}

</script>
</body>
</html>