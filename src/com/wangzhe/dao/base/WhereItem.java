package com.wangzhe.dao.base;

public class WhereItem {
	private String key; 
	private String connector; //连接符
	private Object value;
	
	public WhereItem() {
		
	}
	
	public WhereItem(String key, String connector, Object value) {
		super();
		this.key = key;
		this.connector = connector;
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getConnector() {
		return connector;
	}
	public void setConnector(String connector) {
		this.connector = connector;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	

}
