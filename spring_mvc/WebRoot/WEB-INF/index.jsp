<%@page import="javax.swing.ImageIcon"%>
<%@page import="com.source.plan.entity.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	User user = (User) session.getAttribute("sessionUser");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<%
	String imgpath = user == null ? "peopleHead2.png" : user
			.getUserImage();
	ImageIcon imgiconPhoto1 = null;
	int height = 0;
	int width = 0;
	String imagepath = request.getSession().getServletContext()
			.getRealPath("user/userimg/" + imgpath);
	try {
		imgiconPhoto1 = new ImageIcon(imagepath);
		width = imgiconPhoto1.getIconWidth();
		height = imgiconPhoto1.getIconHeight();
	} catch (Exception ex) {
		System.out.println(ex.getMessage());
	}
%>
<title>源计划源码问答网站</title>
<style type="text/css">
.wid380,.brief{
	width: 330px;
	overflow: hidden;
}
.brief{
	margin-left: 0px;
	margin-top: 60px;
}
.f_l {
	float: left !important;
	margin-left: 500px;
	margin-top: 43px;
}

.f_r {
	
	margin-top: 43px;
}



.title_thit {
	background-color: #f4f4f4;
	background-image: linear-gradient(to bottom, #fff, #f5f5f5);
	height: 38px;
	border: 1px solid #dfdfdf;
	line-height: 40px;
	padding: 0 20px;
}

.title_thit a {
	color: #000;
	cursor: pointer;
}

.title_thit em {
	float: left;
	font-size: 14px;
	font-weight: bold;
	font-style: normal;
}

.jyan_li a {
	color: #888;
}

.title_thit a:hover {
	text-decoration: none;
}

.jyan_li a:hover {
	text-decoration: none;
}

a.more {
	float: right;
	color: #999;
}

.jyan_box {
	background: #fff;
	border: 1px solid #dfdfdf;
	border-top: none;
}

.jyan_box ul {
	padding: 10px 20px;
}

.jyan_box li {
	padding-left: 10px;
	line-height: 35px;
}

body {
	background-color: #666666;
}

.index-body {
	width: 68%;
	margin-left: 32%;
	margin-top: 80px;
}

.inner-div {
	width: 900px;
	height: 300px;
	margin: 40px;
	border: 1px solid gray;
}

#banner {
	position: relative;
	width: 810px;
	height: 410px;
	border: 1px solid #27AE60;
	margin: 0 auto;
}

#banner_list img {
	border: 0px;
}

#banner_bg {
	position: absolute;
	bottom: 0;
	background-color: #000;
	height: 30px;
	filter: Alpha(Opacity =       30);
	opacity: 0.3;
	z-index: 1000;
	cursor: pointer;
	width: 478px;
}

#banner_info {
	position: absolute;
	bottom: 0;
	left: 10px;
	height: 25px;
	color: #fff;
	z-index: 1001;
	cursor: pointer
}

#banner_text {
	position: absolute;
	width: 120px;
	z-index: 1002;
	right: 3px;
	bottom: 3px;
}

#banner ul {
	position: absolute;
	list-style-type: none;
	filter: Alpha(Opacity =       80);
	opacity: 0.8;
	border: 1px solid #fff;
	z-index: 1002;
	margin: 0;
	padding: 0;
	bottom: 3px;
	right: 5px;
}

#banner ul li {
	padding: 0px 8px;
	float: left;
	display: block;
	color: #FFF;
	border: #e5eaff 1px solid;
	background: #6f4f67;
	cursor: pointer
}

#banner ul li.on {
	background: #900
}

#banner_list p {
	position: absolute;
}

.notice-box {
	background-color: #fafafa;
	border-radius: 10px;
	width: 310px;
	height: 400px;
}

.notices {
	height: 330px;
	overflow: auto;
	overflow-x: hidden;
}

.left-index {
	position: absolute;
	top: 450px;
	left: 8%;
	padding-bottom:100px;
	margin-bottom: 50px;
}

.quick-links {
	width: 900px;
	margin-left: 32%;
}

