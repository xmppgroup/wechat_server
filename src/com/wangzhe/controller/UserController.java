package com.wangzhe.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.wangzhe.bean.TokenUserBean;
import com.wangzhe.bean.UserBean;
import com.wangzhe.net.Xmpp;
import com.wangzhe.response.BaseResponse;
import com.wangzhe.response.RegisterBean;
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
	public @ResponseBody BaseResponse<TokenUserBean> login(@Valid @ModelAttribute("user") UserBean userBean, BindingResult bindingResult){
		BaseResponse<TokenUserBean> loginResponse = null;
		if(bindingResult.hasErrors()){
			loginResponse = new BaseResponse<TokenUserBean>(1, "userName or passWord is not right", null);
			return loginResponse;
		}
		userBean = userService.getUserByParams(userBean);
		
		if(userBean != null){
			TokenUserBean tokenUserBean = new TokenUserBean();
			String token = tokenService.newToken(userBean.getUserId(), userBean.getUserName());
			tokenUserBean.setUserBean(userBean);
			tokenUserBean.setToken(token);
			loginResponse = new BaseResponse<TokenUserBean>(0, "success", tokenUserBean);
		}else{
			loginResponse = new BaseResponse<TokenUserBean>(1, "userName or passWord is not right", null);
		}
		return loginResponse;
	}
	
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	public @ResponseBody BaseResponse<RegisterBean> addUser(@Valid @ModelAttribute("user") UserBean userBean, BindingResult bindingResult){
		BaseResponse<RegisterBean> response = null;
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			String errFiled = fieldError.getField();
			RegisterBean registerBean = new RegisterBean();
			registerBean.setErrField(errFiled);
			response = new BaseResponse<RegisterBean>(1, fieldError.getCode(), registerBean);
			return response;
		}
		String result = userService.addUser(userBean);
		if(result.equals("succ")){
			xmpp.register(userBean.getUserName(), userBean.getPassWord());
			response = new BaseResponse<RegisterBean>(0, "success", null);
		}else{
			response = new BaseResponse<RegisterBean>(2, "user_already_existed", null);
		}
		return response;
	}
	
	@RequestMapping(value="/updateUser", method=RequestMethod.POST)
	public @ResponseBody BaseResponse<UserBean> updateUser(HttpServletRequest request,
			@RequestParam("field") String field, @RequestParam("value") Object value){
		BaseResponse<UserBean> response = null;
		boolean canUpdate = true;
		if(field.equals("") || field.equals(UserBean.USERNAME) || field.equals(UserBean.USERID)
				|| field.equals(UserBean.CREATEDATE) || field.equals(UserBean.MODIFYDATE)){
			response = new BaseResponse<UserBean>(1, "refuse_modify", null);
			canUpdate = false;
		}else if(field.equals(UserBean.PASSWORD)){
			String passWord = (String) value;
			if(passWord.length() < 6 || passWord.length() > 16){
				response = new BaseResponse<UserBean>(2, "modified_value_invalid", null);
				canUpdate = false;
			}
		}
		
		if(canUpdate){
			String userName = (String) request.getAttribute("userName");
			UserBean userBean = userService.updateUser(userName, field, value);
			response = new BaseResponse<UserBean>(0, "success", userBean);
		}
		
		return response;
	}
	
	@RequestMapping("/searchUser")
	public @ResponseBody BaseResponse<List<UserBean>> searchUser(@RequestParam("search") String search){
		BaseResponse<List<UserBean>> response = null;
		
		if(search != null && !"".equals(search)){
			List<UserBean> userBeans = userService.searchUser(UserBean.USERNAME, search);
			if(userBeans != null && !userBeans.isEmpty()){
				for(UserBean userBean : userBeans){
					userBean.setPassWord(null);
				}
			}
			response = new BaseResponse<List<UserBean>>(0, "success", userBeans);
		}else {
			response = new BaseResponse<List<UserBean>>(1, "param_invalid", null);
		}
		
		return response;
	}
	
	/**
	 * 客户端请求同步user表数据
	 * @param request
	 * @param userIds 客户端没有该用户信息的集合
	 * @param modifyDate 客户端已存在的用户信息的modifyDate最大值
	 * @return
	 */
	@RequestMapping("/syncUserData")
	public @ResponseBody BaseResponse<List<UserBean>> syncUser(HttpServletRequest request, 
			@RequestParam(value = "userIds[]", required = false) Integer[] userIds, 
			@RequestParam("modifyDate") Long modifyDate){
		Integer userId = (Integer) request.getAttribute(UserBean.USERID);
		
		List<UserBean> userBeans = userService.getUsersByIds(userIds);
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
		
		BaseResponse<List<UserBean>> response = new BaseResponse<List<UserBean>>(0, "success", updatedData);
		return response;
	}
	
	@RequestMapping("/queryUser")
	public @ResponseBody BaseResponse<UserBean> queryUser(@RequestParam(value="userId", defaultValue="0") Integer userId,
			@RequestParam(value="userName", defaultValue = "") String userName){
		BaseResponse<UserBean> userResponse = null;
		UserBean userBean = new UserBean();
		if(userId != 0){
			userBean.setUserId(userId);
		}else {
			userBean.setUserName(userName);
		}
		
		userBean = userService.getUserByParams(userBean);
		
		if(userBean == null){
			userResponse = new BaseResponse<UserBean>(1, "user_not_exist", null);
		}else{
			userResponse = new BaseResponse<UserBean>(0, "success", userBean);
		}
		
		return userResponse;		
	}
}
