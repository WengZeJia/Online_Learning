<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="UTF-8">
<title>在线学习平台</title>
<style type="text/css">
#wPaint {
	position: relative;
	width: 730px;
	background-color: #FFFFFF;
	height: 400px;
	margin: 0 auto;
}
</style>
</head>
<body>
	<input type="hidden" id="courseId" value="${courseId }">
	<div id="wPaint"><img id="wPaintImg" /></div>
</body>
<script type="text/javascript">
$(document).ready(function() {
	if (parseInt('${sessionScope.user.role}')) {
		$('#wPaint').wPaint({
			path : '${context}/wPaint/',
			image: '${context}/wPaint/getImage.do',
			saveImg : saveImg
		});
		initPaintImg();
		
		function initPaintImg() {
			$.ajax({
				cache : false,
				timeout : 30000, //30s超时
				url : "${context}/wPaint/getImage.do",
				data : {
					'courseId' : $("#courseId").val()
				},
			}).done(function(img) {
				$('#wPaint').wPaint('image', img);
				setInterval(saveImg, 1000);
			});
		}

		function saveImg() {
			var imageData = $("#wPaint").wPaint("image");
			var courseId = $("#courseId").val();
			$.ajax({
				type : 'POST',
				url : '${context}/wPaint/saveImg.do',
				data : {'image' : imageData, 'courseId':courseId}
			});
		}
	} else {
		setInterval(function () { 
			getCourseImage();
		}, 500);
		
		function getCourseImage() {
			$.ajax({
				cache : false,
				timeout : 30000, //30s超时
				url : "${context}/wPaint/getImage.do",
				data : {
					'courseId' : $("#courseId").val()
				},
			}).done(function(img) {
				$("#wPaintImg").attr("src", img);
			});
		}
	}
});
	
</script>
</html>