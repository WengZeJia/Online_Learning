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
<title>报名列表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>
<body>
	<form method="post" action="listCourese.do" id="listform">
		<div class="panel admin-panel">
			<div class="panel-head">
				<strong class="icon-reorder">报名列表</strong>
			</div>
			<div class="padding border-bottom">
				<ul class="search">
				</ul>
			</div>
			<table class="table table-hover text-center listAdmin">
				<tr>
					<th width="30%">课程名称</th>
					<th width="30%">学生姓名</th>
					<th width="40%">报名时间</th>
				</tr>
				<c:if test="${fn:length(enrolls) == 0 }">
				<tr>
					<td colspan="3" style="line-height: 35px">暂无学生报名</td>
				</tr>
				</c:if>
				<c:forEach var="c" items="${enrolls}">
					<tr>
						<td>${c.courese.cName }</td>
						<td>${c.user.realName }</td>
						<td>${c.eTime }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</form>
</body>
</html>