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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
	<style type="text/css">
		.search{
		    padding-left: 600px;
    		padding-top: 20px;
    		}
    	.search_chinese{
    		   border-radius: 6px;
   			   background: #0ae;
    		   padding: 2px 15px;
               font-size: 16px;
               padding-top: 6px;
               color: #fff;
    			}
    	.input_search{
    		    padding: 14px 0px;
    			border-radius: 6px;
    			border: 1px solid #CCC;
    			height: 20px;
    			}
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
					<a href="javascript:void(0);" onclick="searchPosition();" class="btn searchBtn search_chinese"><i class="icon icon-search "></i>搜索</a>
				</div>
			<table>
				<thead>
					<tr>
						<th>刊物</th>
						<th>专栏</th>
						<th>稿费</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="scList">
					
				</tbody>
				<tfoot>
					<tr>
						<td colspan="7" id="loadBtn">
							<a href="javascript:void(0);" class="more" onclick="loadMore();">加载更多课程信息...</a>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</div>

<footer id="footer">
	<div class="bottom">Copyright © 在线学习平台| 粤ICP 备120110119 号| 经营许可证：L-YC-BK12345</div>
</footer>
<script type="text/javascript">
	function loadMore(){
		var currPage = parseInt($(".curr-page:last").val());
		var totalPage = parseInt($(".total-page:last").val());
		if(currPage == totalPage){
			$("#loadBtn").html("<a class='more'>已全部加载</a>");
			return ;
		}
		var nextPage = parseInt($(".curr-page:last").val())+1;
		$.get("loadSolicitContributions.do",{"keyword":$("#keyword").val(),"currentpage":nextPage}, function(data){
			$("#scList").append(data);
		});
		
	}
	/* function searchPosition(){
		var param = {"keyword":$("#keyword").val(),currentpage:1};
		$("#scList").empty();
		$("#scList").load("loadSolicitContributions.do", param, function(){
			$("#scList").append(data);
		});
	} */
</script>
</body>
</html>