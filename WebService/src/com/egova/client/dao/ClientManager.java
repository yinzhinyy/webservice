package com.egova.client.dao;

import java.util.Map;

/**
 * @author XueWen
 * @Package com.egova.client.dao 
 * @Description: TODO 
 * @date Oct 13, 2015 2:56:24 PM 
 * @version V1.0  
 */
public interface ClientManager {
	
	/**
	 * 获取派遣接口XML，dispathed==0 && dispatchcount<5
	 * @return
	 */
	public Map<Integer,String> getDispatchXml();
	
	/**
	 * 派遣成功后修改torecdispatch表中的dispatched等字段状态
	 * @param recID
	 */
	public void updateRecDispatched(int recID);
	
	/**
	 * 未派遣成功时，派遣次数+1
	 * @param recID
	 */
	public void updateRecNotDispatched(int recID);
	
	/**
	 * 将未派遣成功案件的dispatchcount复位为0
	 */
	public void resetDispatchCount();
	
	/**
	 * 保存报文
	 * @param recID
	 * @param opName
	 * @param opMark
	 * @param xmlType
	 * @param xmlContent
	 * @param rptChannel
	 */
	public void saveXmlContent(int recID,String opName,String opMark,int isSuccess,String requestXml,String xmlContent,String errorDesc);

	/**
	 * 采集雨花区专业部门案卷至dlmis.torecdispatch
	 */
	public void collect();

}
