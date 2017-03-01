package com.linkage.rainbow.util.context;

import java.util.HashMap;
import java.util.Map;

 

  


/**
 * <p></p>
 * 将session封装到ContextImpl里
 *
 * @version 	0.0.1
 */
public class ContextImpl implements Context{
	private Object session;
	private Map map = new HashMap();
	public ContextImpl(){}

	public void validate(){
		
	}


	/**
	 * 获取session
	 * @return Object
	 */
	public Object getSession() {
		return session;
	}


	/**
	 * 设置session
	 * @param session
	 */
	public void setSession(Object session) {
		this.session = session;
	}
	
	/**
	 * 将变量放入map
	 * @param key
	 * @param value
	 */
	public void put(String key,Object value){
		map.put(key,value);
	}
	
	/**
	 * 获取map中的变量
	 * @param key
	 * @return Object
	 */
	public Object get(String key){
		return map.get(key);
	}
	
}
