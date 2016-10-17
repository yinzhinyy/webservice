package com.egova.webservice.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.egova.client.bean.DispatchRec;
import com.egova.webservice.bean.CommonResult;
import com.egova.webservice.bean.FeedbackRequest;
import com.egova.webservice.bean.FeedbackResponse;
import com.egova.webservice.bean.RegionStatEval;
import com.egova.webservice.bean.StaticQueryRequest;
import com.egova.webservice.bean.StaticQueryResponse;
import com.egova.webservice.common.ResultInfo;
import com.egova.webservice.dao.DistrictRecManager;
import com.egova.webservice.service.DistrictRecService;
import com.egova.webservice.util.DateUtils;
import com.egova.webservice.util.XmlParser;

//@Service("service")
public class DistrictRecServiceImpl implements DistrictRecService{
	Log log = LogFactory.getLog(DistrictRecServiceImpl.class);
	
	@Autowired
	private DistrictRecManager districtRecManager;
	
	public String feedback(String requestXML) {
		log.info("区级平台调用feedback，时间：" + DateUtils.dateTimeToStr(new Date()));
		String responseXml = doProcess(requestXML);
		return responseXml;
	}
	
	public String sspFeedback(String requestXML) {
		log.info("区级平台调用sspFeedback，时间：" + DateUtils.dateTimeToStr(new Date()));
		String responseXml = doProcess(requestXML);
		return responseXml;
	}

	private String doProcess(String requestXML) {
		log.info(requestXML);
		FeedbackRequest request = XmlParser.convertToJavaBean(requestXML, FeedbackRequest.class);
		int errorCode = -1;
		String errorDesc = "";
		if(null == request){
			errorDesc = "传入参数有误，请确认格式是否正确！";
		} else if(!hasDepartmentName(request)) {
			errorDesc = "处理部门名称不能为空！";
		} else {
			//判断是否已连上数据库
			try {
				int recID = request.getRecID();
				DispatchRec dRec = null;
				dRec = districtRecManager.getDispatchedRec(recID).get(0);
				if(!isDispatchedRec(dRec)) {
					errorDesc = "在已派遣问题中找不到该问题recID！";
				} else {
					String transOpinion = request.getTransOpinion();
					Date transTime = request.getTransTime() == null ? new Date() : request.getTransTime();
					String departmentName = request.getDepartmentName();
					//更新问题反馈结果
					try {
						districtRecManager.updateRecTransitInfo(recID, transOpinion, transTime, departmentName);
						//更新问题区级平台专业部门，对成功与否不做判断
						districtRecManager.updateDistrictDepartment(recID, departmentName);
						ResultInfo result = new ResultInfo(true);
						//随手拍上报案卷执行批转
						if(isSspPatrolRec(dRec)) {
							result = districtRecManager.transit(request);
						}
						if(result.isSuccess()) {
							errorCode = 1;
							errorDesc = "处置结果反馈成功！";
						} else {
							errorCode = -1;
							errorDesc = result.getMessage();
						}
					} catch(Exception e) {
						errorDesc = "问题反馈处理出错！";
						log.error("问题反馈处理出错！", e);
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				errorDesc ="后台数据连接中断，请重试！";
				e.printStackTrace();
			}
		}
		FeedbackResponse response = new FeedbackResponse();
		response.setCommonResult(new CommonResult(errorCode, errorDesc));
		String responseXml = XmlParser.convertToXml(response);
		log.info(responseXml);
		return responseXml;
	}

	private boolean isDispatchedRec(DispatchRec dRec) {
		return null != dRec;
	}

	private boolean isSspPatrolRec(DispatchRec dRec) {
		return "市民随手拍".equals(dRec.getRecTypeName()) && null != dRec.getPatrolName();
	}
	
	private boolean hasDepartmentName(FeedbackRequest request) {
		return StringUtils.hasText(request.getDepartmentName());
	}

	public String staticQuery(String requestXML) {
		log.info("区级平台调用staticQuery，时间：" + DateUtils.dateTimeToStr(new Date()));
		String responseXml = doQuery(requestXML);
		return responseXml;
	}

	private String doQuery(String requestXML) {
		log.info(requestXML);
		StaticQueryRequest request = XmlParser.convertToJavaBean(requestXML, StaticQueryRequest.class);
		List<RegionStatEval> list = null;
		int errorCode = -1;
		String errorDesc = "";
		if(null == request){
			errorDesc = "传入参数有误，请确认格式是否正确！";
		} else {
			//判断是否已连上数据库
			try {
				districtRecManager.getDispatchedRec(0);
				Date startTime = request.getStartTime();
				Date endTime = request.getEndTime();
				int regionID = request.getRegionID();
				//调用市级平台统计存过
				try {
					list = districtRecManager.staticQuery(startTime, endTime, regionID);
					errorCode = 1;
				} catch(Exception e) {
					errorDesc = "查询区域考评统计出错，请确认传入参数是否正确！";
					e.printStackTrace();
				}
			} catch (Exception e) {
				errorDesc ="后台数据连接中断，请重试！";
				e.printStackTrace();
			}
		}
		StaticQueryResponse response = new StaticQueryResponse();
		response.setErrorCode(errorCode);
		response.setErrorDesc(errorDesc);
		response.setList(list);
		String responseXml = XmlParser.convertToXml(response);
		log.info(responseXml);
		return responseXml;
	}
	
}
