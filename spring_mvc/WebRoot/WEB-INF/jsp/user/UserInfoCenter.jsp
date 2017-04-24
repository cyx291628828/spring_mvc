<%@page import="com.source.plan.entity.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="javax.swing.ImageIcon"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>查看个人信息</title>
<style type="text/css">
.public-body {
	width: 800px;
	margin-left: 600px;
	margin-top: 60px;
	margin-left: 400px;
	margin-bottom: 50px;
}

.panel-default {
	height: auto;
	margin-top: 50px !important;
}
.btn-div{
	padding: 30px 70px;
}
.btn-amend{
	margin: 0 70px 0 40px;
}

</style>
<%
	User user  = null;
	try{
	user= (User)session.getAttribute("sessionUser");
	}catch(Exception e){
	System.out.print("1");
	}
 %>
 <%
  ImageIcon imgiconPhoto1 = null;  
  int height = 0;  
  int width = 0;  
  String imagepath = request.getSession().getServletContext().getRealPath("user/userimg/"+user.getUserImage());
  try{
      imgiconPhoto1 = new ImageIcon(imagepath);  
      width = imgiconPhoto1.getIconWidth();  
      height = imgiconPhoto1.getIconHeight();
  }catch(Exception ex){
      System.out.println(ex.getMessage());
  }
  //out.println("height=="+height);
  //out.println("<br>width=="+width);
%>
</head>

<body>
	<jsp:include page="/nav/_UserHead.jsp"></jsp:include>
	<jsp:include page="/nav/_UserInfoHead.jsp"></jsp:include>
	<div class="public-body">
		<ul class="breadcrumb">
			<li><a href="index.jsp">主页</a></li>
			<li class="active">个人信息</li>
		</ul>
		<div class="mycontainer">
		<div class="form-group panel panel-default">
			<div class="panel-heading">
				<h3 >个人信息</h3>
			</div>
			<form action="user/AmendInfo.jsp" method="post" class="form-horizontal">
				<div style="margin-top: 20px">
				<div class="form-group">
						<label class="col-md-2 control-label">用户头像:</label>
						<label class="col-md-6" style="width: 130px ; height: 100px;" >
							<img src="user/userimg/<%=user.getUserImage() %>" 
							<% if(height > width) {%> style="height:100% ; margin-left: <%=(1-width*1.0/height)*50 %>px" 
							<%}else{ %> style="width:100% ; margin-top: <%=(1-height*1.0/width)*50 %>px" <%} %>>
						</label>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label">用户ID: </label>
						<div class="col-md-6">
							<label class="control-label"><%=user.getUserId() %></label>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label">用户名: </label>
						<div class="col-md-6">
							<label class="control-label"><%=user.getUserName() %></label>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">注册时间: </label>
						<div class="col-md-6">
							<label class="control-label"><%=user.getUserRegTime().substring(0,10) %></label>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">性别: </label>
						<div class="col-md-6">
							<label class="control-label"><%=user.getUserSex() %></label>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">出生日期: </label>
						<div class="col-md-6">
							<label class="control-label"><%=user.getUserBirth().substring(0,10) %></label>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">我的工作: </label>
						<div class="col-md-6">
							<label class="control-label"><%=user.getUserJob()%></label>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">拥有财富: </label>
						<div class="col-md-6">
							<label class="control-label"><%=user.getUserFakeMoney()%></label><img alt="" src="img/money.ico">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">座右铭: </label>
						<div class="col-md-6">
							<label class="control-label"><%=user.getUserInterest()%></label>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">我的等级: </label>
						<div class="col-md-6">
							<label class="control-label"><%=user.getUserLevel()%></label>
						</div>
					</div>
					<div class="btn-div">
					<input type="submit" class="btn btn-info btn-amend" value="修改">
					<a  class="btn btn-info" class="a-return" href="index.jsp" >返回</a>
					</div>
				</div>
			</form>
		</div>
		</div>
	</div>
	<jsp:include page="/nav/footer.jsp"/>
</body>
</html>
