<%@page import="com.source.plan.beans.*"%>
<%@page import="com.source.plan.entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
	int genreType = 0;
	if (request.getParameter("genreType") != null) {
		genreType = Integer.parseInt(request.getParameter("genreType"));
	}
	String genreID = "0";
	if (request.getParameter("genreID") != null && !request.getParameter("genreID").equals("null")) {
		genreID = request.getParameter("genreID");
	}
	String sortRule = "0";
	if (request.getParameter("sortRule") != null) {
		sortRule = request.getParameter("sortRule");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>我的回答</title>

<style type="text/css">
.box-question {
	border: #006600 3px solid;
	float: left;
	width: 280px;
	height: 250px;
	margin: 50px 25px 20px 50px;
	padding: 10px;
	
	background-color:#FAFAFA;
	border-radius: 20px;
	-moz-box-shadow: 5px 5px 10px gray;
	-webkit-box-shadow: 5px 5px 10px gray;
	-o-box-shadow: 5px 5px 10px gray;
	box-shadow: 5px 5px 10px gray;
}

.browser-questions {
	margin-left: 350px;
}

.course-nav-box {
	margin-left: 30px;
}

.clearfix {
	height: 50px;
}

.non-question {
	margin-top: 150px;
	margin-left: 50px;
}
</style>
</head>

<body>
	<jsp:include page="/nav/_UserHead.jsp" />
	<jsp:include page="/nav/_LeftHead.jsp" />
	<div>
		<div class="browser-questions">
			<ul class="breadcrumb" style="margin:80px 0 10px 30px;">
				<li><a href="index.jsp">主页</a>
				</li>
				<li><a href="RewardHall?linknumber=1">悬赏大厅</a>
				</li>
				<li class="active">我已回答</li>
			</ul>

			<div class="course-nav-box col-lg-11">
				<div class="course-nav-hd">

					<span>分类</span>
				</div>
				<div class="course-nav-row ">
					<span class="hd l">方向：</span>
					<div class="bd">
						<ul>
							<li
								class="course-nav-item <%if (genreType == 0) {%>
								on <%}%>"><a
								href="MyAnswered?linknumber=1">全部</a></li>
							<li
								class="course-nav-item
                                <%if (genreType == 1) {%>
								on <%}%>">
								<a href="MyAnswered?genreType=1&linknumber=1"
								data-ct="Atest?genreType=后端开发">后端开发</a>
							</li>
							<li
								class="course-nav-item
                               <%if (genreType == 2) {%>
								on <%}%>">
								<a href="MyAnswered?genreType=2&linknumber=1"
								data-ct="Atest?genreType=前端开发">前端开发</a>
							</li>
							<li
								class="course-nav-item
                                <%if (genreType == 3) {%>
								on <%}%>">
								<a href="MyAnswered?genreType=3&linknumber=1"
								data-ct="Atest?genreType=移动开发">移动开发</a>
							</li>
							<li
								class="course-nav-item
                                <%if (genreType == 4) {%>
								on <%}%>">
								<a href="MyAnswered?genreType=4&linknumber=1"
								data-ct="Atest?genreType=其他">其他</a>
							</li>
						</ul>
					</div>
				</div>
				<div class="course-nav-row">
					<span class="hd l">分类：</span>
					<div class="bd">
						<ul class="">
							<li
								class="course-nav-item <%if (genreID.equals("0")) {%>
								on <%}%>"><a
								href="MyAnswered?genreType=<%=genreType%>&linknumber=1">全部</a>
							</li>
							<%
								for (Genre g : pageInfo.getGenres()) {
							%>
							<li
								class="course-nav-item <%if (genreID.equals(g.getGenreID())) {%>
								on <%}%>"><a
								href="MyAnswered?genreID=<%=g.getGenreID()%>&genreType=<%=genreType%>&linknumber=1"
								data-id="1370" data-ct="be"><%=g.getGenreName()%></a></li>
							<%
								}
							%>
						</ul>
					</div>
				</div>
				<div class="course-nav-row">
					<span class="hd l">排序方式</span>
					<div class="bd">
						<ul class="">
							<%--
								sortRule=1表示当前时间升序
								sortRule=2表示当前时间降序
								sortRule=3表示当前浏览量升序
								sortRule=4表示当前浏览量降序
								sortRule=5表示当前赏金升序
								sortRule=6表示当前赏金降序 --%>
							<li class="course-nav-item "><a
								href="MyAnswered?genreType=<%=genreType%>&genreID=<%=genreID%>&linknumber=1&<%if (sortRule.equals("1")) {%>sortRule=2<%} else {%>sortRule=1<%}%>">时间<span
									<%if (sortRule.equals("1")) {%>
									class="glyphicon glyphicon-arrow-up"
									<%} else if (sortRule.equals("2")) {%>
									class="glyphicon glyphicon-arrow-down" <%}%>></span> </a>
							</li>

							<li class="course-nav-item"><a
								href="MyAnswered?genreType=<%=genreType%>&genreID=<%=genreID%>&linknumber=1&<%if (sortRule.equals("3")) {%>sortRule=4<%} else {%>sortRule=3<%}%>">浏览量<span
									<%if (sortRule.equals("3")) {%>
									class="glyphicon glyphicon-arrow-up"
									<%} else if (sortRule.equals("4")) {%>
									class="glyphicon glyphicon-arrow-down" <%}%>></span> </a>
							</li>

							<li class="course-nav-item"><a
								href="MyAnswered?genreType=<%=genreType%>&genreID=<%=genreID%>&linknumber=1&<%if (sortRule.equals("5")) {%>sortRule=6<%} else {%>sortRule=5<%}%>">悬赏金额<span
									<%if (sortRule.equals("5")) {%>
									class="glyphicon glyphicon-arrow-up"
									<%} else if (sortRule.equals("6")) {%>
									class="glyphicon glyphicon-arrow-down" <%}%>></span> </a>
							</li>
							

						</ul>
					</div>
				</div>
			</div>
			<div>
				<%
					int boxQuestionNum = 0;
					for (Question q : pageInfo.getQuestions()) {
						boxQuestionNum++;
				%>

				<div class="box-question">

					<div style="height: 50px;margin: 3px;font-size: 14px;">
						<img src="img/help.png"><a
							href="isFinish?QuestionID=<%=q.getQuestionID()%>&linknumber=1"><%=q.getQuestionTheme()%></a>
					</div>
					<hr style="margin: 0px;">

					<p>
						悬赏金额:<%=q.getQuestionMoney()%><img
							style="width: 15px; height: 15px" src="img/money.ico">
					</p>
					<%
						if (q.getQuestionPageView() != 0) {
					%>
					<div
						style="float:right; width: 75px; margin-top: -20px; background-color: #6699FF">
						本问题被浏览了<font color="red"><%=q.getQuestionPageView()%></font>次
					</div>
					<%
						}
					%>


					<p>
						悬赏者：<%=q.getQuestionUsername()%></p>
					<p>
						问题分类：<b><%=q.getQuestionGenre()%></b>
					</p>
					<p>
						结束日期：<i><%=q.getQuestionEndTime().split(" ")[0]%></i>
					</p>
					<%
						if (q.getQuestionAnwserQuantity() != 0) {//问题的回复量
					%>
					<p class="help-block">
						已有<font color="red"><%=q.getQuestionAnwserQuantity()%></font>人提交回答
					</p>
					<%
						} else {
					%>
					<p class="help-block">
						该问题还没有回答，立刻<a
							href="ScanQuestion?QuestionID=<%=q.getQuestionID()%>">帮忙</a>！
					</p>
					<%
						}
					%>
				</div>

				<%
					}
				%>
			</div>
			<%
				if (boxQuestionNum != 0) {
			%>
			<div style="clear:both;margin-left: 50px;">
				<div style="width: 100px;float: left;margin-top: 20px;">
					<form action="RewardHall?linknumber=1&genreType=<%=genreType%>&sortRule=<%=sortRule%>&genreID=<%=genreID%>"
						method="post">
						<div class="input-group">
							<input type="text" class="form-control" name="pageNum" value="1">
							<span class="input-group-btn">
								<button class="btn btn-default" type="submit">跳到</button> </span>
						</div>
					</form>
				</div>
				<ul class="pagination">
					<li <%if (pageInfo.getPageNum() == 1) {%> class="disabled"
						title="已是第一页" <%}%>><a
						href="MyAnswered?pageNum=<%=pageInfo.getPageNum() == 1 ? 1 : pageInfo
						.getPageNum() - 1%>&genreType=<%=genreType%>&sortRule=<%=sortRule%>&genreID=<%=genreID%>"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
					<%
						int i = 0;
							for (i = 0; i < pageInfo.getPageSum(); i++) {
					%>
					<li <%if (i + 1 == pageInfo.getPageNum()) {%> class="active" <%}%>><a
						href="MyAnswered?linknumber=1&pageNum=<%=i + 1%>&ScanType=myScan&genreType=<%=genreType%>&sortRule=<%=sortRule%>&genreID=<%=genreID%>"><%=i + 1%></a>
						<%
							}
						%>
					
					<li <%if (pageInfo.getPageSum() == pageInfo.getPageNum()) {%>
						class="disabled" title="已是最后一页" <%}%>><a
						href="MyAnswered?linknumber=1&pageNum=<%=(pageInfo.getPageNum() + 1) > pageInfo.getPageSum() ? pageInfo
						.getPageNum() : (pageInfo.getPageNum() + 1)%>&genreType=<%=genreType%>&sortRule=<%=sortRule%>&genreID=<%=genreID%>"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span> </a>
					</li>
					<li><a>共<%=pageInfo.getPageSum()%>页</a></li>
				</ul>

			</div>
			<%
				} else {
			%>
			<div class="non-question">
				<img src="img/sorry-ans.png">
			</div>
			<%
				}
			%>
		</div>
	</div>
	<jsp:include page="/nav/footer.jsp"/>
</body>
</html>
