
package com.linkage.rainbow.util.expr.core.expr;

import java.util.Map;


/**
 * not操作
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
public class Not extends Operator
{

    /**
     * 
     */
    private static final long serialVersionUID = 2252812156174638654L;

    public Not()
    {
        pri = 13;
    }
    /**
     * 对操作符的左计算树与右计算树进行 not 计算
     */
    public Object calculate(){
    	return calculate(null);
    }
    /**
     * 对操作符的左计算树与右计算树进行 not 计算
     */
    public Object calculate(Map para)
    {
        if(right == null)
            throw new RuntimeException("not操作缺少右操作数");
        Object value = right.calculate(para);
        
        if(value instanceof Boolean)
        {
            if(Boolean.TRUE.equals(value))
                return Boolean.FALSE;
            else
                return Boolean.TRUE;
        } else
        {
            throw new RuntimeException("not操作的右操作数应为布尔值");
        }
    }

    /**
     * 取得表达式字符串
     */
    public String getExp()
    {
        if(right == null)
            throw new RuntimeException("not操作缺少右操作数");
        else
            return "(!" + right.getExp() + ")";
    }

}
