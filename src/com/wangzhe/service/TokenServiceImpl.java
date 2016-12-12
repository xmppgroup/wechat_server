package com.wangzhe.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangzhe.bean.UserBean;
import com.wangzhe.response.BaseResponse;
import com.wangzhe.response.TokenResponse;
import com.wangzhe.util.keyUtil;

@Service
public class TokenServiceImpl implements TokenService{
	private static final Logger LOGGER = LoggerFactory.getLogger(TokenServiceImpl.class);
	@Autowired
	private UserService userService;
	
	public String newToken(String userName){
		Date expDate = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
		String token = Jwts.builder().claim(UserBean.USERNAME, userName)
			.claim("tokenExpired", expDate).
			signWith(SignatureAlgorithm.RS256, keyUtil.getPrivateKey()).compact();
		return token;
	}
	
	@Transactional
	public TokenResponse refreshToken(String token){
		TokenResponse tokenResponse = null;
		try {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			Jwt<Header, Claims> jwt = Jwts.parser().setSigningKey(keyUtil.getPublicKey()).parse(token);
			String userName = (String) jwt.getBody().get(UserBean.USERNAME);
			Long expTime = (Long) jwt.getBody().get("tokenExpired");
			if(userName == null || expTime == null || !userService.isUserExist(userName)){
				tokenResponse = new TokenResponse(1, "token_invalid", null);			
			}else if(expTime < System.currentTimeMillis()){  //token已经过期了
				tokenResponse = new TokenResponse(2, "token_expired", null);
			}else {
				String newToken = newToken(userName);
				tokenResponse = new TokenResponse(0, "success", newToken);
			}
			return tokenResponse;
		}catch (SignatureException e) {
			LOGGER.error(e.getMessage());
		}catch (JwtException e) {
			LOGGER.error(e.getMessage());
		}
		tokenResponse = new TokenResponse(2, "token_expired", null);
		return tokenResponse;
	}

	@Transactional
	public Map<String, Object> checkToken(String token) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", BaseResponse.TOKEN_INVALID);
		try {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			Jwt<Header, Claims> jwt = Jwts.parser().setSigningKey(keyUtil.getPublicKey()).parse(token);
			String userName = (String) jwt.getBody().get(UserBean.USERNAME);
			Long expTime = (Long) jwt.getBody().get("tokenExpired");
			if(userName == null || expTime == null || !userService.isUserExist(userName)){
				result.put("code", BaseResponse.TOKEN_INVALID);		
			}else if(expTime < System.currentTimeMillis()){  //token已经过期了
				result.put("code", BaseResponse.TOKEN_EXPIRED);
			}else {
				result.put("code", 0);
				result.put("userName", userName);
				result.put("tokenExpired", expTime);
			}
		}catch (SignatureException e) {
			LOGGER.error(e.getMessage());
		}catch (JwtException e) {
			LOGGER.error(e.getMessage());
		}
		
		return result;
	}
}
