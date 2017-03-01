
package com.linkage.rainbow.util.expr.core.expr;

import java.util.Map;

import com.linkage.rainbow.util.expr.model.Report;
import com.linkage.rainbow.util.expr.rowset.CachedRowSet;

/**
 * 对象变量
 * @version 1.0
 * @author 陈亮 2011-03-04
 *         <hr>
 *         修改记录
 *         <hr>
 *         1、修改人员:陈亮 修改时间:2011-03-04<br>
 *         修改内容:新建
 *         <hr>
 *
 */
public class ObjVariable  extends Variable{
	/**
     * 
     */
    private static final long serialVersionUID = -2192970685009642611L;
	private Map para;
	private String name;
	private Object value;
	private boolean isValue;
	
	/**
	 * 空构造
	 *
	 */
	public ObjVariable(){
		
	}
	
	public ObjVariable(String value)
    {
		this.value = value;    
    }
	
	public ObjVariable(String name,String value)
    {
		this.name = name;
		this.value = value;    
    }
	
	public ObjVariable(Map para,String name)
    {
		this.para = para;
		this.name = name;
    }
	
	/**
	 * 计算数据记录集变量结果值
	 */
    public Object calculate()
    {
    	return calculate(null);
    }
	/**
	 * 计算数据记录集变量结果值
	 */
    public Object calculate(Map paraNew)
    {
      
    	Object resultObj = null;
    	if(paraNew != null && name !=null){
    		resultObj = paraNew.get(name);
    	}else{
	       if(para != null && name !=null)
	    	   resultObj = para.get(name);
	       else 
	    	   resultObj = value;
    	}
       return resultObj;
    }
    
    /**
     * 取得表达式字符串
     */
    public String getExp(){
    	return "$"+name;
    }
    
    /**
     * 取得数据记录集变量值
     */
	public  Object getValue(){
		return calculate();
	}
	/**
	 * 设置数据记录集变量值
	 * @param value 数据记录集变量值
	 */
	public void setValue(Object value) {
		this.value = value;
	}
}
