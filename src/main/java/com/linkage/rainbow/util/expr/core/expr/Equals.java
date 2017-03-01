
package com.linkage.rainbow.util.expr.core.expr;

import java.util.Map;

import com.linkage.rainbow.util.expr.util.MathUtil;
/**
 * 等号运算
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
public class Equals extends Operator{
    /**
     * 
     */
    private static final long serialVersionUID = -7055886973728332573L;

    public Equals()
    {
        pri = 7;
    }
    /**
     * 对操作符的左计算树与右计算树进行相等计算
     */
    public Object calculate(){
    	return calculate(null);
    }
    /**
     * 对操作符的左计算树与右计算树进行相等计算
     */
    public Object calculate(Map para)
    {
        if(left == null)
            throw new RuntimeException("等于操作缺少左操作数");
        if(right == null)
            throw new RuntimeException("等于操作缺少右操作数");
        if(MathUtil.equals(left.calculate(para), right.calculate(para)))
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
            throw new RuntimeException("等于操作缺少左操作数");
        if(right == null)
            throw new RuntimeException("等于操作缺少右操作数");
        else
            return "(" + left.getExp() + "==" + right.getExp() + ")";
    }

}
