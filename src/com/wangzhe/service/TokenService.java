package com.wangzhe.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.wangzhe.bean.UserBean;
import com.wangzhe.response.BaseResponse;
import com.wangzhe.response.TokenBean;

public interface TokenService {
	public String newToken(Integer userId, String userName);
	
	public Map<String, Object> checkToken(String token);
	
	public BaseResponse<TokenBean> refreshToken(String token);
}
