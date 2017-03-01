/** 
 * Project Name:rainbow-comm 
 * File Name:InternationalService.java 
 * Package Name:com.asiainfo.foundation.annotation 
 * Date:2014年10月30日上午11:34:31 
 * Copyright (c) 2014, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.foundation.annotation;  
/** 
 * ClassName:InternationalService <br/> 
 * Function: 国际化注解服务. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2014年10月30日 上午11:34:31 <br/> 
 * @author   颖勤 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
@Inherited
public @interface InternationalService {

}
