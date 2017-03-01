package com.linkage.rainbow.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.linkage.rainbow.util.expr.core.Expr;
import com.linkage.rainbow.util.expr.model.Report;
import com.linkage.rainbow.util.expr.rowset.CachedRowSet;

public class ExprUtil {

	
	public static Expr createExpr(String expStr,List list,Map para){
		Report rp =new Report();
		CachedRowSet rs = new CachedRowSet();
		rs.populate(list);
		rp.setDs("list", rs);
		rp.setPara(para);
		Expr expr = new Expr(expStr,rp);
		return expr;
	}
	
	public static Expr createExpr(String expStr,Map para){
		Expr expr = new Expr(expStr,para);
		return expr;
	}
	
	public static Expr createExpr(String expStr){
		Expr expr = new Expr(expStr);
		return expr;
	}
	
	public static void main(String[] args) {
		//建立数据集List,list内可能是javabean或Map
		List list = new ArrayList();
		Map map = new HashMap();
		map.put("ID", "001");
		map.put("exa_value",100);
		list.add(map);
		
		map = new HashMap();
		map.put("ID", "002");
		map.put("exa_value",200);
		list.add(map);
		
		map = new HashMap();
		map.put("ID", "003");
		map.put("exa_value",500);
		list.add(map);
		//动态变化的参数
		Map para = new HashMap();
		para.put("value","7");
		
		//建立表达式，支持的函数包括selectOne,sum,count,avg,case,decode,round，字段名区分大小写
		String exprStr = "case(to_number($value)>list.avg(exa_value),'color:red','color:blue')";
		Expr expr  = ExprUtil.createExpr(exprStr, list, para);
		System.out.println("exprStr:"+expr.calculate());
		
		String exprStr2 = "case(to_number($value)>list.selectOne(exa_value,ID='003'),'color:red','color:blue')";
		Expr expr2  = ExprUtil.createExpr(exprStr2, list, para);
		
		//计算表达式
		for(int i=1;i<10;i++){
			para.put("value", i*100);
			Object result = expr.calculate();
			System.out.println("value:"+(i*100)+"result:"+result);
		}
		System.out.println();
		for(int i=1;i<10;i++){
			para.put("value", i*100);
			Object result2 = expr2.calculate();
			System.out.println("value:"+(i*100)+"result2:"+result2);
		}
	}
}
