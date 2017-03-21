<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script src="${pageContext.request.contextPath}/scripts/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
	.cont-detail{
	
	}
	.cont-detail .title{
	
	}
	.cont-detail .content{
	
	}
	.btn{
		width: 60px;
		height: 30px;
		line-height: 30px;
		text-align: center;
		background-color: #09c;
		color: #fff;
		display: block;
	}
</style>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>查阅来稿</strong></div>
  <div class="body-content cont-detail">
  	<h3 class="title">${cont.title }</h3>
  	<p class="content">${cont.content }</p>
  	<a class="btn" href="javascript:window.history.go(-1);">返回</a>
  </div>
</div>
<script type="text/javascript">
$(function(){
	
});
</script>
</body></html>