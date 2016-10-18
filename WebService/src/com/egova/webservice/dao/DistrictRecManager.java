package com.egova.webservice.dao;

import java.util.Date;
import java.util.List;

import com.egova.client.bean.DispatchRec;
import com.egova.webservice.bean.FeedbackRequest;
import com.egova.webservice.bean.RegionStatEval;
import com.egova.webservice.common.ResultInfo;

/**
 * 区级平台案卷管理
 * @author yinzhinyy
 * @date 2016年9月7日 上午10:57:33
 */
public interface DistrictRecManager {
	/**
	 * 区级平台调用反馈接口后更新torecdispatch表中批转信息
	 * @param recID
	 */
	public void updateRecTransitInfo(int recID, String transOpinion, Date transTime, String departmentName);
	
	/**
	 * 区级平台案卷批转
	 * @param feedbackRequest
	 * @return
	 */
	public ResultInfo transit(FeedbackRequest feedbackRequest);
	
	/**
	 * 区域考评统计查询
	 * @param startTime
	 * @param endTime
	 * @param regionID
	 * @return
	 */
	public List<RegionStatEval> staticQuery(Date startTime, Date endTime, int regionID);

	/**
	 * 更新案卷区级平台处理部门（unitid&unitname，同时更新partid&partname为虚拟节点）
	 * @param recID
	 * @param departmentName
	 */
	public void updateDistrictDepartment(int recID, String departmentName);

	/**
	 * 根据recID从dlmis.torecdispatch获取已派遣案卷信息
	 * @param recID
	 * @return
	 */
	public List<DispatchRec> getDispatchedRec(int recID);
	
}
