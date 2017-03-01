
package com.linkage.rainbow.util.expr.core.expr;

import com.linkage.rainbow.util.expr.core.Sign;

/**
 * 操作符基类
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
public abstract class Operator extends Sign{
	
	
	/**
     * 
     */
    private static final long serialVersionUID = -239536827403966414L;

	/**
	 * 是否为操作符，返回true
	 */
    public boolean isOperator()
    {
        return true;
    }

}
