package com.egova.webservice.dao.procedure.bean;

public class HeaderColumns
{
  private int fieldID;
  private String field;
  private String title;
  private int width;
  private String align;
  private boolean hidden;
  private boolean sortable;
  private int hasfieldvalue;
  private int rowspan;
  private int colspan;
  private String sorter;
  private int dataType;
  private int lineNum;
  private int frozen;
  private int isRowGroup;
  private int urlFlag;
  private String subSysUrl;

  public String getSubSysUrl()
  {
    return this.subSysUrl;
  }
  public void setSubSysUrl(String subSysUrl) {
    this.subSysUrl = subSysUrl;
  }
  public int getUrlFlag() {
    return this.urlFlag;
  }
  public void setUrlFlag(int urlFlag) {
    this.urlFlag = urlFlag;
  }
  public int getIsRowGroup() {
    return this.isRowGroup;
  }
  public void setHidden(boolean hidden) {
    this.hidden = hidden;
  }
  public void setIsRowGroup(int isRowGroup) {
    this.isRowGroup = isRowGroup;
  }
  public void setSortable(boolean sortable) {
    this.sortable = sortable;
  }
  public int getFrozen() {
    return this.frozen;
  }
  public void setFrozen(int frozen) {
    this.frozen = frozen;
  }
  public String getAlign() {
    return this.align;
  }
  public int getColspan() {
    return this.colspan;
  }
  public void setColspan(int colspan) {
    this.colspan = colspan;
  }
  public int getDataType() {
    return this.dataType;
  }
  public String getField() {
    return this.field;
  }
  public int getHasfieldvalue() {
    return this.hasfieldvalue;
  }
  public int getLineNum() {
    return this.lineNum;
  }
  public int getRowspan() {
    return this.rowspan;
  }
  public String getSorter() {
    return this.sorter;
  }
  public String getTitle() {
    return this.title;
  }
  public int getWidth() {
    return this.width;
  }
  public void setAlign(String align) {
    this.align = align;
  }
  public void setDataType(int dataType) {
    this.dataType = dataType;
  }
  public void setField(String field) {
    this.field = field;
  }
  public void setHasfieldvalue(int hasfieldvalue) {
    this.hasfieldvalue = hasfieldvalue;
  }
  public boolean getHidden() {
    return this.hidden;
  }
  public void setHidden(int hidden) {
    this.hidden = (hidden == 1);
  }
  public boolean getSortable() {
    return this.sortable;
  }
  public void setSortable(int sortable) {
    this.sortable = (sortable == 1);
  }
  public void setLineNum(int lineNum) {
    this.lineNum = lineNum;
  }
  public void setRowspan(int rowspan) {
    this.rowspan = rowspan;
  }
  public void setSorter(String sorter) {
    this.sorter = sorter;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public void setWidth(int width) {
    this.width = width;
  }
  public int getFieldID() {
    return this.fieldID;
  }
  public void setFieldID(int fieldID) {
    this.fieldID = fieldID;
  }
}