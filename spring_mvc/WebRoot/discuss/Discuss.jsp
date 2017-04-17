<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.source.plan.entity.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>

<%
	List<Discuss> list_discuss = new ArrayList<Discuss>();
	Integer pageNum = 1;
	Integer pageSum = 0;
	try {
		list_discuss = (List<Discuss>) request
		.getAttribute("list_discuss");
		pageSum = (Integer) request.getAttribute("pageSum");
		pageNum = (Integer) request.getAttribute("pageNum");
	} catch (Exception e) {
		e.printStackTrace();
	}
	Integer pag = pageSum % 20 == 0 ? pageSum / 20 : pageSum / 20 + 1;
	Integer firstpageNum = 1;
	Integer lastpageNum = pag;
	if (pageNum - 5 > 0 && pageNum - 5 < pag - 9) {
		firstpageNum = pageNum - 5;
		lastpageNum = pageNum + 5;
	} else if (pageNum - 5 <= 0) {
		firstpageNum = 1;
		lastpageNum = pag > firstpageNum + 10 ? firstpageNum + 10 : pag;
	} else if (pageNum - 5 >= pag - 9) {
		firstpageNum = pag - 10 < 1 ? 1 : pag - 10;
		lastpageNum = pag;
	}
	User user = new User();
	user = (User) session.getAttribute("sessionUser");
	user = user == null ? new User() : user;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>讨论区</title>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>FirstBid/First.css">
<link href="top.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.public-content div {
	margin: 10px;
}

.public-content button,.link-return {
	margin-top: 20px;
	margin-left: 70px;
}

.btn-div {
	clear: left;
}

.panel-default {
	margin-top: 60px !important;
}

.replynumber {
	width: 60px;
	height: 30px;
	background: url('img/discuss.png');
	-moz-background-size: 100% 100%;
	background-size: 100% 100%;
	padding-left: 20px;
}
</style>
<script type="text/javascript"
	src="<%=basePath%>/src/bootstrap-3.3.5/js/bootstrap-datetimepicker.min.js"></script>

</head>

