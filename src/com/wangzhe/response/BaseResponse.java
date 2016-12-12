package com.wangzhe.response;

public class BaseResponse {
	public static final int SERVER_ERROR = 500;
	public static final int PARAM_MISS = 501;
	public static final int SQL_EXCEPTION = 502;
	public static final int TOKEN_INVALID = 1000;
	public static final int TOKEN_EXPIRED = 1001;
	
	private int code;
	private String msg;
	
	public BaseResponse(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
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
	
	
}
