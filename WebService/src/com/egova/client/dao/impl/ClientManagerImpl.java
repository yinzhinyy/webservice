package com.egova.client.dao.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.egova.client.bean.DispatchRec;
import com.egova.client.bean.MediaPath;
import com.egova.client.dao.ClientManager;
import com.egova.webservice.config.SysConfig;
import com.egova.webservice.util.DAORowMapper;
import com.egova.webservice.util.DateUtils;
import com.egova.webservice.util.log.LogUtils;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import oracle.sql.CLOB;
import sun.misc.BASE64Encoder;

/**
 * @author XueWen
 * @date Oct 13, 2015 2:56:48 PM 
 * @version V1.0  
 */
@Service("clientManager")
public class ClientManagerImpl implements ClientManager {

	Log logger = LogFactory.getLog(ClientManagerImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static BASE64Encoder encoder = new BASE64Encoder();
	
	private static final int dispatchLimit = 5;//未派遣成功次数限制
	
	public Map<Integer,String> getDispatchXml(){
		String sql = "select * from dlmis.torecdispatch t where t.dispatched = 0 and t.dispatchcount < ?";
		try{
			List<DispatchRec> list = jdbcTemplate.query(sql, new Object[]{dispatchLimit} ,new DAORowMapper<DispatchRec>(DispatchRec.class));
			if(null != list && list.size() > 0){
				int len = list.size();
				Map<Integer,String> xmlList = new HashMap<Integer,String>();
				for(int i = 0;i < len;i ++){
					String xml = "<?xml version='1.0' encoding='utf-8'?><request><params>";
					DispatchRec dRec = (DispatchRec)list.get(i);
					int recID = dRec.getRecID();
					xml +=  "<recID>"+dRec.getRecID()+"</recID>" +
							"<taskNum>"+dRec.getTaskNum()+"</taskNum>" +
							"<eventTypeName>"+dRec.getEventTypeName()+"</eventTypeName>" +
							"<eventLevelName>"+dRec.getEventLevelName()+"</eventLevelName>" +
							"<recTypeName>"+dRec.getRecTypeName()+"</recTypeName>" +
							"<mainTypeName>"+dRec.getMainTypeName()+"</mainTypeName>" +
							"<subTypeName>"+dRec.getSubTypeName()+"</subTypeName>" +
							"<eventSrcName>"+dRec.getEventSrcName()+"</eventSrcName>" +
							"<eventDesc>"+dRec.getEventDesc()+"</eventDesc>" +
							"<address>"+dRec.getAddress()+"</address>" +
							"<coordinateX>"+dRec.getCoordinatex()+"</coordinateX>" +
							"<coordinateY>"+dRec.getCoordinatey()+"</coordinateY>" +
							"<districtCode>"+dRec.getDistrictCode()+"</districtCode>" +
							"<districtName>"+dRec.getDistrictName()+"</districtName>" +
							"<streetCode>"+dRec.getStreetCode()+"</streetCode>" +
							"<streetName>"+dRec.getStreetName()+"</streetName>" +
							"<communityCode>"+dRec.getCommunityCode()+"</communityCode>" +
							"<communityName>"+dRec.getCommunityName()+"</communityName>" +
							"<cellCode>"+dRec.getCellCode()+"</cellCode>" +
							"<cellName>"+dRec.getCellName()+"</cellName>" +
							"<acceptTime>"+DateUtils.dateTimeToStr(dRec.getAcceptTime())+"</acceptTime>" +
							"<acceptUser>"+dRec.getAcceptUser()+"</acceptUser>" +
							"<reportName>"+dRec.getReporterName()+"</reportName>" +
							"<reportContact>"+dRec.getReportContact()+"</reportContact>" +
							"<reportTime>"+DateUtils.dateTimeToStr(dRec.getReportTime())+"</reportTime>" +
							"<dispatchTime>"+DateUtils.dateTimeToStr(dRec.getDispatchTime())+"</dispatchTime>" +
							"<dealLimit>"+dRec.getDealLimit()+"</dealLimit>" +
							"<deadLine>"+DateUtils.dateTimeToStr(dRec.getDeadLine())+"</deadLine>" +
							"<dispatchPartName>"+dRec.getDispatchPartName()+"</dispatchPartName>" +
							"<dispatchUnitName>"+dRec.getDispatchUnitName()+"</dispatchUnitName>" +
							"<dispatchRoleName>"+dRec.getDispatchRoleName()+"</dispatchRoleName>" +
							"<dispatchContent>"+dRec.getDispatchContent()+"</dispatchContent>" +
							"<case_desc>"+dRec.getCaseDesc()+"</case_desc>" +
							"<case_pos_desc>"+dRec.getCasePosDesc()+"</case_pos_desc>";
							
					xml += "</params>";
//					String attachment = getRecMediaXml(recID, "上报");
//					String attachment = getMediaByte();
					String attachment = getMediaURL(recID, "上报");
					xml += attachment+"</request>";
//					System.out.println(xml);
					xmlList.put(recID, xml);
				}
				return xmlList;
			}
		}catch(Exception e){
			logger.error("获取派遣案卷出错！", e);
		}
		return null;
	}
	
	public void updateRecDispatched(int recID){
		String sql = "update dlmis.torecdispatch set dispatched = 1, dispatchcount=dispatchcount+1, successtime = sysdate where recid = ?";
		try{
			jdbcTemplate.update(sql, recID);
		}catch(Exception e){
			LogUtils.error("更新派遣成功状态出错", logger, e, sql);
		}
	}
	
	public void updateRecNotDispatched(int recID) {
		String sql = "update dlmis.torecdispatch set dispatchcount=dispatchcount+1 where recid = ?";
		try{
			jdbcTemplate.update(sql, recID);
		}catch(Exception e){
			logger.error("更新未派遣成功状态出错", e);
			e.printStackTrace();
		}
	}
	
	public void resetDispatchCount() {
		String sql = "update dlmis.torecdispatch set dispatchcount=0 where dispatched=0 and dispatchcount >= ?";
		try{
			jdbcTemplate.update(sql, dispatchLimit);
		}catch(Exception e){
			logger.error("未派遣次数复位出错", e);
			e.printStackTrace();
		}
	}
	
	private String getRecMediaXml(int recID,String mediaUsage){
		//共享目录配置
		String userName = SysConfig.COMMON_SHARE_FILE_USERNAME;
		String password = SysConfig.COMMON_SHARE_FILE_PASSWORD;
		String shareDir = SysConfig.COMMON_SHARE_FILE_ROOT;
		shareDir = shareDir.replace('\\', '/');
		if(shareDir.startsWith("//")) {
			shareDir = shareDir.substring(2);
		}
		String smbFilePath = "smb://" + userName + ":" + password + "@" + shareDir;
		try {
			//查询案卷对应类型的上报图片
			String sql = "select t.msgid,t.mediaid,t.mediapath,t.medianame from dlmis.torecmedia t where t.recid = "+recID+
					" and t.mediausage = '"+mediaUsage+"' and t.mediatype = 'IMAGE'";
			List<MediaPath> list = jdbcTemplate.query(sql, new DAORowMapper<MediaPath>(MediaPath.class));
			if(null == list || list.size() < 1){
				System.out.println("图片数据为空");
			}
			if(null != list && list.size() > 0){
				String attachment ="<attachment>";
				for(int i = 0,len = list.size();i < len ;i++){
					attachment +="<item>";
					MediaPath media = (MediaPath)list.get(i);
					String filePath = smbFilePath + "/"+media.getMediaPath()+"/"+media.getMsgID()+"_"+media.getMediaID()+"_"+media.getMediaName();
					SmbFile file = new SmbFile(filePath);
					String mediaName = media.getMediaName();
					String suffixName = "jpg";
					if(null != mediaName && mediaName.length() > 0){
						int index = mediaName.lastIndexOf(".");
						suffixName = mediaName.substring(index);
					}
					if(null != file && file.exists()){
						file.connect();
						byte[] buffer = new byte[file.getContentLength()];
						SmbFileInputStream sfis = new SmbFileInputStream(file);
						InputStream  in = new BufferedInputStream(sfis);
						while ((in.read(buffer)) != -1){}
					    in.close();
					    String content = encoder.encode(buffer);
						attachment += "<content>"+content+"</content><type>"+suffixName+"</type><fileName>"+mediaName+"</fileName>";
					}
					attachment +="</item>";
				}
				attachment +="</attachment>";
				return attachment;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	private String getMediaURL(int recID,String mediaUsage) {
		//查询案卷对应类型的上报图片
		String sql = "select t.msgid,t.mediaid,t.mediapath,t.medianame from dlmis.torecmedia t where t.recid = "+recID+
				" and t.mediausage = '"+mediaUsage+"' and t.mediatype = 'IMAGE'";
		try {
			List<MediaPath> list = jdbcTemplate.query(sql, new DAORowMapper<MediaPath>(MediaPath.class));
			if(null == list || list.size() < 1){
				System.out.println("图片数据为空");
			}
			if(null != list && list.size() > 0){
				String attachment ="<attachment>";
				for(int i = 0,len = list.size();i < len ;i++){
					attachment +="<item>";
					MediaPath media = (MediaPath)list.get(i);
					String virtualDir = SysConfig.COMMON_URL_VIRTUALDIR;
					virtualDir = virtualDir.endsWith("/") ? virtualDir.substring(0, virtualDir.length()-1) : virtualDir;
					String url = virtualDir + "/" + media.getMediaPath()+"/"+media.getMsgID()+"_"+media.getMediaID()+"_"+media.getMediaName();
					String mediaName = media.getMediaName();
					String suffixName = "jpg";
					if(null != mediaName && mediaName.length() > 0){
						int index = mediaName.lastIndexOf(".") + 1;
						suffixName = mediaName.substring(index);
					}
					attachment += "<content>"+url+"</content><type>"+suffixName+"</type><fileName>"+mediaName+"</fileName></item>";
				}
				attachment +="</attachment>";
				return attachment;
			}
		} catch (Exception e) {
			logger.error("获取多媒体内容出错！");
			e.printStackTrace();
		}
		return null;
	}
	
	private String getMediaByte(){
		String attachment ="<attachment>";
		//TODO
		String[] filePath = {"D:/MediaRoot/69/62/Event696227/111.jpg","D:/MediaRoot/69/62/Event696227/555.jpg"};
		String[] fileName = {"111.jpg","555.jpg"};
		for(int i=0;i < 2;i ++){
			File file = new File(filePath[i]);
			if(null != file && file.exists()){
				attachment +="<item>";
				FileInputStream in = null;
				ByteArrayOutputStream baos = null;
				try {
					in = new FileInputStream(file);
					baos = new ByteArrayOutputStream();
					int b = 0;
					while((b = in.read())!=-1){
						baos.write(b);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				byte[] buffer = baos.toByteArray();
			    String content = encoder.encode(buffer);
				attachment += "<content>"+content+"</content><type>jpg</type><fileName>"+fileName[i]+"</fileName>";
				attachment +="</item>";
			}
		}
		attachment +="</attachment>";
		return attachment;
	}
	
	@SuppressWarnings("deprecation")
	public void saveXmlContent(int recID,String opName,String opMark,int isSuccess,String requestXml,String xmlContent,String errorDesc){
		//生成XMLID
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
		String xmlID = sdf.format(new Date()) + (int)(Math.random()*900)+100; 
		//保存报文处理 	
		String sql = "insert into DLMIS.DISPATCHXML(RECID,XMLID,XMLNAME,XMLREMARK,XMLCONTENT,XMLDATE," +
				"XMLSTATE,REQUESTXML,ERRORDESC) values(?,?,?,?,empty_clob(),sysdate,?,empty_clob(),?)";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = jdbcTemplate.getDataSource().getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, recID);
			ps.setString(2, xmlID);
			ps.setString(3, opName);
			ps.setString(4, opMark);
			ps.setInt(5, isSuccess);
			ps.setString(6, errorDesc);
			int flag = ps.executeUpdate();
			conn.commit();
			ps = null;
			if(flag > 0){
				sql = "select XMLCONTENT,REQUESTXML from DLMIS.DISPATCHXML where xmlid = ? for update";
				ps = conn.prepareStatement(sql);
				ps.setString(1, xmlID);
				rs = ps.executeQuery();
				if(rs.next()){
					CLOB responseClob = (CLOB)rs.getClob(1);
					CLOB requestClob = (CLOB)rs.getClob(2);
					Writer writer = responseClob.getCharacterOutputStream();
					writer.write(xmlContent);
					Writer writer1 = requestClob.getCharacterOutputStream();
					writer1.write(requestXml);
					writer.flush();
					writer.close();
					writer1.flush();
					writer1.close();
					rs.close();
					conn.commit();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(IOException e1){
			e1.printStackTrace();
		}finally{
			if(null != ps ){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					if(null != conn){
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	public void collect() {
		String sSql = "select recid from dlmis.torecact"
							+ " where actdefid=57 and ( partid=4487 or rolepartid=4488)"
							+ " and recid not in ( select recid from dlmis.torecdispatch )";
		List<Integer> recs = (List<Integer>)jdbcTemplate.query(sSql, new DAORowMapper<Integer>(Integer.class));
		if(null == recs || recs.size() < 1) {
			return;
		}
		String recIDs = String.valueOf(recs.get(0));
		for(int i=1; i < recs.size(); i++) {
			recIDs += "," + recs.get(i);
		}
		String iSql = "INSERT INTO dlmis.torecdispatch( "
				+ " recid, tasknum, eventtypename, eventlevelname, rectypename, maintypename, subtypename, eventsrcname, eventdesc, address"
				+ ", coordinatex, coordinatey, districtcode, districtname, streetcode, streetname, communitycode, communityname, cellcode, cellname"
				+ ", accepttime, acceptuser, reportername, reportcontact, reporttime"
				+ ", dispatchtime, deallimit, deadline, dispatchpartname, dispatchunitname, dispatchrolename, dispatchcontent"
				+ ", casedesc, caseposdesc, patrolid, patrolname, createtime, successtime, dispatched, dispatchcount, archived"
				+ ", transopinion, transtime, departmentname)"
				+ " SELECT b.recid, b.tasknum, b.eventtypename, b.eventlevelname, b.rectypename, b.maintypename, b.subtypename, b.eventsrcname, b.eventdesc, b.address"
				+ ", b.coordinatex, b.coordinatey"
				+ ", (select c.unitcode from dlsys.tcunit c where c.unitid=b.districtid) AS districtcode, b.districtname"
		        + ", b.streetid AS streetcode, b.streetname"
		        + ", b.communityid AS communitycode, b.communityname"
		        + ", b.cellid AS cellcode, b.cellname"
		        + ", b.newinsttime, b.patrolname, b.reportername, NULL, b.createtime"
		        + ", a.createtime, a.actlimit, a.deadline, a.prepartname, a.unitname, a.rolepartname, a.preactopinion"
		        + ", b.eventdesc, b.address, b.patrolid, b.patrolname, SYSDATE, null, 0, 0, 0"
		        + ", NULL, NULL, NULL"
		        + " FROM dlmis.torecact a, dlmis.torec b"
		        + " WHERE a.recid=b.recid and a.recid in (" + recIDs + ")";
		try {
			jdbcTemplate.update(iSql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
