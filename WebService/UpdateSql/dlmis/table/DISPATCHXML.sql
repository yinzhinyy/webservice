-- Create table
create table DLMIS.DISPATCHXML
(
  recid                    INTEGER not null,
  XMLID                    INTEGER,
  XMLNAME                  VARCHAR2(80),
  XMLREMARK                VARCHAR2(80),
  XMLCONTENT               CLOB,
  XMLDATE                  DATE,
  XMLSTATE                 INTEGER,
  REQUESTXML               CLOB,
  ERRORDESC                VARCHAR2(2000)
)
tablespace DLMIS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 8M
    next 1M
    minextents 1
    maxextents unlimited
  );

