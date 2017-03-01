/** 
 * Project Name:platform-api-core 
 * File Name:LogModel.java 
 * Package Name:com.asiainfo.foundation.log 
 * Date:2014年9月28日下午9:22:47 
 * Copyright (c) 2014, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.foundation.log;  
/** 
 * ClassName:LogModel <br/> 
 * Function: 定义日志类型. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2014年9月28日 下午9:22:47 <br/> 
 * @author   颖勤 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public enum LogModel {
	EVENT_APP_EXCPT(1),
	EVENT_CORE_INFO(2),
	EVENT_SYS_ERR(3),
	EVENT_BIZ_EXCPT(4),
	EVENT_BSH_ERROR(5),
	EVENT_SYS_STATUS(6),
	EVENT_USER_LOGON(7),
	EVENT_APP_STATUS(8),
	EVENT_TSOFT_STATUS(9),
	EVENT_COMPONENT_STATUS(10),
	EVENT_USER_AUTHENTICATE(11),
	EVENT_USER_AUTHEN_TIMES(12),
	EVENT_USER_ROLE_STATUS(13),
	EVENT_CONFILE_MODIFY(14),
	EVENT_DATA_IMP_EXP(15);
	private int value = 0;
	
	private LogModel(int value) {
        this.value = value;
    }
	public static LogModel valueOf(int value) { 
        switch (value) {
        case 1:
            return EVENT_APP_EXCPT;
        case 2:
            return  EVENT_CORE_INFO;
        case 3:
            return  EVENT_SYS_ERR;
        case 4:
            return  EVENT_BIZ_EXCPT;
        case 5:
            return  EVENT_BSH_ERROR;  
        case 6:
            return  EVENT_SYS_STATUS; 
        case 7:
            return  EVENT_USER_LOGON;  
        case 8:
            return  EVENT_APP_STATUS; 
        case 9:
            return  EVENT_TSOFT_STATUS; 
        case 10:
            return  EVENT_COMPONENT_STATUS; 
        case 11:
            return  EVENT_USER_AUTHENTICATE; 
        case 12:
            return  EVENT_USER_AUTHEN_TIMES; 
        case 13:
            return  EVENT_USER_ROLE_STATUS; 
        case 14:
            return  EVENT_CONFILE_MODIFY; 
        case 15:
            return  EVENT_DATA_IMP_EXP; 
        default:
            return  null;
        }
    }

    public int value() {
        return this.value;
    }	
}
