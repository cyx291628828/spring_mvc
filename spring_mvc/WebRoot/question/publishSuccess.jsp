<%@page import="com.source.plan.entity.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Question q = (Question) request.getAttribute("requestQuestion");
	String result = (String) request.getAttribute("result");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>发布成功</title>


<style type="text/css">
.question-info {
	margin-left: 350px;
}


.title-info {
	border-radius: 10px;
	margin-left: 50px;
	margin-top: 50px;
	padding-top: 10px;
	width: 900px;
	padding-bottom: 50px;
}

.collections{
	margin-left: 10px;
	height: 50px;
}
.day {
	font-size: 12px;
	color: #999;
}

.left {
	width: 900px;
	float: left;
	overflow: hidden;
}

.content {
	background: #f8f8f8;
	padding: 20px;
	overflow: hidden;
	color: #444;
	margin-bottom: 20px;
}

.content .layer {
	overflow: hidden;
	border-bottom: 1px dashed #e3e0e0;
	margin-bottom: 10px;
}

.left h4 span {
	display: block;
	float: right;
	font-size: 12px;
	padding-top: 8px;
}

.nopadding {
	padding-left: 0;
	padding-right: 0;
}


</style>
</head>

<body>
	<jsp:include page="/nav/_UserHead.jsp" />
	<jsp:include page="/nav/_LeftHead.jsp" />
	<div class="question-info">
		<ul class="breadcrumb" style="margin:80px 0 10px 30px;">
			<li><a href="index.jsp">主页</a>
			</li>
			<li><a href="RewardHall?linknumber=1">悬赏大厅</a>
			</li>
			<li class="active">查看问题</li>
		</ul>
		<div class="title-info">
			<h3 style="margin: 10px 10px;">
				<img src="img/help.png" width="30px;">&nbsp;
				<%=q.getQuestionTheme()%><span class="day"> <%
 	if (q.getQuestionIsFinish() == 0) {
 %> 未完成 <%
 	} else {
 %> 已完成 <%
 	}
 %> </span>
			</h3>
			<div class="ad collections"
				style="padding-top: 10px; padding-bottom: 10px; border-bottom: 1px solid #ededed;">
				<span class="org"></span><span>赏金：<%=q.getQuestionMoney()%>|</font><img
					src="img/money.ico" /> </span> <span>分类：<b><%=q.getQuestionGenre()%></b>|
				</span> <span style="padding-right:0;">发布者：</span><span><%=q.getUser().getUserName()%>|</span>
				<span>发布时间：<%=q.getQuestionStaTime()%>|</span> <span> <%
 	if (q.getQuestionIsFinish() == 0) {
 %> 结束时间：<i><%=q.getQuestionEndTime()%></i> <%
 	} else {
 %> 完成时间：<i><%=q.getQuestionFinTime()%></i> <%
 	}
 %> </span>
			</div>
			<div style="padding-top: 10px;overflow:hidden;">
				<div class="left">
					<h4>内容描述</h4>
					<div class="content">
						<div class="layer"
							style="line-height: 24px; border-bottom: 1px solid transparent;">
							<p><%=q.getQuestionContent()%></p>
						</div>
					</div>



					<a href="RewardHall" style="margin-left: 700px;"
						class="btn btn-info">返回</a>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/nav/footer.jsp"/>
</body>
</html>
