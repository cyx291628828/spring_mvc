<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style type="text/css">
.myBody {
	float: left;
	margin-top: 80px;
	margin-left: 100px;
	width: 200px;
}

.myLeft {
	border: 1px solid gray;
	border-radius: 5px;
}

.btnDiv {
	background-color: #208d4e;
	padding: 8px 15px;
}

.myrank {
	margin-top: 50px;
}
span img{
	width: 20px;
	height: 20px;
}
</style>
<script type="text/javascript">
	$(function() {
			//$(".top").append("<marquee height='180px' onmouseout='this.start()' behavior='scroll' direction='up' onmouseover='this.stop()'>");
			$.post('Rank', function(result, status) {
				$(".Rank").append(result);
			});
	});
</script>
<body>
<div class="myBody">
	<div class="myLeft">
		<ul class="nav nav-pills nav-stacked">
			<div class="btnDiv">悬赏大厅</div>
			<li role="presentation"><a href="RewardHall?linknumber=1">所有任务</a>
			</li>
			<li role="presentation"><a
				href="MyAnswered?linknumber=1">我已回答</a>
			</li>
			<li role="presentation"><a
				href="MyQuestion?linknumber=1">我的发布</a>
			</li>
			<li role="presentation"><a
				href="MyCollect?linknumber=1">我的收藏</a>
			</li>
			<li role="presentation"><a
				href="getGenre?linknumber=1">我要发布</a>
			</li>
			
		</ul>

	</div>
	<div class="myrank form-group panel panel-default">
		<div class="panel-heading">
			<h4 style="margin-bottom: -5px"><img src="img/rank.ico" style="position: relative;top:-7px;"> &nbsp;排行榜</h4>
		</div>
		<ul class="list-group Rank">
		</ul>
	</div>
</div>
</body>