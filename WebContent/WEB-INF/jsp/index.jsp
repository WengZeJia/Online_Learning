<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8">
	<title>在线学习平台</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/basic.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/column.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
	<style type="text/css">
		 .invalid {background: #BBB;}
		 .invalid:hover {background-color: #BBB;}
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

<div id="headline">
	<div class="center">
		<hgroup>
			<h2>在线学习平台</h2>
			<h3>在这里您可以跟老师们畅游学海，共享知识！</h3>	
		</hgroup>
	</div>
</div>

<div id="container">
	<div class="list ticket">
		<div class="new">
			<h2>课程展示</h2>
				<div class="search">
					<input type="text" id="keyword" name="keyword" class="input_search" value="${key}"/>
					<input id="pageNo" name="pageNo" type="hidden" value="1" />
					<input type="hidden" id="pid" value="${pid}">
					<input class="curr-page" type="hidden" value="${page.currentpage }">
					<input class="total-page" type="hidden" value="${page.totalpage }">
					<a href="javascript:void(0);" onclick="jumpTo(1);" class="btn searchBtn search_chinese"><i class="icon icon-search "></i>搜索</a>
				</div>
			<table>
				<thead>
					<tr>
						<th>课程</th>
						<!-- <th>描述</th> -->
						<th>开课时间</th>
						<th>结课时间</th>
						<th>科目类型</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="scList">
				
				</tbody>
				<%-- <tfoot>
					
					<tr>
						<td colspan="7">
							<%@include file="./Page.jsp" %>
						</td>
					</tr>
				</tfoot> --%>
			</table>
		</div>
	</div>
</div>

<footer id="footer">
	<div class="bottom">Copyright © 在线学习平台| 粤ICP 备120110119 号| 经营许可证：L-YC-BK12345</div>
</footer>
<script type="text/javascript">
//申请
function enrol(pid){
	var param = {pid: pid};
	$.ajax({
		type: "POST",
		async: false,
		url : 'save.do',
		data : param,
		dataType: "json"
	}).done(function(data){
		if(data.result == '00'){
			/* $(".reserve").removeAttr("onclick");
			$(".reserve").addClass("invalid");
			$(".reserve").removeClass("a:hover");
			$(".reserve").text("已报名"); */
			alert("你已成功报名！");
		}
		if(data.result == '01'){
			setTimeout(function(){
				window.location.href = 'login.do';
			},1000);
		}else if(data.result == '02'){
			alert("你已报名，请勿重复报名！");
		}
	}).fail(function(data) {
		swal("系统出错");
	});
}
</script>
</body>
</html>