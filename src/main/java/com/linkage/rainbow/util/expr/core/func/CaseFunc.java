
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
import java.util.HashMap;
import java.util.Map;




import com.linkage.rainbow.util.expr.core.Expr;
import com.linkage.rainbow.util.expr.core.Func;


/**
 * 函数说明：case函数 <br>
 * 语法： case(caseExp1,result1,,caseExpN,resultN,defaultResult) <br>
 * caseExp返回值必须为boolean类型,当某一case条件判断成功时,返回对应的结果值,如果各判断表达式都为false时,返回最后的默认结果值.
 * 参数说明：<br>
 * caseExp 条件判断表达式  <br>
 * result 条件判断为true时,返回的结果值
 * defaultResult 默认结果值
 * @version 1.0
 * @author 陈亮 2011-03-05
 *         <hr>
 *         修改记录
 *         <hr>
 *         1、修改人员:陈亮 修改时间:2011-03-05<br>
 *         修改内容:新建
 *         <hr>
 *
 */
public class CaseFunc extends Func {

	/**
     * 
     */
    private static final long serialVersionUID = -2120195132272176881L;

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

		if(para.length<2)
			return "函数没有足够的参数!";
			
		for(int k=0;k<=para.length-1;k=k+2){
			if(k==para.length-1){
				result= getExprCalculate(para[k],paraNew);
			}else{
				Object value = getExprCalculate(para[k],paraNew);
				value = resultToStr(value); //转换为字符串
				if(value!= null && value.toString().equalsIgnoreCase("true")){
					result= getExprCalculate(para[k+1],paraNew);
					break;
				}
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
