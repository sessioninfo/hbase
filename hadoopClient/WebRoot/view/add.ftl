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
		<h3 style="padding-left:10px">Hbase_Add_<small>新增单条数据</small></h3>
		<div>
			<label style="padding-left:30px">表名:</label>
			<input type="text" id="tableName" placeholder="请输入表名"><br>
			<label style="padding-left:30px">列族:</label>
			<input type="text" id="familyName" placeholder="请输入列族"><br>
			<label style="padding-left:30px">主语:</label>
			<input id="rowKeyS" type="text"  placeholder="请输入主语"/><br>
			<label style="padding-left:30px">谓语:</label>
			<input id="colValP" type="text"  placeholder="请输入谓语"/><br>
			<label style="padding-left:30px">宾语:</label>
			<input id="colValO" type="text"  placeholder="请输入宾语"/>
		
			<button id="submit1" value="清空" onclick="clearInput()">清空</button>
			<button id="submit2" value="提交" onclick="insertData()">提交</button>
		</div>
		
		<h3 style="padding-left:10px">Hbase_Add_<small>批量导入数据</small></h3>
		<div>
			<label style="padding-left:30px">表名:</label>
			<input type="text" id="tableName2" placeholder="请输入表名"><br>
			<label style="padding-left:30px">列族:</label>
			<input type="text" id="familyName2" placeholder="请输入列族"><br>
			<label style="padding-left:30px">路径:</label>
			<input type="text" id="filePath2"  placeholder="请输入要导入txt文件路径"/>
		
			<button value="清空" onclick="clearInput2()">清空</button>
			<button value="提交" onclick="importFile()">提交</button>
		</div>
		
		<h3 style="padding-left:10px">Hbase_Add_<small>批量导入数据_服务器版本</small></h3>
			<div>
			<form action="/hadoopClient/servlet/BaseServlet?methon=upload" method="post" enctype="multipart/form-data">
				<label style="padding-left:30px">上传:</label>			
				<input type="file" name="file" ><br>
				<label style="padding-left:30px">表名:</label>
				<input type="text" name="tableName" placeholder="请输入表名"><br>
				<label style="padding-left:30px">列族:</label>
				<input type="text" name="familyName" placeholder="请输入列族">
				<input type="submit" value="提交">
			</form>
		</div>
		
		<!-- jquery -->
		<script src="/hadoopClient/js/jquery/jquery-3.3.1.min.js" type="text/javascript"></script>
		<!-- <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script> -->
		<!-- 弹出框 -->
		<script src="/hadoopClient/js/showAlert/showAlert.js" type="text/javascript"></script>
		<script src="/hadoopClient/js/showAlert/sweetalert.min.js" type="text/javascript"></script>
		<!--js-->
		<script src="/hadoopClient/js/add.js" type="text/javascript"></script>
	</body>
	
</html>
