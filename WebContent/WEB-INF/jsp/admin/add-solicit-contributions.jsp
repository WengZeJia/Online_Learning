<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/pintuer.js"></script>
<script src="${pageContext.request.contextPath}/scripts/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>发布征稿启示</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="saveSolicitCon.do">  
      <div class="form-group">
        <div class="label">
          <label>标题：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" name="title"/>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>栏目名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" name="columnType"/>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>稿费：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" name="fee"/>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>截止日期：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" name="deadLine" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>详情：</label>
        </div>
        <div class="field">
          <textarea name="content" cols="50" rows="10" style="border: 1px solid #ccc;"></textarea>
          <div class="tips"></div>
        </div>
      </div>
      
      
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
        	<input type="hidden" value="1" name="step">
          <button class="button bg-main icon-check-square-o" type="submit">发布</button>
        </div>
      </div>
    </form>
  </div>
</div>
<script type="text/javascript">
$(function(){
	
});
</script>
</body></html>