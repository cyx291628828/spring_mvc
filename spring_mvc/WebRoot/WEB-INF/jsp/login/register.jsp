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

<title>注册</title>

<jsp:include page="/src/bootStrap.jsp" flush="true" />
<style type="text/css">
.mbody {
	padding-top: 20px;
	margin: 0 auto;
}

.mycontainer {
	width: 400px;
	margin: 0 auto;
	border: 3px solid #fafafa;
	border-radius: 5px;
}

.form-control {
	height: 40px;
}

.mycontainer div {
	margin-bottom: 15px;
}

body {
	width: 100%;
	background-color: #24ad62;
	font-family: '微软雅黑', '黑体', 'times new roman';
}

div button {
	margin: 0 50px;
}

td font {
	font-size: 10px;
	margin-top: 5px;
}

.text-input {
	height: 25px;
}

.flower {
	width: 100px;
	height: 100px;
	position: absolute;
	bottom: 20px;
	left: 20px;
}

.logCenter-div {
	width: 30%;
	height: 80%;
	position: absolute;
	bottom: 20px;
	left: 70%;
}
.form-control{
	width:180px;
	height:30px;
}
.logCenter {
	width: 100%;
	height: 100%;
}
.error{
float:left;
}
#userID-error,#Username-error,#password1-error,#password2-error{
	margin-top:5px;
	margin-left:5px;
	font-size: 10px;
	font-family:"宋体";
	font-style: normal !important;
}
</style>
<script type="text/javascript" src="src/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="src/js/additional-methods.js"></script>
<script type="text/javascript">
	/*$(function() {
		var userID = $("input[name='userID']");
		var Username = $("input[name='Username']");
		var password1 = $("input[name='password1']");
		var password2 = $("input[name='password2']");
		var UserUnit = $("input[name='UserUnit']");
		$userID.blur(function() {
			if (userID.val().length < 6) {
				$(".userID-td").removeClass("glyphicon glyphicon-ok");
				$(".userID-td").text("长度小于6");
			} else {
				$.post('JudegUserID', {
					userID : userID.val()
				}, function(text) {
					if (text == "fail") {
						$(".userID-td").removeClass("glyphicon glyphicon-ok");
						$(".userID-td").text("用户名已被注册");
					} else {
						$(".userID-td").text("");
						$(".userID-td").addClass("glyphicon glyphicon-ok");
					}
				});
			}
		});
		Username.blur(function() {
			if (Username.val().length <= 4 || Username.val().length > 20) {
				$(".Username-td").removeClass("glyphicon glyphicon-ok");
				$(".Username-td").text("用户名长度2-10个汉字");
			} else {
				$(".Username-td").text("");
				$(".Username-td").addClass("glyphicon glyphicon-ok");

			}
		});
		password1.blur(function() {
			if (password1.val().length < 6 || password1.val().length > 15) {
				$(".password1-td").removeClass("glyphicon glyphicon-ok");
				$(".password1-td").text("长度请在6~15之间");
			} else {
				$(".password1-td").text("");
				$(".password1-td").addClass("glyphicon glyphicon-ok");

			}
		});
		password2.blur(function() {
			if (password2.val() != password1.val()) {
				$(".password2-td").removeClass("glyphicon glyphicon-ok");
				$(".password2-td").text("两次密码不一致");
			} else {
				if (password2.val().length < 6 || password2.val().length > 15) {
					$(".password2-td").removeClass("glyphicon glyphicon-ok");
					$(".password2-td").text("长度请在6~15之间");
				} else {
					$(".password2-td").text("");
					$(".password2-td").addClass("glyphicon glyphicon-ok");
				}
			}
		});
		$("input").blur(
				function() {
					if ($(".userID-td").hasClass("glyphicon-ok")
							&& $(".Username-td").hasClass("glyphicon-ok")
							&& $(".password1-td").hasClass("glyphicon-ok")
							&& $(".password2-td").hasClass("glyphicon-ok")) {
						$(".btn-reg").removeAttr("disabled");
					} else {
						$(".btn-reg").attr("disabled", "disabled");
					}
				});
	});*/
	
	$().ready(function() {
		$("#reg-form").validate({
			rules:{
				userID:{
					required:true,
					minlength:6,
					maxlength:15,
					remote:{
						url:"JudegUserID",
						type:"post", 
						dataType: "json",
						data:{
						userID :function () {
                        return $("input[name='userID']").val(); //取要验证的username
                  		  },
						},
					dataFilter: function (data) {  //判断控制器返回的内容
	                    if (data == "fail") {
	                        return false;
	                    }
	                    else {
	                        return true;
	                    }
	                }
				}
				},
				Username:{
			        required: true,
			        minlength: 2,
			        maxlength: 10
			      },
				password1:{
					required: true,
        			minlength: 5
				},
				password2:{
					required: true,
        			equalTo: "#password1"
				},
			},
			messages:{
				userID: {
			        required: "请输入用户名",
			        minlength: "用户ID长度最小为6",
			        maxlength: "用户ID长度最大为15",
			        remote:"用户名已注册"
			      },
			      Username: {
			        required: "请输入用户名",
			        minlength: "用户ID长度最小为2",
			        maxlength: "用户ID长度最大为10"
			      },
				password1:{
					required: "请输入密码",
        			minlength: "长度不符哦！"
				},
				password2:{
					required: "两次密码要相同哦！",
        			equalTo: "长度不符哦！"
				}
			}
		});
	
	});
</script>
</head>

<body>
	<a href="index.jsp"><img src="img/logLOGO.png"> </a>
	<div class="logCenter-div">
		<img src="img/fangZ.jpg" class="logCenter">
	</div>

	<div class="mbody">
		<div class="mycontainer">
			<h2 style="margin-left: 20px;">欢迎注册</h2>
			<form id="reg-form" action="Register?result=reg" method="post">
				<table class="table">
					<col width="100px">
					<tr height="60px">
						<td>用户ID：</td>
						<td><input onblur="this.value=this.value.replace(/[^0-9]/g,'');"
							onkeyup="this.value=this.value.replace(/[^0-9]/g,'');" type="text" name="userID" height="50px"
							class="form-control" placeholder="长度在6-15之间">
						</td>
					</tr>
					<tr height="60px">
						<td>用户名：</td>
						<td><input type="text" name="Username" class="form-control"
							placeholder="用户名">
						</td>
					</tr>
					<tr height="60px">
						<td>密码：</td>
						<td><input type="password" onblur="this.value=this.value.replace(/[^0-9a-zA-Z]/g,'');"
							onkeyup="this.value=this.value.replace(/[^0-9a-zA-Z]/g,'');" id="password1" name="password1"
							class="form-control" placeholder="长度6~15">
						</td>
					</tr>
					<tr height="60px">
						<td>确认密码：</td>
						<td><input type="password" name="password2" onblur="this.value=this.value.replace(/[^0-9a-zA-Z]/g,'');"
							onkeyup="this.value=this.value.replace(/[^0-9a-zA-Z]/g,'');"
							class="form-control" placeholder="确认密码">
						</td>
					</tr>
				</table>
				<hr>
				<div>
					<button type="sumbit" class="btn btn-success btn-reg"
						 title="请认真填写个人信息">注册</button>
					<button type="reset" class="btn btn-success">重置</button>
				</div>
				<p style="margin: 10px 20px">
					已经注册？点此<a href="login/MyLog.jsp">登陆</a>
				</p>
			</form>
		</div>
	</div>
	<img src="img/flower.png" class="flower">
</body>
</html>