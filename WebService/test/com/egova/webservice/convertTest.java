package com.egova.webservice;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import com.egova.webservice.bean.RegionStatEval;
import com.egova.webservice.bean.StaticQueryRequest;
import com.egova.webservice.bean.StaticQueryResponse;
import com.egova.webservice.util.XmlParser;

public class convertTest {
	
	@Test
	public void testConvertToXml() {
		StaticQueryRequest request = new StaticQueryRequest();
		request.setStartTime(new Date());
		request.setRegionID(0);
		String xml = XmlParser.convertToXml(request);
//		System.out.println(xml);
	}
	
	@Test
	public void testConvertToXml2() {
		StaticQueryResponse response = new StaticQueryResponse();
		response.setErrorCode(1);
		response.setErrorDesc("haha");
		ArrayList<RegionStatEval> list = new ArrayList<RegionStatEval>();
		response.setList(list);
		String xml = XmlParser.convertToXml(response);
//		System.out.println(xml);
	}
	
	@Test
	public void testConvertToBean() {
		String xml = "<?xml version='1.0' encoding='UTF-8' ?>";
		xml += "<request>" 
				+ "<regionID>" + "11" + "</regionID>"
				+ "<startTime>" + "2016-09-13T16:53:01.401+08:00" + "</startTime>"
		        + "<endTime>" + "2016-09-13T16:53:01.401+08:00" + "</endTime>"
		        + "</request>";
		StaticQueryRequest request = XmlParser.convertToJavaBean(xml, StaticQueryRequest.class);
//		System.out.println(request.getRegionID() + request.getStartTime().toString() + request.getEndTime().toString());
	}

}
