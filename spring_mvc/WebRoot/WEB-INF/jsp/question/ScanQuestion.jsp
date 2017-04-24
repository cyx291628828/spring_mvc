<%@page import="java.text.DecimalFormat"%>
<%@page import="com.source.plan.beans.*"%>
<%@page import="com.source.plan.entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
	PageInfo pageInfo = (PageInfo) request.getAttribute("request");
	Question q  = pageInfo.getQuestion();
	User admin = (User)session.getAttribute("sessionUser");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>问题详情</title>

<link rel="stylesheet" type="text/css" href="src/css/collect.css" />
<style type="text/css">
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

.users {
	margin: 10px 0;
	padding: 10px 0;
	overflow: hidden;
	font-size: 14px;
}

.question-info {
	margin-left: 350px;
}

.title-info {
	border-radius: 10px;
	margin-left: 50px;
	padding-top: 10px;
	width: 900px;
	padding-bottom: 50px;
}

.anwser {
	width: 900px;
	border-radius: 10px;
	padding: 5px 10px;
	background-color: #f8f8f8;
	margin-left: 50px;
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

.layer p {
	text-indent: 2em;
}

.collections {
	margin-left: 10px;
	height: 50px;
}

.collections span {
	margin-left: 5px;
}

.favor {
	
	cursor: pointer;
	float: right;
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
			$(".heart").click(function() {
			//0代表已收藏 要取消收藏
			//1代表文本为收藏   想要收藏
				var data = 0;
				var D=$(this).attr("rel");
				if (D == "like"){
					data = 1;
				}
					$.post('collect',{ type:data,QuestionID:$("input[name='questionID']").val() }, function(result, status) {
									if(result=="sucess"){
										//do something
										if (data == 1) {
											$(".heart").addClass("heartAnimation").attr("rel","unlike");
										} else {
											$(".heart").removeClass("heartAnimation").attr("rel","like");
										}
									}else{
									//do somthing
									}
					});
			});
			$.post('collect',{ type:3,QuestionID:$("input[name='questionID']").val() }, function(result, status) {
									if(result=="yes"){
										//do something
										$(".heart").addClass("heartAnimation").attr("rel","unlike");
										}else if(result == "no"){
										//do somthing.
										
										$(".heart").removeClass("heartAnimation").attr("rel","like");
									}else{
										$(".favor").text("您还未");
										$(".favor").append('<a href="login/MyLog.jsp">登录</a>');
										
									}
					});
		});
	</script>
	<%
		String type = request.getParameter("type");
		type = type == null ? "0" : type;
		if (type.equals("giveSuccess")) {
	%>
	<div class="float-text">
		<img src="img/subSucc.jpg">
	</div>
	<%
		}
	%>
	<div class="question-info">
		<ul class="breadcrumb" style="margin:80px 0 10px 30px;">
			<li><a href="index.jsp">主页</a>
			</li>
			<li><a href="RewardHall?linknumber=1">悬赏大厅</a>
			</li>
			<li class="active">查看问题</li>
		</ul>
		<div class="title-info">
			<h3>
				<img src="img/help.png" width="30px;">&nbsp;<%=q.getQuestionTheme()%><span
					class="day"> <%
 	if (q.getQuestionisFinish() == 0) {
 %> 未完成 <%
 	} else {
 %> 已完成 <%
 	}
 %> </span>	
 			<div class="heart" id="like1" rel="like"></div>
				<input style="display: none;" name="questionID" value="<%=q.getQuestionID()%>"/>
			</h3>
			<div class="ad collections"
				style="padding-top: 10px; padding-bottom: 10px; border-bottom: 1px solid #ededed;">
				<span class="org"></span><span>赏金：<%=q.getQuestionMoney()%></font><img
					src="img/money.ico" /> </span> <span>分类：<b><%=q.getQuestionGenre()%></b>
				</span> <span style="padding-right:0;">发布者：<font color="#27AE60"><%=q.getQuestionUsername()%></font>
				</span> <span>发布时间：<%=q.getQuestionStaTime().split(" ")[0]%></span> <span>
					<%
						if (q.getQuestionisFinish() == 0) {
					%> 结束时间：<i><%=q.getQuestionEndTime().split(" ")[0]%></i> <%
 	} else {
 %> 完成时间：<i><%=q.getQuestionFinTime().split(" ")[0]%></i> <%
 	}
 %> </span> <span>附件:<%
 	if (!q.getQuestionFile().equals("0")) {
 %><a
					href="<%=basePath %>files/<%=q.getQuestionFile()%>">点我</a>
					<%
						} else {
					%>无附件<%
						}
					%>
				</span>
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
					<div class1="other">
						<h4>
							有谁回答过<span class="green"></span>
						</h4>
						<%
							int amountAnswer = 0;
							for (Answer a : pageInfo.getAnswers()) {
								amountAnswer++;
						%>
						<div class="users">
							<a
								<%if (admin != null
						&& (admin.getUserId().equals(a.getAnswerUserID()) || admin
								.getUserId().equals(q.getQuestionUserID()))) {%>
								href="ScanAnswer?QuestionID=<%=q.getQuestionID() %>&AnswerID=<%=a.getAnswerID()%>&isFinish=<%=q.getQuestionisFinish()%>&QuestionUserID=<%=q.getQuestionUserID()%>"
								<%}%>>
								<div class="col-md-12 nopadding">
									<div class="col-md-2"
										style="padding-left:0; padding-right: 0; overflow: hidden;">
										<i class="state_luoxuan"></i><%=a.getAnswerUsername()%>
									</div>
									<div class="col-md-2 nopadding nowrap">
										<img style="margin-left: 30px;" width="23px" src="img/lv.png">
										<%
											staticVar sv = new staticVar();
												int level = sv
														.getLevel(Integer.parseInt(a.getAnswerUserLevel()));
												String path1 = "img/level" + level / 10 + ".png";
												String path2 = "img/level" + level % 10 + ".png";
												if (level / 10 != 0) {
										%>
										<img width="25px" src=<%=path1%>>
										<%
											}
										%>
										<img width="25px" src=<%=path2%>>
									</div>

									<div class="col-md-3 nopadding">
										回答时间：<span class="org nowrap"><%=a.getAnswerTime().split(" ")[0]%></span>
									</div>
									<div class="col-md-2">
										被采纳数：<span class="org"><%=a.getAnswerSuccessNum()%></span>个
									</div>
									<div class="col-md-3 nopadding">
										被采纳率：<span class="org nowrap"><%=new DecimalFormat("0.0").format(a.getAnswerSuccessNum() * 100.0 / a.getAnswerNum())%>%</span>
									</div>
								</div> </a>
						</div>

						<%
							}
							if (amountAnswer == 0) {
						%>


						<h3 style="margin-left: 200px;margin-top: 30px;">暂无人回答!</h3>
						<%
							}
						%>
					</div>
				</div>
			</div>
		</div>

		<jsp:include page="_answerQuestion.jsp" />

	</div>
	<jsp:include page="/nav/footer.jsp" />
</body>
</html>
