<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.source.plan.entity.*"%>
<%@page import="javax.swing.ImageIcon"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String s= request.getParameter("linknumber");
	s = s == null?"-1":s;
	User user = null;
	try{
	user = ((User)session.getAttribute("sessionUser"));
	}catch(Exception e){
		user=null;
		out.println("meiyou");
	}
	String imgpath = user==null?"peopleHead2.png":user.getUserImage();
%>
<%
  ImageIcon imgiconPhoto1 = null;  
  int height = 0;  
  int width = 0;  
  String imagepath = request.getSession().getServletContext().getRealPath("user/userimg/"+imgpath);
  try{
      imgiconPhoto1 = new ImageIcon(imagepath);  
      width = imgiconPhoto1.getIconWidth();  
      height = imgiconPhoto1.getIconHeight();
  }catch(Exception ex){
      System.out.println(ex.getMessage());
  }
%>
<jsp:include page="Header.jsp" flush="true" />
<style type="text/css">
.img-logo{
		margin-top:-15px;
	}
.caret{
	margin-left: 50px;
	margin-top: -10px
}
.badge {
	background-color: red;
	z-index: 1000;
}
.people-hand-badge{
	float:left;
	margin-top:-30px;
	margin-left:35px;
	}
</style>
<script type="text/javascript">
	$(function() {
		$.post('HaveMessage', {
			data : "0"
		}, function(text, status) {
			$(".badge").text(text);
		});
	});
</script>
<div class="navbar navbar-default">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand var-logo" href="###" style="margin-left:80px"><img
				class="img-logo" alt="" src="<%=basePath %>img/LOGO.png"> </a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li <%if (s.equals("0") || s.equals("")) {%> class="active" <%}%>><a href="index.jsp?linknumber=0">主页 <span
						class="sr-only">(current)</span> </a>
				</li>
				<li <%if (s.equals("1")) {%> class="active" <%}%>><a
					href="RewardHall?linknumber=1">悬赏大厅 <span class="sr-only">(current)</span>
				</a>
				</li>
				<li <%if(s.equals("2")){%> class="active" <%}%> ><a
					href="Download?linknumber=2">下载资源</a></li>
				<li <%if (s.equals("3")) {%> class="active" <%}%>><a
					href="Reply?linknumber=3">讨论区</a>
				</li>

			</ul>
			<%
				if (user == null) {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="login/MyLogin">您还未登录，请登录</a>
				</li>
			</ul>
			<%
				} else {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">
					
					
					<div class="people-head" style="width: 34px ; height: 34px;margin:-10px 5px -10px 0px;">
					<img class="" src="user/userimg/<%=user.getUserImage() %>"
							<% if(height > width) {%> style="height:100% ; margin-left: <%=(1-width*1.0/height)*17 %>px" 
							<%}else{ %> style="width:100% ; margin-top: <%=(1-height*1.0/width)*17 %>px" <%} %>>
					</div> <span class="badge people-hand-badge"></span> <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="user/UserInfoCenter.jsp?linknumber=-1">个人信息</a></li>
						<li><a href="user/AmendPass.jsp?linknumber=-1">修改密码</a></li>
						<li><a href="UserMessageCenter?linknumber=-1">消息中心<span class="badge"></span></a>
						</li>
						<li role="separator" class="divider"></li>
						<li><a href="logoff">注销</a></li>
					</ul>
				</li>
			</ul>
			<%
				}
			%>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="getGenre?linknumber=1"><img alt="" src="img/write.png" title="发布需求" style="margin-top: -38px; margin-bottom: -12px;"> </a>
				</li>
			</ul>
		</div>
	</div>
</div>

