package com.wangzhe.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.wangzhe.bean.UserBean;
import com.wangzhe.net.Xmpp;
import com.wangzhe.response.BaseResponse;
import com.wangzhe.response.LoginResponse;
import com.wangzhe.response.RegisterResponse;
import com.wangzhe.response.SearchUserResponse;
import com.wangzhe.response.SyncUserResponse;
import com.wangzhe.response.UserListResponse;
import com.wangzhe.response.UserResponse;
import com.wangzhe.service.TokenService;
import com.wangzhe.service.UserService;
import com.wangzhe.util.keyUtil;


@Controller
@EnableWebMvc
public class UserController extends BaseController{
	@Autowired
	private UserService userService;
	@Autowired
	private Xmpp xmpp;
	@Autowired
	private TokenService tokenService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody LoginResponse login(@Valid @ModelAttribute("user") UserBean userBean, BindingResult bindingResult){
		LoginResponse loginResponse = null;
		if(bindingResult.hasErrors()){
			loginResponse = new LoginResponse(1, "userName or passWord is not right", null);
			return loginResponse;
		}
		userBean = userService.getUserByParams(userBean);
		if(userBean != null){
			String token = tokenService.newToken(userBean.getUserId(), userBean.getUserName());
			loginResponse = new LoginResponse(0, "success", token, userBean);
		}else{
			loginResponse = new LoginResponse(1, "userName or passWord is not right", null);
		}
		return loginResponse;
	}
	
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	public @ResponseBody RegisterResponse addUser(@Valid @ModelAttribute("user") UserBean userBean, BindingResult bindingResult){
		RegisterResponse response = null;
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			String errFiled = fieldError.getField();
			response = new RegisterResponse(1, fieldError.getCode(), errFiled);
			return response;
		}
		String result = userService.addUser(userBean);
		if(result.equals("succ")){
			xmpp.register(userBean.getUserName(), userBean.getPassWord());
			response = new RegisterResponse(0, "success", null);
		}else{
			response = new RegisterResponse(2, "user_already_existed", null);
		}
		return response;
	}
	
	@RequestMapping(value="/updateUser", method=RequestMethod.POST)
	public @ResponseBody UserResponse updateUser(HttpServletRequest request,
			@RequestParam("field") String field, @RequestParam("value") Object value){
		UserResponse response = null;
		boolean canUpdate = true;
		if(field.equals("") || field.equals(UserBean.USERNAME) || field.equals(UserBean.USERID)
				|| field.equals(UserBean.CREATEDATE) || field.equals(UserBean.MODIFYDATE)){
			response = new UserResponse(1, "refuse_modify", null);
			canUpdate = false;
		}else if(field.equals(UserBean.PASSWORD)){
			String passWord = (String) value;
			if(passWord.length() < 6 || passWord.length() > 16){
				response = new UserResponse(2, "modified_value_invalid", null);
				canUpdate = false;
			}
		}
		
		if(canUpdate){
			String userName = (String) request.getAttribute("userName");
			UserBean userBean = userService.updateUser(userName, field, value);
			response = new UserResponse(0, "success", userBean);
		}
		
		return response;
	}
	
	@RequestMapping("/searchUser")
	public @ResponseBody SearchUserResponse searchUser(@RequestParam("search") String search){
		SearchUserResponse response = null;
		
		if(search != null && !"".equals(search)){
			List<UserBean> userBeans = userService.searchUser(UserBean.USERNAME, search);
			if(userBeans != null && !userBeans.isEmpty()){
				for(UserBean userBean : userBeans){
					userBean.setPassWord(null);
				}
			}
			response = new SearchUserResponse(0, "success", userBeans);
		}else {
			response = new SearchUserResponse(1, "param_invalid", null);
		}
		
		return response;
	}
	
	/**
	 * 客户端请求同步user表数据
	 * @param request
	 * @param userNames 客户端没有该用户信息的集合
	 * @param modifyDate 客户端已存在的用户信息的modifyDate最大值
	 * @return
	 */
	@RequestMapping("/syncUserData")
	public @ResponseBody SyncUserResponse syncUser(HttpServletRequest request, 
			@RequestParam(value = "userNames[]", required = false) String[] userNames, 
			@RequestParam("modifyDate") Long modifyDate){
		Integer userId = (Integer) request.getAttribute("userName");
		
		List<UserBean> userBeans = userService.getUsersByNames(userNames);
		List<UserBean> updatedData = userService.getUpdatedData(userId, modifyDate);
	
		if(userBeans != null && !userBeans.isEmpty()){
			if(updatedData == null){
				updatedData = new ArrayList<UserBean>();
			}
			if(updatedData.isEmpty()){
				updatedData.addAll(userBeans);
			}else {
				for(UserBean userBean : userBeans){
					boolean equal = false;
					for(UserBean userBean2 : updatedData){
						if(userBean.getUserId() == userBean2.getUserId()){
							equal = true;
						}
					}
					if(!equal){
						updatedData.add(userBean);
					}
				}
			}
			
		}
		
		SyncUserResponse response = new SyncUserResponse(0, "success", updatedData);
		return response;
	}
	
	@RequestMapping("/queryUser")
	public @ResponseBody UserResponse queryUser(@RequestParam("queryName") String queryName){
		UserResponse userResponse = null;
		UserBean userBean = new UserBean();
		userBean.setUserName(queryName);
		userBean = userService.getUserByParams(userBean);
		
		if(userBean == null){
			userResponse = new UserResponse(1, "user_not_exist", null);
		}else{
			userResponse = new UserResponse(0, "success", userBean);
		}
		
		return userResponse;		
	}
	
	@RequestMapping("getUsersByNames")
	public @ResponseBody UserListResponse getUsersByNames(@RequestParam(value = "userNames[]") String[] userNames){
		List<UserBean> userBeans = userService.getUsersByNames(userNames);
		UserListResponse response = new UserListResponse(0, "success", userBeans);
		
		return response;
	}
}
