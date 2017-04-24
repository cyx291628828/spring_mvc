<%@page import="com.source.plan.entity.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	List<Question> pageInfo = (List<Question>)request.getAttribute("questions");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>我的收藏</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href="<%=basePath%>/src/css/MyQuestion.css"
	type="text/css" />
<style type="text/css">
.myQuestions-box {
	height: 500px;
	margin-left: 350px;
}

.myQuestion {
	margin: 5px;
	border: #27AE60 1px solid;
}

.list-unstyled {
	padding-left: 0;
	list-style: none;
}

ol,ul {
	margin-top: 0;
	margin-bottom: 10px;
}

* {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

.list li {
	overflow: hidden;
	margin-bottom: 10px;
	position: relative;
	border: 0;
	border-bottom: 1px dashed #e8e8e8;
}

.list .icon_new,.list .icon_recommond {
	background: url(../images/home/icon_12.png);
	width: 29px;
	height: 29px;
	position: absolute;
	left: 0;
	top: 0;
}

.list .left_2 {
	float: left;
	width: 16%;
	padding: 10px;
}

.list .left_8 {
	border-right: 1px solid #f5f3f3;
	border-left: 1px solid #f5f3f3;
	float: left;
	width: 68%;
	padding: 10px;
}

list .left_2 a {
	display: block;
	color: #ff8706;
	text-decoration: none;
	padding-top: 30px;
	text-align: center;
}

.list .left_2 span {
	display: block;
	color: #2eb135;
	text-align: center;
}

.list li h4 {
	color: #333;
	margin: 0;
	line-height: 36px;
	font-size: 16px;
}

.list .left_8 span {
	display: block;
	float: left;
	padding-right: 25px;
	color: #999;
	line-height: 28px;
}

.list .left_2 span {
	display: block;
	color: #2eb135;
	text-align: center;
}

.list .left_8 .green {
	color: #2eb135;
}

li:hover {
	background-color: #c0c0c0;
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
</style>

</head>

<body>
	<jsp:include page="/nav/_UserHead.jsp" />
	<jsp:include page="/nav/_LeftHead.jsp" />
	<script type="text/javascript">
		$(function() {
			$('.set').click(function() {
				$('#y' + this.id).modal('show');
			})
			$('.delete').click(function() {
				$('#myM' + this.id).modal('show');
			})
		});

		$(function() {
			//$(".text").fadeOut(2000);
			$(".float-text").animate({
				"top" : "250px",
				"opacity" : "0"
			}, 2000);

		});
	</script>
	<%
		String type = request.getParameter("result");
		type = type == null ? "0" : type;
		int i = 0;
		if (type.equals("success")) {
	%>
	<div class="float-text">
		<img src="img/subSucc.jpg">
	</div>
	<%
		}
	%>
	<div class="myQuestions-box">
		<ul class="breadcrumb" style="margin:80px 0 10px 30px;">
			<li><a href="index.jsp">主页</a></li>
			<li><a href="RewardHall?linknumber=1">悬赏大厅</a></li>
			<li class="active">我的收藏</li>
		</ul>
		<div style="width: 900px;margin-left: 100px;margin-top: 35px;">
			<c:set var="time" value="1" />

			<% 
				String temp = "1";
			for(Question q : pageInfo) {%>
			<% if(!temp.equals(q.getQuestionStaTime())){ %>
			<% if(!temp.equals("1")){ %>
				</div>
			<%} 
		temp = q.getCollectTime();%>
		<div class="accordion">
			<h4 style="width: 160px"><%=q.getCollectTime() %></h4><!-- 收藏的时间 -->
			<img
				style=" float: right;width: 50px;margin-top: -30px;margin-bottom: 5px;margin-right: 10px;"
				alt="+" src="img/add.png">
		</div>
		<div class="accordion-desc">
			<%} %>

			<div class="list">
				<ul class="list-unstyled">
					<li><i class="icon_new"></i>
						<div class="left_2">
							<span ><%if(q.getQuestionIsFinish() == -1){ %>已过期<%} %>
							<%if(q.getQuestionIsFinish() == 0){ %>解答中<%} %>
							<%if(q.getQuestionIsFinish() == 1){ %>已解答<%} %> </span>
						</div> <a href="isFinish?QuestionID=<%=q.getQuestionId() %>&linknumber=1"><div
								class="left_8">
								<h4>

									<em class="green"><%=q.getQuestionTheme() %></em>
								</h4>

								<span>赏金:<em class="green"><%=q.getQuestionMoney() %> <img
										src="img/money.ico"> </em> </span> <span>访问量 <em><%=q.getQuestionPageView() %>
										</em> </span><span>发布日期 <em><%=q.getQuestionStaTime() %></em> </span> <span>截止日期
									<em><%=q.getQuestionEndTime() %></em> </span>
								<div style="margin-top: 28px;">
									<span class="jineng-label">分类<em><%=q.getQuestionGenre() %>
									</em> </span><span>提交量：<em><%=q.getQuestionAnwserQuantity() %></em> </span> <!-- 问题的提交量 -->
								</div>
							</div>
					</a>
						<div class="left_2" style="padding-top: 30px;">
							<span>
						
							 <!-- 弹出框 -->
						<div class="modal fade " id="myModal<%=q.getQuestionId() %>"
							tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
							<div class="modal-dialog" style="width: 250px;" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>

										<h4 class="modal-title" id="myModalLabel">确定取消收藏？</h4>
									</div>
									<div class="modal-body">
											<a class="btn btn-info" href="deleteCollect?QuestionID=<%=q.getQuestionId() %>" type="text"
												>确定</a>
											<button type="button" class="btn btn-default"
												data-dismiss="modal">取消</button>
									</div>

								</div>
							</div>
						</div> 
							<p id="odal<%=q.getQuestionId() %>" class="delete" style="cursor: pointer;">取消收藏</p>
						</div></li>
				</ul>

			</div>
			<%} %>
			<%if(temp.equals("1")){ %>
			<div style="margin-top: 100px;font-size: 20px;">您还未收藏，赶紧去逛逛！ <a href="RewardHall?linknumber=1">GO</a></div>
			<%} %>
		</div>
	</div>
	<script type="text/javascript" src="<%=basePath%>/src/js/myQuestion.js"></script>
	<jsp:include page="/nav/footer.jsp"/>
</body>
</html>
