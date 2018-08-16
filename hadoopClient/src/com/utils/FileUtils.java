package com.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtils {
	public static <V> List<Map<String, Object>> readFile(String filePath) throws Exception {
		BufferedReader br= new BufferedReader(new FileReader(filePath));
		List<String> list = new ArrayList<String>();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		String turn = "";
		while ((turn = br.readLine()) != null) 
		{
			list.add(turn);
		}
		
		for (int i = 0; i < list.size(); i++) 
		{
			String str = list.get(i);
			// 1.获得带尖括号主语后去尖括号赋值给s
			String s =str.substring(str.indexOf('<')+1, (str.indexOf('>')));
			// 2.获得带尖括号谓语后去尖括号赋值给p
			String p =str.substring(str.indexOf('<',2)+1, (str.indexOf('>',str.indexOf('<',2))));
			
			String o = "";
			// 3.判断宾语符号类型
			if(str.indexOf('"') == -1)
			{
				// 3.1获得带尖括号宾语后去尖括号赋值给o
				o =str.substring(str.lastIndexOf('<')+1, (str.lastIndexOf('>')));
			}
			else
			{
				// 3.2获得带双引号宾语后去双引号赋值给o
				o = str.substring(str.indexOf('"'), (str.lastIndexOf('"')+1));
			}
			map.put("s", s);
			map.put("p", p);
			map.put("o", o);
			resultList.add(map);
		}
		
		br.close();
		return resultList;
	}
	
	public static void main(String[] args) throws Exception {
 
	}
	
}
