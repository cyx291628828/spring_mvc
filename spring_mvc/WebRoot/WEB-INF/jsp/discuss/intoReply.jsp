<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="javax.swing.ImageIcon"%>
<%@page import="com.source.plan.entity.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	Discuss discuss = new Discuss();
	Integer pageNum = 1;
	Integer pageSum = 0;
	try {
		discuss = (Discuss)request.getAttribute("discuss");
		pageSum = (Integer)request.getAttribute("pageSum");
		pageNum = (Integer)request.getAttribute("pageNum");
	} catch (Exception e) {
		e.printStackTrace();
	}
	Integer pag = pageSum%10==0?pageSum/10:pageSum/10+1;
	Integer firstpageNum = 1;
	Integer lastpageNum = pag;
	if(pageNum-5>0 && pageNum-5 < pag - 9){
		firstpageNum = pageNum-5;
		lastpageNum = pageNum + 5;
	}else if(pageNum-5 <= 0){
		firstpageNum = 1;
		lastpageNum = pag>firstpageNum + 10?firstpageNum + 10:pag;
	}else if(pageNum-5 >= pag - 9){
		firstpageNum = pag - 10<1?1:pag - 10;
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
<title>留言板</title>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>FirstBid/First.css">
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
.bid-info {
	background-color: #fafafa;
	margin-top: 50px;
	margin-bottom: 70px;
}

.message-height {
	background-color: #fafafa;
	margin-top: 30px;
	margin-bottom: 30px;
	border: 3px solid #fafafa;
	height: auto;
}

.message-height-1 {
	height: auto !important;
	height: 150px;
	min-height: 150px;
}

textarea {
	resize: none;
}
</style>
<script type="text/javascript"
	src="<%=basePath%>/src\bootstrap-3.3.5\js\bootstrap-datetimepicker.min.js"></script>
</head>

<body>
	<jsp:include page="/nav/_UserHead.jsp" />
	<div class="" style="width: 900px; margin: 0 auto; margin-top: 50px;">
	<div class="theme">
		<font size="5px">主题：<%=discuss.getDiscussContent() %></font>
		<a href="javascript:void(0)" onclick="document.getElementById('bottom').scrollIntoView();"
			 class="btn btn-default btn-lg active" role="button" 
			 style="margin-top: -5px;float: right;">回复</a><br><!-- 锚点链接 -->
		<font size="2px"><%=discuss.getUser().getUserName() %></font><font size="2px" style="margin-left: 20px;"><%=discuss.getDiscussTime() %></font>
	</div>
	<% if(firstpageNum < lastpageNum){%>
	<nav>
		<ul class="pagination">
			<li><a
				href="Reply?linknumber=3&DiscussID=<%=discuss.getDiscussId() %>&pageNum=<%=pageNum == 1?pag:pageNum-1 %>"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span> </a>
			</li>
			<%
				for (int i = firstpageNum; i <= lastpageNum; i++) {
			%>
			<li <%if (i == pageNum) {%> class="active" <%}%>><a
				href="Reply?linknumber=3&DiscussID=<%=discuss.getDiscussId() %>&pageNum=<%=i%>"><%=i%></a> <%
 	}
 %>
			
			<li><a
				href="Reply?linknumber=3&DiscussID=<%=discuss.getDiscussId() %>&pageNum=<%=pageNum == pag?1:pageNum+1%>"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span> </a></li>
		</ul><ul class="pagination"><li><a>共<%=pag %>页</a></li></ul>
		</nav><%} %>
		<%
			int floor = 1;
			Object[] dis = discuss.getDiscussesForThemeDiscussId().toArray();
			for(Object themes : dis){
			if(((Discuss)themes).getIsTheme() == 0){
				ImageIcon imgiconPhoto1 = null;  
  				int height = 0;  
  				int width = 0;  
  				String imagepath = request.getSession().getServletContext().getRealPath("user/userimg/"+((Discuss)themes).getUser().getUserImage());
  			//out.print(imagepath);
  				try{
      				imgiconPhoto1 = new ImageIcon(imagepath);  
      				width = imgiconPhoto1.getIconWidth();  
      				height = imgiconPhoto1.getIconHeight();
     			// out.print(width + "  ,  " + height);
  				}catch(Exception ex){
      				System.out.println(ex.getMessage());
  				}
		%>
		<div class="message-height" style="border: 1px solid gray;border-radius:5px;">
			<%-- <div style="float: left;margin-left: 2px;margin-top: 2px;width: 22%;"
				class="message-height-1">
				<h3 style="width: 100%;text-align: center;"><%=theme.getUserName()%></h3>
				<!-- 留言用户名 -->
				<!-- clear:left -->
			</div> --%>
			<div class="dis_img" style="float: left;width: 130px;height: 130px; margin-left: 10px;margin-top: 20px;">
				<img alt="图片加载中......" src="user/userimg/<%=((Discuss)themes).getUser().getUserImage()%>"
				<% if(height > width) {%> style="height:100% ; margin-left: <%=(1-width*1.0/height)*65 %>px" 
					<%}else{ %> style="width:100% ; margin-top: <%=(1-height*1.0/width)*65 %>px" <%} %>>
			</div>
			<div
				style="float: left; font-size:17px; border: 1px dashed gray;margin-left: 10px;margin-top: 20px; padding-left:10px;padding-right:10px; width: 80%"
				class="message-height-1">
				<%if(discuss.getUser().getUserId().equals(((Discuss)themes).getUser().getUserId())) {%>
				<font color="#27AE60"><%=((Discuss)themes).getUser().getUserName()%></font>
				<%}else{ %>
				<font><%=((Discuss)themes).getUser().getUserName()%></font>
				<%} %>
				：<%=((Discuss)themes).getDiscussContent().replace("\n", "<br>")%>
				<br><font size="2px" color="gray"><%=((Discuss)themes).getDiscussTime()%></font>

				<%
					int index = 0;
					Object[] replys = ((Discuss)themes).getDiscussesForReplyDiscussId().toArray();
						for(Object re_discuss : replys){
				%>
				<br><br>
				<%if(discuss.getUser().getUserId().equals(((Discuss)re_discuss).getUser().getUserId())) {%>
				<font color="#27AE60"><%=((Discuss)re_discuss).getUser().getUserName()%></font>
				<%}else{ %>
				<font><%=((Discuss)re_discuss).getUser().getUserName()%></font>
				<%} %>
				回复内容：<%=((Discuss)re_discuss).getDiscussContent().replace("\n", "<br>")%>
				<br><font size="2px" color="gray"><%=discuss.getDiscussTime()%></font>
				<%
					index++;
					}
				%>
				<%
					if (user.getUserId() != null) {//user.getUserID() != null
				%>
				<br> <br>
				<form
					action="AddReply?UserID=<%=user.getUserId()%>&IsReply=1&ReplyDiscussID=<%=((Discuss)themes).getDiscussId()%>&linknumber=<%=request.getParameter("linknumber")%>&theme=<%=discuss.getDiscussId() %>"
					method="post">

					<textarea class="form-control" style="width: 80%;float: left;"
						maxlength=500 name="Content" placeholder="输入回复内容"></textarea>
					<button type="sumbit" class="btn-submit btn btn-success"
						style="margin-left: 25px;height: 68px;">确认回复</button>
				</form>
				<%
					}
				%>

			</div>
			<div style="clear: left;padding-top: 10px;">
				<!-- 留言用户名所属公司 -->
				<h5
					style="text-align: right; width: 97% ;margin-top: 1px; margin-left: 2px">
					<%=((Discuss)themes).getDiscussTime()%>&nbsp;&nbsp;&nbsp;<%=(pageNum-1)*10+floor++%>#
				</h5>
			</div>
		</div>
		<%}} %>
		<% if(firstpageNum < lastpageNum){%>
		<nav>
		<ul class="pagination">
			<li><a
				href="Reply?linknumber=3&DiscussID=<%=discuss.getDiscussId() %>&pageNum=<%=pageNum == 1?pag:pageNum-1 %>"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span> </a>
			</li>
			<%
				for (int i = firstpageNum; i <= lastpageNum; i++) {
			%>
			<li <%if (i == pageNum) {%> class="active" <%}%>><a
				href="Reply?linknumber=3&DiscussID=<%=discuss.getDiscussId() %>&pageNum=<%=i%>"><%=i%></a> <%
 	}
 %>
			
			<li><a
				href="Reply?linknumber=3&DiscussID=<%=discuss.getDiscussId() %>&pageNum=<%=pageNum == pag?1:pageNum+1%>"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span> </a></li>
		</ul><ul class="pagination"><li><a>共<%=pag %>页</a></li></ul>
		</nav><%} %>
		<div class="bid-info message-height-1" id="bottom"
			style="border: 1px solid gray;border-radius:5px;"><!-- 设置锚点 id="bottom" -->
			<%
					if (user.getUserId() == null) {
				%>
			<h2 style="width: 100%;text-align: center;">
				<a href="<%=basePath%>/login/MyLog.jsp">登陆</a>后方可留言或回复
			</h2>
			<%
					} else {
				%>
			<form
				action="AddReply?UserID=<%=user.getUserId()%>&IsReply=0&ReplyDiscussID=<%=discuss.getDiscussId()%>&linknumber=<%=request.getParameter("linknumber")%>&theme=<%=discuss.getDiscussId() %>"
				method="post">
				<textarea class="form-control"
					style="width: 80%;float: left;margin-top: 50px;margin-left: 30px;"
					maxlength=500 name="Content" placeholder="请输入留言内容"></textarea>
				<button type="sumbit" class="btn-submit btn btn-success"
					style="margin-left: 25px;height: 68px;margin-top: 50px;">确认留言</button>
			</form>
			<%
					}
				%>
		</div>
	</div>
	<jsp:include page="/nav/footer.jsp"/>
</body>
</html>
