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
				<!-- <li><a>&nbsp;</a></li> -->
				<li><a href="index.do">首页</a></li>
				<li><a href="record.do">个人中心</a></li>
				<li id="userInfo" class="active">
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
 	<div class="list ticket">
		<form action="loginForm.do" method="post">
			<h2>用户登录&nbsp;&nbsp;${msg }</h2>
			<div class="form left">
				<p>
					<label for="from_city">登录名</label>
					<input type="text" name="username" id="from_city" placeholder="用于登录本系统">
				</p>
				<p>
					<label for="to_date">密码 </label>
					<input type="password" name="pwd" id="to_date" placeholder="登录密码">
				</p>
			</div>
			<div class="form right">
				<p>
					<label for="">&nbsp; </label>
					<button type="submit" class="submit">登录</button>
				</p>
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