<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:forEach items="${page.records }" var="item">
	<tr>
		<td><a href="javascript:void(0);">${item.cName }</a></td>
		<td class="price">${item.startTime }</td>
		<td class="price"><fmt:formatDate value="${item.releaseTime }" pattern="yyyy-MM-dd"></fmt:formatDate></td>
		<td class="price">${item.type }</td>
		<td><a href="javascript:void(0);" onclick="enrol('${item.coureseId}')" class="reserve">报名</a></td>
	</tr>
</c:forEach>
<tr>
	<td colspan="7">
		<div class="pagelist">
			<c:choose>
				<c:when test="${page.totalpage <= page.maxresult }">
					<c:set var="begin" value="1" />
					<c:set var="end" value="${page.totalpage }" />
				</c:when>
				<c:otherwise>
					<c:set var="begin" value="${page.currentpage-5 }" />
					<c:set var="end" value="${page.currentpage+4 }" />
					<c:if test="${begin < 1 }">
						<c:set var="begin" value="1" />
						<c:set var="end" value="${page.maxresult }" />
					</c:if>
					<c:if test="${end > page.totalpage }">
						<c:set var="begin" value="${page.totalpage - 9 }" />
						<c:set var="end" value="${page.totalpage }" />
					</c:if>
				</c:otherwise>
			</c:choose>
			
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
<input type="hidden" id="totalPage" value="${page.totalpage}">
