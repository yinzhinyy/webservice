

@\dlmis\table\DISPATCHXML.sql;
--����ǲ��������λ�ã���recact insert���껨���ӿ���Ա ����
@\dlmis\table\TORECDISPATCH.sql;

--webservice��ǲ�ӿڵ�ַ
insert into dlsys.tcsysconfigitem (CONFIGITEMID, CONFIGGROUPID, CONFIGITEMNAME, DISPLAYNAME, ITEMVALUE, REMARK, DATATYPEID, CONTROLTYPE, CONTENTRANGE, RELSTR, ENABLEFLAG, DISPLAYORDER, ITEMLISTFLAG, ITEMLISTSQL)
values (30101, 301, 'MIS_REC_DISPATCH_SERVER', 'webservice��ǲ�ӿڵ�ַ  ', 'http://218.77.55.33:3030/KDSYNServer/IEventSynServiceProxy?WSDL', '�껨����ǲ�ӿڵ�ַ', 5, 'textbox', null, null, 1, 30101, 1, null);
--����������ƽ̨��ָ�������ˣ�Ĭ��Ϊ�껨���ӿ���Ա��humanid��4487��
insert into dlsys.tcsysconfigitem (CONFIGITEMID, CONFIGGROUPID, CONFIGITEMNAME, DISPLAYNAME, ITEMVALUE, REMARK, DATATYPEID, CONTROLTYPE, CONTENTRANGE, RELSTR, ENABLEFLAG, DISPLAYORDER, ITEMLISTFLAG, ITEMLISTSQL)
values (21101, 21, 'MIS_REC_AUTOASSIGN_HUMANID', '����������ƽ̨��ָ��������', '4487', '����ڵ�', 5, 'textbox', null, null, 1, 21101, 1, null);
--���ö�ý��·��
UPDATE dlsys.tcsysconfigitem SET itemvalue='http://222.240.136.67:9087/MediaRootV11' WHERE configitemname='COMMON_URL_VIRTUALDIR';

--��Ҫ�����껨���ӿ���Ա��humanid��4487��ͳ��Ȩ�ޣ�queryid=1168��

--tasklist
--����δ�·�����tasklistid=30101���Ƿ���Ҫ�����趨���� -> ��λ���� -> ��Ŀ���� -> ��Ŀ�ֶ�


commit;