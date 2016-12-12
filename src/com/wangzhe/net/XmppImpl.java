package com.wangzhe.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class XmppImpl implements Xmpp{
	private static final Logger LOGGER = LoggerFactory.getLogger(XmppImpl.class);
	
	private HttpClient httpClient;
	
	@Autowired
	private HttpRequestInterceptor requestInterceptor;

	public void register(String userName, String passWord) {
		HttpPost httpPost = new HttpPost("http://www.wangzongwen.cn:9090/plugins/restapi/v1/users");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", userName);
		jsonObject.put("password", passWord);
		StringEntity stringEntity = new StringEntity(jsonObject.toString(), "utf-8");
		stringEntity.setContentType("application/json");
		try {		
			httpPost.setEntity(stringEntity);
			HttpResponse httpResponse = getHttpClient().execute(httpPost);
			printStream(httpResponse.getEntity().getContent());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
	}
	
	public HttpClient getHttpClient(){
		if(httpClient == null){
			httpClient = HttpClients.custom().addInterceptorFirst(requestInterceptor).build();
		}
		return httpClient;
	}
	
	private static void printStream(InputStream is){
		byte[] buffer = new byte[1024];
		int len = 0;
		try {
			while ((len = is.read(buffer)) != -1) {
				String str;
				try {
					str = new String(buffer, "utf-8");
					System.out.println(str);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
