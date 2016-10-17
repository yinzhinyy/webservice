/*
 * Created on 2004-10-6
 *
 * add by zzm@egova.com.cn
 */
package com.egova.webservice.util.log;

import com.egova.webservice.util.EgovaException;

//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import java.applet.Applet;

/**
 * @author zzm
 */
public interface EgovaLogger {
    void debug(Object logMessage);
    void debug(Object logMessage, Throwable t);

	void info(Object logMessage);
	void info(Object logMessage, Throwable t);
	
	void warn(Object logMessage);
	void warn(Object logMessage, Throwable t);
	
	void error(Object logMessage);
	void error(Object logMessage, Throwable t);
	 
	void error(String logMessage, Throwable t, String showMessage)
		throws EgovaException;
	void error(String logMessage, String showMessage)
		throws EgovaException;
	
    /*void error(
            String logMessage, 
            ServletRequest req, 
            ServletResponse res,
            String showMessage);
    
    void error(
            String logMessage,
            String showMessage,
            Applet destApplet);*/
}
