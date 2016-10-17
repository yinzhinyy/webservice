package com.egova.webservice.bean;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author XueWen
 * @Package com.egova.webservice.bean 
 * @Description: TODO 
 * @date Oct 10, 2015 10:48:57 AM 
 * @version V1.0  
 */
public class Item {
	
	private String content;
	private String type;
	private String fileName;
	
	/**
	 * 
	 */
	public Item() {
		super();
	}

	/**
	 * @param content
	 * @param type
	 * @param fileName
	 */
	public Item(String content, String type, String fileName) {
		super();
		this.content = content;
		this.type = type;
		this.fileName = fileName;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	@XmlElement
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	@XmlElement
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	@XmlElement
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
