
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

<title>未登录</title>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>FirstBid/First.css">

<style type="text/css">
.bid-div {
	border: 3px solid #fafafa;
	margin-top: 10px;
}

.bid-div a {
	float: right;
	margin-top: -45px;
	margin-right: 5px;
}

.result-div {
	margin-top: 50px;
}
.mybody{
	background-image:url("img/tip.png") ;
	width: 600px;
	height:300px;
	margin: 50px auto;
}
.txt{
	position: relative;
	width:410px;
	top:200px;
	left:95px;
}
</style>
</head>

<body>
	<jsp:include page="/nav/_UserHead.jsp" />
	<div class="mybody">
	<div class="txt">
		<h3 style=" width:300px;margin-left: 55px;">对不起，您还未登录，无法进行此操作</h3>
		<h3 style=" width:100px;margin-left: 160px;margin-top: 0px;">
			请先<a href="login/MyLog.jsp">登录</a>
		</h3>
		</div>
	</div>
	<jsp:include page="/nav/footer.jsp"/>
</body>
</html>