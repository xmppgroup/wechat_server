package com.wangzhe.net;

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;
import org.springframework.stereotype.Component;

@Component
public class RequestInterceptor implements HttpRequestInterceptor{

	public void process(HttpRequest request, HttpContext context)
			throws HttpException, IOException {
		request.addHeader("authorization",  "3WfUWU3S0tF6tTDq");
		request.addHeader("Accept", "application/json");
	}

}
