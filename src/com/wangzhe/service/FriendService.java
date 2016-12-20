package com.wangzhe.service;

import java.util.List;

import com.wangzhe.bean.FriendBean;
import com.wangzhe.bean.UserBean;
import com.wangzhe.bean.FriendBean.SubType;
import com.wangzhe.util.Paging;

public interface FriendService {
	public List<FriendBean> getFriendsByOwner(Integer ownerId, long lastModifyDate);
	
	public void addOrUpdateFriends(Integer ownerId, Integer contactId, String remark, SubType subType);
	
	public boolean isFriends(Integer ownerId, Integer contactId);
}
