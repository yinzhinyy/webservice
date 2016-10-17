package com.egova.webservice.dao.procedure.bean;

/**
 * @author XueWen
 * @Package com.egova.webservice.service.procedure.bean 
 * @Description: TODO 
 * @date Oct 9, 2015 5:46:55 PM 
 * @version V1.0  
 */
public class WFActTransit {
	protected int humanID;
	protected int actID; 
	protected String itemType;
	protected String transInfo;
	protected String transMemo;
	protected String limitInfo;
	public int getHumanID() {
		return humanID;
	}
	public void setHumanID(int humanID) {
		this.humanID = humanID;
	}
	public int getActID() {
		return actID;
	}
	public void setActID(int actID) {
		this.actID = actID;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getTransInfo() {
		return transInfo;
	}
	public void setTransInfo(String transInfo) {
		this.transInfo = transInfo;
	}
	public String getTransMemo() {
		return transMemo;
	}
	public void setTransMemo(String transMemo) {
		this.transMemo = transMemo;
	}
	public String getLimitInfo() {
		return limitInfo;
	}
	public void setLimitInfo(String limitInfo) {
		this.limitInfo = limitInfo;
	}
}
