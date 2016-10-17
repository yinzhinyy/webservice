package com.egova.webservice.dao.procedure.bean;

public class FCDataBean
{
  private String chartType;
  private String sCaption;
  private String sToolTip;
  private int width;
  private int height;
  private int x;
  private int y;
  private String sFCDataXML;

  public String getChartType()
  {
    return this.chartType;
  }
  public int getHeight() {
    return this.height;
  }
  public String getSCaption() {
    return this.sCaption;
  }
  public String getSFCDataXML() {
    return this.sFCDataXML;
  }
  public String getSToolTip() {
    return this.sToolTip;
  }
  public int getWidth() {
    return this.width;
  }
  public int getX() {
    return this.x;
  }
  public void setChartType(String chartType) {
    this.chartType = chartType;
  }
  public void setHeight(int height) {
    this.height = height;
  }
  public void setSCaption(String caption) {
    this.sCaption = caption;
  }
  public void setSFCDataXML(String dataXML) {
    this.sFCDataXML = dataXML;
  }
  public void setSToolTip(String toolTip) {
    this.sToolTip = toolTip;
  }
  public void setWidth(int width) {
    this.width = width;
  }
  public void setX(int x) {
    this.x = x;
  }
  public int getY() {
    return this.y;
  }
  public void setY(int y) {
    this.y = y;
  }
}