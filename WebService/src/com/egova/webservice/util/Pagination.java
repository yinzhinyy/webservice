package com.egova.webservice.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.egova.webservice.common.PageInfo;

/**
 * 分页函数
 */
public class Pagination<T> {
	private static Log logger = LogFactory.getLog(Pagination.class);
	public static final int NUMBERS_PER_PAGE = 10;

	/** 一页显示的记录数 */
	private int numPerPage = 0;
	/** 记录总数 */
	private int totalRows = 0;
	/** 总页数 */
	private int totalPages = 0;
	/** 当前页码 */
	private int currentPage = 1;
	/** 起始行数 */
	private int startIndex = 0;
	/** 结束行数 */
	private int lastIndex = 0;
	/** 指定类型结果列表 */
	private List<T> resultList = null;
	/** 未指定类型结果列表 */
	private List<Map<String, Object>> resultMapList = null;
	/** JdbcTemplate jTemplate */
	private JdbcTemplate jdbcTemplate = null;
	/** 查询sql语句 */
	private String querySql = null;
	/** RowMapper */
	private DAORowMapper<T> rowMapper = null;
	/** SQL 绑定参数 */
	private Object[] objs = null;
	
	private boolean success = true;
	
	/**
	 * 缺省构造函数
	 */
	public Pagination() {
	}

	/**
	 * 分页构造函数 
	 * @param sql 根据传入的sql语句得到一些基本分页信息
	 * @param currentPage 当前页
	 * @param numPerPage 每页记录数
	 * @param jdbcTemplate JdbcTemplate实例
	 */
	public Pagination(String sql, int currentPage, int numPerPage, JdbcTemplate jdbcTemplate) {
		// 设置查询语句
		setQuerySql(sql);
		// 设置每页显示记录数
		setNumPerPage(numPerPage);
		// 设置要显示的页数
		setCurrentPage(currentPage);
		//	给JdbcTemplate赋值
		this.jdbcTemplate = jdbcTemplate;
		
		this.query();
	}
	
	/**
	 * 分页构造函数 
	 * @param sql 根据传入的sql语句得到一些基本分页信息
	 * @param currentPage 当前页
	 * @param numPerPage 每页记录数
	 * @param jdbcTemplate JdbcTemplate实例
	 * @param objs sql参数绑定
	 */
	public Pagination(String sql, int currentPage, int numPerPage, JdbcTemplate jdbcTemplate, Object[] objs) {
		// 设置查询语句
		setQuerySql(sql);
		// 设置每页显示记录数
		setNumPerPage(numPerPage);
		// 设置要显示的页数
		setCurrentPage(currentPage);
		//	给JdbcTemplate赋值
		this.jdbcTemplate = jdbcTemplate;
		this.objs = objs;
		this.query();
	}
	
	/**
	 * 分页构造函数 
	 * @param sql 根据传入的sql语句得到一些基本分页信息
	 * @param currentPage 当前页
	 * @param numPerPage 每页记录数
	 * @param jdbcTemplate JdbcTemplate实例
	 * @param objs sql参数绑定
	 */
	@SuppressWarnings("unchecked")
	public Pagination(String sql, int currentPage, int numPerPage, JdbcTemplate jdbcTemplate, DAORowMapper<T> rowMapper, Object[] objs) {
		// 设置查询语句
		setQuerySql(sql);
		// 设置每页显示记录数
		setNumPerPage(numPerPage);
		// 设置要显示的页数
		setCurrentPage(currentPage);
		//	给JdbcTemplate赋值
		this.jdbcTemplate = jdbcTemplate;
		this.objs = objs;
		this.setRowMapper(rowMapper);
		this.query();
	}
	
	
	/**
	 * 分页构造函数 
	 * @param sql 根据传入的sql语句得到一些基本分页信息
	 * @param currentPage 当前页
	 * @param numPerPage 每页记录数
	 * @param jdbcTemplate JdbcTemplate实例
	 * @param objs sql参数绑定
	 */
	public Pagination(String sql, int currentPage, int numPerPage, JdbcTemplate jdbcTemplate, DAORowMapper<T> rowMapper) {
		// 设置查询语句
		setQuerySql(sql);
		// 设置每页显示记录数
		setNumPerPage(numPerPage);
		// 设置要显示的页数
		setCurrentPage(currentPage);
		//	给JdbcTemplate赋值
		this.jdbcTemplate = jdbcTemplate;
		this.setRowMapper(rowMapper);
		this.query();
	}
	
	/**
	 * 分页构造函数（一般存储过程使用）
	 * @param currentPage 当前页
	 * @param numPerPage 每页记录数
	 * @param totalRows 总行数
	 * @param list 结果集
	 */
	public Pagination(int currentPage, int numPerPage, int totalRows, List<T> list){
		setTotalRows(totalRows);
		setNumPerPage(numPerPage);
		setCurrentPage(currentPage);
		setResultList(list);
		setTotalPages();
	}

