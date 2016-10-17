package com.egova.webservice.dao.procedure;

import java.sql.Clob;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.egova.webservice.dao.procedure.bean.FCDataBean;
import com.egova.webservice.dao.procedure.bean.HeaderColumns;
import com.egova.webservice.util.DAORowMapper;
import com.egova.webservice.util.MapRowMapper;

public class PoConfiguredStat extends StoredProcedure
{
  public PoConfiguredStat(JdbcTemplate jdbcTemplate)
  {
    super(jdbcTemplate, "umstat.poConfiguredStatV3");
    setFunction(true);
    declareParameter(new SqlOutParameter("result", 4));
    declareParameter(new SqlParameter("iQueryID", 4));
    declareParameter(new SqlParameter("whereSqlStr", -1));
    declareParameter(new SqlParameter("iUseStatTime", 4));
    declareParameter(new SqlParameter("sStatTimeParam", -1));
    declareParameter(new SqlParameter("hasChart", 4));
    declareParameter(new SqlParameter("customChart", 4));
    declareParameter(new SqlParameter("isTopEval", 4));
    declareParameter(new SqlParameter("topEvalCount", 4));
    declareParameter(new SqlOutParameter("headerCur", -10, new DAORowMapper<HeaderColumns>(HeaderColumns.class)));
    declareParameter(new SqlOutParameter("dataCur", -10, new MapRowMapper()));
    declareParameter(new SqlOutParameter("chartCur", -10, new DAORowMapper<FCDataBean>(FCDataBean.class)));
    declareParameter(new SqlParameter("iHumanID", 4));
    declareParameter(new SqlParameter("iHasOrderColumn", 4));
    compile();
  }

  public Map<String, Object> execute(int queryID, String whereStr, int useStatTime, String statTimeParam, int hasChart, int customChart, int isTopEval, int topEvalCount, int humanID, int hasOrderColumn)
  {
    HashMap<String, Object> inParams = new LinkedHashMap<String, Object>();
    inParams.put("iQueryID", Integer.valueOf(queryID));
    inParams.put("whereSqlStr", whereStr);
    inParams.put("iUseStatTime", Integer.valueOf(useStatTime));
    inParams.put("sStatTimeParam", statTimeParam);
    inParams.put("hasChart", Integer.valueOf(hasChart));
    inParams.put("customChart", Integer.valueOf(customChart));
    inParams.put("isTopEval", Integer.valueOf(isTopEval));
    inParams.put("topEvalCount", Integer.valueOf(topEvalCount));
    inParams.put("iHumanID", Integer.valueOf(humanID));
    inParams.put("iHasOrderColumn", Integer.valueOf(hasOrderColumn));
    Map<String, Object> map = execute(inParams);
    Map<String, Object> result = dealStatResult(map, hasChart);
    return result;
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
private Map<String, Object> dealStatResult(Map<String, Object> map, int hasChart)
  {
    String tempDataStr = "";
    FCDataBean chartData = null;
    Map<String, Object> result = new HashMap<String, Object>();
    List headerList = (List)map.get("headerCur");
    Map dataCur = (Map)((List)map.get("dataCur")).get(0);
    if (hasChart > 0) {
      chartData = (FCDataBean)((List)map.get("chartCur")).get(0);
    }
    Clob dataClob = (Clob)dataCur.get("DATARESULT");
    String rowGroupField = dataCur.get("ROWGROUPFIELD").toString();
    try {
      if (dataClob != null) {
        int dataLength = (int)dataClob.length();
        tempDataStr = dataClob.getSubString(1L, dataLength);
      }
    }
    catch (Exception e) {
      this.logger.error("获取统计返回结果串失败：" + e.getLocalizedMessage());
      e.printStackTrace();
    }
    tempDataStr = tempDataStr.endsWith("$") ? tempDataStr.substring(0, tempDataStr.length() - 1) : tempDataStr;
    Map<String, String> strMap = getStatHeaderStr(headerList);
    String dataStr = getStatResultStr(tempDataStr);
    result.put("frozenColumnStr", strMap.get("frozenColumnStr"));
    result.put("columnStr", strMap.get("columnStr"));
    result.put("dataStr", dataStr);
    result.put("chartData", chartData);
    result.put("rowGroupField", rowGroupField);
    return result;
  }

  private Map<String, String> getStatHeaderStr(List<HeaderColumns> headerList)
  {
    Map<String, String> map = new HashMap<String, String>();
    StringBuffer sb = new StringBuffer();
    StringBuffer forzenSB = new StringBuffer();
    int preLineNum = 0;
    String headerColumnStr = "";
    sb.append("[");
    forzenSB.append("[[");
    for (Iterator<HeaderColumns> itr = headerList.iterator(); itr.hasNext(); ) {
      HeaderColumns item = (HeaderColumns)itr.next();
      int lineNum = item.getLineNum();
      headerColumnStr = getHeaderColumnStr(item);
      if (item.getFrozen() == 0) {
        if (lineNum != preLineNum) {
          if (lineNum == 1) {
            sb.append("[");
          } else {
            sb.deleteCharAt(sb.length() - 1);
            sb.append("],[");
          }
        }
        sb.append(headerColumnStr);
        preLineNum = lineNum;
      } else {
        forzenSB.append(headerColumnStr);
      }
    }

    if (sb.toString().endsWith(",")) {
      sb.deleteCharAt(sb.length() - 1);
    }
    if (forzenSB.toString().endsWith(",")) {
      forzenSB.deleteCharAt(forzenSB.length() - 1);
    }
    sb.append("]]");
    forzenSB.append("]]");
    map.put("frozenColumnStr", forzenSB.toString());
    map.put("columnStr", sb.toString());
    return map;
  }

  private String getHeaderColumnStr(HeaderColumns item)
  {
    StringBuffer buff = new StringBuffer();
    buff.append("{");
    buff.append("title:").append("'").append(item.getTitle()).append("',");
    buff.append("field:").append("'").append(item.getField()).append("',");
    buff.append("rowspan:").append(item.getRowspan()).append(",");
    buff.append("colspan:").append(item.getColspan()).append(",");
    buff.append("align:").append("'").append(item.getAlign()).append("',");
    buff.append("dataType:").append("'").append(item.getDataType()).append("',");
    buff.append("isRowGroup:").append("'").append(item.getIsRowGroup()).append("',");
    buff.append("width:").append(item.getWidth());
    buff.append("},");
    String result = buff.toString();
    return result;
  }

  private String getStatResultStr(String tmpData)
  {
    StringBuffer sb = new StringBuffer();
    this.logger.info("tempData:" + tmpData);
    String[] array = tmpData.split("\\$");
    int len = array.length;
    sb.append("[");
    for (int i = 0; i < len; i++) {
      sb.append("{").append(array[i]).append("},");
    }
    sb.deleteCharAt(sb.length() - 1);
    sb.append("]");
    String result = sb.toString();
    return result;
  }
}