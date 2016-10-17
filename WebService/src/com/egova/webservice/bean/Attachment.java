package com.egova.webservice.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author XueWen
 * @Package com.egova.webservice.bean 
 * @Description: TODO 
 * @date Oct 10, 2015 6:01:59 PM 
 * @version V1.0  
 */
public class Attachment {

	private List<Item> item;
	
	/**
	 * 
	 */
	public Attachment() {
		super();
	}

	/**
	 * @param item
	 */
	public Attachment(List<Item> item) {
		super();
		this.item = item;
	}
	
	/**
	 * @return the item
	 */
	public List<Item> getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	@XmlElement
	public void setItem(List<Item> item) {
		this.item = item;
	}
	
}