<body>
	<jsp:include page="/nav/_UserHead.jsp" />
	<script type="text/javascript">
		$(function() {
			$(".newNote").blur(function() {
				if ($(".newNote").val().length < 15) {
					$(".text-ok").text("字数长度不得小于15字");
					$(".btn-submit").attr("disabled", "disabled");
				} else {
					$(".text-ok").text("");
					$(".btn-submit").removeAttr("disabled");
				}
			});
		});
	</script>
	<script type="text/javascript">
		$(function() {

			// 页面浮动面板
			$("#floatPanel > .ctrolPanel > a.arrow").eq(0).click(function() {
				$("html,body").animate({
					scrollTop : 0
				}, 800);
				return false;
			});
			$("#floatPanel > .ctrolPanel > a.arrow").eq(1).click(function() {
				$("html,body").animate({
					scrollTop : $(document).height()
				}, 800);
				return false;
			});
			$("#floatPanel > .ctrolPanel > a.contact").click(function() {
				if (
	<%=user.getUserId()%>
		!= null) {
					$("#create").show();
					$("html,body").animate({
						scrollTop : 0
					}, 800);
					$(".newNote").focus();
				} else {
					window.location.href = "./login/noLog.jsp";
				}
				return false;
			});

		});
	</script>
	<div class=""
		style="width: 1000px;min-height:239px; margin: 0 auto; margin-top: 50px;">
		<%
			//if (user.getUserID() != null) {
		%>
		<div id="create" style="display: none;">
			<form
				action="AddReply?UserID=<%=user.getUserId()%>&IsReply=0&IsTheme=1&linknumber=<%=request.getParameter("linknumber")%>"
				method="post">
				<textarea class="form-control newNote"
					style="width: 80%;float: left;margin-left: 30px; resize:none;font-size: 25px;"
					maxlength=30 rows="1" name="Content" placeholder="请输入建贴主题"></textarea>
				<button type="sumbit" disabled="disabled"
					class="btn-submit btn btn-success"
					style="margin-left: 25px;height: 68px;width: 80px;">新建一贴</button>
				<font class="text-ok" style="margin-left: 85%;margin-top: 10px;"
					color="red"></font>
			</form>
		</div>
		<%
			//}
		%>
		<%
			if (firstpageNum < lastpageNum) {
		%>
		<nav>
		<ul class="pagination">
			<li><a
				href="Reply?linknumber=3&pageNum=<%=pageNum == 1 ? pag : pageNum - 1%>"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span> </a>
			</li>
			<%
				for (int i = firstpageNum; i <= lastpageNum; i++) {
			%>
			<li <%if (i == pageNum) {%> class="active" <%}%>><a
				href="Reply?linknumber=3&pageNum=<%=i%>"><%=i%></a> <%
 	}
 %>
			
			<li><a
				href="Reply?linknumber=3&pageNum=<%=pageNum == pag ? 1 : pageNum + 1%>"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span> </a></li>
		</ul>
		<ul class="pagination">
			<li><a>共<%=pag%>页</a></li>
		</ul>
		</nav>
		<%
			}
		%>
		<table class="table table-hover">
			<col width="10%">
			<col width="60%">
			<col width="20%">
			<%
			int s = 0;
				for (Discuss theme : list_discuss) {
				 s ++;
			%>
			<tr>
				<td><div class="replynumber"><%=theme.getDiscussesForThemeDiscussId().size()%></div>
				</td>
				<td><h4>
						<a href="Reply?linknumber=3&DiscussID=<%=theme.getDiscussId()%>"><%=theme.getDiscussContent()%></a>
					</h4></td>
				<td>
					<table>
						<tr>
							<td width="50px"><font title="发帖者" color=#27AE60
								class="glyphicon glyphicon-user"></font></td>
							<td><font style="color: gray;"><%=theme.getUser().getUserName()%></font>
							</td>

						</tr>
						<tr>
							<td><font title="最后回帖者" color="#F5AA4A"
								class="glyphicon glyphicon-user"></font></td>
							<td style="color: gray;" title="最后回复者"><%=theme.getUser().getUserName()%></td><!-- 这里是最后回复的人名字 -->

						</tr>
					</table></td>
				<td><font style="color: gray;"><%=theme.getLastTime()%></font>
				</td>
			</tr>
			<%
				}if(s == 0){
			%>
			<div style="font-size: 25px;margin-top: 30px;">暂无帖子，要不新建一帖？</div>
			<% }%>
		</table>
		<%
			if (firstpageNum < lastpageNum) {
		%>
		<nav>
		<ul class="pagination">
			<li><a
				href="Reply?linknumber=3&pageNum=<%=pageNum == 1 ? pag : pageNum - 1%>"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span> </a>
			</li>
			<%
				for (int i = firstpageNum; i <= lastpageNum; i++) {
			%>
			<li <%if (i == pageNum) {%> class="active" <%}%>><a
				href="Reply?linknumber=3&pageNum=<%=i%>"><%=i%></a> <%
 	}
 %>
			
			<li><a
				href="Reply?linknumber=3&pageNum=<%=pageNum == pag ? 1 : pageNum + 1%>"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span> </a></li>
		</ul>
		<ul class="pagination">
			<li><a>共<%=pag%>页</a></li>
		</ul>
		</nav>
		<%
			}
		%>

		<div id="floatPanel">

			<div class="ctrolPanel">
				<a class="arrow" href="#"><span>顶部</span> </a> <a class="contact"
					target="_blank" id="contact_create"><span>建贴</span> </a> <a
					class="arrow" href="#"><span>底部</span> </a>
			</div>
		</div>
	</div>
	<jsp:include page="/nav/footer.jsp" />
</body>

</html>
