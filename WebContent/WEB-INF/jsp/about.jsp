<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8">
	<title>在线投稿系统</title>
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
				<li><a href="profile.do">个人中心</a></li>
				<li class="active"><a href="about.do">关于我们</a></li>
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

<div id="headline">
	<div class="center">
		<hgroup>
			<h2>在线投稿系统</h2>
			<h3>在这里您可以向各类刊物投递稿件，一站式服务！</h3>	
		</hgroup>
	</div>
</div>

<div id="container">
	<!-- <aside class="sidebar">
		<div class="sidebox hot">
			<h2>热门刊物</h2>
			<div class="figure" id="hotPeriod">
				
			</div>
		</div>
	</aside> -->
	<div class="list about">
		<section>
			<h2>关于我们</h2>
			<p>在线投稿系统为您提供一站式投稿服务，我们致力于为写作爱好者、职业作者、刊物媒体提供便捷、高效的投稿与征稿服务。</p>
			<p>目前，在线投稿系统已入驻了大量国内主流刊物媒体，注册作者已超过10000人，平台每日发布征稿启示百余份，作者投稿数量上千人次，成为作者展示才华、媒体吸收内容的主流平台。</p>
		</section>
		<p>&nbsp;</p>
		<section>
			<h2>联系方式</h2>
			<address>
				<ul>
					<li>在线投稿平台</li>
					<li>地址：XX省XX市XX路1234 号</li>
					<li>邮编：1234567</li>
					<li>电话：010-88888888</li>
					<li>传真：010-88666666</li>
				</ul>
			</address>
		</section>
	</div>
</div>

<footer id="footer">
	<div class="bottom">Copyright © 在线学习平台| 粤ICP 备120110119 号| 经营许可证：L-YC-BK12345</div>
</footer>

</body>
</html>