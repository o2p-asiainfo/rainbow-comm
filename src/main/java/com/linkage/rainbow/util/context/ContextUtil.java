package com.linkage.rainbow.util.context;

/**
 * 
 * 线程级变量设置与获取<br>
 * <p>
 * @version 1.0
 * @author 陈亮 2009-4-1
 * <hr>
 * 修改记录
 * <hr>
 * 1、修改人员: 陈亮   修改时间:2009-4-1<br>       
 *    修改内容:新建
 * <hr>
 */
public class ContextUtil {
	
	/**
	 * 取得线程级变量值
	 * @param contextName
	 * @return
	 */
	public static Object get(String contextName) {
		if ((ContextHolder.getContext() != null)
				&& (ContextHolder.getContext() instanceof Context)) {
			return ((ContextImpl) ContextHolder.getContext()).get(contextName);
		}
		return null;
	}
	
	/**
	 * 设置线程级变量
	 * @param contextName 变量名
	 * @param obj 变量值
	 */
	public static void put(String contextName,Object obj) {
		if (ContextHolder.getContext() == null) {
			ContextHolder.setContext(new ContextImpl());
		}

		((ContextImpl) ContextHolder.getContext()).put(contextName,obj);
	}
	
	public static void clear(){
		ContextHolder.setContext(null);
	}
}
