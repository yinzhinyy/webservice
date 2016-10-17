package com.egova.webservice;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.egova.webservice.bean.FeedbackRequest;
import com.egova.webservice.common.ResultInfo;
import com.egova.webservice.config.SysConfig;
import com.egova.webservice.dao.DistrictRecManager;
import com.egova.webservice.dao.procedure.PoConfiguredStat;
import com.egova.webservice.util.XmlParser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-datasource.xml", "classpath:applicationContext.xml"})
public class DistrictRecManagerTest {
	
	@Autowired
	private DistrictRecManager manager;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
//	@Test
	public void testUpdateDepartment() {
		manager.updateDistrictDepartment(2535115, "高桥街道");
	}
	
//	@Test
	public void testTransit() {
		String xml = getRequestXML();
		FeedbackRequest request = XmlParser.convertToJavaBean(xml, FeedbackRequest.class);
		ResultInfo result = manager.transit(request);
		System.out.println(result.getMessage());
		System.out.println(result.getData());
	}
	
//	@Test
	public void testPoConfiguredStat() {
	    Map<String, Object> map = new HashMap<String, Object>();
	    int queryID = 1168;
	    String whereSQL = "where createtime >= to_date('2016-04-01 00:00:00','yyyy-mm-dd hh24:mi:ss')"
	    							+ " and createtime <= to_date('2016-09-01 00:00:00','yyyy-mm-dd hh24:mi:ss')"
	    							+ " and districtid not in (7, 8)";
	    int humanID = SysConfig.MIS_REC_AUTOASSIGN_HUMANID;
		try {
			PoConfiguredStat procedure = new PoConfiguredStat(jdbcTemplate);
		    map = procedure.execute(queryID, whereSQL, 0, "", 0, 0, 0, 5, humanID, 0);
	    } catch (Exception e) {
		      e.printStackTrace();
	    }
		System.out.println(map.get("dataStr"));
	}
	
	private String getRequestXML() {
		int recID = 2232364;
		String xml = "<?xml version='1.0' encoding='UTF-8' ?>";
		xml += "<request>" 
				+ "<recID>" + recID + "</recID>"
				+ "<transOpinion>" + "just for test" + "</transOpinion>"
		        + "<transTime>" + "2016-09-13T16:53:01.401+08:00" + "</transTime>"
		        + "<departmentName>" + "department" + "</departmentName>"
		        + "</request>";
		return xml;
	}

}
