/** 
 * Project Name:platform-api-core 
 * File Name:ExTest.java 
 * Package Name:com.asiainfo.fundation.exception 
 * Date:2014年9月28日下午2:59:22 
 * Copyright (c) 2014, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.fundation.exception;  


import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.foundation.log.LogModel;
import com.asiainfo.foundation.log.Logger;
import com.linkage.rainbow.util.FileUtils;

/** 
 * ClassName:ExTest <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2014年9月28日 下午2:59:22 <br/> 
 * @author   颖勤 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class ExTest {
	private Logger log = Logger.getLog(this.getClass());
	private ApplicationContext ctx;
	@Before
	public void initSpringContext(){
//		String[] locations = new String[]{"classpath:spring/applicationContext-fundation-base.xml"};
//		ctx = new ClassPathXmlApplicationContext(locations);
	}
	/**
	 * 
	 * test:(这里用一句话描述这个方法的作用). <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author 颖勤  
	 * @since JDK 1.6
	 */
	@Test
	public void test(){
//		String str = messageSource.getMessage("eaap.op2.conf.prov.audiApprove", null, Locale.US);
//		System.out.println(str);
		Locale.setDefault(Locale.US);
		String[] msgArgs = new String[]{"hello","david"};
		String a = "f";
		if(a.equals("f")){
			BusinessException e= new BusinessException(9999,"eaap.op2.conf.prov.audiApprove",msgArgs,new ClassCastException("String can't be convert to Integer"));
			RuntimeException e2 = new RuntimeException("hhhhh");
			log.error(LogModel.EVENT_APP_EXCPT, e2);
			throw e;
		}
	}
	@Test
	public void testMsgFormate(){
//		String str = MessageUtils.resolve("hello {0},{2}", new Object[]{"david"});
		log.debug("hello {0}, haha {1}", "david","mike");
	}
	@Test
	public void testMsgFormate2(){
		initSpringContext();
		for(LogModel logModel : LogModel.values()){
		   System.out.println(logModel.valueOf(logModel.value()));
		}
		//log.trace("hello {0}, haha {1}", "david","mike");
			log.trace(LogModel.EVENT_SYS_STATUS,"The system {0} operated has been shutdown","david");
			log.debug(LogModel.EVENT_USER_LOGON,"User {0} logs on {1} System {2}","{name:dfd,code:111}","5655","{name:33,key:33}");
			log.error(LogModel.EVENT_COMPONENT_STATUS,"User {0} logs on {1} System {2}","{name:dfd,code:111}","5655","{name:33,key:33}");
			log.info(LogModel.EVENT_USER_ROLE_STATUS,"User {0}'s role assigns successfully","david");
			log.warn(LogModel.EVENT_CONFILE_MODIFY,"These profiles have been modified by {0}","david");
			log.error(LogModel.EVENT_COMPONENT_STATUS,"The component has been upgraded");
			
			log.trace("The system {0} operated has been shutdown","david");
			log.debug("User {0} logs on System","david");
			log.info("User {0}'s role assigns successfully","david");
			log.error(LogModel.EVENT_SYS_ERR,new BusinessException(9001, "The component of initiator  authenticates failure ",null));
			log.error(LogModel.EVENT_SYS_ERR,new BusinessException(9002, "The format of  message's control header provided by O2P's verification initiator is error ",null));
			log.error(LogModel.EVENT_SYS_ERR,new BusinessException(9003, "The format of  message's business package provided by O2P's verification initiator is error ",null));
			log.error(LogModel.EVENT_SYS_ERR,new BusinessException(9004, "The format of  message's control header provided by O2P's verification hometown is error ",null));
			log.error(LogModel.EVENT_SYS_ERR,new BusinessException(9005, "The format of  message's business package provided by O2P's verification hometown is error ",null));
			log.error(LogModel.EVENT_SYS_ERR,new BusinessException(9006, "The format of  message provided by O2P's verification hometown is error ",null));
			log.error(LogModel.EVENT_SYS_ERR,new BusinessException(9007, "Fall local institutions check  the request message format errorly ",null));
			log.error(LogModel.EVENT_SYS_ERR,new BusinessException(9008, "Fall local switching node discoveries the error of  Message Header format",null));
			log.error(LogModel.EVENT_SYS_ERR,new BusinessException(9009, "Fall local switching node discoveries the error of Service Content format",null));
			log.error(LogModel.EVENT_SYS_ERR,new BusinessException(9010, "Local agencies have yet to register this service falls in O2P or Fall local authority does not exist ",null));
			log.error(LogModel.EVENT_BIZ_EXCPT,new BusinessException(9001, "The component of initiator  authenticates failure ",null));
			log.error(LogModel.EVENT_BIZ_EXCPT,new BusinessException(9002, "The format of  message's control header provided by O2P's verification initiator is error ",null));
			log.error(LogModel.EVENT_BIZ_EXCPT,new BusinessException(9003, "The format of  message's business package provided by O2P's verification initiator is error ",null));
			log.error(LogModel.EVENT_BIZ_EXCPT,new BusinessException(9004, "The format of  message's control header provided by O2P's verification hometown is error ",null));
			log.error(LogModel.EVENT_BIZ_EXCPT,new BusinessException(9005, "The format of  message's business package provided by O2P's verification hometown is error ",null));
			log.error(LogModel.EVENT_BIZ_EXCPT,new BusinessException(9006, "The format of  message provided by O2P's verification hometown is error ",null));
			log.error(LogModel.EVENT_BIZ_EXCPT,new BusinessException(9007, "Fall local institutions check  the request message format errorly ",null));
			log.error(LogModel.EVENT_BIZ_EXCPT,new BusinessException(9008, "Fall local switching node discoveries the error of  Message Header format",null));
			log.error(LogModel.EVENT_BIZ_EXCPT,new BusinessException(9009, "Fall local switching node discoveries the error of Service Content format",null));
			log.error(LogModel.EVENT_BIZ_EXCPT,new BusinessException(9010, "Local agencies have yet to register this service falls in O2P or Fall local authority does not exist ",null));
			log.warn("These profiles have been modified by {0}","david");
			log.error("The component has been upgraded");
	}
	@Test
	public void testLog() throws Exception{
		String str = FileUtils.readTxt("d:/htmllog.txt");
		log.debug("ret"+str);
	}
}
