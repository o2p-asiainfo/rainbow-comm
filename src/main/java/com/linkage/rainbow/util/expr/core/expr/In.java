
package com.linkage.rainbow.util.expr.core.expr;
import java.util.List;
import java.util.Map;


import com.linkage.rainbow.util.expr.util.MathUtil;

/*in操作
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
public class In extends Operator	{

	    /**
     * 
     */
    private static final long serialVersionUID = 5914459063514544728L;

	    public In()
	    {
	        pri = 7;
	    }
	    /**
	     * 对操作符的左计算树与右计算树进行 in 计算
	     */
	    public Object calculate(){
	    	return calculate(null);
	    }
	    /**
	     * 对操作符的左计算树与右计算树进行 in 计算
	     */
	    public Object calculate(Map para)
	    {
	        if(left == null)
	            throw new RuntimeException("in操作缺少左操作数");
	        Object value1 = left.calculate(para);
	        if(right == null)
	            throw new RuntimeException("in操作缺少右操作数");
	        Object value2 = right.calculate(para);
	        if((value1 instanceof List) && (value2 instanceof List))
	        {
	            List list1 = (List)value1;
	            List list2 = (List)value2;
	            for(int i = 0; i < list1.size(); i++)
	            {
	                if(!listEquals(list2, list1.get(i)))
	                    return Boolean.FALSE;
	            }

	            return Boolean.TRUE;
	        }
	        if((value1 instanceof List) && !(value2 instanceof List))
	        {
	            List list1 = (List)value1;
	            for(int i = 0; i < list1.size(); i++)
	            {
	                if(!MathUtil.equals(list1.get(i), value2))
	                    return Boolean.FALSE;
	            }

	            return Boolean.TRUE;
	        }
	        if(!(value1 instanceof List) && (value2 instanceof List))
	        {
	            if(listEquals((List)value2, value1))
	                return Boolean.TRUE;
	        } else
	        {
	            if(MathUtil.equals(value1, value2))
	                return Boolean.TRUE;
	        }
	        return Boolean.FALSE;
	    }

	    private boolean listEquals(List list, Object o)
	    {
	        for(int i = 0; i < list.size(); i++)
	        {
	            if(MathUtil.equals(list.get(i), o))
	                return true;
	        }

	        return false;
	    }

	    /**
	     * 取得表达式字符串
	     */
	    public String getExp()
	    {
	        throw new RuntimeException("非法操作符in");
	    }
}
