
package com.linkage.rainbow.util.expr.core.expr;

import java.util.Map;

import com.linkage.rainbow.util.expr.util.MathUtil;

/**
 * > 操作
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
public class BigThen extends Operator{
    /**
     * 
     */
    private static final long serialVersionUID = -2014103088972962585L;

    public BigThen()
    {
        pri = 8;
    }
    
    /**
     * 对操作符的左计算树与右计算树进行 bigThen计算
     */
    public Object calculate(){
    	return calculate(null);   
    }
    /**
     * 对操作符的左计算树与右计算树进行 bigThen计算
     */
    public Object calculate(Map para)
    {
        if(left == null)
            throw new RuntimeException("大于操作缺少左操作数");
        if(right == null)
            throw new RuntimeException("大于操作缺少右操作数");
        if(MathUtil.compare(left.calculate(para), right.calculate(para)) > 0)
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }

    /**
     * 取得表达式字符串
     */
    public String getExp()
    {
        if(left == null)
            throw new RuntimeException("大于操作缺少左操作数");
        if(right == null)
            throw new RuntimeException("大于操作缺少右操作数");
        else
            return "(" + left.getExp() + ">" + right.getExp() + ")";
    }
}
