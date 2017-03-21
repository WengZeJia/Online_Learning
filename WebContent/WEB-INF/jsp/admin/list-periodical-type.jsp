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
</head>
<body>
<form method="post" action="" id="listform">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder">&nbsp;刊物类别列表</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px; ">
        <li> <a class="button border-main icon-plus-square-o" href="toAddPeriodType.do">添加类别</a> </li>
        <!-- <li>
            <a class="button border-red" href="javascript:void(0)" onclick="">
              <span class="icon-trash-o"></span>批量删除
            </a>
          </li> -->
       </ul>
    </div>
    <table class="table table-hover text-center" style="width:60%;border:1px solid #ccc;">
      <tr>
        <th>序号</th>
        <th>名称</th>
        <th>备注</th>
        <th width="">操作</th>
      </tr>
      <c:forEach items="${list }" var="item" varStatus="row">
      <tr id="row_${row.index }">
        <td>${row.count }</td>
        <td>${item.typeName }</td>
        <td>${item.remark }</td>
        <td>
        	<div class="button-group">
				<a class="button border-main" href="toModifyType.do?ptid=${item.periodicalTypeId }"><span class="icon-edit"></span>修改</a>
				<%-- <a class="button border-red" href="javascript:void(0)" onclick="return deleteInfo(${item.buildingInfoId},${row.index });"><span class="icon-trash-o"></span>删除</a> --%>
			</div>
		</td>
      </tr>
      </c:forEach>
    </table>
  </div>
</form>
<script type="text/javascript">


</script>
</body>
</html>