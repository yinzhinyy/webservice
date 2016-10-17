package com.egova.webservice.dao.procedure;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.egova.webservice.common.ResultInfo;
import com.egova.webservice.dao.procedure.bean.WFActTransit;

import oracle.jdbc.driver.OracleTypes;

/**
 * @author XueWen
 * @Description: 案卷批转
 * @date Oct 9, 2015 5:48:33 PM 
 * @version V1.0  
 */
public class FoWFActTransit extends StoredProcedure{
	public FoWFActTransit(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, "dlmis.foWFActTransit");
		setFunction(true);
		declareParameter(new SqlOutParameter("result", OracleTypes.INTEGER));
		declareParameter(new SqlParameter("humanID", Types.INTEGER));
		declareParameter(new SqlParameter("actID", Types.INTEGER));
		declareParameter(new SqlParameter("itemType", Types.VARCHAR));
		declareParameter(new SqlParameter("transInfo", Types.VARCHAR));
		declareParameter(new SqlParameter("transOpinion", OracleTypes.VARCHAR));
		declareParameter(new SqlParameter("limitInfo", OracleTypes.VARCHAR));
		declareParameter(new SqlOutParameter("errorDesc", OracleTypes.VARCHAR));
		compile();
	}
	public ResultInfo execute(WFActTransit wfActTransit){
		return execute(wfActTransit.getHumanID(),wfActTransit.getActID(),wfActTransit.getItemType(),wfActTransit.getTransInfo(),wfActTransit.getTransMemo(),wfActTransit.getLimitInfo());
	}
	/**
	 * @param humanID 人员标识
	 * @param actID 案卷标识
	 * @param itemType 工作项
	 * @param transInfo 流向信息
	 * @param transOpinion 批转意见
	 * @param limitInfo 时限信息
	 * @return
	 */
	public ResultInfo execute(int humanID,int actID,String itemType,String transInfo,String transOpinion,String limitInfo){
		HashMap<String,Object> inParams = new HashMap<String,Object>();
		inParams.put("humanID", humanID);
		inParams.put("actID", actID);
		inParams.put("itemType", itemType);
		inParams.put("transInfo", transInfo);
		inParams.put("transOpinion", transOpinion);
		inParams.put("limitInfo", limitInfo);
		Map<String,Object> result = execute(inParams);
		ResultInfo re = new ResultInfo(false);
		if(0 == (Integer)result.get("result")){
			re.setSuccess(true);
		}else{
			re.setMessage((String)result.get("errorDesc"));
		}
		return re;
	}
}
