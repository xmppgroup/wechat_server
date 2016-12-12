package com.wangzhe.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.wangzhe.bean.FriendBean;
import com.wangzhe.bean.FriendBean.SubType;
import com.wangzhe.response.BaseResponse;
import com.wangzhe.response.GetFriendsResponse;
import com.wangzhe.service.FriendService;

@Controller
@EnableWebMvc
public class FriendController extends BaseController{
	
	@Autowired
	private FriendService friendService;
	
	@RequestMapping(value="/syncFriendData")
	public @ResponseBody GetFriendsResponse getFriends(HttpServletRequest request, 
			@RequestParam(value = "modifyDate", required = false) Long modifyDate){
		GetFriendsResponse response = null;
		String ownerName = (String) request.getAttribute("userName");

		long lastModifyDate = 0;
		try{
			lastModifyDate = modifyDate.longValue();
		}catch(Exception e){}
		List<FriendBean> friendBeans = friendService.getFriendsByOwnerName(ownerName, lastModifyDate);
		response = new GetFriendsResponse(0, "success", friendBeans);

		return response;
	}
	
	@RequestMapping(value="/addFriend")
	public @ResponseBody BaseResponse addFriends(HttpServletRequest request,
			@RequestParam("contactName") String contactName){
		String ownerName = (String) request.getAttribute("userName");
		BaseResponse baseResponse = null;
		if(TextUtils.isEmpty(contactName) || ownerName.equals(contactName)){
			baseResponse = new BaseResponse(1, "invalid_conactname");
		}else {
			if(friendService.isFriends(ownerName, contactName)){
				baseResponse = new BaseResponse(2, "have_been_friends");
			}else {
				friendService.addOrUpdateFriends(ownerName, contactName, null, SubType.BOTH);
				friendService.addOrUpdateFriends(contactName, ownerName, null, SubType.BOTH);
				baseResponse = new BaseResponse(0, "success");
			}
		}
		return baseResponse;
	}
	
}
