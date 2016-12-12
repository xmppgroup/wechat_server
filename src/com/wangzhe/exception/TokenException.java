package com.wangzhe.exception;

public class TokenException extends Exception{
	private int code;
	
	public TokenException() {
		super();
	}
	
	public TokenException(int code, String msg){
		super(msg);
		this.code = code;
	}
	
	public TokenException(String msg, Throwable cause){
		super(msg, cause);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	
}
