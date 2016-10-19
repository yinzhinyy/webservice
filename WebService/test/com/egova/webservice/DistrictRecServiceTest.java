package com.egova.webservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.egova.webservice.service.DistrictRecService;
import com.egova.webservice.service.impl.DistrictRecServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-datasource.xml", "classpath:applicationContext.xml"})
public class DistrictRecServiceTest {  
	
	@Autowired
	private DistrictRecService service;
	
//	@Test
	public void testFeedBack() {
		String xml = getRequestXML();
		service.feedback(xml);
	}
	
	@Test
	public void testSspFeedBack() {
		String xml = getRequestXML();
		service.sspFeedback(xml);
	}
	
//	@Test
	public void testStaticQuery() {
		String xml = getQueryXML();
		service.staticQuery(xml);
	}

	private String getQueryXML() {
		String xml = "<?xml version='1.0' encoding='UTF-8' ?>";
		xml += "<request>" 
				+ "<regionID>" + 0 + "</regionID>"
				+ "<startTime>" + "2016-04-01T16:53:01.401+08:00" + "</startTime>"
		        + "<endTime>" + "2016-09-13T16:53:01.401+08:00" + "</endTime>"
		        + "</request>";
		return xml;
	}

	private String getRequestXML() {
		int recID = 2534615;
		String xml = "<?xml version='1.0' encoding='UTF-8' ?>";
		xml += "<request>" 
				+ "<recID>" + recID + "</recID>"
				+ "<transOpinion>" + "请审核！" + "</transOpinion>"
		        + "<transTime>" + "2016-09-23T16:46:16.936+08:00" + "</transTime>"
		        + "<departmentName>" + "洞井街道" + "</departmentName>"
		        + "</request>";
		return xml;
	}
	
}  

