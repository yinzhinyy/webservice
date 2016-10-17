package com.egova.client.bean;

import java.util.Date;

/**
 * @author XueWen
 * @Description: TODO 
 * @date Oct 13, 2015 3:11:24 PM 
 * @version V1.0  
 */
public class DispatchRec {
	private Integer recID;
	private String taskNum;
	private String eventTypeName;
	private String eventLevelName;
	private String  recTypeName;
	private String  mainTypeName;
	private String  subTypeName;
	private String  eventSrcName;
	private String  eventDesc;
	private String  address;
	private Float  coordinatex;
	private Float  coordinatey;
	private String  districtCode;
	private String  districtName;
	private String  streetCode;
	private String  streetName;
	private String  communityCode;
	private String  communityName;
	private String  cellCode;
	private String  cellName;
	private Date  acceptTime;
	private String acceptUser;
	private String reporterName;
	private String reportContact;
	private Date reportTime;
	private Date dispatchTime;
	private Float dealLimit;
	private Date deadLine;
	private String dispatchPartName;
	private String dispatchUnitName;
	private String dispatchRoleName;
	private String dispatchContent;
	private String caseDesc;
	private String casePosDesc;
	//登记案卷patrol为空
	private Integer patrolID;
	private String patrolName;
	//记录用
	private Date createTime;
	private Date successTime;
	private int dispatched;
	private int dispatchCount;
	private int archived;
	//增加字段，记录批转信息
	private String transOpinion;
	private Date transTime;
	private String departmentName;
	
