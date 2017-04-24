<%@page import="com.source.plan.entity.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@page import="javax.swing.ImageIcon"%>
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

<title>修改个人信息</title>
<script type="text/javascript">
	function checkFile() {
		//在表单中添加一个隐藏元素，目的是测试是否获得动态的用户名，以便在Servlet中接收并传递到数据库中  
		//alert("文件：333"+file) ;
		//用元素的id获得该元素的值，从而进行判断选择的文件是否合法  
		var file = document.getElementById("file0").value;
		//alert("文件："+file) ;  
		if (file == null || file == "") {
			$("form").attr("action", "user/AmendInfo");
			return;
		}
		if (file.lastIndexOf(".") == -1) {
			alert("路径不正确!");
			return false;
		}
		var allImgExt = ".jpg|.jpeg|.gif|.bmp|.png|";
		var extName = file.substring(file.lastIndexOf("."));
		if (allImgExt.indexOf(extName + "|") == -1) {

			errMsg = "该文件类型不允许上传。请上传 " + allImgExt + " 类型的文件，当前文件类型为" + extName;
			alert(errMsg);
			return false;
		}
		$("form").attr("action", "user/AmendInfo");
		// document.uploadForm.submit();  
	}
</script>
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

.btn-div {
	padding: 30px 70px;
}
</style>
<%
	User user = (User) session.getAttribute("sessionUser");
	String result = request.getParameter("result");
	result = result == null ? "" : result;
	Date date = new Date();
	DateFormat format = new SimpleDateFormat("yyyy");
	String NowTime = format.format(date);
	Date ddday = new Date(2016, 3, 0);
	int sss = ddday.getDate();
	//out.println(sss);