	/**
	 * 分页构造函数（信采一般存储过程使用）
	 * @param currentPage 当前页
	 * @param numPerPage 每页记录数
	 * @param totalRows 总行数
	 * @param list 结果集
	 */
	public Pagination(int currentPage, int numPerPage, int totalRows, List<T> list,List<Map<String, Object>> listMap){
		setTotalRows(totalRows);
		setNumPerPage(numPerPage);
		setCurrentPage(currentPage);
		setResultList(list);
		setResultMapList(listMap);
		setTotalPages();
	}
	/**
	 * querySql优化(去掉order by语句和select嵌套以提高查询效率)
	 * @param querySQL
	 * return String 
	 */
	public String querySQLFilter(String querySql){
		String sql = querySql;
		int pos = sql.indexOf("order by");
		if(pos > 0 && sql.substring(pos).indexOf((")")) < 0)//order by之后没有括号
			sql = sql.substring(0, pos);
		StringBuffer sb = new StringBuffer("select count(*) ");
		int from = sql.indexOf("from");
		sb.append(sql.substring(from));
		return sb.toString();
	}
	
	/**
	 * 初始化 
	 * @param sql 根据传入的sql语句得到一些基本分页信息
	 * @param currentPage 当前页
	 * @param numPerPage 每页记录数
	 * @param jdbcTemplate JdbcTemplate实例
	 */
	public void query() {
		if (this.jdbcTemplate == null) {
			throw new IllegalArgumentException(
					"cn.com.egova.mis.common.Pagination.jdbcTemplate is null,please initial it first. ");
		} else if (querySql == null || querySql.equals("")) {
			throw new IllegalArgumentException(
					"cn.com.egova.mis.common.Pagination.querySql is empty,please initial it first. ");
		}

		String totalSQL =  querySQLFilter(querySql) ;
		// 总记录数
		try{
			if(objs == null){
				setTotalRows(jdbcTemplate.queryForInt(totalSQL));
			}else{
				setTotalRows(jdbcTemplate.queryForInt(totalSQL,objs));
			}
			
		}catch(Exception e){
			this.setSuccess(false);
			logger.error("总数记录异常：e:"+e);
		}
		// 计算总页数
		setTotalPages();
		// 计算起始行数
		setStartIndex();
		// 计算结束行数
		setLastIndex();
		// 构造oracle数据库的分页语句
		StringBuffer paginationSQL = new StringBuffer(" SELECT * FROM ( ");
		paginationSQL.append(" SELECT temp.* ,ROWNUM num FROM ( ");
		paginationSQL.append(querySql);
		paginationSQL.append("　) temp where ROWNUM <= " + lastIndex);
		paginationSQL.append(" ) WHERE　num > " + startIndex);

		// 装入结果集List
		String paginationQuerySQL = paginationSQL.toString();
		try{
			if(this.rowMapper != null){
				if(this.objs == null){
					setResultList(this.jdbcTemplate.query(paginationQuerySQL, this.rowMapper));
				}else{
					setResultList(this.jdbcTemplate.query(paginationQuerySQL, this.rowMapper, objs));
				}
			}else{
				if(this.objs == null){
					setResultMapList(this.jdbcTemplate.queryForList(paginationQuerySQL));
				}else{
					setResultMapList(this.jdbcTemplate.queryForList(paginationQuerySQL, objs));
				}
			}
		}catch(Exception e){
			this.setSuccess(false);
			logger.error("获取列表数据异常:" + e);
		}
	}

	/**
	 * 获取分页信息
	 * @return
	 */
	public PageInfo getPageInfo(){
		if(this.getResultList() != null){
			return new PageInfo(this.getTotalPages(), currentPage, this.getTotalRows(),
					this.getResultList().size(), this.getNumPerPage());
		}
		if(this.getResultMapList() != null){
			return new PageInfo(this.getTotalPages(), currentPage, this.getTotalRows(),
					this.getResultMapList().size(), this.getNumPerPage());
		}
		return new PageInfo(this.getTotalPages(), currentPage, this.getTotalRows(), 0, this.getNumPerPage());
	}

	private void setRowMapper(DAORowMapper<T> rowMapper) {
		this.rowMapper = rowMapper;
	}

	public RowMapper<T> getRowMapper() {
		return rowMapper;
	}

	public void setQuerySql(String querySql) {
		this.querySql = querySql;
	}

	public String getQuerySql() {
		return querySql;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		if(currentPage > 0){
			this.currentPage = currentPage;
		}else{
			this.currentPage = 1;
		}
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public List<T> getResultList() {
		return this.resultList;
	}
	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
	public List<Map<String, Object>> getResultMapList() {
		return this.resultMapList;
	}
	public void setResultMapList(List<Map<String, Object>> resultMapList) {
		this.resultMapList = resultMapList;
	}

	public int getTotalPages() {
		return this.totalPages;
	}

	// 计算总页数
	public void setTotalPages() {
		if (totalRows % numPerPage == 0) {
			this.totalPages = totalRows / numPerPage;
		} else {
			this.totalPages = (totalRows / numPerPage) + 1;
		}
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex() {
		this.startIndex = (currentPage - 1) * numPerPage;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// 计算结束时候的索引
	public void setLastIndex() {
		if (totalRows < numPerPage) {
			this.lastIndex = totalRows;
		} else if ((totalRows % numPerPage == 0)
				|| (totalRows % numPerPage != 0 && currentPage < totalPages)) {
			this.lastIndex = currentPage * numPerPage;
		} else if (totalRows % numPerPage != 0 && currentPage == totalPages) {// 最后一页
			this.lastIndex = totalRows;
		}
	}

	public Object[] getObjs() {
		return objs;
	}

	public void setObjs(Object[] objs) {
		this.objs = objs;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}