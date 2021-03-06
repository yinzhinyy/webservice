

@\dlmis\table\DISPATCHXML.sql;
--需派遣案卷保存位置，由recact insert至雨花区接口人员 触发
@\dlmis\table\TORECDISPATCH.sql;

--webservice派遣接口地址
insert into dlsys.tcsysconfigitem (CONFIGITEMID, CONFIGGROUPID, CONFIGITEMNAME, DISPLAYNAME, ITEMVALUE, REMARK, DATATYPEID, CONTROLTYPE, CONTENTRANGE, RELSTR, ENABLEFLAG, DISPLAYORDER, ITEMLISTFLAG, ITEMLISTSQL)
values (30101, 301, 'MIS_REC_DISPATCH_SERVER', 'webservice派遣接口地址  ', 'http://218.77.55.33:3030/KDSYNServer/IEventSynServiceProxy?WSDL', '雨花区派遣接口地址', 5, 'textbox', null, null, 1, 30101, 1, null);
--案件到区级平台后指定办理人，默认为雨花区接口人员（humanid：4487）
insert into dlsys.tcsysconfigitem (CONFIGITEMID, CONFIGGROUPID, CONFIGITEMNAME, DISPLAYNAME, ITEMVALUE, REMARK, DATATYPEID, CONTROLTYPE, CONTENTRANGE, RELSTR, ENABLEFLAG, DISPLAYORDER, ITEMLISTFLAG, ITEMLISTSQL)
values (21101, 21, 'MIS_REC_AUTOASSIGN_HUMANID', '案件到区级平台后指定办理人', '4487', '虚拟节点', 5, 'textbox', null, null, 1, 21101, 1, null);
--设置多媒体路径
UPDATE dlsys.tcsysconfigitem SET itemvalue='http://222.240.136.67:9087/MediaRootV11' WHERE configitemname='COMMON_URL_VIRTUALDIR';

--需要配置雨花区接口人员（humanid：4487）统计权限（queryid=1168）

--tasklist
--新增未下发问题tasklistid=30101（是否需要重新设定？） -> 岗位配置 -> 栏目定义 -> 栏目字段


commit;
