package com.wangzhe.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangzhe.bean.DynamicBean;
import com.wangzhe.dao.DynamicDao;
import com.wangzhe.dao.UserDao;
import com.wangzhe.util.Paging;
import com.wangzhe.util.TimeUtil;

@Service
public class DynamicServiceImpl implements DynamicService {
	private static final int ACTION_REFRESH = 0;
	private static final int ACTION_LOAD_MORE = 1;
	
	@Autowired
	private DynamicDao dynamicDao;
	@Autowired
	private UserDao userDao;

	@Transactional
	public DynamicBean addDynamicBean(DynamicBean dynamic) {
		dynamic.setModifyDate(BigInteger.valueOf(TimeUtil.getTime()));
		Integer newId = dynamicDao.addObj(dynamic);
		return dynamicDao.getObjById(newId);
	}

	public List<DynamicBean> getMyAndFriendDynamcis(Integer userId, int action,
			int dynamicId, int limit) {
		if(action == ACTION_REFRESH){
			List<DynamicBean> latestDynamics = 
					dynamicDao.getLatestDynamicBeans(userId, limit);
			if(dynamicId != 0 && latestDynamics != null && latestDynamics.size() > 0){
				if(latestDynamics.get(0).getDynamicId() == dynamicId){
					return null; //客户端传过来的id和查询到的id是一致的，说明没有新数据需要返回。
				}
			}
		}else {
			List<DynamicBean> loadMoreDynamicBeans =
					dynamicDao.getDynamisByPage(userId, dynamicId, limit);
			return loadMoreDynamicBeans;
		}
		return null;
	}

}
