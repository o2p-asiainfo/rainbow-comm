
package com.linkage.rainbow.util.expr.core.func;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;




import com.linkage.rainbow.util.expr.core.Expr;
import com.linkage.rainbow.util.expr.core.Func;
import com.linkage.rainbow.util.expr.util.MathUtil;


/**
 * 函数说明：to_number函数 <br>
 * 语法： to_number(exp1,) <br>
 * 将其他类型的值转为number.
 * 参数说明：<br>
 * exp1 表达式  <br>
 * @version 1.0
 * @author 陈亮 2011-03-09
 *         <hr>
 *         修改记录
 *         <hr>
 *         1、修改人员:陈亮 修改时间:2011-03-09<br>
 *         修改内容:新建
 *         <hr>
 *
 */
public class ToNumberFunc extends Func {

	
	/**
     * 
     */
    private static final long serialVersionUID = -6821059718654619026L;

	/**
	 * case函数计算
	 */
	public Object calculate() {
		return calculate(null);
	}
	/**
	 * case函数计算
	 */
	public Object calculate(Map paraNew) {
		Object result = null;

		if(para.length<1)
			return "函数没有足够的参数!";
			
		result= getExprCalculate(para[0],paraNew);
		
		if(result != null && !MathUtil.isNumber(result)){ //如果非数字则转为数字。
			try {
				BigDecimal tmp = new BigDecimal(result.toString());
				result = tmp;
			} catch (Exception e) {
				return "ToNumber函数转换数据出错:"+e.toString();
			}						
		}
	
		return result;
	}
	
	/**
	 * 计算表达式
	 * @param expStr 表达式字符串
	 * @return 表达式计算结果
	 */
	public Object getExprCalculate(String expStr,Map paraNew){
		Object result = null;
		if(expStr != null && expStr.trim().length()>0){
			try {
				Expr expr = new Expr(expStr,this.report,this.dsName);
				Object obj = expr.calculate(paraNew);
				//result = obj != null?obj.toString():null;
				result = obj ;
			} catch (Exception e) {
			}
		}
		return result;
	}
	
	/**
	 * 取得表达式字符串
	 */
	public String getExp(){
		return null;
	}
 
}
