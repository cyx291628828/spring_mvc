<%@page import="com.source.plan.beans.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
PageInfo pageInfo = (PageInfo) request.getAttribute("request");
	Question q  = pageInfo.getQuestion();
%>
<script type="text/javascript">
function checkFile() {
			//在表单中添加一个隐藏元素，目的是测试是否获得动态的用户名，以便在Servlet中接收并传递到数据库中  
			//alert("文件：333"+file) ;
			//用元素的id获得该元素的值，从而进行判断选择的文件是否合法  
			var file = document.getElementById("exampleInputFile").value;
			//alert("文件："+file) ;  
			if (file == null || file == "") {
				$("form").attr("action", "GiveAnswer?linknumber=1&QuestionID=<%=q.getQuestionID()%>&QuestionUserID=<%=q.getQuestionUserID()%>");
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
			$("form").attr("action", "GiveAnswer?linknumber=1&QuestionID=<%=q.getQuestionID()%>&QuestionUserID=<%=q.getQuestionUserID()%>");
			// document.uploadForm.submit();  
		}
</script>
 
<div class="anwser">
	<h2>我想帮忙</h2>
	<form
		method="post" class="form-group" enctype="multipart/form-data">
		<h3 style="margin-left: 30px;">文字形式</h3>
		<textarea rows="5" cols="100" class="form-control"
			name="answerContent" placeholder="请输入答案"></textarea>
		<h3 style="margin-left: 30px;">文件形式</h3>
		<input type="file" id="exampleInputFile" class="form-file"
			name="files"> <input type="submit" onclick="checkFile()"
			style="float:right; margin-top: -20px;" value="提交"
			class="btn btn-default">
	</form>
</div>