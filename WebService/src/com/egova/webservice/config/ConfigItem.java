package com.egova.webservice.config;

public class ConfigItem {
	private String configItemName;
	private Integer dataTypeID;
	private String itemValue;
	private String displayName;
	private Integer configGroupID;
	protected String controlType;
	protected Integer configItemID;
	public Integer getConfigItemID() {
		return configItemID;
	}
	public void setConfigItemID(Integer configItemID) {
		this.configItemID = configItemID;
	}
	public String getControlType() {
		return controlType;
	}
	public void setControlType(String controlType) {
		this.controlType = controlType;
	}
	public String getConfigItemName() {
		return configItemName;
	}
	public void setConfigItemName(String configItemName) {
		this.configItemName = configItemName;
	}
	public Integer getDataTypeID() {
		return dataTypeID;
	}
	public void setDataTypeID(Integer dataTypeID) {
		this.dataTypeID = dataTypeID;
	}
	public String getItemValue() {
		return itemValue;
	}
	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public Integer getConfigGroupID() {
		return configGroupID;
	}
	public void setConfigGroupID(Integer configGroupID) {
		this.configGroupID = configGroupID;
	}
}
