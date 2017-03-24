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
				<li class="active"><a href="record.do">个人中心</a></li>
				<li id="userInfo">
					<c:if test="${sessionScope.user ne null}">
						<a href="#" class="username">${sessionScope.user.realName}</a>
						<a href="logout.do" class="logout">登出</a>
					</c:if>
					<c:if test="${sessionScope.user eq null}">
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
			<h2>报名课程&nbsp;&nbsp;${msg }</h2>
			<table>
				<tr>
					<th>用户</th>
					<th>课程</th>
					<th>报名时间</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${page.records }" var="item">
					<tr>
						<td><a href="javascript:void(0);">${item.user.realName }</a></td>
						<td class="price">${item.courese.cName }</td>
						<td class="price">${item.eTime }</td>
						<td><a href="javascript:void(0);" onclick="" class="reserve">进入学堂</a></td>
					</tr>
				</c:forEach>
				<tr>
	<td colspan="7">
		<div class="pagelist">
			<c:choose>
				<%-- 如果总页数不足10页，那么把所有的页数都显示出来！ --%>
				<c:when test="${page.totalpage <= page.maxresult }">
					<c:set var="begin" value="1" />
					<c:set var="end" value="${page.totalpage }" />
				</c:when>
				<c:otherwise>
					<%-- 当总页数>10时，通过公式计算出begin和end --%>
					<c:set var="begin" value="${page.currentpage-5 }" />
					<c:set var="end" value="${page.currentpage+4 }" />
					<%-- 头溢出 --%>
					<c:if test="${begin < 1 }">
						<c:set var="begin" value="1" />
						<c:set var="end" value="${page.maxresult }" />
					</c:if>
					<%-- 尾溢出 --%>
					<c:if test="${end > page.totalpage }">
						<c:set var="begin" value="${page.totalpage - 9 }" />
						<c:set var="end" value="${page.totalpage }" />
					</c:if>
				</c:otherwise>
			</c:choose>
			<%-- 循环遍历页码列表 --%>
			<a href="" onclick="jumpTo('${page.currentpage-1}');return false">上一页</a>
			<c:forEach var="i" begin="${begin }" end="${end }">
				<c:choose>
					<c:when test="${i eq page.currentpage }">
						<span class="current">${i }</span>
					</c:when>
					<c:otherwise>
						<a href="" onclick="jumpTo('${i }');return false">${i }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${end < page.totalpage}">....</c:if>
			<a href="" onclick="jumpTo('${page.currentpage+1}');return false">下一页</a>
			<a href="" onclick="jumpTo('${page.totalpage}');return false">尾页</a>
		</div>
	</td>
</tr>
				
			</table>
			<input type="hidden" id="totalPage" value="${page.totalpage}">
			<input class="currentpage" name="currentpage" type="hidden" value="1">
			<%-- <div class="pageinfo">
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
			</div>	 --%>
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