<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>用户列表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pintuer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>
<body>
	<form method="post" action="listUser.do" id="listform">
		<div class="panel admin-panel">
			<div class="panel-head">
				<strong class="icon-reorder">用户列表</strong>
			</div>
			<div class="padding border-bottom">
				<ul class="search">
					<li><a class="button border-yellow" href="editUser.do"><span class="icon-plus-square-o"></span>新增</a>
					</li>
					<li>姓名:&nbsp;&nbsp;<input type="text" name="username" class="input" style="width: 200px; line-height: 17px; display: inline-block"  value="${username}"/>
					</li>
					<li><a href="javascript:void(0)" class="button border-main icon-search" onclick="gosearch();">搜索</a></li>
				</ul>
			</div>
			<table class="table table-hover text-center listAdmin">
				<tr>
					<th width="20%">姓名</th>
					<th width="20%">账号</th>
					<th width="20%">密码</th>
					<th width="10%">上次登录时间</th>
					<th width="20%">操作</th>
				</tr>
				<c:forEach var="user" items="${userlist}">
					<tr>
						<td>${user.realName }</td>
						<td>${user.userName }</td>
						<td>${user.pwd }</td>
						<td>${user.lastLogin}</td>
						<td>
							<a class="button border-main" href="" onclick="toEdit('${user.userId}');return false"><span class="icon-edit"></span>编辑</a>
							<a class="button border-red" href="" onclick="del('${user.userId}');return false"><span	class="icon-trash-o"></span>删除</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</form>
	<script type="text/javascript">
		function del(id) {
			if (confirm("您确定要删除吗?")) {
				$.ajax({
				       cache:false,
				       async:false,
					   type: "POST",
				       url: "delUser.do",
				       data: "userId="+id,
				       success: function(data){
				    	   alert("删除成功!");
				    	   gosearch();
				       },
				       error:function(){
				    	   alert("删除错误！请联系管理员");
				       }
				   });
			}
		}

		function toEdit(id) {
			window.location.href = "editUser.do?userId=" + id;
		}

		function gosearch() {
			$("#listform").submit();
		}
	</script>
</body>
</html>