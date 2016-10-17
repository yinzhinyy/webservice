package com.egova.webservice.util;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 获取jdbcTemplateSource引用
 * @author yinzhinyy
 * @date 2016年9月7日 上午11:16:35
 */
public class JdbcHelper {
	
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

}
