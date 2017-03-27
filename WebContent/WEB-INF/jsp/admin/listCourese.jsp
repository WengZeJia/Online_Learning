<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>课程列表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>
<body>
	<form method="post" action="listCourese.do" id="listform">
		<div class="panel admin-panel">
			<div class="panel-head">
				<strong class="icon-reorder">课程列表</strong>
			</div>
			<div class="padding border-bottom">
				<ul class="search">
					<li><a class="button border-yellow" href="editCourese.do"><span class="icon-plus-square-o"></span>新增课程</a>
					</li>
					<%-- <li>姓名:&nbsp;&nbsp;<input type="text" name="username" class="input" style="width: 200px; line-height: 17px; display: inline-block"  value="${username}"/>
					</li>
					<li><a href="javascript:void(0)" class="button border-main icon-search" onclick="gosearch();">搜索</a></li> --%>
				</ul>
			</div>
			<table class="table table-hover text-center listAdmin">
				<tr>
					<th width="10%">课程名称</th>
					<th width="30%">课程描述</th>
					<th width="20%">开始时间</th>
					<th width="10%">科目</th>
					<th width="30%">操作</th>
				</tr>
				<c:if test="${fn:length(coureses) == 0 }">
				<tr>
					<td colspan="3" style="line-height: 35px">暂未添加课程</td>
				</tr>
				</c:if>
				<c:forEach var="c" items="${coureses}">
					<tr>
						<td>${c.cName }</td>
						<td>${c.cDescribe }</td>
						<td>${c.startTime }</td>
						<td>${c.type}</td>
						<td>
							<a class="button border-main" href="" onclick="toList('${c.coureseId}');return false">报名情况</a>
							<a class="button border-main" href="" onclick="toEdit('${c.coureseId}');return false"><span class="icon-edit"></span>编辑</a>
							<select id="status" class="input inpt button border-main" style="width:75px; display:inline-block" onchange="del('${c.coureseId}',this);return false">
				            	<option value="0" <c:if test="${c.status == 0}">selected</c:if>>上线</option>
				            	<option value="1" <c:if test="${c.status == 1}">selected</c:if>>下线</option>
				         	</select>
			         	</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</form>
	<script type="text/javascript">
		function del(id,obj) {
			$.ajax({
				async : false,
				type : "POST",
				url : "changeCStatus.do",
				data : "coureseId=" + id +"&status="+$(obj).val(),
				dataType: "text",
				success : function(data) {
				},
				error : function(data) {
					alert("系统繁忙！请联系管理员");
				}
			});
		}

		function toEdit(id) {
			window.location.href = "editCourese.do?coureseId=" + id;
		}
		
		function toList(id) {
			window.location.href = "listEnroll.do?coureseId=" + id;
		}
	</script>
</body>
</html>