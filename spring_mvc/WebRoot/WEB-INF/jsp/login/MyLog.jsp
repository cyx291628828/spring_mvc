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

<title>登陆</title>
<jsp:include page="/src/bootStrap.jsp" flush="true" />
<style type="text/css">
.mbody {
	margin-top: 100px;
	padding-top: 5px;
	padding-bottom: 30px;
}

.mycontainer {
	width: 300px;
	position: absolute;
	left: 70%;
	top: 150px;
}

.vcode {
	width: 150px !important;
	margin-right: 20px;
}

.form-control {
	width: 300px;
	height: 30px;
}

.form-div {
	width: 300px;
}

body {
	background-color: #24ad62;
	font-family: '微软雅黑', '黑体', 'times new roman';
}

.btn {
	margin: 10 30px;
}

.vcode-div {
	height: 30px;
}

.vcode-img {
	cursor: pointer;
	position: relative;
	bottom: 50px;
	left: 200px;
}

div input {
	margin: 20px 0;
}

.top-tip {
	margin: 0 auto;
	text-align: center;
}

div a:visited {
	color: yellow;
}

div a:hover {
	color: green;
	background-color: white;
}

.btn-submit {
	width: 250px;
}

.logLOGO {
	position: absolute;
	top: 0px;
	left: 0px;
}

.logCenter {
	width: 40%;
	height: 80%;
	position: absolute;
	left: 15%;
	bottom: 10%;
}

.flower {
	width: 100px;
	height: 100px;
	position: absolute;
	bottom: 20px;
	left: 20px;
}

.layout-center-login {
	width: 40%;
	height: 80%;
	position: absolute;
	left: 15%;
	bottom: 10%
}

.slideBox {
	overflow: hidden;
	position: relative;
	height: 800px;
}

.slideBox * {
	overflow: hidden;
}

.slideBox ul {
	padding: 0px;
	margin: 0px;
}

.slideBox .bd {
	position: relative;
	height: 100%;
	z-index: 0;
}

.slideBox .bd li {
	width: 100%;
	height: 800px;
	zoom: 1;
	vertical-align: middle;
	padding: 0px;
	margin: 0px;
	list-style: none;
}

.slideBox .bd img {
	display: block;
	margin: 0px;
	width: 100%;
}


</style>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/nav/min.css">
<script type="text/javascript"
	src="<%=basePath%>src/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript">
	$(function() {
		$(".vcode-img").click(function() {
			$(".vcode-img").attr("src", "vcode.jpg?s=" + Math.random());
		});
		$(".btn-submit").click(function() {
			var id = $("input[name='userID']").val();
			var pw = $("input[name='password']").val();
			var vcode = $("input[name='vcode']").val();
			if (id == "")
				alert("请输入用户名");
			else if (pw == "")
				alert("请输入密码");
			else if (vcode == "") {
				alert("请输入验证码");
				$("input[name='password']").val("");
			} else
				$("form").attr("action", "login/sumbit");
		});
	});
	$(function() {
		$(".slideBox").slide({
			mainCell : ".bd ul",
			autoPlay : true,
			effect : 'fold',
			delayTime : 3000,
			interTime : 6000
		});
	});
</script>
</head>

<body>
	<a href="index.jsp"><img class="logLOGO" src="<%=basePath %>img/logLOGO.png">
	</a>
	<div class="layout-center-login">
		<div id="slideBox" class="slideBox">
			<div class="bd">
				<ul>
					<li><img src="<%=basePath %>img/logCenter2.png" style="z-index: -1"></li>
					<li><img src="<%=basePath %>img/logCenter1.png" style="z-index: -1"></li>
					<li><img src="<%=basePath %>img/logCenter4.png" style="z-index: -1"></li>
					<li><img src="<%=basePath %>img/logCenter3.png" style="z-index: -1"></li>

				</ul>
			</div>
		</div>
	</div>
	<div class="mbody">
		<div class="mycontainer">
			<h1 style="margin-bottom: 30px">登陆</h1>
			<form method="post">
				<div class="form-div">
					<div>
						<input type="text" name="userID" class="form-control"
							placeholder="请输入用户名"
							value="<%=request.getParameter("userID") == null ? "" : request
					.getParameter("userID")%>">
					</div>
					<div>
						<input type="password" name="password" class="form-control"
							placeholder="请输入密码">
					</div>
					<div class="vcode-div">
						<input type="text" name="vcode" class="form-control vcode"
							placeholder="请输入验证码"> <img class="vcode-img"
							src="vcode.jpg" />
					</div>
				</div>
				<div style="height:20px">
					<%
						try {
							String result = request.getParameter("result");
							if (result.equals("vcode")) {
					%>
					<font color="red">验证码错误</font>
					<%
						}
							if (result.equals("password")) {
					%>
					<font color="red">用户名或密码错误</font>
					<%
						}
							if (result.equals("reg")) {
					%>
					<font color="red">注册成功！请登录</font>
					<%
						}
						} catch (Exception e) {
						}
					%>
				</div>
				<div>
					<button type="sumbit" class="btn-submit btn btn-success">登陆</button>
				</div>
				没有账号？点此<a href="login/register" class="a-reg">注册</a>
			</form>
		</div>
	</div>
	<img src="<%=basePath %>img/flower.png" class="flower">
</body>
</html>