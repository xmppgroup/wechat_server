package com.wangzhe.response;

import com.wangzhe.bean.UserBean;

public class LoginResponse extends BaseResponse{
	public static final int ERROR_PARAM = 1;
	public static final int ERROR_USER_EXISTS = 2;
	
	private String token;
	private UserBean userBean;
	
	public LoginResponse(int code, String msg, String token) {
		super(code, msg);
		this.token = token;
	}

	public LoginResponse(int code, String msg, String token, UserBean userBean) {
		super(code, msg);
		this.token = token;
		this.userBean = userBean;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	
	


}
