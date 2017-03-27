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
<title>实时课程列表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>
<body>
	<form method="post" action="listCourese.do" id="listform">
		<div class="panel admin-panel">
			<div class="panel-head">
				<strong class="icon-reorder">实时课程列表</strong>
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
							<a class="button border-main" href="${pageContext.request.contextPath }/chat/toChatPage.do?coureseId=${c.coureseId}" target="_blank">开始课程</a>
			         	</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</form>
</body>
</html>