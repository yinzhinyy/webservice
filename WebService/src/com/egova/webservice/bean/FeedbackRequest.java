package com.egova.webservice.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author XueWen
 * @Description: 长沙市县对接请求参数  
 * @date Oct 10, 2015 9:14:58 AM 
 * @version V1.0  
 */
@XmlRootElement(name="request")
public class FeedbackRequest {
	
	private int recID;
	private String transOpinion;
	private Date transTime;
	private String departmentName;
	
	/**
	 * 
	 */
	public FeedbackRequest() {
		super();
	}


	/**
	 * @param recID
	 * @param transOpinion
	 * @param transTime
	 */
	public FeedbackRequest(int recID, String transOpinion, Date transTime, String departmentName) {
		super();
		this.recID = recID;
		this.transOpinion = transOpinion;
		this.transTime = transTime;
		this.departmentName = departmentName;
	}


	/**
	 * @return the recID
	 */
	public int getRecID() {
		return recID;
	}


	/**
	 * @param recID the recID to set
	 */
	@XmlElement
	public void setRecID(int recID) {
		this.recID = recID;
	}


	/**
	 * @return the transOpinion
	 */
	public String getTransOpinion() {
		return transOpinion;
	}


	/**
	 * @param transOpinion the transOpinion to set
	 */
	@XmlElement
	public void setTransOpinion(String transOpinion) {
		this.transOpinion = transOpinion;
	}


	/**
	 * @return the transTime
	 */
	public Date getTransTime() {
		return transTime;
	}

	/**
	 * @param transTime the transTime to set
	 */
	@XmlElement
	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}


	public String getDepartmentName() {
		return departmentName;
	}


	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	
}
