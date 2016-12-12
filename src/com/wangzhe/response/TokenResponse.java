package com.wangzhe.response;

public class TokenResponse extends BaseResponse{
	private String token;
	
	public TokenResponse(int code, String msg, String token) {
		super(code, msg);
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
