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
	
	public FriendBean getFriendByName(String ownerName, String contactName){
		List<WhereItem> whereItems = new ArrayList<WhereItem>();
		whereItems.add(new WhereItem(FriendBean.OWNER_NAME, "=", ownerName));
		whereItems.add(new WhereItem(FriendBean.CONTACT_NAME, "=", contactName));
		whereItems.add(new WhereItem(FriendBean.FLAG, "=", Flag.VALID.value()));
		whereItems.add(new WhereItem(FriendBean.SUB_TYPE, "!=", SubType.NONE.toString()));
		
		return getTByParams(whereItems);
	}
	
}
