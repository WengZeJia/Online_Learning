<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>课程编辑</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/pintuer.js"></script>
<script src="${pageContext.request.contextPath}/js/common.js"></script>
<script src="${pageContext.request.contextPath}/scripts/My97DatePicker/WdatePicker.js"></script>
</head>
 <body>
	<div class="panel admin-panel">
		<div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>课程编辑</strong></div>
		<div class="body-content">
			 <form method="post" id="adminForm" class="form-x" action="saveCourese.do">  
			 <input type="hidden" name="coureseId" value="${courese.coureseId }">
			 <input type="hidden" name="status" value="${courese.status }">
				<div class="form-group">
				  <div class="label">
				    <label>课程名称</label>
				  </div>
				  <div class="field">
				    <input type="text" class="input w50" id="cName" name="cName" data-validate="required:请输入课程名称" value="${courese.cName }">
				    <div class="tips"></div>
				  </div>
					</div>  
				<div class="form-group">
				  <div class="label">
				    <label>课程科目 </label>
				  </div>
				  <div class="field">
				    <input type="text" class="input w50" id="type" name="type" data-validate="required:请输入课程科目" value="${courese.type }">
				    <div class="tips"></div>
				  </div>
				</div>  
				<div class="form-group">
				  <div class="label">
				    <label>开始时间</label>
				  </div>
				  <div class="field">
				   <input type="text" class="input w50" name="startTime" data-validate="required:请输入开始时间" value="${courese.startTime }" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'});" readonly="readonly">
				   <div class="tips"></div>
				  </div>
				</div>
				<div class="form-group">
				  <div class="label">
				    <label>课程描述</label>
				  </div>
				  <div class="field">
				    <textarea class="input" id="cDescribe" name="cDescribe" rows="4" cols="1" data-validate="required:请输入课程描述">${courese.cDescribe }</textarea>
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
	$(document).on("blur","#realName", function(){
		$("#userName").val(pinyin.getFullChars($(this).val()).toLowerCase());
	});
	</script>
 </body>
</html>