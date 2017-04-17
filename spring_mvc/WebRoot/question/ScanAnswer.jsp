<%@page import="com.source.plan.entity.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//PageInfo pageInfo = (PageInfo) request.getAttribute("request");
	//Question q  = pageInfo.getQuestion();
	User user = (User) session.getAttribute("sessionUser");
	Answer answer = (Answer) request.getAttribute("requestAnswer");
	String QuestionUserID = request.getParameter("QuestionUserID");
	String QuestionID = request.getParameter("QuestionID");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>回答详情</title>


<style type="text/css">
.answer {
	background-color: #E9F6FF;
	border-radius: 10px;
	margin-left: 50px;
	margin-top: 50px;
	padding-top: 10px;
	width: 900px;
	padding-bottom: 50px;
}

.answer-body {
	margin-left: 350px;
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

.float-text {
	border-radius: 5px;
	width: 200px;
	height: 80px;
	opacity: 0.8;
	position: absolute;
	top: 60%;
	left: 45%;
}
.layer p{
	text-indent: 2em;
}
</style>
</head>

<body>
	<jsp:include page="/nav/_UserHead.jsp" />
	<jsp:include page="/nav/_LeftHead.jsp" />
	<script type="text/javascript">
		$(function() {
			//$(".text").fadeOut(2000);
			$(".float-text").animate({
				"top" : "250px",
				"opacity" : "0"
			}, 2000);

		});
	</script>
	<%
		String type = request.getParameter("type");
		type = type == null ? "0" : type;
		if (type.equals("confirmSuccess")) {
	%>
	<div class="float-text">
		<img src="img/subSucc.jpg">

	</div>
	<%
		}
	%>
	<div class="answer-body">
		<ul class="breadcrumb" style="margin:80px 0 10px 30px;">
			<li><a href="index.jsp">主页</a></li>
			<li><a href="RewardHall?linknumber=1">悬赏大厅</a></li>
			<li class="active">查看回答</li>
		</ul>
		<div style="margin-left: 30px;margin-top: 50px;">
			<h3>
				<img src="user/userimg/<%=answer.getUser().getUserImage()%>" width="50px"
					style="float: left; margin:5px;"><%=answer.getUser().getUserName()%><span
					class="day"> <%=answer.getAnswerIsSelect().equals("0")  ? "未采纳" : "已采纳"%>
				</span>
			</h3>
			<div class="ad collections"
				style="padding-top: 10px; padding-bottom: 10px; border-bottom: 1px solid #ededed;">
				<span class="org"></span><span>回答时间：<%=answer.getAnswerTime()%></span>
				<span style="margin-left: 50px;">附件：</span><span> <%
					 	if (answer.getAnswerFile().equals("0")) {
					 %> 没有附件 ，详情请看文字解答 <%
					 	} else {
					 %>
					<a
					href="<%=basePath %>answers/<%=answer.getAnswerFile()%>">点我</a>
					<%
						}
					%> </span><span><%if (type.equals("confirmSuccess")) { %><font color="red">采纳成功，默认设置可下载，下载金额为10<img src="img/money.ico">，有其他设置，请前往“我的发布”进行设置</font><%} %></span>
			</div>
			<div style="padding-top: 10px;overflow:hidden;">
				<div class="left">
					<h4>内容描述</h4>
					<div class="content">
						<div class="layer"
							style="line-height: 24px; border-bottom: 1px solid transparent;">
							<p><%=answer.getAnswerContent()%></p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%if(request.getParameter("isFinish").equals("0")&&user!=null&user.getUserId().equals(QuestionUserID)){ %>
		<a class="btn btn-info"
				href="confirmAdopt?AnswerID=<%=answer.getAnswerId()%>&QuestionID=<%=answer.getQuestion().getQuestionId()%>&GiveUserID=<%=user.getUserId()%>&GetUserID=<%=answer.getUser().getUserId()%>"
				style="margin-bottom:50px; position:relative ;top:50px;left:900px;" token>采纳</a>
				<%}else{ %>
				<a class="btn btn-info"
				style="margin-bottom:50px; position:relative ;top:50px;left:900px;" href="ScanQuestion?QuestionID=<%=QuestionID%>&linknumber=1">返回</a>
				<%} %>
	</div>
	<jsp:include page="/nav/footer.jsp"/>
</body>
</html>
