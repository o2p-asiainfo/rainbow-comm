package com.linkage.rainbow.util.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import com.linkage.rainbow.util.StringUtil;

/**
 * 实现公共组件参数配置的读取
 * <p>
 * @version 1.0
 * <hr>
 */
public class DefaultSettings {
	
	/**
	 * 默认配置文件路径
	 */
	static String dufaultResource = "com/linkage/rainbow/ui/default.properties";
	/**
	 * 项目组使用时,修改的配置文件路径
	 */
	static String customResource = "comm.properties";
	
	/**
	 * 报表组件配置文件
	 */
	static String reportResource = "com/linkage/rainbow/ui/reportDefault.properties";
	/**
	 * 工作流组件配置文件
	 */
	static String workflowResource = "com/linkage/rainbow/ui/workflowDefault.properties";
	
	/**
	 * 存储默认属性信息
	 */
	static Properties dufaultProp =null;
	/**
	 * 存储项目组定义的属性信息
	 */
	static Properties customProp =null;
	/**
	 * 存储报表组件属性信息
	 */
	static Properties reportProp =null;
	/**
	 * 存储工作流组件属性信息
	 */
	static Properties workflowProp =null;
	
	/**
	 * 加载默认配置文件,与加载项目组使用时修改的配置文件.
	 */
	static{
		InputStream in = null;
		//默认属性加载
		try {
			in = Resources.getResourceAsStream(dufaultResource);
			dufaultProp = new Properties();
			dufaultProp.load(in);
        } catch (IOException e) {
            //System.out.println("WARN: Could not load " + dufaultResource + e);
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch(IOException io) {
                }
            }
        }
        
