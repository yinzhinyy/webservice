/*
 * Created on 2004-9-27
 *
 * add by zzm@egova.com.cn
 */
package com.egova.webservice.util.log;

/**
 * @author zzm
 *
 * the clsss is used to package the java logger api to 
 * used in simple siturations
 * SEVERE>WARNING>INFO>CONFIG>FINE>FINER>FINEST.
 * in this package only server, warning, info, fine are 
 * used. The corresponding interfaces are error, warn, 
 * info, debug as descripted in log4j.
 */
//import java.applet.Applet;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.egova.webservice.util.EgovaException;


public class SimpleLogger
implements EgovaLogger {
    private String name = null;
    private Logger logger = null;
    
    public SimpleLogger(String name) {
        this.name = name;
        if (logger == null) {
            logger = Logger.getLogger(name);
        }
    }
    private void log (Level level, String msg, Throwable ex) {
        if (logger == null) {
            logger = Logger.getLogger(name);
        }
        if (logger.isLoggable(level)) {
            Throwable dummyException = new Throwable();
            StackTraceElement locations[] = dummyException.getStackTrace();
            String cname = "unknown";
            String method = "unknown";
            if (locations != null && locations.length  > 2) {
                StackTraceElement caller = locations[2];
                cname = caller.getClassName();
                method = caller.getMethodName();
            }
            if (ex == null) {
                logger.logp (level, cname, method, msg);
            } else {
                logger.logp (level, cname, method, msg, ex);
            }
        }

    }

    /**
     * get a logger of ServerLogger by the given class
     * @param clazz
     * @return SimpleLogger
     */
    public static SimpleLogger getLogger(String clazz) {
        SimpleLogger sLogger = new SimpleLogger(clazz);
        return sLogger;
    }

    /**
     * the package of cooresponding method of log4j.
     * @param logMessage
     */
    public void debug(Object logMessage) {
        log(Level.FINE, String.valueOf(logMessage), null);
    }
    public void debug(Object logMessage, Throwable t) {
        log(Level.FINE, String.valueOf(logMessage), t);
    }

    /**
     * the package of cooresponding method of log4j.
     * @param logMessage
     */
    public void info(Object logMessage) {
        log(Level.INFO, String.valueOf(logMessage), null);
    }
    public void info(Object logMessage, Throwable t) {
        log(Level.INFO, String.valueOf(logMessage), t);
    }
    
    /**
     * the package of cooresponding method of log4j.
     * @param logMessage
     */
    public void warn(Object logMessage) {
        log(Level.WARNING, String.valueOf(logMessage), null);
    }
    public void warn(Object logMessage, Throwable t) {
        log(Level.WARNING, String.valueOf(logMessage), t);
    }

    /**
     * the package of cooresponding method of log4j.
     * @param logMessage
     */
    public void error(Object logMessage) {
        log(Level.SEVERE, String.valueOf(logMessage), null);
    }
    public void error(Object logMessage, Throwable t) {
        log(Level.SEVERE, String.valueOf(logMessage), t);
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
    public 
    void error(String logMessage, Throwable t, String showMessage)
    throws EgovaException {
        log(Level.SEVERE, String.valueOf(logMessage), t);
        throw new EgovaException(showMessage);
    }
	public
	void error(String logMessage, String showMessage)
	throws EgovaException {
        log(Level.SEVERE, String.valueOf(logMessage), null);
        throw new EgovaException(showMessage);
	}

    /*public 
    void error(
            String logMessage, 
            ServletRequest req, 
            ServletResponse res,
            String showMessage) {
        log(Level.SEVERE, String.valueOf(logMessage), null);
        RequestDispatcher dis = null;
	    try { dis = 
	            req.getRequestDispatcher(
	                LoggerContext.getErrorPage() 
	                + "?message=" 
	                + new String(showMessage.getBytes("GB2312"), "8859_1"));
	    } catch (Exception e) {
	        error("字符转换错误");
	    }
        try {
            dis.forward(req, res);            
        //}catch(ServletException ex){
        //    error("不能够将请求重定向");
        } catch (IOException ex) {
            error("输入输出错误");
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
        log(Level.SEVERE, String.valueOf(logMessage), null);
        if (JSObject.getWindow(destApplet) == null) {
            return;
        }
		JSObject.getWindow(destApplet).eval(
				"javascript:alert('" + showMessage + "')");
    }*/
}
