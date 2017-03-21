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
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>添加刊物</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="modifyPeriod.do" enctype="multipart/form-data">  
      <div class="form-group">
        <div class="label">
          <label>刊物类别</label>
        </div>
        <div class="field">
          <select name="periodicalType.periodicalTypeId" class="input w50"  id="">
          	<c:forEach items="${typeList }" var="item">
          		<c:choose>
          			<c:when test="${p.periodicalType.periodicalTypeId eq item.periodicalTypeId }">
		            	<option value="${item.periodicalTypeId }" selected="selected">${item.typeName }</option>
          			</c:when>
          			<c:otherwise>
          				<option value="${item.periodicalTypeId }">${item.typeName }</option>
          			</c:otherwise>
          		</c:choose>
          		
            </c:forEach>
          </select>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${p.periodicalName }" name="periodicalName"/>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>刊号：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${p.isbn }" name="isbn"/>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>通信地址：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${p.address }" name="address"/>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>联系电话：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${p.tel }" name="tel"/>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>电子邮箱：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${p.email }" name="email"/>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>管理员账号：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${p.admin }" name="admin"/>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>管理员密码：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${p.pwd }" name="pwd"/>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
        	<img src="${pageContext.request.contextPath }/images/${p.pic}" width="200"/>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>封面：</label>
        </div>
        <div class="field">
          <input type="file" class="input w50" value="" name="periodPic"/>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>刊物介绍：</label>
        </div>
        <div class="field">
          <textarea name="detail" cols="50" rows="10" style="border: 1px solid #ccc;">${p.detail }</textarea>
          <div class="tips"></div>
        </div>
      </div>
      
      
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
        	<input type="hidden" value="${p.periodicalId }" name="periodicalId">
        	<input type="hidden" value="${p.status }" name="status">
        	<input type="hidden" value="${p.pic }" name="_orgPicFile">
          <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
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