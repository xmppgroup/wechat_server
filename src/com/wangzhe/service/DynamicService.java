package com.wangzhe.service;

import java.util.List;

import com.wangzhe.bean.DynamicBean;
import com.wangzhe.bean.FriendBean;
import com.wangzhe.bean.UserBean;
import com.wangzhe.bean.FriendBean.SubType;
import com.wangzhe.util.Paging;

public interface DynamicService {
	public Integer addDynamicBean(DynamicBean dynamic);
	
	public DynamicBean getDynamicById(Integer dynamicId);
	
	public List<DynamicBean> getMyAndFriendDynamcis(Integer userId, int action,
			int dynamicId, int limit);	
}
