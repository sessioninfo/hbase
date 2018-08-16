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
		<h3  style="padding-left:10px">Hbase_Delete_<small>根据表名删除表</small></h3>
		<div>
			<label  style="padding-left:30px">表名:</label>
			<input type="text" id="tableName1" placeholder="请输入要删除的表名">
			
			<button id="submit" value="清空" onclick="clearInput1()">清空</button>
			<button id="submit" value="提交" onclick="dropTableByTableName()">提交</button>
		</div>
		
		<h3  style="padding-left:10px">Hbase_Delete_<small>根据行键key删除一条记录</small></h3>
		<div>
			<label  style="padding-left:30px">表名:</label>
			<input type="text" id="tableName2" placeholder="请输入rowkey的表名"><br>
			<label  style="padding-left:30px">行键:</label>
			<input type="text" id="rowkey2"   placeholder="请输入rowkey的值"/>
			
			<button id="submit1" value="清空" onclick="clearInput2()">清空</button>
			<button id="submit2" value="提交" onclick="deleteByRowkey()">提交</button>
		</div>
		
		<!-- jquery -->
		<script src="/hadoopClient/js/jquery/jquery-3.3.1.min.js" type="text/javascript"></script>
		<!-- <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script> -->
		<!-- 弹出框 -->
		<script src="/hadoopClient/js/showAlert/showAlert.js" type="text/javascript"></script>
		<script src="/hadoopClient/js/showAlert/sweetalert.min.js" type="text/javascript"></script>
		<!--js-->
		<script src="/hadoopClient/js/delete.js" type="text/javascript"></script>
	</body>
</html>
