package com.wangzhe.dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.wangzhe.bean.DynamicBean;
import com.wangzhe.bean.Flag;
import com.wangzhe.bean.FriendBean;
import com.wangzhe.bean.FriendBean.SubType;
import com.wangzhe.dao.base.DaoSupportImpl;
import com.wangzhe.dao.base.WhereItem;

@Repository
public class DynamicDao extends DaoSupportImpl<DynamicBean>{
	
	private static final String QUERY_LATEST_DYNAMICS =
		"SELECT * FROM wcdynamic WHERE (publisherId IN(SELECT contactId FROM wcfriend WHERE ownerId = ? AND subType = 'both')"
			+ " OR publisherId = ?) ORDER BY dynamicId DESC LIMIT ? ";
	
	private static final String QUERY_DYNAMICS_BY_PAGE =
			"SELECT * FROM wcdynamic WHERE (publisherId IN(SELECT contactId FROM wcfriend WHERE ownerId = ? AND subType = 'both')"
				+ " OR publisherId = ?) AND dynamicId < ? ORDER BY dynamicId DESC LIMIT ? ";
	
	/**
	 * 用户拉取朋友圈最新动态，按createDate时间倒序返回
	 * @param ownerId
	 * @param limit
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<DynamicBean> getLatestDynamicBeans(Integer ownerId, int limit){
		SQLQuery sqlQuery = currentSession().createSQLQuery(QUERY_LATEST_DYNAMICS);
		sqlQuery.setInteger(0, ownerId);
		sqlQuery.setInteger(1, ownerId);
		sqlQuery.setInteger(2, limit);
		
		sqlQuery.setResultTransformer(resultTransformer);
		return sqlQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<DynamicBean> getDynamisByPage(Integer userId, int dynamicId, int limit){
		SQLQuery sqlQuery = currentSession().createSQLQuery(QUERY_DYNAMICS_BY_PAGE);
		sqlQuery.setInteger(0, userId);
		sqlQuery.setInteger(1, userId);
		sqlQuery.setInteger(2, dynamicId);
		sqlQuery.setInteger(3, limit);
		
		sqlQuery.setResultTransformer(resultTransformer);
		return sqlQuery.list();
	}
}
