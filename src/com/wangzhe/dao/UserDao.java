package com.wangzhe.dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.wangzhe.bean.UserBean;
import com.wangzhe.dao.base.DaoSupportImpl;
import com.wangzhe.util.TimeUtil;


@Repository
public class UserDao extends DaoSupportImpl<UserBean> {
	private static final String GET_UPDATE_DATA =
			"SELECT t.* FROM ( " +
			"SELECT t1.* FROM (select u.* from wcuser u, wcfriend r where " +
			"(r.ownerName = ? and u.userName = r.contactName) and (r.subType = 'both' or r.subType = 'from')) t1" +
			" UNION SELECT u.* from wcuser u WHERE u.userName = ?) t where t.modifyDate > ? order by t.modifyDate";
	
	
	
	@SuppressWarnings("unchecked")
	public List<UserBean> searchUser(String propName,String value){
		List<UserBean> list = new ArrayList<UserBean>();
		list = currentSession().createCriteria(UserBean.class).add(Restrictions.like(propName,value, MatchMode.ANYWHERE)).list();
		return list;
	}
	
	public boolean isUserExist(String userName){
		Criteria criteria = currentSession().createCriteria(UserBean.class).add(Restrictions.eq(UserBean.USERNAME, userName));
		if(criteria.list().size()>0){
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserBean> searchUserByM(String search){
		Criteria criteria = currentSession().createCriteria(UserBean.class).
			add(Restrictions.ilike("loginname", search, MatchMode.ANYWHERE));
			
		return criteria.list();
	}
	
	public UserBean getUserByUName(String uname){
		Criteria criteria = currentSession().createCriteria(UserBean.class).add(Restrictions.eq("loginname", uname));
		return (UserBean) criteria.uniqueResult();
	}
	
	public void updateUser(String userName, String field, Object value){
		Query query = currentSession().createQuery("update UserBean set "
				+ field + " = \'" + value + "\', modifyDate = " + TimeUtil.getTime()
				+ " where userName = \'" + userName + "\'");
		query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<UserBean> getMyFriendsByModifyDate(String userName, long modifyDate) {
		SQLQuery sqlQuery = currentSession().createSQLQuery(GET_UPDATE_DATA);
		sqlQuery.setString(0, userName);
		sqlQuery.setString(1, userName);
		sqlQuery.setLong(2, modifyDate);
		
		sqlQuery.setResultTransformer(resultTransformer);
		return sqlQuery.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<UserBean> getUsersByNames(String[] userNames){
		SimpleExpression[] expressions = new SimpleExpression[userNames.length];
		
		for(int i = 0 ; i < userNames.length; i++){
			SimpleExpression expression = Restrictions.eq(UserBean.USERNAME, userNames[i]);
			expressions[i] = expression;
		}
	
		Criteria criteria = currentSession().createCriteria(UserBean.class)
				.add(Restrictions.or(expressions));
		
		return criteria.list();				
	}
	
}
