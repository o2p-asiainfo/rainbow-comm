
package com.linkage.rainbow.util.expr.core.func;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.linkage.rainbow.util.expr.core.DBFunc;
import com.linkage.rainbow.util.expr.core.Expr;
import com.linkage.rainbow.util.expr.core.FuncParse;
import com.linkage.rainbow.util.expr.core.Sign;
import com.linkage.rainbow.util.expr.core.expr.DBVariable;
import com.linkage.rainbow.util.expr.rowset.BaseRow;
import com.linkage.rainbow.util.expr.rowset.CachedRowSet;
import com.linkage.rainbow.util.expr.rowset.DBGroup;
import com.linkage.rainbow.util.expr.util.MathUtil;

/**
 * 函数说明：根据条件表达式，从数据集中取得符合条件的记录中第一行的某一字段值 <br>
 * 语法： dsName.selectOne(selectExp,filterExp) <br>
 * 参数说明：<br>
 * selectExp select字段名 <br>
 * filterExp 过滤表达式 <br>
 * 
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

public class DBSelectOneFunc extends DBFunc {


	/**
     * 
     */
    private static final long serialVersionUID = -8060934138864511629L;
    private static Log log = LogFactory.getLog(DBSelectOneFunc.class);
	private String selectExpStr = null;
	private Expr selectExp = null;
	private String isAutoFilterExpStr = null;
	private Expr isAutoFilterExp = null;
	public DBSelectOneFunc() {
	}
	/**
	 * 从数据集中取出一个字段.
	 */
	public Object calculate() {
		return calculate(null);
	}
	/**
	 * 从数据集中取出一个字段.
	 */
	public Object calculate(Map paraNew) {
//		if(newCS.getColIndex()==13)
//			System.out.println("aaaaaaaaaa");
		if(ds == null)
			return "记录集对象:"+dsName+" 为null,请检查报表设置!";
		
		Object result = null;
		// 字段名
		selectExpStr = para.length > 0&&para[0] != null ? para[0].trim() : null; //.toLowerCase()
		isAutoFilterExpStr = para.length > 2&&para[2] != null ? para[2].trim() : null; //.toLowerCase()
//		if(isAutoFilterExpStr!=null){
//			System.out.println("isAutoFilterExpStr:"+isAutoFilterExpStr);
//		}
		String strFilterExp = null;

		if (selectExpStr != null && selectExpStr.trim().length() > 0) {
			boolean isAutoFilter = true; //是否采用自动过滤功能.
			if(isAutoFilterExpStr!=null){
				try {
					Expr isAutoFilterExp = new Expr(isAutoFilterExpStr,this.report,this.dsName);
					Object tmp = isAutoFilterExp.calculate(paraNew);
					if(tmp != null && tmp.toString().toLowerCase().equals("false")){
						isAutoFilter = false; 
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
			
			List resultRows=null;
			List rows = null;
//			if(isAutoFilter){
//				List groupRows = getGroupEventRows();
//				BaseRow row = getSelectEventRows();
//				rows = cross(groupRows,row);
//			}
			if(rows != null){
				resultRows = rows ;
			} else {
				resultRows = ds.getCachedRows();
			}
			
			//根据过滤表达式,对结果集进行筛选,取得符合条件的记录.
			resultRows = getFilterResult(resultRows);
			
			if(resultRows != null && resultRows.size()>0){
				BaseRow tmpRow = (BaseRow)resultRows.get(0);
				Object value = getExprCalculate(tmpRow,selectExpStr,paraNew);
				result = value;
			}
		}
		return result;
	}
	
	
	/**
	 * 计算表达式
	 * @param expStr
	 * @return
	 */
	public Object getExprCalculate(BaseRow row,String expStr,Map paraNew){
		Object result = null;
		if(expStr != null && expStr.trim().length()>0){
			try {
				ds.goTo(row.getIndex());
				Expr expr = new Expr(expStr,this.report,this.dsName);
				result = expr.calculate(paraNew);
			} catch (Exception e) {
				log.error(e.getStackTrace());
			}
		}
		
		return result;
	}
	

	/**
	 * 取得分组事件与select选择事件交叉的记录
	 * @param rows
	 * @param row
	 * @return
	 */
	private List cross(List rows,BaseRow row){
		List result =null;
		if(rows ==null && row==null){
			result =null;
		} else if(rows ==null && row!=null){
			result = new ArrayList();
			result.add(row);
		}else if(rows !=null && row==null){
			result = rows;
		} else {
			result = new ArrayList();
			int iSize = rows.size();
			for(int i=0;i<iSize;i++){
				BaseRow tmp = (BaseRow)rows.get(i);
				if(tmp.getIndex()==row.getIndex()){
					result.add(row);
					break;
				}
			}
		}
		
		return result;
	}


	/**
	 * 取得表达式字符串
	 * 
	 * @return 表达式字符串
	 */
    public String getExp(){
    	return "";
    }
}
