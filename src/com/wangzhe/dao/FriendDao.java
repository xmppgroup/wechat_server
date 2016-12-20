package com.wangzhe.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wangzhe.bean.Flag;
import com.wangzhe.bean.FriendBean;
import com.wangzhe.bean.FriendBean.SubType;
import com.wangzhe.dao.base.DaoSupportImpl;
import com.wangzhe.dao.base.WhereItem;

@Repository
public class FriendDao extends DaoSupportImpl<FriendBean>{
	
	public FriendBean getFriendByOwnerAndContact(Integer ownerId, Integer contactId){
		return getFriendByOwnerAndContact(ownerId, contactId, null);
	}
	
	public FriendBean getFriendByOwnerAndContact(Integer ownerId, Integer contactId, SubType subType){
		List<WhereItem> whereItems = new ArrayList<WhereItem>();
		whereItems.add(new WhereItem(FriendBean.OWNER_ID, "=", ownerId));
		whereItems.add(new WhereItem(FriendBean.CONTACT_ID, "=", contactId));
		whereItems.add(new WhereItem(FriendBean.FLAG, "=", Flag.VALID.value()));
		if(subType == null){
			whereItems.add(new WhereItem(FriendBean.SUB_TYPE, "!=", SubType.NONE.toString()));
		}else {
			whereItems.add(new WhereItem(FriendBean.SUB_TYPE, "!=", subType.toString()));
		}
		
		return getTByParams(whereItems);
	}
	
}
