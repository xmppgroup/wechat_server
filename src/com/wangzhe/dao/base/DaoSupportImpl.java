package com.wangzhe.dao.base;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.wangzhe.bean.UserBean;
import com.wangzhe.util.Paging;

public class DaoSupportImpl<T> implements DaoSupport<T>{
	protected Class<?> clazz ;
	protected Logger logger;
	
	protected ResultTransformer resultTransformer;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public DaoSupportImpl() {
		this.clazz = this.getSuperClassGenricType();
		this.logger = Logger.getLogger(clazz);
		
		resultTransformer = new ResultTransformer() {
			private static final long serialVersionUID = 1L;

			public Object transformTuple(Object[] values, String[] columns) {
				return Transformers.aliasToBean(clazz).transformTuple(values, columns);
			}
			
			@SuppressWarnings("rawtypes")
			public List transformList(List arg0) {
				List<T> results = new ArrayList<T>();
				for(Object obj : arg0){
					@SuppressWarnings("unchecked")
					T t= (T) obj;
					results.add(t);
				}
				return results;
			}
		};
	}
	
	@SuppressWarnings("unchecked")
	public T getObjById(int id) {
		return (T) sessionFactory.getCurrentSession().get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return sessionFactory.getCurrentSession().createQuery("from " + clazz.getSimpleName()).list();
	}
	
	@SuppressWarnings("unchecked")
	public T getTByParams(T t) {
		return (T) sessionFactory.getCurrentSession().createCriteria(clazz).add(Example.create(t)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<T> getAllByParams(T t) {
		return sessionFactory.getCurrentSession().createCriteria(clazz).add(Example.create(t)).list();
	}
	
	@SuppressWarnings("null")
	public T getTByParams(List<WhereItem> whereItems) {
		List<T> results = getAllByParams(whereItems);
		if(results != null && !results.isEmpty()){
			return results.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAllByParams(List<WhereItem> whereItems) {
		String tableName = ((Table) clazz.getAnnotation(Table.class)).name();
		StringBuffer stringBuffer = new StringBuffer("select * from " + tableName + " where 1 = 1");
		if(whereItems != null && !whereItems.isEmpty()){
			for(WhereItem whereItem : whereItems){
				String key = whereItem.getKey();
				String connector = whereItem.getConnector();
				Object value = whereItem.getValue();
				stringBuffer.append(" and ").append(key).append(" " + connector + " ");
				
				if(value instanceof List){
					List list = (List) value;
					for(Object obj : list){
						stringBuffer.append("(' ").append(obj).append(", ");
					}
					stringBuffer.delete(stringBuffer.lastIndexOf(","), stringBuffer.length());
					stringBuffer.append(")");
				}else {
					stringBuffer.append("'").append(value).append("'");
				}
			}
		}
		
		SQLQuery sqlQuery = currentSession().createSQLQuery(stringBuffer.toString());
		sqlQuery.setResultTransformer(resultTransformer);
		
		return sqlQuery.list();
	}

	@SuppressWarnings("unchecked")
	public T getTBySqlQuery(String sqlQuery) {
		SQLQuery query = currentSession().createSQLQuery(sqlQuery);
		query.setResultTransformer(resultTransformer);
		return (T) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<T> getAllBySqlQuery(String sqlQuery) {
		SQLQuery query = currentSession().createSQLQuery(sqlQuery);
		query.setResultTransformer(resultTransformer);
		return query.list();
	}


	public void updateObj(T t) {
		sessionFactory.getCurrentSession().update(t);
	}

	public Integer addObj(T t) {
		Integer newId = (Integer) currentSession().save(t);
		return newId;
	}

	public void deleteObj(int id) {
		try {
			T t = this.getObjById(id);
			sessionFactory.getCurrentSession().delete(t);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<T> getObjByPaging(Paging<T> paging) {
		this.clazz = getSuperClassGenricType();
		Query query = sessionFactory.getCurrentSession().createQuery("from " + clazz.getSimpleName());
		query.setFirstResult((paging.getNowPage() - 1) * paging.getPageSize());
		query.setMaxResults(paging.getPageSize());
		return query.list();
	}

	public Class<?> getSuperClassGenricType() {
		ParameterizedType pt=	(ParameterizedType) this.getClass().getGenericSuperclass();
		Class<?> clazz = (Class<?>) pt.getActualTypeArguments()[0];
		return clazz;
	}

	public final Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	
}
