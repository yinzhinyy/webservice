/*
 * Created on 2004-9-27
 *
 * add by zzm@egova.com.cn
 */
package com.egova.webservice.util.log;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import com.egova.webservice.util.EgovaException;
/**
 * @author zzm
 *
 * the clsss is used to package the funciton of log4j.
 * OFF--FATAL--ERROR--WARN--INFO--DEBUG--ALL
 * it is strongly recommended that only ERROR、WARN、INFO、
 * DEBUG are used.
 */
public class ServerLogger 
extends Logger
implements EgovaLogger {
    private static final String FQCN = ServerLogger.class.getName();
    
    public
    ServerLogger(String name) {
      super(name);
    }
    
    /**
     * get a logger of ServerLogger by the given class
     * @param clazz
     * @return ServerLogger
     */
    public static ServerLogger getEgovaLogger(String clazz) { 
        return (ServerLogger) LogManager.getLogger(clazz, new EgovaLoggerFactory());
    }
    
    /**
     * the package of cooresponding method of log4j.
     * @param logMessage
     */
   /* public void debug(String logMessage){
        super.debug(logMessage);
    }
    public void debug(String logMessage, Throwable t){
        super.debug(logMessage, t);
    }*/
    
    /**
     * the package of cooresponding method of log4j.
     * @param logMessage
     */
    /*public void info(String logMessage){
        super.info(logMessage);
    }
    public void info(String logMessage, Throwable t){
        super.info(logMessage, t);
    }*/

    /**
     * the package of cooresponding method of log4j.
     * @param logMessage
     */
    /*public void warn(String logMessage){
        super.warn(logMessage);
    }
    public void warn(String logMessage, Throwable t){
        super.info(logMessage, t);
    }*/
    

    /**
     * the package of cooresponding method of log4j.
     * @param logMessage
     */
    /*public void error(String logMessage){
        super.error(logMessage);
    }
    public void error(String logMessage, Throwable t){
        super.error(logMessage, t);
    }*/
    

    /**
     * the package of cooresponding method of log4j.
     * @param logMessage
     */
   /* private void fatal(String logMessage){
        super.fatal(logMessage);
    }*/
    /*public void fatal(String logMessage, Throwable t){
        logger.fatal(logMessage, t);
    }*///not used----@zzm
    
    /**
     * throw the exception that package in BeLogException 
     * and log it.
     * @param logMessage the message that show in the log
     * @param t exception that should be thrown.
     * @param showMessage the message that show in BeLogException
     * @throws EgovaException the exception that package the 
     * 		Exception the logger cought.
     */
    public 
    void error(String logMessage, Throwable t, String showMessage)
    throws EgovaException {
        log(FQCN, (Priority) Level.ERROR, logMessage, t);
        throw new EgovaException(showMessage);
    }
	public
	void error(String logMessage, String showMessage)
	throws EgovaException {
        log(FQCN, (Priority) Level.ERROR, logMessage, null);
        throw new EgovaException(showMessage);
	}
    
    /**
     * throw the exception that package in BeLogException 
     * and log it.
     * @param logMessage the message that show in the log
     * @param t exception that should be thrown.
     * @param showMessage the message that show in BeLogException
     * @throws EgovaException the exception that package the 
     * 		Exception the logger cought.
     */
    /*public 
    void fatal(String logMessage, Throwable t, String showMessage)
    throws BeLogException{
        logger.fatal(logMessage, t);
        throw new BeLogException(showMessage);
    }*///not used ---@zzm
    
    /*public 
    void error(
            String logMessage, 
            ServletRequest req, 
            ServletResponse res,
            String showMessage) {
        log(FQCN, (Priority) Level.ERROR, logMessage, null);
        RequestDispatcher dis = null;
	    try { dis = 
	            req.getRequestDispatcher(
	                LoggerContext.getErrorPage() 
	                + "?message=" 
	                + new String(showMessage.getBytes("GB2312"), "8859_1"));
	    } catch (Exception e) {
	        fatal("字符转换错误");
	    }
        try {
            dis.forward(req, res);            
        } catch (ServletException ex) {
            fatal("不能够将请求重定向");
        } catch (IOException ex) {
            fatal("输入输出错误");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    
    /**
     * this error method is used in the applet log only.
     */
    /*public void error(
            String logMessage,
            String showMessage,
            Applet destApplet) {
        log(FQCN, (Priority) Level.ERROR, logMessage, null);
    }*/
}