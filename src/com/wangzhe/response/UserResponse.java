package com.wangzhe.response;

import com.wangzhe.bean.UserBean;

public class UserResponse extends BaseResponse{
	
	private int code;
	private String msg;
	private UserBean userBean;
	
	public UserResponse(int code, String msg, UserBean userBean) {
		super(code, msg);
		this.code = code;
		this.msg = msg;
		this.userBean = userBean;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	
	
}
