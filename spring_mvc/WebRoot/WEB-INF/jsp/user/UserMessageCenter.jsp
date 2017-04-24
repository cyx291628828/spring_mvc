<%@page import="com.source.plan.beans.*"%>
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

<title>消息中心</title>
<style type="text/css">
.public-body {
	width: 800px;
	margin-left: 400px;
	margin-top: 60px;
	margin-left: 400px;
	margin-bottom: 50px;
}

.panel-default {
	height: 300px;
	width: 800px;
	margin-top: 50px !important;
	line-height: 100px;
	overflow: auto;
	overflow-x: hidden;
}

tr {
	height: 50px;
}
</style>
<%
	String result = request.getParameter("result");
	result = result == null ? "" : result;
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
%>
</head>

<body>
	<jsp:include page="/nav/_UserHead.jsp"></jsp:include>
	<jsp:include page="/nav/_UserInfoHead.jsp"></jsp:include>
	<div class="public-body">
		<ul class="breadcrumb">
			<li><a href="index">主页</a></li>
			<li class="active">消息中心</li>
		</ul>
		<div class="form-group panel  panel-default">
			<div class="panel-heading">
				<h3 style="">未读消息</h3>
			</div>
			<div style="margin-top: 0px;">
				<table>
					<col width="50px" />
					<col width="350px" />
					<col width="200px" />
					<%
						int i = 0, j = 0;
						for (Notice notice : pageInfo.getNotices()) {
							if (notice.getNoticeIsCheck() == 0 ) {
								i++;
					%>
					<tr>
						<td><span class="glyphicon glyphicon-envelope" style="margin-left: 10px;"></span>
						</td>
						<td><%=notice.getNoticeContent()%></td>
						<td><%=notice.getNoticeDate()%></td>
						<td><a
							href="NoticeScan?QuestionID=<%=notice.getNoticeProblemID()%>&NoticeID=<%=notice.getNoticeID()%>"
							class="btn btn-info  ">点击查看</a>
						</td>
					</tr>

					<%
						}
						}
					%>
				</table>
				<%
					if (i == 0) {
				%><h2 style="margin-top: 20px;margin-left: 50px;">无未读消息!</h2>
				<%
					}
				%>
			</div>
		</div>
		<div class="form-group panel panel-default">
			<div class="panel-heading">
				<h3 style="">已读消息</h3>
			</div>
			<div style="margin-top: 0px;">
				<table>
					<col width="50px" />
					<col width="350px" />
					<col width="200px" />
					<%
						for (Notice notice : pageInfo.getNotices()) {
						if (notice.getNoticeIsCheck()==1) {
							j++;
					%>
					<tr>
						<td><span class="glyphicon glyphicon-envelope" style="margin-left: 10px;"></span>
						</td>
						<td><%=notice.getNoticeContent()%></td>
						<td><%=notice.getNoticeDate()%></td>
						<td><a
							href="NoticeScan?QuestionID=<%=notice.getNoticeProblemID()%>&NoticeID=<%=notice.getNoticeID()%>"
							class="btn btn-info  ">点击查看</a>
							<a
						href="NoticeDelete?NoticeID=<%=notice.getNoticeID()%>"
						class="btn btn-danger">点击删除</a>
						</td>
					</tr>

					<%
						}
						}
					%>
				</table>
				<%
					if (j == 0) {
				%><h2 style="margin-top: 20px;margin-left: 50px;">无未读消息</h2>
				<%
					}
				%>
			</div>
		</div>
	</div>
<jsp:include page="/nav/footer.jsp"/>
</body>
</html>
