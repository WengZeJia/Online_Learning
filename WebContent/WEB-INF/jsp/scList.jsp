<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:forEach items="${page.records }" var="item">
	<tr>
		<td><a href="pdetail.do?pid=${item.periodical.periodicalId }">${item.periodical.periodicalName }</a></td>
		<td>${item.columnType }</td>
		<td class="price">¥${item.fee }</td>
		<td><a href="scDetail.do?scid=${item.solicitContributionsId }" class="reserve">详情</a>&nbsp;<a href="toContr.do?scid=${item.solicitContributionsId }" class="reserve">报名</a></td>
	</tr>
</c:forEach>
<input class="curr-page" type="hidden" value="${page.currentpage }">
<input class="total-page" type="hidden" value="${page.totalpage }">