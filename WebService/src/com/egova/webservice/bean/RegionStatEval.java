package com.egova.webservice.bean;

import javax.xml.bind.annotation.XmlElement;

/**
 * 区域考评统计
 * @author yinzhinyy
 *
 */
public class RegionStatEval {
	
	private String regionName;
	private Integer fisrtRecNum;
	private Integer firstRepeatNum;
	private Float firstPointNum;
	private Integer secondRecNum;
	private Integer secondRepeatNum;
	private Float secondPointNum;
	private Integer thirdRecNum;
	private Integer thirdRepeatNum;
	private Float thirdPointNum;
	private Integer totalRecNum;
	private Float totalPoint;
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	@XmlElement
	public Integer getFisrtRecNum() {
		return fisrtRecNum;
	}
	public void setFisrtRecNum(Integer fisrtRecNum) {
		this.fisrtRecNum = fisrtRecNum;
	}
	@XmlElement
	public Integer getFirstRepeatNum() {
		return firstRepeatNum;
	}
	public void setFirstRepeatNum(Integer firstRepeatNum) {
		this.firstRepeatNum = firstRepeatNum;
	}
	@XmlElement
	public Float getFirstPointNum() {
		return firstPointNum;
	}
	public void setFirstPointNum(Float firstPointNum) {
		this.firstPointNum = firstPointNum;
	}
	@XmlElement
	public Integer getSecondRecNum() {
		return secondRecNum;
	}
	public void setSecondRecNum(Integer secondRecNum) {
		this.secondRecNum = secondRecNum;
	}
	@XmlElement
	public Integer getSecondRepeatNum() {
		return secondRepeatNum;
	}
	public void setSecondRepeatNum(Integer secondRepeatNum) {
		this.secondRepeatNum = secondRepeatNum;
	}
	@XmlElement
	public Float getSecondPointNum() {
		return secondPointNum;
	}
	public void setSecondPointNum(Float secondPointNum) {
		this.secondPointNum = secondPointNum;
	}
	@XmlElement
	public Integer getThirdRecNum() {
		return thirdRecNum;
	}
	public void setThirdRecNum(Integer thirdRecNum) {
		this.thirdRecNum = thirdRecNum;
	}
	@XmlElement
	public Integer getThirdRepeatNum() {
		return thirdRepeatNum;
	}
	public void setThirdRepeatNum(Integer thirdRepeatNum) {
		this.thirdRepeatNum = thirdRepeatNum;
	}
	@XmlElement
	public Float getThirdPointNum() {
		return thirdPointNum;
	}
	public void setThirdPointNum(Float thirdPointNum) {
		this.thirdPointNum = thirdPointNum;
	}
	@XmlElement
	public Integer getTotalRecNum() {
		return totalRecNum;
	}
	public void setTotalRecNum(Integer totalRecNum) {
		this.totalRecNum = totalRecNum;
	}
	@XmlElement
	public Float getTotalPoint() {
		return totalPoint;
	}
	public void setTotalPoint(Float totalPoint) {
		this.totalPoint = totalPoint;
	}
	
}
