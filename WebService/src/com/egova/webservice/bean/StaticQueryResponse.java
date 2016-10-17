package com.egova.webservice.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 区域考评统计返回参数
 * @author yinzhinyy
 *
 */
@XmlRootElement(name="request")
public class StaticQueryResponse {
	
	private int errorCode;
	private String errorDesc;
	private List<RegionStatEval> list;
	
	public int getErrorCode() {
		return errorCode;
	}
	
	@XmlElement
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getErrorDesc() {
		return errorDesc;
	}
	
	@XmlElement
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public List<RegionStatEval> getList() {
		return list;
	}

	@XmlElement
	public void setList(List<RegionStatEval> list) {
		this.list = list;
	}
	
	public StaticQueryResponse(int errorCode, String errorDesc, List<RegionStatEval> list) {
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
		this.list = list;
	}
	
	public StaticQueryResponse() {}
	
}
