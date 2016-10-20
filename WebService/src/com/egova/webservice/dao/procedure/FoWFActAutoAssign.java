package com.egova.webservice.dao.procedure;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.egova.webservice.common.ResultInfo;
/**
 * 自动办理
 * @author yindl
 *
 */
public class FoWFActAutoAssign extends StoredProcedure{
	
	public FoWFActAutoAssign(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, "dlmis.foWFActAutoAssign");
		setFunction(true);
		declareParameter(new SqlOutParameter("result", Types.INTEGER));
		declareParameter(new SqlParameter("humanID", Types.INTEGER));
		declareParameter(new SqlParameter("actID", Types.INTEGER));
		declareParameter(new SqlParameter("itemType", Types.VARCHAR));
		declareParameter(new SqlOutParameter("errorDesc", Types.VARCHAR));
		compile();
	}
	
	public ResultInfo execute(int humanID, int actID, String itemType){
		HashMap<String,Object> inParams = new HashMap<String,Object>();
		inParams.put("humanID", humanID);
		inParams.put("actID", actID);
		inParams.put("itemType", itemType);
		Map<String, Object> result = execute(inParams);
		if((Integer)result.get("result") == 0){
			return new ResultInfo(true);
		}else{
			return new ResultInfo(false,(String)result.get("errorDesc"));
		}
	}
}
