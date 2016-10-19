package com.egova.webservice.dao.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.egova.client.bean.DispatchRec;
import com.egova.webservice.bean.FeedbackRequest;
import com.egova.webservice.bean.RegionStatEval;
import com.egova.webservice.common.ResultInfo;
import com.egova.webservice.config.SysConfig;
import com.egova.webservice.dao.DistrictRecManager;
import com.egova.webservice.dao.procedure.FoWFActAutoAssign;
import com.egova.webservice.dao.procedure.FoWFActTransit;
import com.egova.webservice.dao.procedure.PoConfiguredStat;
import com.egova.webservice.dao.procedure.bean.WFActTransit;
import com.egova.webservice.util.DAORowMapper;
import com.egova.webservice.util.DateUtils;
import com.egova.webservice.util.log.LogUtils;

@Service("districtRecManager")
public class DistrictRecManagerImpl implements DistrictRecManager {
	Log logger = LogFactory.getLog(DistrictRecManagerImpl.class);

	@Autowired 
	JdbcTemplate jdbcTemplate;
	
	public void updateRecTransitInfo(FeedbackRequest request) {
		int recID = request.getRecID();
		String transOpinion = request.getTransOpinion();
		Date transTime = request.getTransTime() == null ? new Date() : request.getTransTime();
		String departmentName = request.getDepartmentName();
		String sql = "update dlmis.torecdispatch set transopinion=?, transtime=?, departmentname=? where recid=?";
//		Date convertedTransTime = DateUtils.strToFormatDate(transTime, "yyyy-MM-dd HH:mm:ss");//TODO
		try {
			jdbcTemplate.update(sql, new Object[]{ transOpinion, transTime, departmentName, new Integer(recID) }, 
														new int[]{ Types.VARCHAR, Types.TIMESTAMP, Types.VARCHAR, Types.INTEGER });
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ResultInfo assign(FeedbackRequest request)  {
		int recID = request.getRecID();
		String departmentName = request.getDepartmentName();
		ResultInfo result = new ResultInfo(false);
		try {
			//获取当前处理部门下人员
			String hSql = "SELECT a.humanid FROM dlsys.tchuman a, dlsys.tcunit b WHERE a.unitid=b.unitid"
					+ " AND ( b.unitid=66 OR b.seniorid=66 OR b.seniorid IN ( SELECT unitid FROM dlsys.tcunit WHERE seniorid=66))"
					+ " AND b.unitname = ? AND ROWNUM=1";//66为雨花区专业部门
			int humanID = jdbcTemplate.queryForInt(hSql, departmentName);
			//获取当前活动的actID
			String aSql = "select actid from (select t.actid from dlmis.torecact t where t.actdefid = 57 and t.recid = ?  order by t.createtime desc) where rownum = 1";
			int actID = jdbcTemplate.queryForInt(aSql, recID);//57为雨花区专业部门actdefID
			if(actID > 0){
				//获取当前处理部门ID，并于RECACT更新
				String rSql = "select roleid from dlsys.tcrole where rolename=?";
				int roleID = jdbcTemplate.queryForInt(rSql, departmentName);
				if(roleID > 0 && humanID >0) {
					String uSql = "UPDATE dlmis.torecact SET partid=0, rolepartid=?, rolepartname=? where actid=?";
					jdbcTemplate.update(uSql, roleID, departmentName, actID);
				} else {
					humanID = SysConfig.MIS_REC_AUTOASSIGN_HUMANID;
				}
				result = this.doAssign(humanID, actID, "assign"); 
			}
		} catch (Exception e) {
			result.setMessage("案卷办理失败！");
			e.printStackTrace();
		}
		return result;
	}
	
	private ResultInfo doAssign(int humanID, int actID, String itemType){
		try{
			FoWFActAutoAssign fo = new FoWFActAutoAssign(jdbcTemplate);
		    return fo.execute(humanID, actID, itemType);
		}catch(Exception e){
			logger.error("案卷办理存过出错！", e);
			e.printStackTrace();
		}
		return new ResultInfo(false);
	}
	
	public ResultInfo transit(FeedbackRequest request){
		int actID = 0;
		int recID = request.getRecID();
		ResultInfo result = new ResultInfo(false);
		try{
			//获取当前活动的actID
			String sql = "select actid from (select t.actid from dlmis.torecact t where t.actdefid = 57 and t.recid = ?  order by t.createtime desc) where rownum = 1";
			actID = jdbcTemplate.queryForInt(sql, recID);//57为雨花区专业部门actdefID
			if(actID > 0){
				WFActTransit wTransit = new WFActTransit();
				wTransit.setActID(actID);
				wTransit.setHumanID(SysConfig.MIS_REC_AUTOASSIGN_HUMANID);
				wTransit.setItemType("transit");
				wTransit.setTransInfo("52,15,1");//52为监督中心（核查）actdefID，15为接线员roleID
				wTransit.setTransMemo(request.getTransOpinion());
				//执行批转操作
				result = this.doTransit(wTransit);
			}
		}catch(Exception e){
			result.setMessage("案卷批转失败！");
			e.printStackTrace();
		}
		return result;
	}
	
	private ResultInfo doTransit(WFActTransit wfActTransit){
		try{
			FoWFActTransit foWFActTransit = new FoWFActTransit(jdbcTemplate);
			return foWFActTransit.execute(wfActTransit);
		}catch(Exception e){
			logger.error("案卷批转存过出错！", e);
			e.printStackTrace();
		}
		return new ResultInfo(false);
	}
	
	public List<RegionStatEval> staticQuery(Date startTime, Date endTime, int regionID) {
		int humanID = 23;//管理员humanid
		int queryID = 1168;//1168为2016年区域考评表queryID
		String statTimeStr = "";//默认为空
	    String whereSQL = "where districtid not in (7,8) and hungnum <> 1";
	    if(null != startTime) {
	    	String startTimeStr = DateUtils.dateTimeToStr(startTime);
	    	whereSQL += " and createtime >= to_date('" + startTimeStr + "','yyyy-mm-dd hh24:mi:ss')";
	    }
	    if(null != endTime) {
	    	String endTimeStr = DateUtils.dateTimeToStr(endTime);
	    	whereSQL += " and createtime <= to_date('" + endTimeStr + "','yyyy-mm-dd hh24:mi:ss')" ;
	    }
	    Map<String, Object> map = new HashMap<String, Object>();
		try {
			PoConfiguredStat procedure = new PoConfiguredStat(jdbcTemplate);
		    map = procedure.execute(queryID, whereSQL, 0, statTimeStr, 0, 0, 0, 5, humanID, 0);
	    } catch (Exception e) {
		      LogUtils.error("执行统计过程出错！", logger, e);
	    }
		String jsonStats = (String)map.get("dataStr");
		jsonStats = replace(jsonStats);
		//json转bean
		ObjectMapper mapper = new ObjectMapper();
		RegionStatEval[] arrayStats = null;
		try {
			arrayStats = mapper.readValue(jsonStats, RegionStatEval[].class);
		} catch (Exception e) {
			logger.error("json转bean出错！", e);
			e.printStackTrace();
		}
		List<RegionStatEval> list = null;
		if(null != arrayStats) {
			list = Arrays.asList(arrayStats);
		}
		return list;
	}

	private String replace(String jsonStats) {
		String result = jsonStats.replace("districtname", "regionName")
								.replace("一挡问题数", "fisrtRecNum")
								.replace("一挡重复问题数", "firstRepeatNum")
								.replace("一档问题扣分", "firstPointNum")
								.replace("二挡问题数", "secondRecNum")
								.replace("二挡重复问题数", "secondRepeatNum")
								.replace("二档问题扣分", "secondPointNum")
								.replace("三挡问题数", "thirdRecNum")
								.replace("三挡重复问题数", "thirdRepeatNum")
								.replace("三档问题扣分", "thirdPointNum")
								.replace("问题总数", "totalRecNum")
								.replace("总扣分", "totalPoint");
		return result;
	}

	private List<String> convertToXML(List<RegionStatEval> stats) {
		List<String> list = new ArrayList<String>();
		for(RegionStatEval stat: stats) {
			String statXML = "<regionName>" + stat.getRegionName() + "</regionName>" +
										"<fisrtRecNum>" + stat.getFisrtRecNum() + "</fisrtRecNum>" +
										"<firstRepeatNum>" + stat.getFirstRepeatNum() + "</firstRepeatNum>" +
										"<firstPointNum>" + stat.getFirstPointNum() + "</firstPointNum>" +
										"<secondRecNum>" + stat.getSecondRecNum() + "</secondRecNum>" +
										"<secondRepeatNum>" + stat.getSecondRepeatNum() + "</secondRepeatNum>" +
										"<secondPointNum>" + stat.getSecondPointNum() + "</secondPointNum>" +
										"<thirdRecNum>" + stat.getThirdRecNum() + "</thirdRecNum>" +
										"<thirdRepeatNum>" + stat.getThirdRepeatNum() + "</thirdRepeatNum>" +
										"<thirdPointNum>" + stat.getThirdPointNum() + "</thirdPointNum>" +
										"<totalRecNum>" + stat.getTotalRecNum() + "</totalRecNum>" +
										"<totalPoint>" + stat.getTotalPoint() + "</totalPoint>";
			list.add(statXML);
		}
		return list;
	}

	public List<DispatchRec> getDispatchedRec(int recID) {
		String sql = "select * from dlmis.torecdispatch where recid=? and dispatched=1 order by createtime desc";
		List<DispatchRec> result = null;
		try {
			result = jdbcTemplate.query(sql, new Object[]{recID}, new DAORowMapper<DispatchRec>(DispatchRec.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
