<%@page import="com.source.plan.entity.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
	User	 user = (User)session.getAttribute("sessionUser");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>发布问题</title>
<style type="text/css">
.form-group-div {
	width: 600px;
	padding: 10px;
	margin-top: 40px;
	margin-left: 100px;
}

.content {
	margin-left: 350px;
}

.genre-div {
	background-color: #f8f8f8;
	margin-bottom: 5px;
}

label h3 {
	margin: 10px;
}
</style>
<%
	request.setCharacterEncoding("utf-8");
	List<Genre> genres = (ArrayList<Genre>) request
	.getAttribute("genres");
%>
</head>

<body>
	<jsp:include page="/nav/_UserHead.jsp" />
	<jsp:include page="/nav/_LeftHead.jsp" />
	<script type="text/javascript">
		$(function() {
			var theme = $("input[name='QuestionTheme']");
			var content = $(".QuestionContent");
			var day = $("input[name='QuestionEndTime']");
			var money = $("input[name='QuestionMoney']");
			$("input").blur(function() {
				if (theme.val().length <= 0) {
					$(".theme-ok").removeClass("glyphicon glyphicon-ok");
					$(".theme-ok").text("请输入主题");
				} else {
					$(".theme-ok").text("");
					$(".theme-ok").addClass("glyphicon glyphicon-ok");
				}
			});
			content.blur(function() {
				if (content.val().length <= 0) {
					$(".content-ok").removeClass("glyphicon glyphicon-ok");
					$(".content-ok").text("请输入主题");
				} else {
					$(".content-ok").text("");
					$(".content-ok").addClass("glyphicon glyphicon-ok");
				}
			});

			$("input")
					.blur(
							function() {
								if (day.val().length <= 0) {
									$(".day-ok").removeClass(
											"glyphicon glyphicon-ok");
									$(".day-ok").text("请规范输入天数");
								} else {
									$(".day-ok").text("");
									$(".day-ok").addClass(
											"glyphicon glyphicon-ok");
								}
							});

			$("input").blur(
					function() {
						if (money.val().length <= 0) {
							$(".money-ok")
									.removeClass("glyphicon glyphicon-ok");
							$(".money-ok").text("请输入赏金");
						} else {
							$.post('JudegMoney', {
								money : money.val(),
								userID : $(".userID").val()
							}, function(text) {
								if (text == "success") {
									$(".money-ok").text("");
									$(".money-ok").addClass(
											"glyphicon glyphicon-ok");
								} else {
									
											$(".money-ok").removeClass(
											"glyphicon glyphicon-ok");
									$(".money-ok").text("金额不足");
								}
							});
						}
					});
			$("input[type='checkbox']")
					.click(
							function() {
								if ($("input[name='QuestionGenre']:checked").length >= 6
										|| $("input[name='QuestionGenre']:checked").length <= 0) {
									$(".genre-ok").removeClass(
											"glyphicon glyphicon-ok");
									$(".genre-ok").text("至少选一项，至多选五项");
								} else {
									$(".genre-ok").text("");
									$(".genre-ok").addClass(
											"glyphicon glyphicon-ok");
								}
							});
			$(".btn-submit").click(
					function() {
						if ($(".theme-ok").hasClass("glyphicon-ok")
								&& $(".content-ok").hasClass("glyphicon-ok")
								&& $(".day-ok").hasClass("glyphicon-ok")
								&& $(".money-ok").hasClass("glyphicon-ok")
								&& $(".genre-ok").hasClass("glyphicon-ok")) {
							checkFile();
						} else {
							alert("信息不完善哦！");
							return false;
						}
					});
		});

		function checkFile() {
			//在表单中添加一个隐藏元素，目的是测试是否获得动态的用户名，以便在Servlet中接收并传递到数据库中  
			alert("文件") ;
			//用元素的id获得该元素的值，从而进行判断选择的文件是否合法  
			var file = document.getElementById("exampleInputFile").value;
			//alert("文件："+file) ;  
			if (file == null || file == "") {
				$("form").attr("action", "PublishQuestion?linknumber=1");
				return;
			}
			if (file.lastIndexOf(".") == -1) {
				alert("路径不正确!");
				return false;
			}
			var allImgExt = ".rar|.zip|";
			var extName = file.substring(file.lastIndexOf("."));
			if (allImgExt.indexOf(extName + "|") == -1) {

				errMsg = "该文件类型不允许上传。\n请上传 " + allImgExt + " 类型的文件，\n当前文件类型为"
						+ extName;
				alert(errMsg);
				return false;
			}
			alert("34") ;
			$("form").attr("action", "PublishQuestion?linknumber=1");
			// document.uploadForm.submit();  
		}
	</script>
	<div class="content">
		<ul class="breadcrumb" style="margin:80px 0 10px 30px;">
			<li><a href="index.jsp">主页</a>
			</li>
			<li><a href="RewardHall?linknumber=1">悬赏大厅</a>
			</li>
			<li class="active">发布任务</li>
		</ul>
		<div class="form-group-div">
			<form method="post" enctype="multipart/form-data">
				<div style="display: none">
					<%
						if (user != null) {
					%>
					<input class="userID" value='<%=user.getUserId()%>' />
					<%
						}
					%>
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1"><h3>
							主题 &nbsp;&nbsp;&nbsp;<font class="theme-ok" color="red" size="3"></font>
						</h3>
					</label> <input type="text" class="form-control" id="exampleInputEmail1"
						name="QuestionTheme" placeholder="请用一句话概括你的目的，少于30字"
						maxlength="30">
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1"><h3>
							简述&nbsp;&nbsp;&nbsp;<font class="content-ok" color="red" size="3"></font>
						</h3>
					</label>
					<textarea class="form-control QuestionContent" rows="5" cols="5"
						maxlength="300" name="QuestionContent" style="resize:none"></textarea>
				</div>
				<div class="form-group">
					<label for="exampleInputFile"><h3>
							上传附件请上传<span style="color: #f00;">.zip</span>或者<span
								style="color: #f00;">.rar</span>格式文件
						</h3>
					</label> <input name="QuestionFile" type="file" id="exampleInputFile">
					<p class="help-block">如果附件数量大于一个，请将附件压缩成一个文件上传</p>
				</div>
				<div class="form-group"
					style="border: 1px solid black;border-radius:5px;padding: 5px;">
					<label for="exampleInputEmail1"><h3>
							选择分类&nbsp;&nbsp;&nbsp;<font class="genre-ok" color="red" size="3"></font>
						</h3>
					</label>
					<div class="genre-div">
						<h4 style="margin-bottom: 10px;">前端开发</h4>
						<%
							for (Genre g : genres) {
								if (g.getGenreType().equals("前端开发")) {
						%>
						<input type="checkbox" name="QuestionGenre"
							value="<%=g.getGenreId()%>">
						<%=g.getGenreName()%>
						<%
							}
							}
						%>
					</div>
					<div class="genre-div">
						<h4 style="margin-bottom: 10px;">后端开发</h4>
						<%
							for (Genre g : genres) {
								if (g.getGenreType().equals("后端开发")) {
						%>
						<input type="checkbox" name="QuestionGenre"
							value="<%=g.getGenreId()%>">
						<%=g.getGenreName()%>
						<%
							}
							}
						%>
					</div>
					<div class="genre-div">
						<h4 style="margin-bottom: 10px;">移动开发</h4>
						<%
							for (Genre g : genres) {
								if (g.getGenreType().equals("移动开发")) {
						%>
						<input type="checkbox" name="QuestionGenre"
							value="<%=g.getGenreId()%>">
						<%=g.getGenreName()%>
						<%
							}
							}
						%>
					</div>
					<div class="genre-div">
						<h4 style="margin-bottom: 10px;">其他</h4>
						<%
							for (Genre g : genres) {
								if (g.getGenreType().equals("其他")) {
						%>
						<input type="checkbox" name="QuestionGenre"
							value="<%=g.getGenreId()%>">
						<%=g.getGenreName()%>
						<%
							}
							}
						%>
					</div>
				</div>
				<div class="form-group"
					style="height: 30px; margin: 20px 20px 20px 0;">
					<label>持续时长：</label>
					<div style="position: relative;left: 70px;top:-30px;">
						<input type="text" name="QuestionEndTime" class="form-control"
							maxlength="2" style="font-size:16px;font-weight:700;width:50px"
							value=""
							onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"  
                                    onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\D/g,'')}" />
					</div>
					<div style="position: relative;left: 140px;top:-60px;">
						天<font color="gray" style="margin-left: 20px;">最多99天</font>&nbsp;&nbsp;&nbsp;<font
							class="day-ok" color="red" size="3"></font>
					</div>
				</div>

				<div class="form-group"
					style="height: 30px; margin: 20px 20px 20px 0;">
					<label>悬赏金额：</label>
					<div style="position: relative;left: 70px;top:-30px;">
						<input type="text" name="QuestionMoney" class="form-control"
							maxlength="5" style="font-size:16px;font-weight:700;width:70px"
							value=""
							onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
							  onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\D/g,'')}" />
					</div>
					<div style="position: relative;left: 160px;top:-60px;">
						<img src="img/money.ico" />&nbsp;&nbsp;&nbsp;<font
							class="money-ok" color="red" size="3"></font>
					</div>
				</div>
				<%--<div class="form-group" style="height: 30px; margin: 20px 20px 20px 0;">
					<label>赏金分配：</label>
					<div style="width: 150px;position: relative;left:70px;top:-30px;">
					<select class="form-control" name="QuestionAspect">
						<option value="1">选择最优</option>
						<option value="2">选中平分</option>
					</select></div>
				</div>--%>
				<button type="submit" class="btn btn-info btn-submit">提交</button>
			</form>
		</div>
	</div>
	<jsp:include page="/nav/footer.jsp"/>
</body>
</html>
