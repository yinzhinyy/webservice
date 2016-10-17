package com.egova.client.dao.impl;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egova.client.dao.ClientManager;
import com.egova.client.dao.ClientService;
import com.egova.client.factory.ClientBeanFactory;
import com.egova.client.service.yhdistrict.IEventSynServiceProxy;
import com.egova.webservice.bean.CommonResult;
import com.egova.webservice.bean.FeedbackResponse;
import com.egova.webservice.util.XmlParser;


/**
 * @author XueWen
 * @Description: 第三方接口调用
 * @date Oct 13, 2015 2:36:29 PM 
 * @version V1.0  
 */
@Service("clientService")
public class ClientServiceImpl implements ClientService {

	Log logger = LogFactory.getLog(ClientServiceImpl.class);
	
	@Autowired 
	private ClientManager clientManager;
	
	public void process() {
		logger.info("=============开始调用区级平台派遣接口process=============");
		//获取需要派遣至区级品台的案卷列表
		Map<Integer,String> xmlMap = clientManager.getDispatchXml();
		if(xmlMap == null|| xmlMap.size() < 1){
			logger.info("暂无需下发至雨花区平台的问题！");
		} else {
			String errorDesc = null;
			Set<Entry<Integer, String>> entrySet = xmlMap.entrySet();
			for(Entry<Integer,String> entry : entrySet){
				int isSuccess = 0;
				int recID = entry.getKey();
				String responseXml = "";
				String requestXml = entry.getValue();
				logger.info("requestXML: " + requestXml);
				String recTypeName = getRecTypeName(requestXml);
				try {
					IEventSynServiceProxy webService = ClientBeanFactory.getWebService();
					if(webService != null){
						//派遣接口调用，分普通案卷和随手拍两种情况
						if(isSspRec(recTypeName)) {
							responseXml = webService.sspProcess(requestXml);
						} else {
							responseXml = webService.process(requestXml);
						}
					}
					/*String methodName = null;
					if(isSspRec(recTypeName)) {
						methodName = "sspProcess";
					} else {
						methodName = "process";
					}
					YHHttpClient client = HttpClientFactory.getHttpClient();
					responseXml  = client.process(methodName, requestXml);*/
				} catch (Exception e) {
					clientManager.updateRecNotDispatched(recID);
					logger.error("区级平台接口无法连接！", e);
					e.printStackTrace();
				}
				logger.info("responseXML: " + responseXml);
				//派遣成功后修改案卷派遣状态
				FeedbackResponse feedback = XmlParser.convertToJavaBean(responseXml, FeedbackResponse.class);
				if(feedback != null){
					//如果报错，则终止服务，以免重复派遣
					clientManager.updateRecDispatched(recID);
					CommonResult result = feedback.getCommonResult();
					if(result != null) {
						isSuccess = result.getErrorCode();
						errorDesc = result.getErrorDesc();
					}
				} else {
					clientManager.updateRecNotDispatched(recID);
				}
				//保存接口报文
				try{
					clientManager.saveXmlContent(recID, recTypeName, "调用雨花区派遣接口", isSuccess, requestXml, responseXml, errorDesc);
				}catch(Exception e){
					logger.error("保存区级平台派遣接口调用报文出错！", e);
					e.printStackTrace();
				}
			}
		}
		logger.info("=============结束调用区级平台派遣接口process=============");
	}

	private String getRecTypeName(String requestXML) {
		int startIndex = requestXML.indexOf("<recTypeName>");
		startIndex += 13;
		int endIndex = requestXML.indexOf("</recTypeName>");
		return requestXML.substring(startIndex, endIndex).trim();
	}
	
	private boolean isSspRec(String recTypeName) {
		return "市民随手拍".equals(recTypeName);
	}

}
