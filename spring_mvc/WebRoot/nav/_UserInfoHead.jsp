<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<style type="text/css">
	.myBody{
		float:left;
		margin-top:80px;
		margin-left:100px;
		width:200px;
		border: 1px solid gray;
		border-radius:5px;
	}
	.btnDiv{
	background-color: #208d4e;
	padding: 8px 15px;
	
	}
</style>

<body>
	<div class="myBody">
		<ul class="nav nav-pills nav-stacked">
			<div class="btnDiv">个人中心</div>
			<li role="presentation"><a href="user/UserInfoCenter.jsp">个人信息</a>
			</li>
			<li role="presentation"><a href="user/AmendPass.jsp">修改密码</a>
			</li>
			<li role="presentation"><a href="UserMessageCenter">消息中心</a>
			</li>
		</ul>
	</div>

