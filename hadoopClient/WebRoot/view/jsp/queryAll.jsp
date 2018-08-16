<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
   	//List list=(List)request.getAttribute("list");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Hbase</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body>
  	<h1><a href="/hadoopClient/index.jsp" style="text-decoration:none">Hbase首页_返回首页</a></h1>
    <hr>
    <h3>Hbase_根据表名查询所有数据结果页</h3>
    	<div>
			<c:forEach items="${requestScope.queryAllList}" var="keyword" varStatus="id">  
				主语：${keyword.rowKeyS}
				谓语：${keyword.colValP}
				宾语：${keyword.colValO}
				<hr>
			</c:forEach>  
 		</div>
  </body>
</html>