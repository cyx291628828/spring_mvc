<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style type="text/css">
.footer {
	clear:both;
	height:100px;
	padding: 8px 0 5px 0;
	background: #f8f8f8;
	font-size:15px;
	border-top: 1px #f8f8f8 solid;
	color: #ABABAB;
	z-index: 100;
}

.LiquidContainer {
	height: 35px;
	line-height: 35px;
}

.f-l {
	margin-left:100px;
	margin-top:10px;
	float: left;
}
.f-l p{
	margin: 0px !important;
}
.f-r {
	float: right;
	margin-right:100px;
	margin-top:20px;
}

.footer a{
	color: #ABABAB;
}
.footer a:hover{
	color: black;
}
</style>


<body>

	<div class="footer">
		<div class="LiquidContainer">
		<div  class="f-l p-2">
			<p>
				<a href="#">源计划源码问答网站</a> © 2016 www.cjplan.cn 版权所有
			</p>
			<p>联系电话：13277986699<span>|</span>联系邮箱：291628828@qq.com</p>
			</div>
			<div class="f-r p-1">
				<a
					href="index.jsp?linknumber=0">主页</a><span>|</span><a
					href="RewardHall?linknumber=1">悬赏大厅</a><span>|</span><a
					href="Download?linknumber=2">下载资源</a><span>|</span><a
					href="Reply?linknumber=3">讨论区</a><span>|</span><a
					href="user/UserInfoCenter.jsp?linknumber=-1">个人信息</a>
			</div>
		</div>
	</div>
</body>
