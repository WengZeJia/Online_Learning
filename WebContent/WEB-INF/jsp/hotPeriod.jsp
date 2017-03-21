<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:forEach items="${hotPeriods }" var="item">
	<figure>
		<a href="pdetail.do?pid=${item.periodicalId }"><img src="${pageContext.request.contextPath}/images/${item.pic }" alt="${item.periodicalName }" width="120">
		<figcaption>${item.periodicalName }</figcaption></a>
	</figure>
</c:forEach>