.quick-link {
	width: 250px;
	height: 150px;
	background-color: #27AE60;
	margin-top: 50px;
	margin-left: 100px;
	float: left;
	border: 5px solid #fafafa;
	padding: 10px;
	-moz-box-shadow: 5px 5px 10px gray;
	-webkit-box-shadow: 5px 5px 10px gray;
	-o-box-shadow: 5px 5px 10px gray;
	box-shadow: 5px 5px 10px gray;
	background: -webkit-radial-gradient(circle, #fafafa, #27AE60);
	background: -moz-radial-gradient(circle, #fafafa, #27AE60);
	background: -o-radial-gradient(circle, #fafafa, #27AE60);
	background: radial-gradient(circle, #fafafa, #27AE60);
}

h1 img {
	width: 25px;
	height: 25px;
}

.cd-timeline-img {
	width: 25px;
	height: 25px;
}

.cd-timeline-block a:hover {
	text-decoration: none;
}

.cd-timeline-block a {
	color: black;
}

.info {
	margin-left:0px;
	margin-top:60px;
	height: 200px;
	width:300px;
	background-color: #fafafa;
	-webkit-box-shadow: 0 0 5px #000;
	-moz-box-shadow: 0 0 5px #000;
	box-shadow: 0 0 5px #000;
	background: -webkit-radial-gradient(center, circle, #fafafa, #27AE60);
	background: -moz-radial-gradient(center, circle, #fafafa, #27AE60);
	background: -ms-radial-gradient(center, circle, #fafafa, #27AE60);
	background: -o-radial-gradient(center, circle, #fafafa, #27AE60);
	background: radial-gradient(center, circle, #fafafa, #27AE60);
}
.center{
	margin-top:50px;
	margin-left: 35%;
	width: 65%;
}
.lv {
	width: 20px;
	height: 20px;
	margin-right: -5px;
}

.web-tip {
	
}

.number2 {
	float: right;
}

.question {
	margin-left: auto;
	margin-right: auto;
	display: block;
	color: #999;
}

.question .question-list {
	margin-top: 10px;
	padding-right: 160px;
}

.question .question-list .mod-item {
	padding-top: 35px;
	margin-bottom: 20px;
	border-top: 1px solid #e5e5e5;
	margin-top: -1px;
}

.question .question-list .vm-question {
	position: relative;
	padding-left: 65px;
	font-size: 18px;
	line-height: 2;
	color: #333;
}

.question .question-list .vm-question:after {
	content: "";
	position: absolute;
	left: 10px;
	top: 3px;
	width: 36px;
	height: 26px;
	background: url(img/icons_q.png) no-repeat left top;
}

.question .question-list .vm-answer {
	position: relative;
	margin: 0px;
	padding: 15px 0 20px 65px;
	line-height: 1.8;
}

.question .question-list .vm-answer:after {
	content: "";
	position: absolute;
	left: 10px;
	top: 12px;
	width: 36px;
	height: 26px;
	background: url(img/icons_a.png) no-repeat left top;
}
</style>
	
</head>

<body>
	<jsp:include page="<%=basePath %>/nav/_UserHead.jsp" />
	
	<script type="text/javascript">
		//图片轮播
		var t = n = 0, count;
		$(document)
				.ready(
						function() {
							count = $("#banner_list p").length;
							$("#banner_list p:not(:first-child)").hide();
							$("#banner_info").html(
									$("#banner_list p:first-child").find("img")
											.attr('alt'));
							$("#banner_info").click(
									function() {
										window.open($(
												"#banner_list p:first-child")
												.attr('href'), "_blank");
									});
							$("#banner li")
									.click(
											function() {
												var i = $(this).text() - 1;
												n = i;
												if (i >= count)
													return;
												$("#banner_info").html(
														$("#banner_list p").eq(
																i).find("img")
																.attr('alt'));
												$("#banner_info")
														.unbind()
														.click(
																function() {
																	window
																			.open(
																					$(
																							"#banner_list p")
																							.eq(
																									i)
																							.attr(
																									'href'),
																					"_blank");
																});
												$("#banner_list p").filter(
														":visible")
														.fadeOut(500).parent()
														.children().eq(i)
														.fadeIn(1000);
												document
														.getElementById("banner").style.background = "";
												$(this).toggleClass("on");
												$(this).siblings().removeAttr(
														"class");
											});
							t = setInterval("showAuto()", 4000);
							$("#banner").hover(function() {
								clearInterval(t);
							}, function() {
								t = setInterval("showAuto()", 4000);
							});
						});
		function showAuto() {
			n = n >= (count - 1) ? 0 : ++n;
			$("#banner li").eq(n).trigger('click');
		};
	</script>
	

	<div style="height: 1000px;">
		<div class="index-body">
			<!-- 图片 -->
			<div id="banner">

				<!-- 标题 -->
				<div id="banner_info"></div>
				<ul>
					<li class="on">1</li>
					<li>2</li>
					<li>3</li>
				</ul>
				<div id="banner_list">
					<p>
						<img src="img/headimg/index1.png" title="标题1" />
					</p>
					<p>
						<img src="img/headimg/index2.png" title="标题2" />
					</p>
					<p>
						<img src="img/headimg/index3.png" title="标题3" />
					</p>

				</div>
			</div>
		</div>

		<div class="left-index">
			<div class="notice-box">
				<div style="position: relative;left:250px;top:15px;width: 100px;">
					<a href="RewardHall?linknumber=1">more <img src="img/more.png"
						width="15px"> </a>
				</div>
				<h3 style="margin-left: 50px;margin-top: -10px;">最新发布</h3>
				<div class="notices">
					<section id="cd-timeline" class="cd-container"> <script
						type="text/javascript">
						$(function() {
							$.post('indexNotice', function(result, status) {
								$(".cd-container").append(result);
							});
						});
					</script> </section>
				</div>
			</div>
			
			<div class="info">
				<h3 style="margin-left: 50px;margin-top: 20px;">个人信息</h3>
				<%
					if (user == null) {
				%>
				<h3 style="margin-top: 40px;margin-left: 50px;">
					糟糕！忘记<a href="login/MyLog.jsp">登陆</a>了
				</h3>
				<%
					} else {
				%>

				<div style="margin-top: 20px">
					<div
						style="width: 100px ; height: 100px;margin-left:10px;margin-top:10px; border:3px solid #a9c9e2;background:#e8f5fe">
						<a href="user/UserInfoCenter.jsp"><img class=""
							src="user/userimg/<%=user.getUserImage()%>"
							<%if (height > width) {%>
							style="height:100% ; margin-left: <%=(1 - width * 1.0 / height) * 50%>px"
							<%} else {%>
							style="width:100% ; margin-top: <%=(1 - height * 1.0 / width) * 50%>px"
							<%}%>> </a>
					</div>
					<div class="info-inner-box"
						style="position: relative; top:-90px; left:120px">
						<div>

							<a style="font-size: 15px" href="user/UserInfoCenter.jsp"><%=user.getUserName()%></a>
						</div>
						<div style="margin: 10px;">
							<%
								staticVar sv = new staticVar();
									int level = sv.getLevel(user.getUserLevel());
									String path1 = "img/level" + level / 10 + ".png";
									String path2 = "img/level" + level % 10 + ".png";
							%>


							<img src='img/lv.png' class="lv"">
							<%
								if (level / 10 != 0) {
							%>
							<img width='25px' src="<%=path1%>" class="lv">
							<%
								}
							%>
							<img width='25px' src="<%=path2%>" class="lv"> <br>
						</div>
						<div style="margin-top: 10px;">
							<label>大洋: </label> <label><img src="img/money.ico"><%=user.getUserFakeMoney()%>
							</label>
						</div>
						<div style="position: relative;top:5px;left:-100px;width: 280px;">
							座右铭:<%=user.getUserInterest()%>
						</div>
					</div>
				</div>

				<%
					}
				%>
			</div>

			<div class="wid380 f_r" >
				<div class="title_thit">
					<em><a>下载排行榜</a> </em><a class="more" href="Download?linknumber=2">更多&nbsp;&gt;</a>
				</div>
				<div class="jyan_box clearfix">
					<ul class="clearfixr" style="padding: 5px 0px 0px 0px;">
						<table class="table table-hover">
							<col width="8%">
							<col width="70%">
							<tbody class="addtext">
								<script type="text/javascript">
									$(function() {
										$.post('TopTen', function(result,
												status) {
											$(".addtext").append(result);
										});
									});
								</script>
							</tbody>
						</table>
					</ul>
				</div>
			</div>
			
			<div class="brief">
				<div class="title_thit">
					<em><a target="_blank">网站简介</a> </em>
				</div>
				<div class="jyan_box clearfix">
					<ul class="clearfix">
						<div>
							<img alt="" src="img/LOGO.png">&nbsp;<span>源计划源码问答网站建站于2016年04月25日，是一个致力于项目源码问答的交易平台。任何人都能在这里找到自己想要的项目源码，网站聘请100多名长期驻站的高级程序员，力求帮助解决所有人的需求。为广大有学习需求的朋友创建一个学习交流的平台。</span>
						</div>
					</ul>
				</div>
			</div>
			
		</div>
		
		<div class="center">
			


			

	<div class="row-fluid" style="margin-top: -60px;">
		<div class="span8 offset2" style="margin-top:140px;">
			<div class="content">
				<div class="question" id="question">
					<div class="question-list">
						<h3>热门回答</h3>
						<dl class="mod-item">
							<dt class="vm-question">我没有虚拟币能够发布任务吗？</dt>
							<dd class="vm-answer">当然可以，回答人数可能会少，您可以先帮助他人增加虚拟币再来发布问题</dd>
						</dl>
						<dl class="mod-item">
							<dt class="vm-question">我等级太低了，怎么办？</dt>
							<dd class="vm-answer">你可以积极回答别人问题，或者发布问题。这两种方式都可以提升等级</dd>
						</dl>
						<dl class="mod-item">
							<dt class="vm-question">如何在本网站彻底的发布一个问题？</dt>
							<dd class="vm-answer">本网站操作流程极其简单，步骤如下：1、选择发布按钮，进行详细的问题描述填写；2、等待其他用户提交答案；3、对于答案进行筛选，选出合适答案；4、选出答案后，可以设置是否能被其他人下载，其他人若下载便可获得虚拟币。</dd>
						</dl>
						<dl class="mod-item">
							<dt class="vm-question">如何在本网站彻底的回答一个问题？</dt>
							<dd class="vm-answer">回答问题只需四步：1、在悬赏大厅中找到自己擅长的问题；2、准备问题答案，进行提交；3、等待发布者选择答案；4、如果发布者选择了您的答案，此问题便完成，双方进行虚拟币的互换并同时增长经验。</dd>
						</dl>
						<dl class="mod-item">
							<dt class="vm-question">我怎么样获得更多的虚拟金币？能充值吗？</dt>
							<dd class="vm-answer">
								本网站主要追求学习交流，不设有任何利益方式，只是问了网站能有更好的资源和发展才设有虚拟币，且虚拟币只能通过回答问题才能得到。同时为了鼓励注册成为网站用户，我们会给每一位新注册的用户100虚拟金币。</dd>
						</dl>
						<dl class="mod-item">
							<dt class="vm-question">我之前下载过的一个问题源码，现在下载还会扣我的金币吗？</dt>
							<dd class="vm-answer">
								我们设置的是一次下载，永久可以二次下载且不再收取第二次的金币费用。并且只有下载之后的用户才能有权限去评论和对问题进行点赞，评论可以多次评论，但是点赞只能点赞一次。所以我们建议您谨慎操作哦，这样能够给以后想要下载该问题的用户提供更多的参考。</dd>
						</dl>
						<dl class="mod-item">
							<dt class="vm-question">别人下载我已经完成的问题时，所花费我金币全部都会给我吗？网站会抽取一部分吗？</dt>
							<dd class="vm-answer">
								下载花费的金币会平分给提问者和回答问题的人。网站不会抽取其中的任何费用。当你所提出的问题得到解答之后并且你也已经设置下载金币后，别人下载时所扣的金币有一半会存进提问者的账号，另一半会存进回答人的账号。所以要想长期能获得金币提问和回答问题都是一种不错的选择。</dd>
						</dl>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>

<jsp:include page="/nav/footer.jsp"/>
</body>
</html>
