package com.wangzhe.response;

public class RegisterResponse extends BaseResponse{
	public static final int ERROR_FIELD = 1;
	public static final int ERROR_USER_EXISTS = 2;
	
	private String errField;
	
	public RegisterResponse(int code, String msg, String errField) {
		super(code, msg);
		this.errField = errField;
	}

	public String getErrField() {
		return errField;
	}

	public void setErrField(String errField) {
		this.errField = errField;
	}

}
