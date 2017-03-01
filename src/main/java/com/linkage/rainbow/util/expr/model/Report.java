package com.linkage.rainbow.util.expr.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.linkage.rainbow.util.expr.rowset.CachedRowSet;

/**
 * 报表请求类<br>
 * <p>
 * @version 1.0
 * @author 陈亮 2011-03-05
 * <hr>
 * 修改记录
 * <hr>
 * 1、修改人员: 陈亮   修改时间:2011-03-05<br>       
 *    修改内容:新建
 * <hr>
 */
public class Report implements Serializable, Cloneable{

	/**
     * 
     */
    private static final long serialVersionUID = 5055810471838940162L;

	/**报表ID*/
	protected String reportId;

	/**数据源配置信息*/
	protected Map dsDef = new HashMap();

	/** 记录集*/
	protected Map ds = new HashMap();

	
	/**报表查询参数 */
	protected Map para = new HashMap();

	/**
	 * 取得报表查询参数
	 * @return 报表查询参数
	 */
	public Map getPara() {
		return para;
	}

	/**
	 * 设置报表查询参数
	 * @param para 报表查询参数
	 */
	public void setPara(Map para) {
		this.para = para;
	}

	/**
	 * 设置报表查询参数
	 * @param id 报表查询参数ID
	 * @param value　报表查询参数值
	 */
	public void setPara(String id, Object value) {
		this.para.put(id, value);
	}

	/**
	 * 根据参数ID取得报表查询参数
	 * @param id 报表查询参数ID
	 * @return 报表查询参数值
	 */
	public Object getPara(String id) {
		return this.para.get(id);
	}

	public Report() {
	}

	/**
	 * 设置报表记录集配置信息
	 * @param strDsName
	 * @param value
	 */
	public void setDsDef(String strDsName, String value) {
		dsDef.put(strDsName, value);

	}

	/**
	 * 取得报表记录集配置信息
	 * @param strDsName
	 * @return
	 */
	public String getDsDef(String strDsName) {
		return (String) dsDef.get(strDsName);

	}

	/**
	 * 设置报表记录集
	 * @param strDsName 记录集名称
	 * @param value 记录集
	 */
	public void setDs(String strDsName, List value) {
		ds.put(strDsName, value);
 
	}
	/**
	 * 设置报表记录集
	 * @param strDsName 记录集名称
	 * @param value 记录集
	 */
	public void setDs(String strDsName, CachedRowSet value) {
		ds.put(strDsName, value);

	}

	/**
	 * 取得报表记录集
	 * @param strDsName 记录集名称
	 * @return 记录集
	 */
	public CachedRowSet getDs(String strDsName) {
		return (CachedRowSet) ds.get(strDsName);

	}

	/**
	 * 取得记录集Ｍap,按记录集名称=>记录集存储
	 * @return 记录集Ｍap,按记录集名称=>记录集存储
	 */
	public Map getDsMap() {
		return ds;
	}

	/**
	 * 取得报表ID
	 * @return 报表ID
	 */
	public String getReportId() {
		return reportId;
	}

	/**
	 * 设置报表ID
	 * @param reportId 报表ID
	 */
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

}