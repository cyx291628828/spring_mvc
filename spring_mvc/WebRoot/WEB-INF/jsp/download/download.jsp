<%@page import="com.source.plan.entity.*"%>
<%@page import="javax.swing.ImageIcon"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
	int genreType = 0;
	if (request.getParameter("genreType") != null) {
		genreType = Integer.parseInt(request.getParameter("genreType"));
	}
	String genreID = "0";
	if (request.getParameter("genreID") != null && !request.getParameter("genreID").equals("null")) {//!request.getParameter("genreID").equals("null") && 
		genreID = request.getParameter("genreID");
	}
	String sortRule = "0";
	if (request.getParameter("sortRule") != null) {
		sortRule = request.getParameter("sortRule");
	}
	
	User user = null;
	try{
	user = ((User)session.getAttribute("sessionUser"));
	}catch(Exception e){
		user=null;
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>下载资源</title>
    
<style type="text/css">
.wdc_p {
	height: 35px;
	text-align: center;
	border-top: 2px solid #dbdbdd;
}

.wdc_p a {
	width: 25%;
	padding:5px;
	display: block;
	text-decoration: none;
	float: left;
	line-height: 25px;
	border-right: 1px solid #e8eaeb;
	color: #36b35f;
}
.wdc_p a:hover{
	background:#eee;
}

.wdc_p a:last-child {
	border-right: 0px;
}

.wdc_p i {
	font-style: normal;
	font-size: 0.8em;
}
.wdc_p span{
	color: #969696;
}

.wdc_p i[class *="icon"] {
	font-size: 0.9em;
}
.mydownbody{
	width: auto;
	height: auto;
	border-radius:5px; 
	clear:both;
	margin: 0 auto;
	margin-top: 350px;
}
.down{
	width: 900px;
	min-height: 150px;
	padding: 15px 5px 5px 5px; 
	box-shadow: 2px 2px 2px #e3e3e5, -2px -2px 2px #e3e3e5;
	clear:both;
	margin: 0 auto;
	margin-bottom: 50px;
}
.down_hear{
	width: 13%;
	height: 150px;
	margin-left:10px;
	float: left;
}
.hear_Qname{
}
.hear_Aname{
}
.hearimg{
	margin-top:10px;
	width: 110px;
	height: 110px;
}
.question{
	width: 70%;
	padding:0px 5px 0px 10px;
	min-height: 130px;
	float: left;
}
.question p{
	padding:0px 5px 0px 10px;
	resize:none;
	font-size: 0.9em;
	color: #969696;
	max-height: 80px;
}
.down-right{
	float: left;
	width:15%;
}
.box-question {
	border: #006600 3px solid;
	float: left;
	width: 280px;
	height: 250px;
	margin: 50px 25px 20px 50px;
	padding: 10px;
	border-radius: 20px;
	-moz-box-shadow: 5px 5px 10px gray;
	-webkit-box-shadow: 5px 5px 10px gray;
	-o-box-shadow: 5px 5px 10px gray;
	box-shadow: 5px 5px 10px gray;
}

.browser-questions {
	margin-left: 200px;
}

.course-nav-box {
	margin-left: 30px;
}

.clearfix {
	height: 50px;
}

.non-question {
	margin-top: 150px;
	margin-left: 250px;
}
</style>
<script type="text/javascript">
</script>
</head>
  
  <body>
  <jsp:include page="/nav/_UserHead.jsp" />
  <div class="browser-questions">
			<ul class="breadcrumb" style="margin:80px 0 10px 30px;">
				<li><a href="index">主页</a>
				</li>
				<li><a href="Download?linknumber=2">下载资源</a>
				</li>
				<li class="active">所有资源</li>
			</ul>
			
			<div class="course-nav-box col-lg-11">
				<div class="course-nav-hd">

					<span>分类</span>
				</div>
				<div class="course-nav-row ">
					<span class="hd l">方向：</span>
					<div class="bd">
						<ul>
							<li
								class="course-nav-item <%if (genreType == 0) {%>
								on <%}%>"><a
								href="Download?linknumber=2">全部</a></li>
							<li
								class="course-nav-item
                                <%if (genreType == 1) {%>
								on <%}%>">
								<a href="Download?genreType=1&linknumber=2"
								data-ct="Atest?genreType=后端开发">后端开发</a>
							</li>
							<li
								class="course-nav-item
                               <%if (genreType == 2) {%>
								on <%}%>">
								<a href="Download?genreType=2&linknumber=2"
								data-ct="Atest?genreType=前端开发">前端开发</a>
							</li>
							<li
								class="course-nav-item
                                <%if (genreType == 3) {%>
								on <%}%>">
								<a href="Download?genreType=3&linknumber=2"
								data-ct="Atest?genreType=移动开发">移动开发</a>
							</li>
							<li
								class="course-nav-item
                                <%if (genreType == 4) {%>
								on <%}%>">
								<a href="Download?genreType=4&linknumber=2"
								data-ct="Atest?genreType=其他">其他</a>
							</li>
						</ul>
					</div>
				</div>
				<div class="course-nav-row">
					<span class="hd l">分类：</span>
					<div class="bd">
						<ul class="">
							<li
								class="course-nav-item <%if (genreID.equals("0")) {%>
								on <%}%>"><a
								href="Download?genreType=<%=genreType%>&linknumber=2">全部</a>
							</li>
							<%
								for (Genre g : pageInfo.getGenres()) {
							%>
							<li
								class="course-nav-item <%if (genreID.equals(g.getGenreId())) {%>
								on <%}%>"><a
								href="Download?genreID=<%=g.getGenreId()%>&genreType=<%=genreType%>&linknumber=2"
								data-id="1370" data-ct="be"><%=g.getGenreName()%></a></li>
							<%
								}
							%>
						</ul>
					</div>
				</div>
				<div class="course-nav-row">
					<span class="hd l">排序方式</span>
					<div class="bd">
						<ul class="">
							<%--
								sortRule=1表示当前时间升序
								sortRule=2表示当前时间降序
								sortRule=3表示当前浏览量升序
								sortRule=4表示当前浏览量降序
								sortRule=5表示当前赏金升序
								sortRule=6表示当前赏金降序 --%>
							<li class="course-nav-item "><a
								href="Download?genreType=<%=genreType%>&genreID=<%=genreID%>&linknumber=2&<%if (sortRule.equals("1")) {%>sortRule=2<%} else {%>sortRule=1<%}%>">时间<span
									<%if (sortRule.equals("1")) {%>
									class="glyphicon glyphicon-arrow-up"
									<%} else if (sortRule.equals("2")) {%>
									class="glyphicon glyphicon-arrow-down" <%}%>></span> </a>
							</li>

							<li class="course-nav-item"><a
								href="Download?genreType=<%=genreType%>&genreID=<%=genreID%>&linknumber=2&<%if (sortRule.equals("3")) {%>sortRule=4<%} else {%>sortRule=3<%}%>">浏览量<span
									<%if (sortRule.equals("3")) {%>
									class="glyphicon glyphicon-arrow-up"
									<%} else if (sortRule.equals("4")) {%>
									class="glyphicon glyphicon-arrow-down" <%}%>></span> </a>
							</li>

							<li class="course-nav-item"><a
								href="Download?genreType=<%=genreType%>&genreID=<%=genreID%>&linknumber=2&<%if (sortRule.equals("5")) {%>sortRule=6<%} else {%>sortRule=5<%}%>">下载金额<span
									<%if (sortRule.equals("5")) {%>
									class="glyphicon glyphicon-arrow-up"
									<%} else if (sortRule.equals("6")) {%>
									class="glyphicon glyphicon-arrow-down" <%}%>></span> </a>
							</li>

						</ul>
					</div>
				</div>
			</div>
	</div>
  
  	<div class="mydownbody">
  	
  	<% int boxQuestionNum = 0;
  	for( Answer answer : pageInfo.getAnswers()){
  		boxQuestionNum++;
  ImageIcon imgiconPhoto1 = null;  
  int height = 0;  
  int width = 0;  
  String imagepath = request.getSession().getServletContext().getRealPath("user/userimg/"+answer.getUser().getUserImage());
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
  	<div class="down">
    	<div class="down_hear">
    		
    		<div class="hearimg"><img alt="图片加载中......" src="user/userimg/<%=answer.getUser().getUserImage() %>"
    				<% if(height > width) {%> style="height:100% ; margin-left: <%=(1-width*1.0/height)*40 %>px" 
					<%}else{ %> style="width:100% ; margin-top: <%=(1-height*1.0/width)*40 %>px" <%} %>></div>
    		
    	</div>
    	<div class="question">
    		<a style="text-decoration: none;" href="ScanDownQuestion?QuestionID=<%=answer.getQuestion().getQuestionId()%>&linknumber=1"><h4><%=answer.getQuestion().getQuestionTheme() %></h4></a>
    		<p>内容：<%=answer.getQuestion().getQuestionContent().replace("\n", "</p><p>") %></p>
    	</div>
    	
    	<div class="down-right">
    		<div style="margin-top: 5px;float: left;"><h6>下载花费：</h6>
    		<span style="font-size: 30px;padding-left : 20px;"><%=answer.getQuestion().getQuestionDownMoney() %></span>
    		<img style="margin-top: -6px;" src="./img/money.ico"></div>
    		<div >
    			<div id="text"></div>
    		</div>
    	</div>
    	<div style="clear: left;" class="wdc_p">
    		<a id="a<%=answer.getQuestion().getQuestionId()%>" style="text-decoration: none; cursor: pointer;"><i>解答者：<span><%=answer.getUser().getUserName() %></span></i> </a> 
    		<a id="b<%=answer.getQuestion().getQuestionId()%>" style="text-decoration: none;" href="ScanDownQuestion?QuestionID=<%=answer.getQuestion().getQuestionId()%>"><i>评论个数<span><%=answer.getPinglun_num() %></span></i> </a>  
    		<a id="c<%=answer.getQuestion().getQuestionId()%>" style="text-decoration: none; cursor: pointer;" onclick="zanfun(<%=answer.getQuestion().getQuestionId()%>)"><i>给TA一赞<span id="dz<%=answer.getQuestion().getQuestionId().replace("0", "")%>"><%=answer.getZan_num() %></span></i> </a >
    		<a id="d<%=answer.getQuestion().getQuestionId()%>" style="text-decoration: none; cursor: pointer;" ><i>下载次数<span id="xz"><%=answer.getDownload_num() %></span></i> </a >
    	</div>
    	<!-- 显示评论
    	<div style="width: 100%;height: 80px;border: 1px solid gray;">
    		
    	</div>  -->
    	
    </div>
    <%} %>
    </div>
    
    <%
				if (boxQuestionNum != 0) {
			%>
			<div style="clear:both;margin-left: 350px;">
				<div style="width: 100px;float: left;margin-top: 20px;">
					<form action="Download?linknumber=2&genreType=<%=genreType%>&sortRule=<%=sortRule%>&genreID=<%=genreID%>"
						method="post">
						<div class="input-group">
							<input type="text" class="form-control" name="pageNum" value="1">
							<span class="input-group-btn">
								<button class="btn btn-default" type="submit">跳到</button> </span>
						</div>
					</form>
				</div>
				<ul class="pagination">
					<li <%if (pageInfo.getPageNum() == 1) {%> class="disabled"
						title="已是第一页" <%}%>><a
						href="Download?pageNum=<%=pageInfo.getPageNum() == 1 ? 1 : pageInfo
						.getPageNum() - 1%>&genreType=<%=genreType%>&sortRule=<%=sortRule%>&genreID=<%=genreID%>"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
					<%
						int i = 0;
							for (i = 0; i < pageInfo.getPageSum(); i++) {
					%>
					<li <%if (i + 1 == pageInfo.getPageNum()) {%> class="active" <%}%>><a
						href="Download?linknumber=2&pageNum=<%=i + 1%>&ScanType=myScan&genreType=<%=genreType%>&sortRule=<%=sortRule%>&genreID=<%=genreID%>"><%=i + 1%></a>
						<%
							}
						%>
					
					<li <%if (pageInfo.getPageSum() == pageInfo.getPageNum()) {%>
						class="disabled" title="已是最后一页" <%}%>><a
						href="Download?linknumber=2&pageNum=<%=(pageInfo.getPageNum() + 1) > pageInfo.getPageSum() ? pageInfo
						.getPageNum() : (pageInfo.getPageNum() + 1)%>&genreType=<%=genreType%>&sortRule=<%=sortRule%>&genreID=<%=genreID%>"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span> </a>
					</li>
					<li><a>共<%=pageInfo.getPageSum()%>页</a></li>
				</ul>

			</div>
			<%
				} else {
			%>
			<div class="non-question">
				<img src="img/sorry-hall.png">
			</div>
			<%
				}
			%>
			
    
	<script type="text/javascript">
	function zanfun( QuestionID) {
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.open("GET", "Zan?DownQuestionID="+QuestionID, true);
		xmlhttp.send();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			if(xmlhttp.responseText=="NO"){
				alert("每人只能点赞一次哦！");
			}
			else if(xmlhttp.responseText=="LOGIN"){
				alert("登陆之后再赞吧！");
			}else if(xmlhttp.responseText=="NODOWN"){
				alert("下载之后才能点赞和评论");
			}else{
			 var a = document.getElementById("dz"+QuestionID).innerHTML;
			 document.getElementById("dz"+QuestionID).innerHTML = parseInt(a)+1;
			}
			}
		}
	}

	</script>
	<jsp:include page="/nav/footer.jsp"/>
</body>
</html>
