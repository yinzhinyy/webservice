/**
 * @author Wangshiming
 * @createTime 2015-1-27
 * @filename LogUtils.java
 * @team  Dongnan Jiangxi-Fujian
 */
package com.egova.webservice.util.log;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.log4j.PropertyConfigurator;

import com.egova.webservice.util.EgovaException;



/**
 * @author Wangshiming
 * 日志打印工具类
 */
public class LogUtils {
	public static String newLine = "\n";
	static {
		if("\\".equals(File.separator)){//windows 下 log 换行符
			newLine = "\r\n";
		}
	}
	public static void error(String msg, Log logger,Exception e,Object...params){
		StringBuffer errorInfo = new StringBuffer(); //为打印log
		errorInfo.append(msg);
		errorInfo.append(newLine);
		errorInfo.append(e);
		errorInfo.append(newLine);
		
		StringBuffer paramsInfo = new StringBuffer();//参数信息
		int i = 1;
		if(params != null){
			for(Object param : params){
				i = getParam(paramsInfo, param, i, true);
			}
		}
		String errorInfoStr = errorInfo.toString();
		String paramsInfoStr = paramsInfo.toString();
		logger.error(errorInfoStr + paramsInfoStr);
		throw new EgovaException(e, errorInfoStr, paramsInfoStr);
	}
	
	public static void info(String msg, Log logger){
		logger.info(msg);
	}
	
	public static void warn(String msg, Log logger){
		logger.warn(msg);
	}
	
	public static int getParam(StringBuffer paramsInfo, Object param, int i, boolean isNewLine){//暂时没有考虑param为对象的情况
		if(param != null && param.getClass().isArray()){
			paramsInfo.append("{");
			for (int j=0;j<Array.getLength(param);j++){
				i = getParam(paramsInfo,Array.get(param,j),i,false);
			}
			paramsInfo.append("}");
		}else if(param != null && (param instanceof Collection)){
			paramsInfo.append("{");
			@SuppressWarnings("rawtypes")
			Iterator itr = ((Collection) param).iterator();
			while (itr.hasNext()){
				i = getParam(paramsInfo,itr.next(),i, false);
			}
			paramsInfo.append("}");	
		}else{
			paramsInfo.append("[参数" + (i++) + ":" + param + "]");
			if(isNewLine){//是否换行
				paramsInfo.append(newLine);
			}
		}
		return i;
	}
	
	/**
	 * 重新对log4j进行配置
	 * @param pro
	 */
	public static void reconfigure(Properties pro){
		PropertyConfigurator.configure(pro);
	}
}
