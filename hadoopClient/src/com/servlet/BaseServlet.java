package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Entity.Medicine;
import com.java.OperateTable;


/**
 * 
 * 简介：BaseServlet基础层<BR/>
 *
 * 描述：BaseServlet基础层<BR/>
 *
 * @author  bxs
 *
 * @since JDK1.7
 *
 * @version  V1.00
 *
 * @date 2018年3月01日
 */
public class BaseServlet extends HttpServlet {

	private static final long serialVersionUID = -9151030491707013435L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request,response);
    }

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String methon = request.getParameter("methon");
    	
    	if("createTable".equals(methon))
    	{
    		String json = OperateTable.createTable(request);
    		resultMethon(response, json);
    	}
    	else if("insertData".equals(methon))
    	{
    		String json = OperateTable.insertData(request);
    		resultMethon(response, json);
    	}
    	else if("importFile".equals(methon))
    	{
    		String json = OperateTable.importFile(request);
    		resultMethon(response, json);
    	}
    	else if("upload".equals(methon))
    	{
    		String json = OperateTable.upload(request, response);
    		resultMethon(response, json);
    	}
    	else if("dropTableByTableName".equals(methon))
    	{ 
    		String json = OperateTable.dropTableByTableName(request);
    		resultMethon(response, json);
    	}
    	else if("deleteByRowkey".equals(methon))
    	{
    		String json = OperateTable.deleteByRowkey(request);
    		resultMethon(response, json);
    	}
    	else if("queryAll".equals(methon))
    	{
    		request.removeAttribute("queryAllList");
    		String tableName = request.getParameter("tableName1");
    		List<Medicine> list = OperateTable.queryAll(tableName);
    		request.setAttribute("queryAllList",list);
    		RequestDispatcher dispatcher=request.getRequestDispatcher("/view/jsp/queryAll.jsp");
            dispatcher.forward(request,response);
    	}
    	else if("queryAllByRowkeyS".equals(methon))
    	{
    		request.removeAttribute("queryAllByRowkeySList");
    		String tableName = request.getParameter("tableName2");
    		String rowKeyS = request.getParameter("rowKeyS");
    		List<Medicine> list = OperateTable.queryAllByRowkeyS(tableName,rowKeyS);
    		request.setAttribute("queryAllByRowkeySList",list);
    		RequestDispatcher dispatcher=request.getRequestDispatcher("/view/jsp/queryAllByRowkeyS.jsp");
            dispatcher.forward(request,response);
    	}
    	else if("queryVisual".equals(methon))
    	{
    		String json = OperateTable.queryVisual(request);
    		resultMethon(response, json);
    	}
    	else
    	{
    		return;
    	}
    	
    }

	private void resultMethon(HttpServletResponse response, String json) throws IOException {
		response.setContentType("application/json;charset=utf-8");  
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json);
		out.close();
	}

}
