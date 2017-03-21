<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8">
	<title>在线学习平台</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/basic.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/column.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
</head>
<body>

<header id="header">
	<div class="center">
		<h1 class="logo">在线学习</h1>
		<nav class="link">
			<h2 class="none">网站导航</h2>
			<ul>
				<li><a>&nbsp;</a></li>
				<li><a href="index.do">首页</a></li>
				<li class="active"><a href="profile.do">个人中心</a></li>
				<li id="userInfo">
					<c:if test="${sessionScope.currUser ne null}">
						<a href="#" class="username">${sessionScope.currUser.realName}</a>
						<a href="logout.do" class="logout">登出</a>
					</c:if>
					<c:if test="${sessionScope.currUser eq null}">
						<a href="login.do">登录&nbsp;</a><!-- <a href="toRegist.do">注册</a> -->
					</c:if>
				</li>
			</ul>
		</nav>
	</div>
</header>

<div id="container">
	<!-- <aside class="sidebar">
		<div class="sidebox hot">
			<h2>热门刊物</h2>
			<div class="figure" id="hotPeriod">
				
			</div>
		</div>
	</aside> -->
	<div class="list ticket">
		<form action="loginForm.do" method="post">
			<h2>投稿记录&nbsp;&nbsp;${msg }</h2>
			<table>
				<tr>
					<td>序号</td>
					<td>投稿日期</td>
					<td>刊物</td>
					<td>栏目</td>
					<td>状态</td>
				</tr>
				<c:forEach items="${page.records }" var="item" varStatus="row">
					<tr>
						<td>${row.count }</td>
						<td><fmt:formatDate value="${item.publishTime }" type="date" pattern="yyyy-MM-dd"/></td>
						<td>${item.solicitContributions.periodical.periodicalName }</td>
						<td>${item.solicitContributions.columnType }</td>
						<td>
							<c:choose>
								<c:when test="${item.status == 0}">待审</c:when>
								<c:when test="${item.status == 1}">已采用</c:when>
								<c:when test="${item.status == -1}">退稿</c:when>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="pageinfo">
				<ul>
					<c:choose>
						<c:when test="${page.currentpage==1}"></c:when>
						<c:otherwise><li><a href="profile.do?currentpage=1">首页</a></li></c:otherwise>
					</c:choose>
					<c:if test="${page.currentpage>2}"><li><a href="profile.do?currentpage=${page.currentpage-1}">上一页</a></li></c:if>
					<c:if test="${page.currentpage<page.totalpage}"><li><a href="profile.do?currentpage=${page.currentpage+1}">下一页</a></li></c:if>
							<c:choose>
						<c:when test="${page.currentpage<page.totalpage}"><li><a href="profile.do?currentpage=${page.totalpage}">末页</a></li></c:when>
						<c:otherwise></c:otherwise>
					</c:choose>
				</ul>
			</div>	
			<div class="form button">
				
			</div>
		</form>
	</div>
</div>

<footer id="footer">
	<div class="bottom">Copyright © 在线学习平台| 粤ICP 备120110119 号| 经营许可证：L-YC-BK12345</div>
</footer>

</body>
</html>