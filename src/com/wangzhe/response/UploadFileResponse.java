package com.wangzhe.response;

import java.util.List;

import com.wangzhe.bean.FriendBean;

public class UploadFileResponse extends BaseResponse{
	private String fileName;

	public UploadFileResponse(int code, String msg, String fileName) {
		super(code, msg);
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
}
