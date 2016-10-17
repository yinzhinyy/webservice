package com.egova.webservice.bean;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author XueWen
 * @date Oct 27, 2015 6:04:24 PM 
 * @version V1.0  
 */
public class CommonResult {

	//返回结果编码
		private int errorCode;
		
		//返回结果描述
		private String errorDesc;

		/**
		 * 构造 
		 */
		public CommonResult() {
			super();
		}

		/**
		 * @param errorCode
		 * @param errorDesc
		 */
		public CommonResult(int errorCode, String errorDesc) {
			super();
			this.errorCode = errorCode;
			this.errorDesc = errorDesc;
		}

		/**
		 * @return the errorCode
		 */
		public int getErrorCode() {
			return errorCode;
		}

		/**
		 * @param errorCode the errorCode to set
		 */
		@XmlElement
		public void setErrorCode(int errorCode) {
			this.errorCode = errorCode;
		}

		/**
		 * @return the errorDesc
		 */
		public String getErrorDesc() {
			return errorDesc;
		}

		/**
		 * @param errorDesc the errorDesc to set
		 */
		@XmlElement
		public void setErrorDesc(String errorDesc) {
			this.errorDesc = errorDesc;
		};
	
}
