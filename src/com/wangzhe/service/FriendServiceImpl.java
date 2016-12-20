package com.wangzhe.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangzhe.bean.Flag;
import com.wangzhe.bean.FriendBean;
import com.wangzhe.bean.FriendBean.SubType;
import com.wangzhe.dao.FriendDao;
import com.wangzhe.dao.base.WhereItem;
import com.wangzhe.util.TimeUtil;

@Service
public class FriendServiceImpl implements FriendService{
	@Autowired
	private FriendDao friendDao;

	@Transactional
	public List<FriendBean> getFriendsByOwner(Integer ownerId, long modifyDate) {
		List<WhereItem> whereItems = new ArrayList<WhereItem>();
		whereItems.add(new WhereItem(FriendBean.OWNER_ID, "=", ownerId));
		whereItems.add(new WhereItem(FriendBean.MODIFY_DATE, ">", modifyDate));
		return friendDao.getAllByParams(whereItems);
	}

	@Transactional
	public void addOrUpdateFriends(Integer ownerId, Integer contactId, String remark, SubType subType) {
		FriendBean existFriendBean = friendDao.getFriendByOwnerAndContact(ownerId, contactId);
		
		if(existFriendBean == null){
			FriendBean friendBean = new FriendBean();
			friendBean.setOwnerId(ownerId);
			friendBean.setContactName(contactId);
			friendBean.setSubType(subType.toString());
			friendBean.setRemark(remark);
			friendBean.setModifyDate(BigInteger.valueOf(TimeUtil.getTime()));
			friendBean.setFlag(Flag.VALID.value());
			friendDao.addObj(friendBean);
		}else {
			existFriendBean.setSubType(subType.toString());
			existFriendBean.setRemark(remark);
			existFriendBean.setModifyDate(BigInteger.valueOf(TimeUtil.getTime()));
			friendDao.updateObj(existFriendBean);
		}
			
	}
	
	@Transactional
	public boolean isFriends(Integer ownerId, Integer contactId){
		if(ownerId == null || contactId == null){
			return false;
		}
		return friendDao.getFriendByOwnerAndContact(ownerId, contactId, SubType.BOTH) != null;
	}


}