        //报表默认属性加载
        try {
	        in = Resources.getResourceAsStream(reportResource);
	        reportProp = new Properties();
	        reportProp.load(in);
			for (Iterator i = reportProp.keySet().iterator(); i.hasNext(); ) {
	            String nameTmp = (String) i.next();
	            dufaultProp.setProperty(nameTmp, reportProp.getProperty(nameTmp));
	        }
        } catch (IOException e) {
        	 //System.out.println("WARN: Could not load " + customResource + e);
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch(IOException io) {
                }
            }
        }
        
        //工作流默认属性加载
        try {
	        in = Resources.getResourceAsStream(workflowResource);
	        workflowProp = new Properties();
	        workflowProp.load(in);
			for (Iterator i = workflowProp.keySet().iterator(); i.hasNext(); ) {
	            String nameTmp = (String) i.next();
	            dufaultProp.setProperty(nameTmp, workflowProp.getProperty(nameTmp));
	        }
        } catch (IOException e) {
        	 //System.out.println("WARN: Could not load " + workflowResource + e);
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch(IOException io) {
                }
            }
        }
        
        //项目组自定义配置文件
        try {
	        in = Resources.getResourceAsStream(customResource);
			customProp = new Properties();
			customProp.load(in);
			for (Iterator i = customProp.keySet().iterator(); i.hasNext(); ) {
	            String nameTmp = (String) i.next();
	            dufaultProp.setProperty(nameTmp, customProp.getProperty(nameTmp));
	        }
        } catch (IOException e) {
        	 //System.out.println("WARN: Could not load " + customResource + e);
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch(IOException io) {
                }
            }
        }
       
	}

	/**
	 * 根据键 获取配置文件的值
	 * @param key
	 * @return String
	 */
	static public String getProperty(String key) {
		String value=dufaultProp.getProperty(key);
        //if(value !=null && value.trim().length()>0 ){
//    	if(value !=null){
//    		//System.out.println(key+"="+value);
//    		try {
//    			value = new String(value.getBytes("iso-8859-1"),"GBK");
////    			System.out.println(key+"="+value);
////    			value = new String(dufaultProp.getProperty(key).getBytes("iso-8859-1"),"UTF-8");
////    			System.out.println(key+"="+value);
//			} catch (Exception e) { 
//				// TODO: handle exception
//			}
//    	}
		 return value;
	}
	
	
	/**
	 * 根据配置文件,设置某一类中的默认值.
	 * 例
	 * 1.配置文件中配置内容:
	 * #是否显示周
	 * comm.ui.date.isShowWeek=true
	 * #是否只读
	 * comm.ui.date.readOnly=false
	 * #日期组件皮肤
	 * comm.ui.date.skin =whyGreen
	 * 2.在标签类的构造函数中中通过调用此方法实现
	 * 在com.linkage.rainbow.views.jsp.ui.date.DateTag类的构造函数中
	 * 通过调用：DefaultSettings.setDefaultValue(this,"comm.ui.date");
	 * 实现此标签的默认值设置
	 * @param bean
	 * @param key
	 */
	static public void setDefaultValue(Object bean,String key){
		key = key+".";
		String methodName = null;
		int iStart = key.length();
		for (Iterator i = dufaultProp.keySet().iterator(); i.hasNext(); ) {
            String keyName = (String) i.next();
            if(keyName.startsWith(key)&&keyName.length()>=iStart){
            	String value=getProperty(keyName);
                //if(value !=null && value.trim().length()>0 ){
            	if(value !=null){
	            	methodName = keyName.substring(iStart);
//	            	//判断设置默认值时,是否有限制条件.
//	            	//例:comm.ui.gridFootCell.styleClass@template(simple)@skin(simple)=comm_grid_simple_tfoot_td
//	            	//表示在此组件的template值为simple,且skin值为simple时,才设置此默认值.
//	            	if(methodName.indexOf("@")>-1){ 
//	            		String limitStr = methodName.substring(methodName.indexOf("@")+1);
//	            		methodName = methodName.substring(0,methodName.indexOf("@"));
//	            		String limits[] = limitStr.split("@");
//	            		boolean isOk = true;
//	            		for(int index=0;index<limits.length;index++){
//	            			String attributeName = limits[index].substring(0,limits[index].indexOf("("));
//	            			String limiAttributeValue = limits[index].substring(limits[index].indexOf("(")+1,limits[index].indexOf(")"));
//	            			String attributeValue =StringUtil.valueOf(getBeanValue(bean,attributeName));
//	            			if(!attributeValue.trim().equals(limiAttributeValue.trim())){
//	            				isOk = false;
//	            			}
//	            		}
//	            		if(isOk)
//	            			setBeanValue(bean,methodName,value);
//	            	}else{
	            	
//	            		if(methodName.equals("skin"))
//	            			value="default:"+value;
	            		
	            		setBeanValue(bean,methodName,value);
//	            	}
                }
            }
            //System.out.println("keyName:"+keyName);
        }

	}
	
