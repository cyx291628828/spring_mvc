<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.source.plan.entity.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String find = request.getParameter("find");
	find = find==null?"":find;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'newHead.jsp' starting page</title>

<jsp:include page="/src/bootStrap.jsp" flush="true" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>nav/min.css">
<link rel="icon" href="<%=basePath %>img/Favicon.ico" type="image/x-icon">
<style type="text/css">
	.search-div{
		margin: 0 auto;
	}
</style>
<%
	String username = null;
	String RegorQuit = null;
	try{
	username = ((User)session.getAttribute("sessionUser")).getUserName();
	RegorQuit = "注销";
	}catch(Exception e){
		username="登录";
		RegorQuit = "注册";
	}
 %>
<script type="text/javascript">
	$(function(){
		var user = $(".user-a");
		var quit = $(".quit-a");
		user.click(function(){
			if(user.text() == "登录")
				user.attr("href","login/MyLogin");
			else
				user.attr("href","user/UserInfoCenter.jsp?linknumber=-1");
		});
		quit.click(function(){
			if(quit.text() == "注销")
				quit.attr("href","login/logoff");
			else
				quit.attr("href","login/register");
		});
	});
</script>
</head>

<body>
	<div class="site-notice">
		<a href="">欢迎来到源计划源码问答网站</a>
	</div>
	<header class="site-header jumbotron">
	<div class="site-nav">
		<a href="#" class="user-a"><%= username %></a>
		<span>/</span> <a href="#about" class="quit-a"><%= RegorQuit %></a>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<h1>源计划源码问答网站</h1>
				<p>程序不是年轻的专利，但是，他属于年轻</p>
				<p class="num">成功完成项目已达
				<script type="text/javascript">
						$(function() {
							$.post('successnum', function(result, status) {
								$(".num").append(result);
							});
						});
					</script>
					</p>
					</div>
				</form>
			</div>
		</div>
	</div>
	</header>
</body>
</html>
