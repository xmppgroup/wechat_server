package com.wangzhe.response;

import java.util.List;

import com.wangzhe.bean.UserBean;

public class SearchUserResponse extends BaseResponse{
	public static final int ERROR_PARAM = 1;
	public static final int ERROR_USER_EXISTS = 2;
	
	private List<UserBean> userBeans;
	
	public SearchUserResponse(int code, String msg, List<UserBean> userBeans) {
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
