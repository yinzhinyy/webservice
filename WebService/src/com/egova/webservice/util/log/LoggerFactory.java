/*
 * Created on 2004-10-6
 *
 * add by zzm@egova.com.cn
 */
package com.egova.webservice.util.log;

/**
 * @author zzm
 *
 * the clsss is used to dispatch the logger object to
 * SimpleLogger and ServerLogger depending on the system
 * property named as "egovaLoggerType". 
 */
public class LoggerFactory {
    private static final int TYPE_LOG4J = 1;
    private static final int TYPE_SUNLOGGING = 2;
    private static int type = -1; //means that logger is not specified
    
    public static EgovaLogger getLogger(String clazz) {
        if (type ==  -1) {
            String logType = null;
            try {
                logType = System.getProperty("egovaLoggerType");
            } catch (Exception e) {  
                System.out.println("不能获取系统参数，使用java本地logApi");
                //e.printStackTrace();
            }
            if (logType  != null) {
                if (logType.equals("TYPE_LOG4J")) {
                    type = TYPE_LOG4J;
                } else {
                    type = TYPE_SUNLOGGING;
                }
            } else {
                type = TYPE_SUNLOGGING;
            }
        }
        if (type == TYPE_LOG4J) {
            return ServerLogger.getEgovaLogger(clazz);
        } else if (type == TYPE_SUNLOGGING) {
            return SimpleLogger.getLogger(clazz);
        } else {
            return SimpleLogger.getLogger(clazz);
        }
        
    }
    
    /*public static EgovaLogger getLogger(Class clazz) {
        return getLogger(clazz.getName());
    }*/
    
}
