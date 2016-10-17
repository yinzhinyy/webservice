package com.egova.webservice.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 长沙市-区级平台对接接口
 * @author yinzhinyy
 *
 */
@WebService
public interface DistrictRecService {

	/**
	 * 问题处置反馈接口
	 * @param requestXML
	 * @return
	 */
	@WebMethod()
	public String feedback(@WebParam(name="requestXML") String requestXML);
	
	/**
	 * 随手拍问题处置反馈接口
	 * @param requestXML
	 * @return
	 */
	@WebMethod
	public String sspFeedback(@WebParam(name="requestXML") String requestXML);
	
	/**
	 * 区域考评统计接口
	 * @param requestXML
	 * @return
	 */
	@WebMethod
	public String staticQuery(@WebParam(name="requestXML") String requestXML);
	
}
