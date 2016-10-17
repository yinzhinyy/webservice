package com.egova.webservice.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
/**
 * 用于获取列值
 * @author yindl
 *
 */
public class MapRowMapper implements RowMapper<Map<String,Object>> {
	public Map<String,Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
		Map<String,Object> map = new LinkedHashMap<String,Object>();
		for(int i=0;i<rs.getMetaData().getColumnCount();i++){
			map.put(rs.getMetaData().getColumnName(i+1), rs.getObject(1+i));
		}
		return map;
	}
}