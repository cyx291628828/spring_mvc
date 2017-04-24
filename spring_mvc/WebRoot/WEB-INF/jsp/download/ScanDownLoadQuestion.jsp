<%@page import="javax.swing.ImageIcon"%>
<%@page import="com.source.plan.beans.*"%>
<%@page import="com.source.plan.entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
	PageInfo pageInfo = (PageInfo) request.getAttribute("request");
	String message = request.getParameter("message");
	message = message==null?"error":message;
	Question q  = pageInfo.getQuestion();
	Answer a  = pageInfo.getAnswer();
	User admin = null;
	admin =  (User)session.getAttribute("sessionUser");
	int answershow = 0;
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
.pl_img{
	width: 40px;height: 40px;
	margin-top:10px;
	margin-left:20px;
	float: left;
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
	border: 3px solid #208D4E;
	z-index: 100;
	float: left;
	margin: 0 auto;
	border-radius: 5px;
	width: 200px;
	height: 80px;
	opacity: 0.8;
	background-color: #27AE60;
	position: absolute;
	top: 400px;
	left: 500px;
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
.pay{
	cursor: pointer;
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
		<h2>提交成功！</h2>
	</div>
	<%
		}
	%>
	<div class="question-info">
		<ul class="breadcrumb" style="margin:80px 0 10px 30px;">
			<li><a href="index.jsp">主页</a>
			</li>
			<li><a href="Download?linknumber=2">下载资源</a>
			</li>
			<li class="active">查看资源</li>
		</ul>
		<div class="title-info">
			<h3>
				<a style="cursor: pointer;" onClick="javascript :history.back(-1);"><img src="img/return.png" width="30px;" title="返回上一页"></a>&nbsp;<%=q.getQuestionTheme()%><span
					class="day"> <%
 	if (q.getQuestionisFinish() == 0) {
 %> 未完成 <%
 	} else {
 %> 已完成 <%
 	}
 %> </span>
			</h3>
			<div class="collections"
				style="padding-top: 10px; padding-bottom: 10px; border-bottom: 1px solid #ededed;">
				<span class="org"></span><span>下载金币：<%=q.getQuestionDownMoney()%></font><img
					src="img/money.ico" /> </span> <span>分类：<b><%=q.getQuestionGenre()%></b>
				</span> <span style="padding-right:0;">发布者：<font color="#27AE60"><%=q.getQuestionUsername()%></font>
				</span> <span>发布时间：<%=q.getQuestionStaTime().split(" ")[0]%></span> <span>
					<%
						if (q.getQuestionisFinish() == 0) {
					%> 结束时间：<i><%=q.getQuestionEndTime().split(" ")[0]%></i> <%
 	} else {
 %> 完成时间：<i><%=q.getQuestionFinTime().split(" ")[0]%></i> <%
 	}
 %> </span><div class="heart" id="like1" rel="like"></div>
		 <input style="display: none;" name="questionID" value="<%=q.getQuestionID()%>"/>
			</div>
			<div style="padding-top: 10px;overflow:hidden;">
				<div class="left">
					<h4>问题描述</h4>
					<div class="content">
						<div class="layer"
							style="line-height: 24px; border-bottom: 1px solid transparent;">
							<p><%=q.getQuestionContent()%></p>
						</div>
						<span style="margin-left: 50px;">附件浏览：</span><span> 
												<%if (q.getQuestionFile().equals("0")) {%> 
												没有附件  <%} else {%>
												<a href="<%=basePath %>answers/<%=a.getAnswerFile()%>">点我</a>
													<%
														}
													%> </span>
					</div>
					<div class1="other">
						<h4>
							题主选择的最佳答案<span class="green"></span>
						</h4>


						<div class="answer-body">
							<div style="margin-left: 30px;margin-top: 50px;">
								<h3>
									<img src="user/userimg/<%=a.getAnswerUserImage()%>"
										width="50px" style="float: left; margin:5px;"><%=a.getAnswerUsername()%><span
										class="day"> <%=a.getAnswerIsSelect().equals("0") ? "未采纳" : "已采纳"%>
									</span>
								</h3>
								<div class="collections"
									style="padding-top: 10px; padding-bottom: 10px; border-bottom: 1px solid #ededed;">
									<span class="org"></span><span>回答时间：<%=a.getAnswerTime()%></span>
								</div>
								<div style="padding-top: 10px;overflow:hidden;">
									<div class="left">
										<h4>回答描述</h4>
										<div class="content">
											<%// || q.getQuestionID().equals(admin.getUserID()) || a.getAnswerID().equals(admin.getUserID())
												if (admin != null) {
													for (Pinglun pl : pageInfo.getPinglins()) {
														if ((pl.getDownUserID().equals(admin.getUserId()) 
																)) {
											%>
											
											<%
												answershow = 1;
															break;
														}
													}
													if(q.getQuestionUserID().equals(admin.getUserId()) || a.getAnswerUserID().equals(admin.getUserId())){
														answershow = 1;
													}
												if (answershow == 0) {
											%>
											<script type="text/javascript">
												$(function(){
													$('.pay').click(function() {
															$('#myModal').modal('show');
														})
												})
											</script>
											<div class="layer"
												style="line-height: 24px; border-bottom: 1px solid transparent;">
												<%
													if (message.equals("lose")) {
												%>
												<span style="color: red;">您的金币不足！</span>
												<%
													}
												%>
												<p style="font-size: 20px;">
													此内容需要<a class="pay">支付</a>相应的金币之后才能查看哦！
												</p>
											</div>
											<!-- 弹出框 -->
												<div class="modal fade " id="myModal"
													tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
													<div class="modal-dialog" style="width: 250px;" role="document">
														<div class="modal-content">
															<div class="modal-header">
																<button type="button" class="close" data-dismiss="modal"
																	aria-label="Close">
																	<span aria-hidden="true">&times;</span>
																</button>
						
																<h4 class="modal-title" id="myModalLabel">确定支付？</h4>
															</div>
															<div class="modal-body">
																	<a class="btn btn-info" href="PayMoney?QuestionID=<%=q.getQuestionID()%>" type="text"
																		>确定</a>
																	<button type="button" class="btn btn-default"
																		data-dismiss="modal">取消</button>
															</div>
						
														</div>
													</div>
												</div> 
											<%
												}else{%>
													<div class="content">
													<%
													if (message.equals("success")) {
												%>
												<span>支付成功,您可以查看</span>
												<%
													}
												%>
												<div class="layer"
													style="line-height: 24px; border-bottom: 1px solid transparent;">
													<p><%=a.getAnswerContent()%></p>
												</div>
												<span style="margin-left: 50px;">下载：</span><span> 
												<%if (a.getAnswerFile().equals("0")) {%> 
												没有附件 ，详情请看文字解答 <%} else {%>
												<a href="<%=basePath %>answers/<%=a.getAnswerFile()%>">点我</a>
													<%
														}
													%> </span>
												

											</div><%
												}
												} else {
											%>
											<div class="layer"
												style="line-height: 24px; border-bottom: 1px solid transparent;">
												<p style="font-size: 20px;">登陆之后才可以才查看哦！</p>
											</div>
											<%
												}
											%>
										</div>
									</div>

									<h4 style="margin-top: 50px;">全部评论：</h4>
									<div style=" background: #f8f8f8;padding: 20px;">
										<%
											for (Pinglun pl : pageInfo.getPinglins()) {
												if (pl.getIsPing() == 1) {

													ImageIcon imgiconPhoto1 = null;
													int height = 0;
													int width = 0;
													String imagepath = request
															.getSession()
															.getServletContext()
															.getRealPath("user/userimg/" + pl.getDownUserImage());
													//out.print(imagepath);
													try {
														imgiconPhoto1 = new ImageIcon(imagepath);
														width = imgiconPhoto1.getIconWidth();
														height = imgiconPhoto1.getIconHeight();
														// out.print(width + "  ,  " + height);
													} catch (Exception ex) {
														System.out.println(ex.getMessage());
													}
										%>
										<div>
										<div class="pl_img">
											<img alt="图片加载中......"
												src="user/userimg/<%=pl.getDownUserImage()%>"
												<%if (height > width) {%>
												style="height:100% ; margin-left: <%=(1 - width * 1.0 / height) * 20%>px"
												<%} else {%>
												style="width:100% ; margin-top: <%=(1-height*1.0/width)*20 %>px"
												<%} %>>
										</div>
										<div
											style="width:90%; margin: 0 auto; float: left; margin-left: 10px;">
											<h5><%=pl.getDownUserName()%>:<%=pl.getPingConcent() %></h5>
											<h5 style="float: right;">
												评论时间：<%=pl.getPingTime() %></h5>
											<br>
										</div>
										<div style="clear: both;">
										<hr style="margin-top: 30px;width: 100%;padding-bottom: -30px;">
										</div>
										</div>
										<%}}%>
									</div>
								</div>
							</div>
						</div>


					</div>
				</div>
				
			</div>
			<%if(answershow == 1 ) { %>
										<div>
										<a style="color: #000;text-decoration: none;cursor: pointer;"><h4>我也要发表评论:</h4></a>
										<div style="margin-top: 30px;margin-left: 80px;margin-bottom: 100px;">
										 <form action="AddPing?QuestionID=<%=q.getQuestionID() %>" method="post">
										 	<textarea name="pingconcent" class="form-control"
												style="width: 680px;maxlength=500;resize: none;float: left;"
												placeholder="输入想要留下的话"></textarea>
												<button type="sumbit" class="btn-submit btn btn-success"
												style="margin-left: 25px;height: 68px;margin-top: 0px;">确认评论</button>
										 </form>
										 </div>
										</div>
										<%} %>
		</div>
	</div>
	<jsp:include page="/nav/footer.jsp"/>
</body>
</html>
