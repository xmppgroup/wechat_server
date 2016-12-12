package com.wangzhe.controller;

import java.sql.SQLException;

import org.aspectj.weaver.reflect.IReflectionWorld;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangzhe.exception.TokenException;
import com.wangzhe.response.BaseResponse;

public class BaseController {
	@ExceptionHandler(Exception.class)
	public @ResponseBody BaseResponse handleException(Exception ex){
		BaseResponse baseResponse = null;
		if(ex instanceof MissingServletRequestParameterException){
			baseResponse = new BaseResponse(BaseResponse.PARAM_MISS, "missing_param");
		}else if (ex instanceof TokenException) {
			baseResponse = new BaseResponse(BaseResponse.TOKEN_EXPIRED, "token_expired");
		}else if(ex instanceof SQLException){
			baseResponse = new BaseResponse(BaseResponse.SQL_EXCEPTION, "database_error");
		}else{
			baseResponse = new BaseResponse(BaseResponse.SERVER_ERROR, "internal_server_error");
		}
		return baseResponse;
	}
}
