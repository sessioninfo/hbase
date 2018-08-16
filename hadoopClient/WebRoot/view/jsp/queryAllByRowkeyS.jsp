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
    <h3>Hbase_根据主语模糊递归查询页</h3>
    	<div>
			<c:forEach items="${requestScope.queryAllByRowkeySList}" var="keyword1" varStatus="id">  
				<label><b>一级结果：</b></label><br>
					主语:${keyword1.rowKeyS};
					&nbsp;谓语:${keyword1.colValP};
					&nbsp;宾语:${keyword1.colValO};<br>
					
				<c:forEach items="${keyword1.list}" var="keyword2" varStatus="id2">
					<label><b>&nbsp;&nbsp;&nbsp;二级结果：</b></label><br>
					&nbsp;&nbsp;&nbsp;主语:${keyword2.rowKeyS};
								&nbsp;谓语:${keyword2.colValP};
								&nbsp;宾语:${keyword2.colValO};<br>
				</c:forEach>
				<hr>
			</c:forEach>  
 		</div>
  </body>
</html>