%>
<%
	ImageIcon imgiconPhoto1 = null;
	int height = 0;
	int width = 0;
	String imagepath = request.getSession().getServletContext()
			.getRealPath("user/userimg/" + user.getUserImage());
	try {
		imgiconPhoto1 = new ImageIcon(imagepath);
		width = imgiconPhoto1.getIconWidth();
		height = imgiconPhoto1.getIconHeight();
	} catch (Exception ex) {
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
			<li><a href="index">主页</a>
			</li>
			<li><a href="user/UserInfoCenter">个人信息</a>
			</li>
			<li class="active">修改信息</li>
		</ul>
		<%
			if (result.equals("")) {
		%>
		<div class="mycontainer">
			<div class="form-group panel panel-default">
				<div class="panel-heading">
					<h3>修改信息</h3>
				</div>
				<form action="" method="post" class="form-horizontal"
					enctype="multipart/form-data">
					<div style="margin-top: 20px">
						<div class="form-group">
							<label class="col-md-2 control-label">用户ID: </label>
							<div class="col-md-6">
								<label class="control-label"><%=user.getUserId()%></label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">用户头像:</label> <label
								class="col-md-6" style="width: 130px ; height: 100px;">
								<img id="img10" src="user/userimg/<%=user.getUserImage()%>"
								<%if (height > width) {%>
								style="height:100% ; margin-left: <%=(1 - width * 1.0 / height) * 50%>px"
								<%} else {%>
								style="width:100% ; margin-top: <%=(1 - height * 1.0 / width) * 50%>px"
								<%}%>> </label>
							<p id="dd">请选择需要更换的图片</p>
							<input type="file" name="file0" id="file0" accept="image/png ,image/jpg ,image/jpeg,image/gif,image/bmp,image/png"/>
						</div>

						<div class="form-group">
							<label class="col-md-2 control-label">用户名: </label>
							<div class="col-md-6">
								<input name="username" class="form-control"
									style="width: 170px;" maxlength='10'
									value="<%=user.getUserName()%>">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">注册时间: </label>
							<div class="col-md-6">
								<label class="control-label"><%=user.getUserRegTime().substring(0, 10)%></label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">性别: </label>
							<div class="col-md-6">
								<select class="form-control big" style="width: 60px;" name="sex">
									<option value="男"
										<%if (user.getUserSex() == null || user.getUserSex().equals("男")) {%>
										selected="selected" <%}%>>男</option>
									<option value="女"
										<%if (user.getUserSex() != null && user.getUserSex().equals("女")) {%>
										selected="selected" <%}%>>女</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">出生日期: </label>
							<div class="col-md-6">
								<input name="date" class="form-control"
									style="width: 110px; float: left;" type="text" id="J-xl"
									value=<%=user.getUserBirth()%>> 
								<img src="./img/dateicon.png"
									style="margin-left: 8px;margin-top: 8px;width: 25px;height: 20px;"
									onclick="laydate({elem: '#J-xl'});">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">我的工作: </label>
							<div class="col-md-6">
								<input name="userjob" class="form-control" style="width: 170px;"
									maxlength='10' value="<%=user.getUserJob()%>">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">拥有财富: </label>
							<div class="col-md-6">
								<label class="control-label"><%=user.getUserFakeMoney()%></label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">座右铭: </label>
							<div class="col-md-6">
								<input name="userinterest" class="form-control"
									style="width: 250px;"
									value="<%=user.getUserInterest()%>">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">我的等级: </label>
							<div class="col-md-6">
								<label class="control-label"><%=user.getUserLevel()%></label>
							</div>

						</div>
						<div class="btn-div">
							<input type="submit" class="btn btn-info" value="确认"
								onclick="checkFile()"> <a class="btn btn-info"
								style="margin-left: 50px" href="javascript :;"
								onClick="javascript :history.back(-1);">返回</a>
						</div>
					</div>
				</form>

			</div>

		</div>
		<%
			} else {
		%>
		<div class="form-group panel panel-default">
			<div class="panel-heading">
				<h3>修改信息</h3>
			</div>
			<h4 style="margin: 50px">
				信息修改成功,点此<a href="user/UserInfoCenter.jsp">返回</a>个人信息
			</h4>

		</div>
		<%
			}
		%>
	</div>
	<script type="text/javascript" src="src/js/laydate.dev.js"></script>
	<script>
		laydate({
			elem : '#J-xl'
		});
		function newsnext() {

			elem: '#J-xl';

		}
		$("#date-img").change(function() {
			laydate({
				elem : '#J-xl'
			});
		});
		$("#file0").change(
				function() {
					var objUrl = getObjectURL(this.files[0]);
					console.log("objUrl = " + objUrl);
					if (objUrl) {
						$("#img10").attr("src", objUrl);
						var screenImage = $("#img10");
						$("<img/>").attr("src", $(screenImage).attr("src"))
								.load(
										function() {//新建临时img标签
											//img标签加载完后获取图片的真是高宽。
											var imageWidth = this.width;
											var imageHeight = this.height;
											var marginleft = (1 - imageWidth
													* 1.0 / imageHeight) * 50;
											var margintop = (1 - imageHeight
													* 1.0 / imageWidth) * 50;
											if (imageHeight > imageWidth) {
												//		var marginleft = (1-imageWidth*1.0/imageHeight)*50;
												$("#img10").css("height",
														"100%");
												$("#img10")
														.css("width", "auto");
												$("#img10").css("margin-left",
														marginleft);
											} else {
												//		var margintop = (1-imageHeight*1.0/imageWidth)*50;
												$("#img10")
														.css("width", "100%");
												$("#img10").css("height",
														"auto");
												$("#img10").css("margin-top",
														margintop);
											}

											$("#dd").html("左边为预览图");
										});
					}
				});
		//建立一個可存取到該file的url
		function getObjectURL(file) {
			var url = null;
			if (window.createObjectURL != undefined) { // basic
				url = window.createObjectURL(file);
			} else if (window.URL != undefined) { // mozilla(firefox)
				url = window.URL.createObjectURL(file);
			} else if (window.webkitURL != undefined) { // webkit or chrome
				url = window.webkitURL.createObjectURL(file);
			}
			widths = 1;
			return url;
		}
	</script>
	<jsp:include page="/nav/footer.jsp"/>
</body>
</html>
