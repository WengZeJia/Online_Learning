<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title></title>
</head>
<body>
<%-- <div class="pagelist"> 
	<c:choose>
		如果总页数不足10页，那么把所有的页数都显示出来！
		<c:when test="${page.totalpage <= page.maxresult }">
			<c:set var="begin" value="1" />
			<c:set var="end" value="${page.totalpage }" />
		</c:when>
		<c:otherwise>
			当总页数>10时，通过公式计算出begin和end
			<c:set var="begin" value="${page.currentpage-5 }" />
			<c:set var="end" value="${page.currentpage+4 }" /> 
			头溢出
			<c:if test="${begin < 1 }">
				<c:set var="begin" value="1" />
				<c:set var="end" value="${page.maxresult }" />
			</c:if> 
			尾溢出
			<c:if test="${end > page.totalpage }">
				<c:set var="begin" value="${page.totalpage - 9 }" />
				<c:set var="end" value="${page.totalpage }" />
			</c:if> 
		</c:otherwise>
	</c:choose>
	循环遍历页码列表
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
</div> --%>
</body>
</html>