
package com.linkage.rainbow.util.expr.core.expr;

import java.util.List;
import java.util.Map;


import com.linkage.rainbow.util.expr.util.MathUtil;
/**
 * 相加操作
 * <p>
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
public class Add extends Operator
{

    /**
     * 
     */
    private static final long serialVersionUID = 5656025610823060742L;

    public Add()
    {
        pri = 10;
    }
    /**
     * 对操作符的左计算树与右计算树进行相加计算
     */
    public Object calculate(){
    	return calculate(null);    	
    }
    
    /**
     * 对操作符的左计算树与右计算树进行相加计算
     */
    public Object calculate(Map para)
    {
        if(right == null)
            throw new RuntimeException("加操作缺少右操作数");
        if(left == null)
            return right.calculate(para);
        Object o1 = left.calculate(para);
        Object o2 = right.calculate(para);
        List list = null;
        Object obj = null;
        boolean b = true;
        if((o1 instanceof List) && !(o2 instanceof List))
        {
            list = (List)o1;
            obj = o2;
            b = true;
        } else
        if(!(o1 instanceof List) && (o2 instanceof List))
        {
            list = (List)o2;
            obj = o1;
            b = false;
        }
        if(list != null)
        {
            int i = 0;
            for(int size = list.size(); i < size; i++)
            {
                Object tmp = list.get(i);

                    list.set(i, b ? MathUtil.add(tmp, obj) : MathUtil.add(obj, tmp));
            }

            return list;
        } else
        {
            return MathUtil.add(o1, o2);
        }
    }

    /**
     * 取得表达式字符串
     */
    public String getExp()
    {
        if(right == null)
            throw new RuntimeException("加操作缺少右操作数");
        if(left == null)
            return right.getExp();
        else
            return "(" + left.getExp() + "+" + right.getExp() + ")";
    }

}
