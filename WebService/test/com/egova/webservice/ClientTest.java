package com.egova.webservice;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.egova.client.dao.ClientManager;
import com.egova.client.dao.ClientService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-datasource.xml", "classpath:applicationContext.xml"})
public class ClientTest {
	
	@Autowired 
	private ClientManager clientManager;
	
	@Autowired 
	private ClientService clientService;
	
//	@Test
	public void testProcess(){
		clientService.process();
	}
	
//	@Test
	public void testGetDispatchXml() {
		Map<Integer,String> xmlMap = clientManager.getDispatchXml();
		Set<Entry<Integer, String>> entrySet = xmlMap.entrySet();
		for(Entry<Integer,String> entry : entrySet){ 
			System.out.println(entry.getValue());
		}
	}
	
//	@Test
	public void testUpdateRecDispatched() {
		int recID = 2536871;
		clientManager.updateRecDispatched(recID);
	}
	
//	@Test
	public void testSaveXml() {
		clientManager.saveXmlContent(1, "tester", "test", 1, "<?xml version='1.0' encoding='utf-8'?><request><params><recID>2537943<recID><taskNum>16091201571</taskNum><eventTypeName>事件</eventTypeName><eventLevelName>区级</eventLevelName><recTypeName>重点 </recTypeName><mainTypeName>主次干道采集标准</mainTypeName><subTypeName>隔离墩柱、麻石球(3类)</subTypeName><eventSrcName>巡查上报</eventSrcName><eventDesc>麻石球移位</eventDesc><address>雨花区新建东路102号宏轩花苑出入口西侧5米</address><coordinateX>112.9911</coordinateX><coordinateY>28.158783</coordinateY><districtCode></districtCode><districtName>雨花区</districtName><streetCode>159</streetCode><streetName>砂子塘街道</streetName><communityCode>1194</communityCode><communityName>雨花区梨子山区</communityName><cellCode>10380</cellCode><cellName>43011101100401</cellName><acceptTime></acceptTime><acceptUser>冯云</acceptUser><reportName></reportName><reportContact></reportContact><reportTime>2016-09-12 15:41:42</reportTime><dispatchTime>2016-09-12 16:50:57</dispatchTime><dealLimit>71.95</dealLimit><deadLine>2016-09-15 16:49:00</DeadLine><dispatchPartName>雨花区派遣员6</dispatchPartName><dispatchUnitName>雨花区市政局</dispatchUnitName><dispatchRoleName>雨花区市政局</dispatchRoleName><dispatchContent></dispatchContent><case_desc>麻石球移位</case_desc><case_pos_desc>雨花区新建东路102号宏轩花苑出入口西侧5米</case_pos_desc></params><attachment><item><content>/253/79/Event2537943/1_1_20160912154025.jpg</content><type>jpg</type><fileName>20160912154025.jpg</fileName></item><item><content>/253/79/Event2537943/1_2_20160912154020.jpg</content><type>jpg</type><fileName>20160912154020.jpg</fileName></item></attachment></request>", "resonseXML", "right");
	}
	
	@Test
	public void testCollect() {
		clientManager.collect();
	}

}
