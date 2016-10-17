package com.egova.webservice.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 区域考评统计接口入参
 * @author yinzhinyy
 *
 */
@XmlRootElement(name="request")
public class StaticQueryRequest {
	
	private Date startTime;
	private Date endTime;
	private int regionID;
	
	public Date getStartTime() {
		return startTime;
	}
	
	@XmlElement
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	
	@XmlElement
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public int getRegionID() {
		return regionID;
	}
	
	@XmlElement
	public void setRegionID(int regionID) {
		this.regionID = regionID;
	}
	
	public StaticQueryRequest(Date startTime, Date endTime, int regionID) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.regionID = regionID;
	}
	
	public StaticQueryRequest() {}
}
