<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>修改密码</title>
<style type="text/css">
.public-body {
	width: 800px;
	margin-left: 400px;
	margin-top: 60px;
	margin-left: 400px;
	margin-bottom: 50px;
}

.panel-default {
	height: 300px;
	margin-top: 50px !important;
}
.a-try{
	margin-left: 10px;
}
</style>
<%
	String result=request.getParameter("result");
	result = result == null?"":result;
 %>
</head>

<body>
	<jsp:include page="/nav/_UserHead.jsp"></jsp:include>
	<jsp:include page="/nav/_UserInfoHead.jsp"></jsp:include>
	<div class="public-body">
		<ul class="breadcrumb">
			<li><a href="index">主页</a></li>
			<li class="active">修改密码</li>
		</ul>
		<%if(result.equals("")) {%>
		<div class="form-group panel panel-default">
			<div class="panel-heading">
				<h3 style="">修改密码</h3>
			</div>
			<form action="AmendPass" method="post" class="form-horizontal">
				<div style="margin-top: 20px">
					<div class="form-group">
						<label class="col-md-2 control-label">原密码: </label>
						<div class="col-md-6">
							<input class=" form-control" type="password" name="oldPassword">
						</div>
					</div>
					<div class="form-group">

						<label class="col-md-2 control-label">新密码: </label>
						<div class="col-md-6">
							<input class=" form-control" type="password"  name="newPassword">
						</div>
					</div>
					<div class="col-md-offset-2 col-md-6">
						<input type="submit" value="提交" class="btn btn-info">
					</div>
				</div>
			</form>
		</div>
		<%}else if(result.equals("success")){ %>
		<div class="form-group panel panel-default">
		<div class="panel-heading">
				<h3 style="">修改密码</h3>
			</div>
			<h4 style="margin: 50px">修改密码成功，请重新<a href="login/MyLog">登录</h4></p>
		</div>
		<%}else{ %>
		<div class="form-group panel panel-default">
		<div class="panel-heading">
				<h3 >修改密码</h3>
			</div>
			<h4 style="margin: 50px">很遗憾，原始密码错误<a  href="#">点此重试</a></h4>
			
		</div>
		<%} %>
	</div>
	<jsp:include page="/nav/footer.jsp"/>
</body>
</html>
