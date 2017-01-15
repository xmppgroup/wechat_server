package com.wangzhe.service;

import java.util.List;

import com.wangzhe.bean.FriendBean;
import com.wangzhe.bean.UserBean;
import com.wangzhe.bean.FriendBean.SubType;
import com.wangzhe.util.Paging;

public interface FriendService {
	public List<FriendBean> getFriendsByOwner(Integer ownerId, long lastModifyDate);
	
	public void addOrUpdateFriends(Integer ownerId, Integer contactId, String remark, SubType subType);
	
	public boolean isBothFriends(Integer ownerId, Integer contactId);

	public FriendBean getFriendByOwnerAndContact(Integer ownerId, Integer contactId);
	//获取联系人名字，如果remark不为NULL，则remark.否则从user表中去取
	public String getContactName(Integer ownerId, Integer contactId);
}
