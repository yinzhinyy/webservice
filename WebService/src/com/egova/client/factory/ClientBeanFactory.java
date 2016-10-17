package com.egova.client.factory;

import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.egova.client.service.yhdistrict.IEventSynServiceProxy;
import com.egova.client.service.yhdistrict.IEventSynServiceProxyService;
import com.egova.webservice.config.SysConfig;

/**
 * @author XueWen
 * @Description: 获取websercie接口
 * @date Oct 13, 2015 3:03:19 PM 
 * @version V1.0  
 */
public class ClientBeanFactory {
	
	private final static Log logger = LogFactory.getLog(ClientBeanFactory.class);
	
	private final static Object monitor = new Object();
	
	private static IEventSynServiceProxy webService;
	
	/**
	 * 获取webservice接口
	 */
	public static IEventSynServiceProxy getWebService(){
		if(webService == null) {
			synchronized(monitor) {
				if(webService == null) {
					initIEventSynServiceProxy();
				}
			}
		}
		return webService;
	}
	
	private static void initIEventSynServiceProxy() {
		URL url = null;
		try {
			url = new URL(SysConfig.MIS_REC_DISPATCH_SERVER);
		} catch (Exception e) {
			logger.error("无法初始化派遣接口WSDL地址");
			e.printStackTrace();
		}
		IEventSynServiceProxyService service = new IEventSynServiceProxyService(url);
		webService =  (IEventSynServiceProxy)service.getIEventSynServiceProxyPort();
	}
}
