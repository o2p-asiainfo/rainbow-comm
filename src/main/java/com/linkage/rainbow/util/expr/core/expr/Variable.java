
package com.linkage.rainbow.util.expr.core.expr;

import com.linkage.rainbow.util.expr.core.Sign;

/**
 * 变量基类
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
public abstract class Variable extends Sign{

	/**
     * 
     */
    private static final long serialVersionUID = 102220495184756070L;

	/**
	 * 返回变量的结果值
	 * @return  变量的结果值
	 */
	public abstract Object getValue();

}
