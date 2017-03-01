
package com.linkage.rainbow.util.expr.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.linkage.rainbow.util.StringUtil;
import com.linkage.rainbow.util.expr.model.Report;

import com.linkage.rainbow.util.expr.rowset.BaseRow;
import com.linkage.rainbow.util.expr.rowset.CachedRowSet;
import com.linkage.rainbow.util.expr.rowset.DBGroup;

/**
 * 函数
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
public abstract class Func extends Sign {
	
	/**
     * 
     */
    private static final long serialVersionUID = 1541149001420687017L;

	/**报表请求类*/
	protected Report report;

	/**记录集*/
	protected CachedRowSet ds;
	/**记录集Map*/
	protected Map dsMap;

	/**记录集名称*/
	protected String dsName;
	/**所有记录集名称*/
	protected String dsNames[];

	// protected ArrayList paramList = new ArrayList();
	/**函数所有参数*/
	protected String para[];

	public Func() {
	}
	/**根据字符中建立函数*/
	public Func(String strPara) {
		para = FuncParse.getSubPara(strPara,false);
	}
	/**
	 * 函数计算结果
	 */
	public abstract Object calculate();

	/**
	 * 取得记录集
	 * @return 记录集
	 */
	public CachedRowSet getDs() {
		return ds;
	}

	/**
	 * 设置记录集
	 * @param ds 记录集
	 */
	public void setDs(CachedRowSet ds) {
		this.ds = ds;
	}




	/**
	 * 设置函数参数.
	 * 
	 * @param para 函数参数数组
	 * @throws Exception 
	 */
	protected void setParameter(String para[]) throws Exception {
		this.para = para;
	}

	/**
	 * 设置函数参数.
	 * 
	 * @param para 函数参数,通过,号分隔多个参数
	 * @throws Exception
	 */
	public void setParameter(String strPara) throws RuntimeException {
		para = FuncParse.getSubPara(strPara,false);
	}

	/**
	 * 取得记录集名称
	 * @return 记录集名称
	 */
	public String getDsName() {
		return dsName;
	}

	/**
	 * 设置记录集名称
	 * @param dsName 记录集名称
	 */
	public void setDsName(String dsName) {
		this.dsName = dsName;
	}

	/**
	 * 取得报表请求类
	 * @return 报表请求类
	 */
	public Report getReport() {
		return report;
	}

	/**
	 * 设置报表请求类
	 * @param report 报表请求类
	 */
	public void setReport(Report report) {
		this.report = report;
	}

	/**
	 * 取得记录集Map
	 * @return 记录集Map
	 */
	public Map getDsMap() {
		return dsMap;
	}

	/**
	 * 设置记录集Map
	 * @param dsMap 记录集Map
	 */
	public void setDsMap(Map dsMap) {
		this.dsMap = dsMap;
	}

	/**
	 * 取得记录集名称数组
	 * @return 记录集名称数组
	 */
	public String[] getDsNames() {
		return dsNames;
	}

	/**
	 * 设置记录集名称数组
	 * @param dsNames 记录集名称数组
	 */
	public void setDsNames(String[] dsNames) {
		this.dsNames = dsNames;
	}
	
	/**
	 * 取得子参数,如果变量为[参数1,参数2,...]的方式,则转为参数数组
	 * 
	 * @return 参数数组
	 */
	protected String[] getSubPara(String para) {
		return FuncParse.getSubPara(para);
	}
	
	
	
	/**
	 * 表达式结果转换为字符串,如果表达式结果为List,则将取得各元素的值用,号相加
	 * @param calculateExpResult
	 * @return
	 */
	protected String resultToStr(Object calculateExpResult ){
		String result = null;
		if(calculateExpResult != null){
			if(calculateExpResult instanceof List){
				List list = ((List)calculateExpResult);
				String str="";
				int iSize = ((List)list).size();
				for(int i=0;i<iSize;i++){
					if(i>0)
						str = str +",";
					Object tmp = ((List)list).get(i);
					if(tmp instanceof DBGroup){
						str =str +StringUtil.valueOf(((DBGroup)tmp).getValue());
					}else if(tmp instanceof BaseRow){
						str =str +StringUtil.valueOf(((BaseRow)tmp).getValue());
					}else{
						str =str +StringUtil.valueOf(tmp);
					}
				}
				calculateExpResult = str;
				
			}
			result=calculateExpResult.toString().trim();
		}
		
		return result;
	}
}