//	static public void setRelDefaultValue(Object bean,String key,String changeAttributeName){
//		
//		key = key+".";
//		String methodName = null;
//		int iStart = key.length();
//		for (Iterator i = dufaultProp.keySet().iterator(); i.hasNext(); ) {
//            String keyName = (String) i.next();
//            if(keyName.startsWith(key)&&keyName.length()>=iStart){
//            	String value=getProperty(keyName);
//            	if(value !=null){
//	            	methodName = keyName.substring(iStart);
//	            	//判断设置默认值时,是否有限制条件.
//	            	//例:comm.ui.gridFootCell.styleClass@template(simple)@skin(simple)=comm_grid_simple_tfoot_td
//	            	//表示在此组件的template值为simple,且skin值为simple时,才设置此默认值.
//	            	if(methodName.indexOf("@"+changeAttributeName+"(")>-1){//有相关的过滤条件 
//	            		String limitStr = methodName.substring(methodName.indexOf("@")+1);
//	            		methodName = methodName.substring(0,methodName.indexOf("@"));
//	            		String oldValue =StringUtil.valueOf(getBeanValue(bean,methodName));
//	            		if(oldValue.equals("")){//原有属性值不为空时,才加入默认值
//		            		String limits[] = limitStr.split("@");
//		            		boolean isOk = true;
//		            		for(int index=0;index<limits.length;index++){
//		            			String attributeName = limits[index].substring(0,limits[index].indexOf("("));
//		            			String limiAttributeValue = limits[index].substring(limits[index].indexOf("(")+1,limits[index].indexOf(")"));
//		            			//System.out.println("attributeName:"+attributeName);
//		            			//System.out.println("limiAttributeValue:"+limiAttributeValue);
//		            			String attributeValue =StringUtil.valueOf(getBeanValue(bean,attributeName));
//		            			if(!attributeValue.trim().equals(limiAttributeValue.trim())){
//		            				isOk = false;
//		            			}
//		            		}
//		            		if(isOk)
//		            			setBeanValue(bean,methodName,value);
//	            		}
//	            	}	            	
//                }
//            }
//        }
//	}
	
	/**
	 * 取得javabean的属性
	 * @param bean
	 * @param methodName
	 * @return
	 */
	static private Object getBeanValue(Object bean,String methodName){
		Object result = null;
    	methodName="get"+methodName.substring(0,1).toUpperCase()+methodName.substring(1);
    	Class argsClass[] = new Class[0];
        java.lang.reflect.Method  method = null;
        try {
    	   method = bean.getClass().getMethod(methodName,argsClass);
    	   String[] parameters = new String[0];
    	   result = method.invoke(bean,parameters);
        }
        catch (Exception ex) {
        	//ex.printStackTrace();
        }
        return result;
	}
	
	/**
	 * 设置取得javabean的属性
	 * @param bean
	 * @param methodName
	 * @param value
	 */
	static private void setBeanValue(Object bean,String methodName,String value){
    	methodName="set"+methodName.substring(0,1).toUpperCase()+methodName.substring(1);
    	Class argsClass[] = new Class[1];
    	argsClass[0]=String.class;
        java.lang.reflect.Method  method = null;
        try {
    	   method = bean.getClass().getMethod(methodName,argsClass);
    	   String[] parameters = new String[1];
    	   parameters[0]=value;
	       method.invoke(bean,parameters);
        }
        catch (Exception ex) {
        	//ex.printStackTrace();
        }
	}

	
	/*
	public void abc(){
        String name="com/linkage/rainbow/uicomm.properties";
		String name2 = "comm.properties";
        
        URL settingsUrl22= null;
		try {
			settingsUrl22=  Resources.getResourceURL(name);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		LocatableProperties settings=null;
		URL settingsUrl =settingsUrl22;// = ClassLoaderUtils.getResource(name , getClass());
        if (settingsUrl == null) {
           
            settings = new LocatableProperties();
            return;
        }
		settings = new LocatableProperties(new LocationImpl(null, settingsUrl.toString()));

        // Load settings
        try {
            in = settingsUrl.openStream();
            settings.load(in);
        } catch (IOException e) {
            throw new StrutsException("Could not load " + name + ".properties:" + e, e);
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch(IOException io) {
                }
            }
        }
        
        LocatableProperties settings2=null;
		URL settingsUrl2 = ClassLoaderUtils.getResource(name2 , getClass());
        if (settingsUrl2 == null) {
           
            settings2 = new LocatableProperties();
            return;
        }
		settings2 = new LocatableProperties(new LocationImpl(null, settingsUrl2.toString()));

        // Load settings
        InputStream in2 = null;
        try {
            in2 = settingsUrl2.openStream();
            settings2.load(in2);
        } catch (IOException e) {
            throw new StrutsException("Could not load " + name + ".properties:" + e, e);
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch(IOException io) {
                }
            }
        }
        
        for (Iterator i = settings2.keySet().iterator(); i.hasNext(); ) {
            String nameTmp = (String) i.next();
            settings.setProperty(nameTmp, settings2.getProperty(nameTmp));
        }

        System.out.println(settings2);
	}
	*/
	public static void main(String[] args) {
		DefaultSettings a =new DefaultSettings();
	
		//a.abc();
		//a.setDefaultValue(null,null);
		
	}
}
