package com.wangzhe.response;

import com.wangzhe.bean.DynamicBean;
import com.wangzhe.bean.UserBean;

public class AddDynamicResponse extends BaseResponse{
	private int code;
	private String msg;
	private DynamicBean body;


	public AddDynamicResponse(int code, String msg, DynamicBean body) {
		super(code, msg);
		this.code = code;
		this.msg = msg;
		this.body = body;
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


	public DynamicBean getBody() {
		return body;
	}


	public void setBody(DynamicBean body) {
		this.body = body;
	}


}