	public Integer getPatrolID() {
		return patrolID;
	}
	public void setPatrolID(Integer patrolID) {
		this.patrolID = patrolID;
	}
	public String getPatrolName() {
		return patrolName;
	}
	public void setPatrolName(String patrolName) {
		this.patrolName = patrolName;
	}
	public Date getSuccessTime() {
		return successTime;
	}
	public void setSuccessTime(Date successTime) {
		this.successTime = successTime;
	}
	public int getDispatchCount() {
		return dispatchCount;
	}
	public void setDispatchCount(int dispatchCount) {
		this.dispatchCount = dispatchCount;
	}
	public String getTransOpinion() {
		return transOpinion;
	}
	public void setTransOpinion(String transOpinion) {
		this.transOpinion = transOpinion;
	}
	public Date getTransTime() {
		return transTime;
	}
	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	/**
	 * @return the recID
	 */
	public Integer getRecID() {
		return recID;
	}
	/**
	 * @param recID the recID to set
	 */
	public void setRecID(Integer recID) {
		this.recID = recID;
	}
	/**
	 * @return the taskNum
	 */
	public String getTaskNum() {
		return taskNum;
	}
	/**
	 * @param taskNum the taskNum to set
	 */
	public void setTaskNum(String taskNum) {
		this.taskNum = taskNum;
	}
	/**
	 * @return the eventTypeName
	 */
	public String getEventTypeName() {
		return eventTypeName;
	}
	/**
	 * @param eventTypeName the eventTypeName to set
	 */
	public void setEventTypeName(String eventTypeName) {
		this.eventTypeName = eventTypeName;
	}
	/**
	 * @return the eventLevelName
	 */
	public String getEventLevelName() {
		return eventLevelName;
	}
	/**
	 * @param eventLevelName the eventLevelName to set
	 */
	public void setEventLevelName(String eventLevelName) {
		this.eventLevelName = eventLevelName;
	}
	/**
	 * @return the recTypeName
	 */
	public String getRecTypeName() {
		return recTypeName;
	}
	/**
	 * @param recTypeName the recTypeName to set
	 */
	public void setRecTypeName(String recTypeName) {
		this.recTypeName = recTypeName;
	}
	/**
	 * @return the mainTypeName
	 */
	public String getMainTypeName() {
		return mainTypeName;
	}
	/**
	 * @param mainTypeName the mainTypeName to set
	 */
	public void setMainTypeName(String mainTypeName) {
		this.mainTypeName = mainTypeName;
	}
	/**
	 * @return the subTypeName
	 */
	public String getSubTypeName() {
		return subTypeName;
	}
	/**
	 * @param subTypeName the subTypeName to set
	 */
	public void setSubTypeName(String subTypeName) {
		this.subTypeName = subTypeName;
	}
	/**
	 * @return the eventsrcName
	 */
	public String getEventSrcName() {
		return eventSrcName;
	}
	/**
	 * @param eventsrcName the eventsrcName to set
	 */
	public void setEventSrcName(String eventSrcName) {
		this.eventSrcName = eventSrcName;
	}
	/**
	 * @return the eventDesc
	 */
	public String getEventDesc() {
		return eventDesc;
	}
	/**
	 * @param eventDesc the eventDesc to set
	 */
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the coordinatex
	 */
	public Float getCoordinatex() {
		return coordinatex;
	}
	/**
	 * @param coordinatex the coordinatex to set
	 */
	public void setCoordinatex(Float coordinatex) {
		this.coordinatex = coordinatex;
	}
	/**
	 * @return the coordinatey
	 */
	public Float getCoordinatey() {
		return coordinatey;
	}
	/**
	 * @param coordinatey the coordinatey to set
	 */
	public void setCoordinatey(Float coordinatey) {
		this.coordinatey = coordinatey;
	}
	/**
	 * @return the districtCode
	 */
	public String getDistrictCode() {
		return districtCode;
	}
	/**
	 * @param districtCode the districtCode to set
	 */
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	/**
	 * @return the districtName
	 */
	public String getDistrictName() {
		return districtName;
	}
	/**
	 * @param districtName the districtName to set
	 */
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	/**
	 * @return the streetCode
	 */
	public String getStreetCode() {
		return streetCode;
	}
	/**
	 * @param streetCode the streetCode to set
	 */
	public void setStreetCode(String streetCode) {
		this.streetCode = streetCode;
	}
	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}
	/**
	 * @param streetName the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	/**
	 * @return the communitCode
	 */
	public String getCommunityCode() {
		return communityCode;
	}
	/**
	 * @param communitCode the communitCode to set
	 */
	public void setCommunityCode(String communityCode) {
		this.communityCode = communityCode;
	}
	/**
	 * @return the communitName
	 */
	public String getCommunityName() {
		return communityName;
	}
	/**
	 * @param communitName the communitName to set
	 */
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	/**
	 * @return the cellCode
	 */
	public String getCellCode() {
		return cellCode;
	}
	/**
	 * @param cellCode the cellCode to set
	 */
	public void setCellCode(String cellCode) {
		this.cellCode = cellCode;
	}
	/**
	 * @return the cellName
	 */
	public String getCellName() {
		return cellName;
	}
	/**
	 * @param cellName the cellName to set
	 */
	public void setCellName(String cellName) {
		this.cellName = cellName;
	}
	/**
	 * @return the acceptTime
	 */
	public Date getAcceptTime() {
		return acceptTime;
	}
	/**
	 * @param acceptTime the acceptTime to set
	 */
	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}
	/**
	 * @return the acceptUser
	 */
	public String getAcceptUser() {
		return acceptUser;
	}
	/**
	 * @param acceptUser the acceptUser to set
	 */
	public void setAcceptUser(String acceptUser) {
		this.acceptUser = acceptUser;
	}
	/**
	 * @return the reporterName
	 */
	public String getReporterName() {
		return reporterName;
	}
	/**
	 * @param reporterName the reporterName to set
	 */
	public void setReporterName(String reporterName) {
		this.reporterName = reporterName;
	}
	/**
	 * @return the reportContact
	 */
	public String getReportContact() {
		return reportContact;
	}
	/**
	 * @param reportContact the reportContact to set
	 */
	public void setReportContact(String reportContact) {
		this.reportContact = reportContact;
	}
	/**
	 * @return the reportTime
	 */
	public Date getReportTime() {
		return reportTime;
	}
	/**
	 * @param reportTime the reportTime to set
	 */
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	/**
	 * @return the dispatchTime
	 */
	public Date getDispatchTime() {
		return dispatchTime;
	}
	/**
	 * @param dispatchTime the dispatchTime to set
	 */
	public void setDispatchTime(Date dispatchTime) {
		this.dispatchTime = dispatchTime;
	}
	/**
	 * @return the dealLimit
	 */
	public Float getDealLimit() {
		return dealLimit;
	}
	/**
	 * @param dealLimit the dealLimit to set
	 */
	public void setDealLimit(Float dealLimit) {
		this.dealLimit = dealLimit;
	}
	/**
	 * @return the deadLine
	 */
	public Date getDeadLine() {
		return deadLine;
	}
	/**
	 * @param deadLine the deadLine to set
	 */
	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}
	/**
	 * @return the dispatchPartName
	 */
	public String getDispatchPartName() {
		return dispatchPartName;
	}
	/**
	 * @param dispatchPartName the dispatchPartName to set
	 */
	public void setDispatchPartName(String dispatchPartName) {
		this.dispatchPartName = dispatchPartName;
	}
	/**
	 * @return the dispatchUnitName
	 */
	public String getDispatchUnitName() {
		return dispatchUnitName;
	}
	/**
	 * @param dispatchUnitName the dispatchUnitName to set
	 */
	public void setDispatchUnitName(String dispatchUnitName) {
		this.dispatchUnitName = dispatchUnitName;
	}
	/**
	 * @return the dispatchRoleName
	 */
	public String getDispatchRoleName() {
		return dispatchRoleName;
	}
	/**
	 * @param dispatchRoleName the dispatchRoleName to set
	 */
	public void setDispatchRoleName(String dispatchRoleName) {
		this.dispatchRoleName = dispatchRoleName;
	}
	/**
	 * @return the dispatchContent
	 */
	public String getDispatchContent() {
		return dispatchContent;
	}
	/**
	 * @param dispatchContent the dispatchContent to set
	 */
	public void setDispatchContent(String dispatchContent) {
		this.dispatchContent = dispatchContent;
	}
	/**
	 * @return the caseDesc
	 */
	public String getCaseDesc() {
		return caseDesc;
	}
	/**
	 * @param caseDesc the caseDesc to set
	 */
	public void setCaseDesc(String caseDesc) {
		this.caseDesc = caseDesc;
	}
	/**
	 * @return the casePosDesc
	 */
	public String getCasePosDesc() {
		return casePosDesc;
	}
	/**
	 * @param casePosDesc the casePosDesc to set
	 */
	public void setCasePosDesc(String casePosDesc) {
		this.casePosDesc = casePosDesc;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the dispatched
	 */
	public int getDispatched() {
		return dispatched;
	}
	/**
	 * @param dispatched the dispatched to set
	 */
	public void setDispatched(int dispatched) {
		this.dispatched = dispatched;
	}
	/**
	 * @return the archived
	 */
	public int getArchived() {
		return archived;
	}
	/**
	 * @param archived the archived to set
	 */
	public void setArchived(int archived) {
		this.archived = archived;
	}
	
	
}
