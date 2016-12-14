package com.wangzhe.service;

import java.util.List;

import com.wangzhe.bean.DynamicBean;
import com.wangzhe.bean.FriendBean;
import com.wangzhe.bean.UserBean;
import com.wangzhe.bean.FriendBean.SubType;
import com.wangzhe.util.Paging;

public interface DynamicService {
	public void addDynamicBean(DynamicBean dynamic);
	
	public List<DynamicBean> getDynamcisByFriends(Integer userId, long lastModifyDate);
	public List<DynamicBean> getDynamcisByOwnerName(String ownerName, long lastModifyDate);	
}
