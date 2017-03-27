<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>用户编辑</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/pintuer.js"></script>
<script src="${pageContext.request.contextPath}/js/common.js"></script>
<script src="${pageContext.request.contextPath}/js/pinyin.js"></script>
</head>
 <body>
	<div class="panel admin-panel">
		<div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>用户编辑</strong></div>
		<div class="body-content">
			 <form method="post" id="adminForm" class="form-x" action="saveUser.do">  
			 <input type="hidden" name="userId" value="${u.userId }">
				<div class="form-group">
				  <div class="label">
				    <label>姓名</label>
				  </div>
				  <div class="field">
				    <input type="text" class="input w50" id="realName" name="realName" data-validate="required:请输入姓名" value="${u.realName }">
				    <div class="tips"></div>
				  </div>
				</div>  
				<div class="form-group">
				  <div class="label">
				    <label>用户名</label>
				  </div>
				  <div class="field">
				    <input type="text" class="input w50" id="userName" name="userName" data-validate="required:请输入用户名" value="${u.userName }" <c:if test="${!empty u.userId}">readonly</c:if>>
				    <div class="tips"></div>
				  </div>
				</div>   
				<div class="form-group">
				  <div class="label">
				    <label>密码</label>
				  </div>
				  <div class="field">
				   <input type="text" class="input w50" id="pwd" name="pwd" data-validate="required:请输入密码" value="${u.pwd }">
				   <div class="tips"></div>
				  </div>
				</div>
				<div class="form-group">
				  <div class="label">
				    <label>角色</label>
				  </div>
				  <div class="field">
				  	<select name="role" class="input w50">
				  		<option value="0" <c:if test="${u.role ==0 }">selected</c:if>>学生</option>
				  		<option value="1" <c:if test="${u.role ==1 }">selected</c:if>>老师</option>
				  	</select>
				    <div class="tips"></div>
				  </div>
				</div>
				<c:if test="${!empty error}">
				<div class="form-group">
				  <div class="label">
				    <label></label>
				  </div>
				  <div class="field">
				    <div class="tips" style="color: red;">${error}</div>
				  </div>
				</div>
				</c:if>
				 <div class="form-group">
				  <div class="label">
				    <label></label>
				  </div>
				  <div class="field">
				    <button class="button bg-main icon-check-square-o" type="submit">保存</button>
				    <button class="button bg-main " type="button" onclick="window.history.go(-1)">返回</button>
				  </div>
				 </div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		if('${u.userId }' == ''){
			$(document).on("blur","#realName", function(){
				$("#userName").val(pinyin.getFullChars($(this).val()).toLowerCase());
			});
		}
	</script>
 </body>
</html>