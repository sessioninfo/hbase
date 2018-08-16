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
		<h3 style="padding-left:10px">Hbase_Create_<small>创建表</small></h3>
		<div>
			<label style="padding-left:30px">表名:&nbsp;</label>
			<input type="text" id="tableName" placeholder="请输入表名"><br>
			<label style="padding-left:30px">列族:&nbsp;</label>
			<input type="text" id="familyName" placeholder="请输入列族">&nbsp;
			
			<button value="清空" onclick="clearInput()">清空</button>
			<button value="提交" onclick="createTable()">提交</button>
		</div>
		
		<!-- jquery -->
		<script src="/hadoopClient/js/jquery/jquery-3.3.1.min.js" type="text/javascript"></script>
		<!-- <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script> -->
		<!-- 弹出框 -->
		<script src="/hadoopClient/js/showAlert/showAlert.js" type="text/javascript"></script>
		<script src="/hadoopClient/js/showAlert/sweetalert.min.js" type="text/javascript"></script>
		<!--js-->
		<script src="/hadoopClient/js/create.js" type="text/javascript"></script>
	</body>
</html>
