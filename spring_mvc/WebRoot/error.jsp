<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>您访问的页面不存在！</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

</head>
  
  <body>
    <jsp:include page="nav/_UserHead.jsp" />
    <div class="err404" style="width: auto;height: auto;margin-left: 550px; margin-top: 200px;padding-bottom: 200px;">
    	<a href="index.jsp"><img alt="" src="img/404.png"></a>
    </div>
  </body>
</html>
