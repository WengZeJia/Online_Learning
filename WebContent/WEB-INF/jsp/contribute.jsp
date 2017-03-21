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
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tinymce/tinymce.min.js"></script>
	<style type="text/css">
		.sc-content{line-height: 35px;text-indent:40px}
	</style>
</head>
<body>

<header id="header">
	<div class="center">
		<h1 class="logo">在线学习</h1>
		<nav class="link">
			<h2 class="none">网站导航</h2>
			<ul>
				<li><a>&nbsp;</a></li>
				<li class="active"><a href="index.do">首页</a></li>
				<li><a href="profile.do">个人中心</a></li>
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
	<div class="list ticket">
		<!-- <form action="contribute.do" method="post"> -->
			<h2>投稿</h2>
			<input type="text" name="title" id="noticeTitle" placeholder="请输入标题" style="height: 40px; line-height: 40px; border: 1px solid #ccc; margin: 20px 0; width: 500px;">
			<textarea rows="10" cols="50" placeholder="正文内容"></textarea>
			<br/>
			<div class="form right">
				<p>
					<label for="">&nbsp; </label>
					<input type="hidden" id="solicitContributionsId" value="${sc.solicitContributionsId }">
					<button type="submit" class="submit" onclick="submitNotice();">提交稿件</button>
				</p>
			</div>
		<!-- </form> -->
	</div>
</div>

<footer id="footer">
	<div class="bottom">Copyright © 在线学习平台| 粤ICP 备120110119 号| 经营许可证：L-YC-BK12345</div>
</footer>
<script type="text/javascript">
tinyMCE.init({
	mode : "textareas",
	plugins :"textcolor"
	});
	
	function submitNotice(){
		var content = tinyMCE.activeEditor.getContent();
		var scid=$("#solicitContributionsId").val();
		var noticeTitle = $("#noticeTitle").val();
		console.log(content);
		console.log(scid);
		console.log(noticeTitle);
		$.post("contribute.do",{"scid":scid,"title":noticeTitle,"content":content}, function(){
				window.location.href="profile.do";
			/* if(data==0){
			}else if(data==-1){
				window.location.href="login.do";
			} */
		});
	}
</script>
</body>
</html>