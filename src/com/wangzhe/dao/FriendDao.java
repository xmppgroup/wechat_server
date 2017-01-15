package com.wangzhe.dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.type.StringType;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.wangzhe.bean.Flag;
import com.wangzhe.bean.FriendBean;
import com.wangzhe.bean.FriendBean.SubType;
import com.wangzhe.dao.base.DaoSupportImpl;
import com.wangzhe.dao.base.WhereItem;

@Repository
@CacheConfig(cacheNames = "cache_friend")
public class FriendDao extends DaoSupportImpl<FriendBean>{
/*	private static final String QUERY_CONTACT_NAME =
			"SELECT CASE WHEN ISNULL(wf.remark) THEN CASE WHEN ISNULL(wu.nickName) THEN wu.userName ELSE wu.nickName END " +
					"ELSE wf.remark END contactName from wcfriend wf, wcuser wu " +
					"WHERE wf.ownerId = ? AND wf.contactId = wu.userId AND wf.contactId = ?;";*/

	@Cacheable(key = "#{ownerId + '_' + contactId}")
	public FriendBean getFriendByOwnerAndContact(Integer ownerId, Integer contactId){
		return getFriendByOwnerAndContact(ownerId, contactId, null);
	}

	@Override
	public Integer addObj(FriendBean friendBean) {
		return super.addObj(friendBean);
	}

	@Override
	@CachePut(key = "#{friendBean.getOwnerId() + '_' + friendBean.getContactId()}")
	public void updateObj(FriendBean friendBean) {
		super.updateObj(friendBean);
	}

	@Cacheable
	public FriendBean getFriendByOwnerAndContact(Integer ownerId, Integer contactId, SubType subType){
		List<WhereItem> whereItems = new ArrayList<WhereItem>();
		whereItems.add(new WhereItem(FriendBean.OWNER_ID, "=", ownerId));
		whereItems.add(new WhereItem(FriendBean.CONTACT_ID, "=", contactId));
		whereItems.add(new WhereItem(FriendBean.FLAG, "=", Flag.VALID.value()));
		if(subType == null){
			whereItems.add(new WhereItem(FriendBean.SUB_TYPE, "!=", SubType.NONE.toString()));
		}else {
			whereItems.add(new WhereItem(FriendBean.SUB_TYPE, "=", subType.toString()));
		}
		
		return getTByParams(whereItems);
	}
	
}
