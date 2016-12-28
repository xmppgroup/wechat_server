package com.wangzhe.bean;

public class TokenUserBean {
	private UserBean userBean;
	private String token;
	
	public TokenUserBean() {
		// TODO Auto-generated constructor stub
	}
	
	public TokenUserBean(UserBean userBean, String token) {
		this.userBean = userBean;
		this.token = token;
	}
	public UserBean getUserBean() {
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
