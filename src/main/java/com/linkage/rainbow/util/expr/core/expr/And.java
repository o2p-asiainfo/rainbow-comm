
package com.linkage.rainbow.util.expr.core.expr;

import java.util.Map;


/**
 * and 操作
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

public class And extends Operator{

    private static final long serialVersionUID = -2924083217673678960L;

    public And()
    {
        pri = 3;
    }
    /**
     * 对操作符的左计算树与右计算树进行 and计算
     */
    public Object calculate(){
    	return calculate(null);   
    }
    /**
     * 对操作符的左计算树与右计算树进行 and计算
     */
    public Object calculate(Map para)
    {
        if(left == null)
            throw new RuntimeException("and操作缺少左操作数");
        if(right == null)
            throw new RuntimeException("and操作缺少右操作数");
        Object value = left.calculate(para);
        if(value instanceof Boolean)
        {
            if(Boolean.FALSE.equals(value))
                return Boolean.FALSE;
        } else
        {
            throw new RuntimeException("and操作左操作数应为布尔值");
        }
        value = right.calculate(para);
        if(value instanceof Boolean)
            return value;
        else
            throw new RuntimeException("and操作右操作数应为布尔值");
    }

    /**
     * 取得表达式字符串
     */
    public String getExp()
    {
        if(left == null)
            throw new RuntimeException("and操作缺少左操作数");
        if(right == null)
            throw new RuntimeException("and操作缺少右操作数");
        else
            return "(" + left.getExp() + "&&" + right.getExp() + ")";
    }

}
