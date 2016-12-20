package com.wangzhe.dao.base;

import java.util.List;
import java.util.Map;

import com.wangzhe.util.Paging;


public interface DaoSupport<T> {
	public T getObjById(int id); 
	public List<T> getAll();
	public T getTByParams(T t);
	public List<T> getAllByParams(T t);
	public T getTByParams(List<WhereItem> whereItems);
	public List<T> getAllByParams(List<WhereItem> whereItems);
	public T getTBySqlQuery(String sqlQuery);
	public List<T> getAllBySqlQuery(String sqlQuery);
	public void updateObj(T t);
	public Integer addObj(T t);
	public void deleteObj(int id);	
	public List<T> getObjByPaging(Paging<T> paging);
	public Class<?> getSuperClassGenricType();
}
