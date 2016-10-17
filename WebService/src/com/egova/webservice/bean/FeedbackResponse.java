package com.egova.webservice.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 长沙-接口调用返回bean
 * @author yinzhinyy
 *
 */
@XmlRootElement(name="request")
public class FeedbackResponse {

	private CommonResult commonResult;

	public CommonResult getCommonResult() {
		return commonResult;
	}

	@XmlElement(name="commonResult")
	public void setCommonResult(CommonResult commonResult) {
		this.commonResult = commonResult;
	}
	
	public FeedbackResponse(CommonResult commonResult) {
		super();
		this.commonResult = commonResult;
	}
	
	public FeedbackResponse() {
		super();
	}
		
}
