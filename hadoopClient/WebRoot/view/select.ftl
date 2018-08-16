<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta content="width=device-width, initial-scale=1" name="viewport" />
		<meta content="" name="description" />
		<meta content="" name="author" />
        <title>Hbase</title>
        
        <link href="/hadoopClient/css/sweetalert.css" rel="stylesheet" type="text/css" />
        <link href="/hadoopClient/css/common.css" rel="stylesheet" type="text/css" />
		
	</head>
	
	<body style="background-color:#FFFFFF;" id="userLoginLogManage">
		<h1><a href="../index.jsp" style="text-decoration:none">Hbase首页_返回首页</a></h1>
	    <hr>
		<h3  style="padding-left:10px">Hbase_Select<small>根据表名查询所有数据</small></h3>
		<div>
    		<form action="/hadoopClient/servlet/BaseServlet?methon=queryAll" method="post">
    			<label  style="padding-left:30px">表名:</label>
				<input type="text" id="tableName1" name="tableName1" placeholder="请输入表名">
			
				<button value="提交" >提交</button>
    		</form>
		</div>
		
		<hr>
		<h3  style="padding-left:10px">Hbase_Select_<small>根据主语模糊递归查询_普通版</small></h3>
		<div>
    		<form action="/hadoopClient/servlet/BaseServlet?methon=queryAllByRowkeyS" method="post">
    			<label style="padding-left:30px">表名:</label>
				<input type="text" id="tableName2" name="tableName2" placeholder="请输入表名"><br>
				<label style="padding-left:30px">行键:</label>
				<input type="text" id="rowKeyS" name="rowKeyS" placeholder="请输入行键">
				<button value="提交" >提交</button>
    		</form>
		</div>
		
		<hr>
		<h3  style="padding-left:10px">Hbase_Select_<small>根据主语模糊递归查询_可视化</small></h3>
		<div>
			<label style="padding-left:30px">表名:</label>
			<input type="text" id="tableName3" placeholder="请输入表名"><br>
			<label style="padding-left:30px">行键:</label>
			<input type="text" id="rowKeyS3" placeholder="请输入行键">
			
			<button value="清空" onclick="clearVisualInput()">清空</button>
			<button value="提交" onclick="queryVisual()">提交</button>
		</div>
		
		<!-- 可视化DOM -->
		<div id = "visualResult" >
			<h3  style="padding-left:10px">Hbase_Select_<small>可视化结果:</small></h3>
			<div id="main" style="height:600px;width:900px;border:1px solid #ccc;padding:10px;">
			</div>
		</div>
		<!-- jquery -->
		<script src="/hadoopClient/js/jquery/jquery-3.3.1.min.js" type="text/javascript"></script>
		<!-- <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script> -->
		<!-- echarts-标签式单文件引入 -->
	  	<!-- <script src="/hadoopClient/js/echarts/echarts-all.js" type="text/javascript"></script> -->
  	    <script src="http://echarts.baidu.com/build/dist/echarts-all.js"></script>
	  	
		<!-- echarts-模块化单文件引入 -->
	  	<!-- <script src="/hadoopClient/js/echarts/echarts.js" type="text/javascript"></script> -->
		<!-- 弹出框 -->
		<script src="/hadoopClient/js/showAlert/showAlert.js" type="text/javascript"></script>
		<script src="/hadoopClient/js/showAlert/sweetalert.min.js" type="text/javascript"></script>
		<!--js-->
		<script src="/hadoopClient/js/select.js" type="text/javascript"></script>
	</body>
</html>
