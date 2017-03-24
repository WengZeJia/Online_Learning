<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <ul>
 	<c:forEach var="user" items="${courseUsers }">
	 	<li class="${sessionScope.user.userId == user.userId ?'choosed':''}">
	 		<c:choose>
	 			<c:when test="${user.online }">
			        <label class="online"></label>
	 			</c:when>
	 			<c:otherwise>
			        <label class="offline"></label>
	 			</c:otherwise>
	 		</c:choose>
	        <a href="javascript:;" class="chat03_name">${user.realName }</a>
	        <c:if test="${sessionScope.user.role==1 }">
	        	<c:choose>
	        		<c:when test="${user.gag }">
	        			<a href="javascript:;" class="user_gag_op chat03_gag"></a>
	        		</c:when>
	        		<c:otherwise>
			 	    	<a href="javascript:;" class="user_gag_op chat03_talk_ok"></a>
	        		</c:otherwise>
	        	</c:choose>
	        	<c:choose>
	        		<c:when test="${user.noHandUp }">
			       		<a href="javascript:;" class="user_hand_op chat03_hand_stop"></a>
	        		</c:when>
	        		<c:otherwise>
			       		<a href="javascript:;" class="user_hand_op chat03_hand_up"></a>
	        		</c:otherwise>
	        	</c:choose>
	        </c:if>
	    </li>
 	</c:forEach>
</ul>