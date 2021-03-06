
package com.linkage.rainbow.util.expr.core.expr;

import java.util.Map;


/**
 * 或操作
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
public class Or extends Operator{

    /**
     * 
     */
    private static final long serialVersionUID = -2126113035802887826L;

    public Or()
    {
        pri = 2;
    }
    /**
     * 对操作符的左计算树与右计算树进行 或 计算
     */
    public Object calculate(){
    	return calculate(null);
    }
    /**
     * 对操作符的左计算树与右计算树进行 或 计算
     */
    public Object calculate(Map para)
    {
        if(left == null)
            throw new RuntimeException("or操作缺少左操作数");
        if(right == null)
            throw new RuntimeException("or操作缺少右操作数");
        Object value = left.calculate(para);
        if(value instanceof Boolean)
        {
            if(Boolean.TRUE.equals(value))
                return Boolean.TRUE;
        } else
        {
            throw new RuntimeException("or操作的左操作数应为布尔值");
        }
        value = right.calculate(para);

        if(value instanceof Boolean)
            return value;
        else
            throw new RuntimeException("or操作的右操作数应为布尔值");
    }

    /**
     * 取得表达式字符串
     */
    public String getExp()
    {
        if(left == null)
            throw new RuntimeException("or操作缺少左操作数");
        if(right == null)
            throw new RuntimeException("or操作缺少右操作数");
        else
            return "(" + left.getExp() + " or " + right.getExp() + ")";
    }

}
