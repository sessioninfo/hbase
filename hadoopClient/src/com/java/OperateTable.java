package com.java;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryPrefixComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

import com.Entity.Link;
import com.Entity.Medicine;
import com.Entity.Node;
import com.utils.FileUtils;

/**
 * 
 * 简介：Java在Hbase中的增删改查方法类<BR/>
 *
 * 描述：用于操作Hbase数据库中的增删改查<BR/>
 *
 * @author  bxs
 *
 * @since JDK1.7
 *
 * @version  V1.00
 *
 * @date 2018年3月01日
 */
@SuppressWarnings("deprecation")
public class OperateTable {
	
	public static Logger logger = Logger.getLogger(OperateTable.class);

    public static Configuration conf;
    public static HBaseAdmin hBaseAdmin;
    public static HTable hTable;
    static 
    {
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "bxs");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
    }

    
    /**
     * 功能描述：创建表
     * 
     * @param tableName
     * 
     * @author bxs
     * 
     * @date 2018年3月01日
     *
     */
	@SuppressWarnings("resource")
	public static String createTable(HttpServletRequest request) 
    {
    	JSONObject json = new JSONObject();
        try 
        {
        	String tableName = request.getParameter("tableName");
        	String familyName = request.getParameter("familyName");
        	
        	if(tableName == "" || tableName == null)
        	{
        		json.put("status", "fail");
            	json.put("message", "表名不能为空!");
            	return json.toString();
        	}
        	
        	if(familyName == "" || familyName == null)
        	{
        		json.put("status", "fail");
            	json.put("message", "列族不能为空!");
            	return json.toString();
        	}
        	
        	HBaseAdmin hBaseAdmin = new HBaseAdmin(conf);
            if (hBaseAdmin.tableExists(tableName)) 
            {
            	json.put("status", "fail");
            	json.put("message", "表名已存在!");
            	return json.toString();
            }
            
            HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
            tableDescriptor.addFamily(new HColumnDescriptor(familyName));
            hBaseAdmin.createTable(tableDescriptor);
            json.put("status", "success");
            json.put("message", "创建表成功!");
            
        } 
        catch (Exception e) 
        {
        	json.put("status", "error");
            json.put("message", "创建表异常!");
        	logger.error("[创建表异常-ErrorMsg:]", e);
        }
        
    	return json.toString();
    }

    /**
     * 新增-单条数据
     */
	@SuppressWarnings("resource")
	public static String insertData(HttpServletRequest request) 
    {
		JSONObject json = new JSONObject();
		
        String tableName = request.getParameter("tableName");
		String familyName = request.getParameter("familyName");
		String rowKeyS = request.getParameter("rowKeyS");
		String colValP = request.getParameter("colValP");
		String colValO = request.getParameter("colValO");
		
		if(tableName == "" || tableName == null)
    	{
    		json.put("status", "fail");
        	json.put("message", "表名不能为空!");
        	return json.toString();
    	}
		
		if(familyName == "" || familyName == null)
    	{
    		json.put("status", "fail");
        	json.put("message", "列族不能为空!");
        	return json.toString();
    	}
		
		if(rowKeyS == "" || rowKeyS == null)
		{
			json.put("status", "fail");
			json.put("message", "主语不能为空!");
			return json.toString();
		}
		
		if(colValP == "" || colValP == null)
		{
			json.put("status", "fail");
			json.put("message", "谓语不能为空!");
			return json.toString();
		}
		
		if(colValO == "" || colValO == null)
		{
			json.put("status", "fail");
			json.put("message", "宾语不能为空!");
			return json.toString();
		}
		
		try 
		{
        	HBaseAdmin hBaseAdmin = new HBaseAdmin(conf);
			if (!hBaseAdmin.tableExists(tableName)) 
			{
				json.put("status", "fail");
				json.put("message", "数据库中不存在该表!");
				return json.toString();
			}
			else
			{
				// 客户端-表数据管理
				HTable hTable = new HTable(conf, tableName);
				Random random = new Random();
				Date date = new Date();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
				String time = simpleDateFormat.format(date);
				int numFive = (int) (random.nextDouble() * (90000)) + 10000;
				rowKeyS = rowKeyS+"_"+time+"_"+numFive;
				
				Put p = new Put(Bytes.toBytes(rowKeyS));
		        p.add(Bytes.toBytes(familyName), Bytes.toBytes(colValP.toCharArray()[0]), Bytes.toBytes(colValP));
		        hTable.put(p);
		        
		        Put o = new Put(Bytes.toBytes(rowKeyS));
		        o.add(Bytes.toBytes(familyName), Bytes.toBytes("o"), Bytes.toBytes(colValO));
		        //o.add(Bytes.toBytes(familyName), Bytes.toBytes(colValO.toCharArray()[1]), Bytes.toBytes(colValO));
		        hTable.put(o);
		        
		        json.put("status", "success");
				json.put("message", "新增单条数据成功!");
				
		        hTable.close();
			}
        } 
        catch (Exception e) 
        {
        	logger.error("[新增_Exception-ErrorMsg:]", e);
        }
		
		return json.toString();
    }
	
	/**
	 * 新增-批量导入数据_本地版本
	 */
	 /**
     * 新增-批量导入数据
     */
	@SuppressWarnings("resource")
	public static String importFile(HttpServletRequest request) 
    {
		JSONObject json = new JSONObject();
        String tableName = request.getParameter("tableName");
		String familyName = request.getParameter("familyName");
		String filePath = request.getParameter("filePath");
		
		if(tableName.isEmpty())
    	{
    		json.put("status", "fail");
        	json.put("message", "表名不能为空!");
        	return json.toString();
    	}
		
		if(familyName.isEmpty())
    	{
    		json.put("status", "fail");
        	json.put("message", "列族不能为空!");
        	return json.toString();
    	}
		
		if(filePath.isEmpty())
		{
			json.put("status", "fail");
			json.put("message", "txt文件路径不能为空!");
			return json.toString();
		}
		
		try 
		{
        	HBaseAdmin hBaseAdmin = new HBaseAdmin(conf);
			if (!hBaseAdmin.tableExists(tableName)) 
			{
				json.put("status", "fail");
				json.put("message", "数据库中不存在该表!");
				return json.toString();
			}
			
			List<Map<String, Object>> resultList = FileUtils.readFile(filePath);
			
			if(resultList.size() <= 0)
			{
				json.put("status", "fail");
				json.put("message", "txt文件读取内容为空!");
				return json.toString();
			}
			
			// 客户端-表数据管理
			HTable hTable = new HTable(conf, tableName);
			Random random = new Random();
			
			String rowKeyS = "";
			String colValP = "";
			String colValO = "";
			
			for (Map<String, Object> map : resultList) 
			{
				for (String key : map.keySet()) 
				{
					if("s".equals(key))
					{
						rowKeyS = (String) map.get(key);
					}
					else if("p".equals(key))
					{
						colValP = (String) map.get(key);
					}
					else if("o".equals(key))
					{
						colValO = (String) map.get(key);
					}
				}

				Date date = new Date();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
				String time = simpleDateFormat.format(date);
				int numFive = (int) (random.nextDouble() * (90000)) + 10000;
				
				if(!rowKeyS.isEmpty())
				{
					rowKeyS = rowKeyS + "_" + time + "_" + numFive;
				}
				else
				{
					json.put("status", "fail");
		        	json.put("message", "主语不能为空!");
		        	return json.toString();
				}
				
				if(!colValP.isEmpty())
				{
					Put p = new Put(Bytes.toBytes(rowKeyS));
			        p.add(Bytes.toBytes(familyName), Bytes.toBytes("p"), Bytes.toBytes(colValP));
			        hTable.put(p);
				}
				else
				{
					json.put("status", "fail");
		        	json.put("message", "谓语不能为空!");
		        	return json.toString();
				}
				
				if(!colValO.isEmpty())
				{
					Put o = new Put(Bytes.toBytes(rowKeyS));
			        o.add(Bytes.toBytes(familyName), Bytes.toBytes("o"), Bytes.toBytes(colValO));
			        hTable.put(o);
				}
				else
				{
					json.put("status", "fail");
		        	json.put("message", "宾语不能为空!");
		        	return json.toString();
				}
				
				json.put("status", "success");
	        	json.put("message", "批量导入数据成功!");
	        	
		        hTable.close();
			}
        } 
        catch (Exception e) 
        {
        	logger.error("[新增_Exception-ErrorMsg:]", e);
        }
		
		return json.toString();
    }
	
	 /**
     * 新增-批量导入数据_服务器版本
     */
	@SuppressWarnings({ "unchecked"})
	public static String upload(HttpServletRequest request, HttpServletResponse response) 
    {
		JSONObject json = new JSONObject();
		String tableName = "";
		String familyName = "";
		String filePath = "";
		
		try 
        {
			//PrintWriter pw = response.getWriter();
			//-------------------------上传部分开始-------------------------
			// 创建工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
             
            // 创建fileUpload组件
            ServletFileUpload fileupload = new ServletFileUpload(factory);
            fileupload.setHeaderEncoding("utf-8");
            
            // 解析request
            List<FileItem> fileItems = fileupload.parseRequest(request);
            
            // 遍历集合
            for(FileItem fileItem:fileItems)
            {
                // 判断是否为普通字段
                if(fileItem.isFormField())
                {
                    // 获得字段名和字段值
                    String name = fileItem.getFieldName();
                    String value = fileItem.getString("utf-8");
                    if("tableName".equals(name))
                    {
                    	tableName = value;
                    }
                    else if("familyName".equals(name))
                    {
                    	familyName = value;
                    }
                }
                else
                {
                	// 上传的文件路径,根据浏览器的不同会有区别
                    String fileName = fileItem.getName();
                    //pw.println("文件来源"+fileName+"<br>");
                    // 截取出文件名
                    fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
                    //pw.print("成功上传文件："+fileName+"<br>");
                    
                    // 文件名唯一
                    //fileName = UUID.randomUUID().toString().replace("-", "")+"_"+fileName;
                     
                    // 在服务器创建同名文件
                    String webPath = "/upload/";
                    // 分离目录
                    String hexString =Integer.toHexString(fileName.hashCode());
                    String path = hexString.charAt(0) + "/" + hexString.charAt(1);
                    filePath = request.getSession().getServletContext().getRealPath(webPath+path+fileName);
                    System.out.println("服务器文件路径:"+filePath);
                    
                    //创建文件
                    File file = new File(filePath);
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                    
                    //获得上传文件流
                    InputStream in = fileItem.getInputStream();
                    //获得写入文件流
                    OutputStream out = new FileOutputStream(file);
                    //流的对拷
                    IOUtils.copy(in,out);

                    //释放资源
                    IOUtils.closeQuietly(in);
                    IOUtils.closeQuietly(out);
                     
                    fileItem.delete();
                    //-------------------------上传部分结束-------------------------
                }
            }
            
            //-------------------------读取导入开始-------------------------
            if(tableName.isEmpty() || tableName == null)
        	{
        		json.put("status", "fail");
            	json.put("message", "表名不能为空!");
            	return json.toString();
        	}
    		
    		if(familyName.isEmpty() || familyName == null)
        	{
        		json.put("status", "fail");
            	json.put("message", "列族不能为空!");
            	return json.toString();
        	}
    		
    		HBaseAdmin hBaseAdmin = new HBaseAdmin(conf);
			if (!hBaseAdmin.tableExists(tableName)) 
			{
				json.put("status", "fail");
				json.put("message", "数据库中不存在该表!");
				return json.toString();
			}
    		
            List<Map<String,Object>> resultList = FileUtils.readFile(filePath);
            
            if(resultList.size() <= 0)
			{
				json.put("status", "fail");
				json.put("message", "txt文件读取内容为空!");
				return json.toString();
			}
			
			// 客户端-表数据管理
			HTable hTable = new HTable(conf, tableName);
			Random random = new Random();
			
			String rowKeyS = "";
			String colValP = "";
			String colValO = "";
			
			for (Map<String, Object> map : resultList) 
			{
				for (String key : map.keySet()) 
				{
					if("s".equals(key))
					{
						rowKeyS = (String) map.get(key);
					}
					else if("p".equals(key))
					{
						colValP = (String) map.get(key);
					}
					else if("o".equals(key))
					{
						colValO = (String) map.get(key);
					}
				}

				Date date = new Date();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
				String time = simpleDateFormat.format(date);
				int numFive = (int) (random.nextDouble() * (90000)) + 10000;
				
				if(rowKeyS != "" && rowKeyS != null)
				{
					rowKeyS = rowKeyS + "_" + time + "_" + numFive;
				}
				else
				{
					json.put("status", "fail");
		        	json.put("message", "主语不能为空!");
		        	return json.toString();
				}
				
				if(colValP != "" && colValP != null)
				{
					Put p = new Put(Bytes.toBytes(rowKeyS));
			        p.add(Bytes.toBytes(familyName), Bytes.toBytes("p"), Bytes.toBytes(colValP));
			        hTable.put(p);
				}
				else
				{
					json.put("status", "fail");
		        	json.put("message", "谓语不能为空!");
		        	return json.toString();
				}
				
				if(colValO != "" && colValO != null)
				{
					Put o = new Put(Bytes.toBytes(rowKeyS));
			        o.add(Bytes.toBytes(familyName), Bytes.toBytes("o"), Bytes.toBytes(colValO));
			        hTable.put(o);
				}
				else
				{
					json.put("status", "fail");
		        	json.put("message", "宾语不能为空!");
		        	return json.toString();
				}
				
				json.put("status", "success");
	        	json.put("message", "批量导入数据成功!");
	        	
		        hTable.close();
			}
			//-------------------------读取导入结束-------------------------
        }
		catch(Exception e)
        {
        	json.put("status", "error");
        	json.put("message", "批量导入数据失败,请检查txt文件及数据格式!");
        	logger.error("[新增_Exception-ErrorMsg:]", e);
        }
		return json.toString();
    }

	
    /**
     * 删除一张表
     */
    @SuppressWarnings("resource")
	public static String dropTableByTableName(HttpServletRequest request) 
    {
    	JSONObject json = new JSONObject();
    	
        try 
        {
        	HBaseAdmin hBaseAdmin = new HBaseAdmin(conf);
    		String tableName = request.getParameter("tableName");
    		if (!hBaseAdmin.tableExists(tableName)) 
    		{
    			json.put("status", "fail");
    			json.put("message", "数据库中不存在该表,无需删除!");
    			return json.toString();
    		}
        	
            HBaseAdmin admin = new HBaseAdmin(conf);
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
            
            json.put("status", "success");
			json.put("message", "删除表成功!");
        } 
        catch (Exception e) 
        {
        	json.put("status", "error");
        	json.put("message", "删除表异常!");
        	logger.error("[删除表异常-ErrorMsg:]", e);
        }
        
        return json.toString();
    }

    /**
     * 根据 rowkey删除一条记录
     */
    @SuppressWarnings({ "unchecked", "rawtypes", "resource" })
	public static String deleteByRowkey(HttpServletRequest request) 
    {
    	JSONObject json = new JSONObject();
    	
        try 
        {
        	HBaseAdmin hBaseAdmin = new HBaseAdmin(conf);
        	String tableName = request.getParameter("tableName");
    		String rowkey = request.getParameter("rowkey");
        	if (!hBaseAdmin.tableExists(tableName)) 
    		{
    			json.put("status", "fail");
    			json.put("message", "数据库中不存在该表!");
    			return json.toString();
    		}
        	
            HTable table = new HTable(conf, tableName);
            List list = new ArrayList();
            Delete d1 = new Delete(rowkey.getBytes());
            list.add(d1);
            table.delete(list);
            json.put("status", "success");
			json.put("message", "删除rowkey成功!");
        } 
        catch (Exception e) 
        {
        	json.put("status", "error");
        	json.put("message", "删除rowkey异常!");
        	logger.error("[删除rowkey异常-ErrorMsg:]", e);
        }
        
        return json.toString();
    }

    /**
     * 根据表名查询所有数据
     */
	@SuppressWarnings("resource")
	public static List<Medicine> queryAll(String tableName) 
    {
    	List<Medicine> list = new ArrayList<Medicine>();
    	
        try 
        {
        	Medicine me;
        	HTable hTable = new HTable(conf, tableName);
            ResultScanner rs = hTable.getScanner(new Scan());
            for (Result r : rs) 
            {
            	me = new Medicine();
            	me.setRowKeyS(new String(r.getRow()));
                for (int i = 0; i < r.raw().length; i++) 
				{
					KeyValue keyValue = r.raw()[i];
					if (i != 0) 
					{
						me.setColValO(new String(keyValue.getValue()));
					} 
					else 
					{
						me.setColValP(new String(keyValue.getValue()));
					}
				}
                list.add(me);
            }
        } 
        catch (Exception e) 
        {
        	logger.error("[根据表名查询所有数据_Exception-ErrorMsg:]", e);
        }
		
        return list;
        
    }
    
    /**
     * 根据主语模糊递归查询
     */
    @SuppressWarnings("resource")
	public static <E> List<Medicine> queryAllByRowkeyS(String tableName, String rowKeyS) 
    {
    	List<Medicine> list = new ArrayList<Medicine>();
    	
        try 
        {
        	Medicine me;
        	HTable hTable = new HTable(conf, tableName);
        	Scan scan = new Scan();
			RowFilter filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new BinaryPrefixComparator(Bytes.toBytes(rowKeyS)));
			scan.setFilter(filter);
            ResultScanner rs = hTable.getScanner(scan);
            
            for (Result r : rs) 
            {
            	me = new Medicine();
            	me.setRowKeyS(new String(r.getRow()));
            	/**
            	 * 根据主语条件查询得到查询结果r，根据角标i获取每个角标数据
            	 * keyvalues={1s1_2018-04-10-17:10:36_14536/po:o/1523351405712/Put/vlen=3/seqid=0, 1s1_2018-04-10-17:10:36_14536/po:p/1523351405703/Put/vlen=3/seqid=0}
            	 */
                for (int i = 0; i < r.raw().length; i++) 
				{
                	/**
                	 * 当角标i为0时：keyvalues=1s1_2018-04-10-17:10:36_14536/po:o/1523351405712/Put/vlen=3/seqid=0
                	 */
					KeyValue keyValue = r.raw()[i];
					if (i != 0) 
					{
						// 进入角标为0的判断
						// 当i为0时，获得宾语的值：ColValO=1o1
						String ColValO = new String(keyValue.getValue());
						me.setColValO(ColValO);
						
						Medicine me2;
			            List<Medicine> list2 = new ArrayList<Medicine>();
						Scan scan2 = new Scan();
						RowFilter filter2 = new RowFilter(CompareFilter.CompareOp.EQUAL, new BinaryPrefixComparator(Bytes.toBytes(ColValO)));
						scan2.setFilter(filter2);
			            ResultScanner rs2 = hTable.getScanner(scan2);
			            
			            /**
			             * 第一次r2的值为
			             * keyvalues={1o12_2018-04-10-17:11:51_34497/po:o/1523351479781/Put/vlen=3/seqid=0, 
			             * 			  1o12_2018-04-10-17:11:51_34497/po:p/1523351479778/Put/vlen=3/seqid=0}
			             * 这是根据第一次查询的结果中的宾语1o1，作为主语再次进行查询得到的结果，这个结果主语尾号为34497的两个值。
			             */
			            for (Result r2 : rs2) 
			            {
			            	me2 = new Medicine();
		            		for (int j = 0; j < r2.raw().length; j++) 
			 				{
		            			me2.setRowKeyS(new String(r2.getRow()));
			 					KeyValue keyValue2 = r2.raw()[j];
			 					if (j != 0) 
			 					{
									me2.setColValO(new String(keyValue2.getValue()));
			 					}
			 					else
			 					{
			 						me2.setColValP(new String(keyValue2.getValue()));
			 					}
		 					}
		            		list2.add(me2);
			            }
			            me.setList(list2);
					}
					else 
					{
						me.setColValP(new String(keyValue.getValue()));
					}
				}
                list.add(me);
            }
        } 
        catch (Exception e) 
        {
        	logger.error("[根据主语模糊递归查询_Exception-ErrorMsg:]", e);
        }
        
		return list;
		
    }

    /**
     * 根据主语模糊递归查询_可视化
     */
	@SuppressWarnings("resource")
	public static String queryVisual(HttpServletRequest request) 
	{
		JSONObject json = new JSONObject();
    	List<Medicine> list = new ArrayList<Medicine>();
		
    	String tableName = request.getParameter("tableName");
    	String rowKeyS = request.getParameter("rowKeyS");
    	
        try 
        {
        	Medicine me;
			HTable hTable = new HTable(conf, tableName);
        	Scan scan = new Scan();
			RowFilter filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new BinaryPrefixComparator(Bytes.toBytes(rowKeyS)));
			scan.setFilter(filter);
            ResultScanner rs = hTable.getScanner(scan);
            
            for (Result r : rs) 
            {
            	me = new Medicine();
            	me.setRowKeyS(new String(r.getRow()));
                for (int i = 0; i < r.raw().length; i++) 
				{
					KeyValue keyValue = r.raw()[i];
					if (i != 0) 
					{
						String ColValO = new String(keyValue.getValue());
						me.setColValO(ColValO);
						
						Medicine me2;
			            List<Medicine> list2 = new ArrayList<Medicine>();
						Scan scan2 = new Scan();
						RowFilter filter2 = new RowFilter(CompareFilter.CompareOp.EQUAL, new BinaryPrefixComparator(Bytes.toBytes(ColValO)));
						scan2.setFilter(filter2);
			            ResultScanner rs2 = hTable.getScanner(scan2);
			            
			            for (Result r2 : rs2) 
			            {
			            	me2 = new Medicine();
		            		for (int j = 0; j < r2.raw().length; j++) 
			 				{
		            			me2.setRowKeyS(new String(r2.getRow()));
			 					KeyValue keyValue2 = r2.raw()[j];
			 					if (j != 0) 
			 					{
									me2.setColValO(new String(keyValue2.getValue()));
			 					}
			 					else
			 					{
			 						me2.setColValP(new String(keyValue2.getValue()));
			 					}
		 					}
		            		list2.add(me2);
			            }
			            me.setList(list2);
					}
					else 
					{
						me.setColValP(new String(keyValue.getValue()));
					}
				}
                list.add(me);
            }
        } 
        catch (Exception e) 
        {
        	logger.error("[根据主语模糊递归查询_Exception-ErrorMsg:]", e);
        	json.put("status", "error");
         	json.put("message", "请求异常!");
         	return json.toString();
        }
        
        /**
         * 直接在list结果中建立nodes和links关联关系
         */
        // nodes
    	List<Node> nodes = new ArrayList<Node>();
    	// links
    	List<Link> links = new ArrayList<Link>();
    	
    	// 添加根节点
    	Node node0 = new Node();
		node0.setCategory(0);
		node0.setName(rowKeyS);
		node0.setLabel(rowKeyS);
		node0.setValue(list.size());
		nodes.add(node0);
		
        for (Medicine medicine : list) {
        	String S = medicine.getRowKeyS();
        	String P = medicine.getColValP();
        	String O = medicine.getColValO();
    		
    		// 添加一级节点即主语节点
        	Node node1 = new Node();
    		node1.setCategory(1);
    		node1.setValue(2);
    		node1.setName(S);
    		node1.setLabel(S);
    		nodes.add(node1);
    		// 添加一级节点与根节点关联关系
    		Link link1 = new Link();
        	link1.setSource(S);
        	link1.setTarget(rowKeyS);
    		link1.setWeight(1);
    		link1.setName("行键检索到的主语");
    		links.add(link1);
    		
    		/*// 添加二级节点主语节点
    		Node node2 = new Node();
    		node2.setCategory(2);
    		node2.setValue(1);
    		node2.setName(S);
    		node2.setLabel(S);
    		nodes.add(node2);
    		// 添加二级节点与一级节点关联关系
    		Link link2 = new Link();
    		link2.setSource(S);
    		link2.setTarget(S);
    		link2.setWeight(1);
    		link2.setName("一级下的主语1");
    		links.add(link2);*/
    		
    		// 添加二级节点谓语节点
    		Node node3 = new Node();
    		node3.setCategory(2);
    		node3.setValue(1);
    		node3.setName(P);
    		node3.setLabel(P);
    		nodes.add(node3);
    		// 添加二级节点与一级节点关联关系
    		Link link3 = new Link();
    		link3.setSource(P);
    		link3.setTarget(S);
    		link3.setWeight(1);
    		link3.setName("一级节点下的谓语");
    		links.add(link3);
    		
    		List<Medicine> list2 = medicine.getList();
    		
    		// 添加二级节点宾语节点
    		Node node4 = new Node();
    		node4.setCategory(2);
    		node4.setValue(list2.size());
    		node4.setName(O);
    		node4.setLabel(O);
    		nodes.add(node4);
    		// 添加二级节点与一级节点关联关系
    		Link link4 = new Link();
    		link4.setSource(O);
    		link4.setTarget(S);
    		link4.setWeight(1);
    		link4.setName("一级节点下的宾语");
    		links.add(link4);
    		
    		for (Medicine medicine2 : list2) 
    		{
    			String S2 = medicine2.getRowKeyS();
            	String P2 = medicine2.getColValP();
            	String O2 = medicine2.getColValO();
            	
    			// 添加三级节点主语节点
        		Node node5 = new Node();
        		node5.setCategory(3);
        		node5.setValue(1);
        		node5.setName(S2);
        		node5.setLabel(S2);
        		nodes.add(node5);
        		// 添加三级节点与二级节点关联关系
        		Link link5 = new Link();
        		link5.setSource(S2);
        		link5.setTarget(O);
        		link5.setWeight(1);
        		link5.setName("二级节点下的主语");
        		links.add(link5);
        		
        		// 添加三级节点谓语节点
        		Node node6 = new Node();
        		node6.setCategory(2);
        		node6.setValue(1);
        		node6.setName(P2);
        		node6.setLabel(P2);
        		nodes.add(node6);
        		// 添加三级节点与三级主语节点关联关系
        		Link link6 = new Link();
        		link6.setSource(P2);
        		link6.setTarget(S2);
        		link6.setWeight(1);
        		link6.setName("三级节点下的谓语");
        		links.add(link6);
        		
        		// 添加三级节点与三级宾语节点关联关系
        		Node node7 = new Node();
        		node7.setCategory(2);
        		node7.setValue(1);
        		node7.setName(O2);
        		node7.setLabel(O2);
        		nodes.add(node7);
        		// 添加三级节点与二级节点关联关系
        		Link link7 = new Link();
        		link7.setSource(O2);
        		link7.setTarget(S2);
        		link7.setWeight(1);
        		link7.setName("三级节点下的宾语");
        		links.add(link7);
        		
			}
			
		}
        json.put("list", list);
        json.put("nodes", nodes);
        json.put("links", links);
        json.put("status", "success");
    	json.put("message", "根据主语模糊递归查询_可视化成功!");
    	
		return json.toString();
    }

}
