package com.wangzhe.response;

import java.util.List;

import com.wangzhe.bean.UserBean;

public class UserListResponse extends BaseResponse{
	
	private List<UserBean> userBeans;
	
	public UserListResponse(int code, String msg, List<UserBean> userBeans) {
		super(code, msg);
		this.userBeans = userBeans;
	}

	public List<UserBean> getUserBeans() {
		return userBeans;
	}

	public void setUserBeans(List<UserBean> userBeans) {
		this.userBeans = userBeans;
	}
	


}
