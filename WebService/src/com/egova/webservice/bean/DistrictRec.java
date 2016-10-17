package com.egova.webservice.bean;

import java.util.Date;

/**
 * 备用
 * 派遣至区级平台的案卷列表
 * @author yinzhinyy
 *
 */
public class DistrictRec {
	
	private int recID;
	private int eventSrcID;
	private String transOpinion;
	private Date transTime;
	private String departmentName;
	private int transStatus;
	private int ackFlag;
	private int actID;
	private int actdefID;

	public int getEventSrcID() {
		return eventSrcID;
	}
	public void setEventSrcID(int eventSrcID) {
		this.eventSrcID = eventSrcID;
	}
	public String getTransOpinion() {
		return transOpinion;
	}
	public void setTransOpinion(String transOpinion) {
		this.transOpinion = transOpinion;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public int getRecID() {
		return recID;
	}
	public void setRecID(int recID) {
		this.recID = recID;
	}
	public int getActID() {
		return actID;
	}
	public void setActID(int actID) {
		this.actID = actID;
	}
	public int getActdefID() {
		return actdefID;
	}
	public void setActdefID(int actdefID) {
		this.actdefID = actdefID;
	}
	public int getTransStatus() {
		return transStatus;
	}
	public void setTransStatus(int transStatus) {
		this.transStatus = transStatus;
	}
	public Date getTransTime() {
		return transTime;
	}
	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}
	public int getAckFlag() {
		return ackFlag;
	}
	public void setAckFlag(int ackFlag) {
		this.ackFlag = ackFlag;
	}
	
}
