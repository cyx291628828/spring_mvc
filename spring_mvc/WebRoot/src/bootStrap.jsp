<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


	<script type="text/javascript" src="<%=basePath%>src/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>src/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
	<link rel="stylesheet" href="<%=basePath%>src/bootstrap-3.3.7-dist/css/bootstrap.min.css">
