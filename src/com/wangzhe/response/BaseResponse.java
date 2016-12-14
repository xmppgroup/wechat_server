package com.wangzhe.response;

import org.apache.log4j.varia.StringMatchFilter;

public class BaseResponse<T> {
	public static final int SERVER_ERROR = 500;
	public static final int PARAM_MISS = 501;
	public static final int SQL_EXCEPTION = 502;
	public static final int TOKEN_INVALID = 1000;
	public static final int TOKEN_EXPIRED = 1001;
	
	private int code;
	private String msg;
	private T data;
	
	public BaseResponse(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public BaseResponse(int code, String msg, T data){
		this.code = code;
		this.msg = msg;
		this.data = data;
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	
}
