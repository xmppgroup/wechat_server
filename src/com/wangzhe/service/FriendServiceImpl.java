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
	public List<FriendBean> getFriendsByOwnerName(String ownerName, long modifyDate) {
		List<WhereItem> whereItems = new ArrayList<WhereItem>();
		whereItems.add(new WhereItem(FriendBean.OWNER_NAME, "=", ownerName));
		whereItems.add(new WhereItem(FriendBean.MODIFY_DATE, ">", modifyDate));
		return friendDao.getAllByParams(whereItems);
	}

	@Transactional
	public void addOrUpdateFriends(String ownerName, String contactName, String remark, SubType subType) {
		FriendBean existFriendBean = friendDao.getFriendByName(ownerName, contactName);
		
		if(existFriendBean == null){
			FriendBean friendBean = new FriendBean();
			friendBean.setOwnerName(ownerName);
			friendBean.setContactName(contactName);
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
	public boolean isFriends(String ownerName, String contactName){
		if(TextUtils.isEmpty(ownerName) || TextUtils.isEmpty(contactName)){
			return false;
		}
		return friendDao.getFriendByName(ownerName, contactName) != null;
	}


}
