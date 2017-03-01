
package com.linkage.rainbow.util.expr.core.expr;
import java.util.List;
import java.util.Map;


import com.linkage.rainbow.util.expr.util.MathUtil;

/**
 * 除操作
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
public class Divide extends Operator{
    /**
     * 
     */
    private static final long serialVersionUID = 3306736694544557018L;

    public Divide()
    {
        pri = 11;
    }
    /**
     * 对操作符的左计算树与右计算树进行相除计算
     */
    public Object calculate(){
    	return calculate(null);
    }
    /**
     * 对操作符的左计算树与右计算树进行相除计算
     */
    public Object calculate(Map para)
    {
        if(left == null)
            throw new RuntimeException("除操作缺少左操作数");
        if(right == null)
            throw new RuntimeException("除操作缺少右操作数");
        Object o1 = left.calculate(para);
        Object o2 = right.calculate(para);
        List list = null;
        Object obj = null;
        if((o1 instanceof List) && !(o2 instanceof List))
        {
            list = (List)o1;
            obj = o2;
        }
        if(list != null)
        {
            int i = 0;
            for(int size = list.size(); i < size; i++)
            {
                Object tmp = list.get(i);
  
                    list.set(i, MathUtil.divide(tmp, obj));

            }

            return list;
        } else
        {
            return MathUtil.divide(o1, o2);
        }
    }

    /**
     * 取得表达式字符串
     */
    public String getExp()
    {
        if(left == null)
            throw new RuntimeException("除操作缺少左操作数");
        if(right == null)
            throw new RuntimeException("除操作缺少右操作数");
        else
            return "(" + left.getExp() + "/" + right.getExp() + ")";
    }

}
