<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>在线学习平台后台管理中心</title>  
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>   
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1>后台管理中心</h1>
  </div>
  <div class="head-l">
  	<a class="button button-little bg-green" href="/osrs/index.do" target="_blank">
  		<span class="icon-home"></span> 前台首页</a> &nbsp;&nbsp;
  	<a class="button button-little bg-red" href="logout.do">
  		<span class="icon-power-off"></span> 退出登录
  	</a>
  </div>
</div>
<div class="leftnav">
  <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
  <h2><span class="icon-user"></span>系统信息管理</h2>
  <c:if test="${sessionScope.adminRole == 0}">
  <ul style="display: block;">
    <li><a href="periodMgr.do?step=1" target="right"><span class="icon-caret-right"></span>发布课程</a></li>
    <li><a href="toAddPeriod.do" target="right"><span class="icon-caret-right"></span>实时课程</a></li>
  </ul>   
  </c:if>
  <c:if test="${sessionScope.adminRole == 1}">
  <h2><span class="icon-star"></span>用户管理</h2>
  <ul style="display:block">
    <li><a href="listUser.do?step=1" target="right"><span class="icon-caret-right"></span>用户列表</a></li>
  </ul>
  </c:if>
</div>
<script type="text/javascript">
$(function(){
  $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);	
	  $(this).toggleClass("on"); 
  });
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  });
});
</script>
<div class="admin">
  <iframe scrolling="auto" rameborder="0" src="/osrs/html/welcome.html" name="right" width="100%" height="100%"></iframe>
</div>
</body>
</html>