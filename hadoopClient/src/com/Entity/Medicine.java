package com.Entity;

import java.util.List;

/**
 * 
 * 简介：封装对象<BR/>
 *
 * @author  bxs
 *
 * @since JDK1.7
 *
 * @version  V1.00
 *
 * @date 2018年3月01日
 */
public class Medicine {
	
    private String rowKeyS;
    private String colValP;
    private String colValO;
    private List<Medicine> list;
    
	public String getRowKeyS() {
		return rowKeyS;
	}
    
	public void setRowKeyS(String rowKeyS) {
		this.rowKeyS = rowKeyS;
	}
	
	public String getColValP() {
		return colValP;
	}
	
	public void setColValP(String colValP) {
		this.colValP = colValP;
	}
	
	public String getColValO() {
		return colValO;
	}
	
	public void setColValO(String colValO) {
		this.colValO = colValO;
	}
	
	public List<Medicine> getList() {
		return list;
	}

	public void setList(List<Medicine> list) {
		this.list = list;
	}
}
