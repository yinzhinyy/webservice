/**
 * @author Wangshiming
 * @createTime 2015-1-27
 * @filename EgovaException.java
 * @team  Dongnan Jiangxi-Fujian
 */
package com.egova.webservice.util;

/**
 * @author Wangshiming
 * Egova异常，用来统一处理异常
 */
public class EgovaException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
    private String errorMsg = null;
    private String paramsInfo = null;
    private String message = null;
	public EgovaException(Exception e, String errorMsg, String paramsInfo){
		this.errorMsg = errorMsg;
		this.paramsInfo = paramsInfo;
		this.initCause(e);
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getParamsInfo() {
		return paramsInfo;
	}
	public void setParamsInfo(String paramsInfo) {
		this.paramsInfo = paramsInfo;
	}
	public EgovaException(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
}
