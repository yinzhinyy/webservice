-- Create table
create table DLMIS.TORECDISPATCH
(
  recid                    INTEGER not null,
  tasknum                  VARCHAR2(20),
  eventtypename            VARCHAR2(80),
  eventlevelname           VARCHAR2(20),
  rectypename              VARCHAR2(80),
  maintypename             VARCHAR2(80),
  subtypename              VARCHAR2(80),
  eventsrcname             VARCHAR2(80),
  eventdesc                VARCHAR2(2000),
  address                  VARCHAR2(2000),
  coordinatex              NUMBER(18,6),
  coordinatey              NUMBER(18,6),
  districtcode               VARCHAR2(20),
  districtname             VARCHAR2(40),
  streetcode                 VARCHAR2(20),
  streetname               VARCHAR2(40),
  communitycode              VARCHAR2(20),
  communityname            VARCHAR2(40),
  cellcode                  VARCHAR2(20),
  cellname                 VARCHAR2(40),
  accepttime               DATE,
  acceptuser               VARCHAR2(50),
  reportername             VARCHAR2(30),
  reportcontact            VARCHAR2(30),
  reporttime               DATE,
  dispatchtime             DATE,
  deallimit                NUMBER(5,2),
  deadline                 DATE,
  dispatchPartName         VARCHAR2(40),
  dispatchUnitName         VARCHAR2(40),
  dispatchRoleName         VARCHAR2(40),
  dispatchContent          VARCHAR2(2000),
  casedesc                 VARCHAR2(2000),
  caseposdesc              VARCHAR2(2000),
  patrolid                 INTEGER,
  patrolname                VARCHAR2(40),
  createtime               DATE,
  successtime              DATE,
  dispatched               INTEGER,
  dispatchcount            INTEGER,
  archived                 INTEGER,
  transopinion             VARCHAR2(2000),
  transtime                DATE,
  departmentName           VARCHAR2(80)
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
