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
	private UserService userService;
	@Autowired
	private CommentService commentService;

	@Transactional
	public Integer addDynamicBean(DynamicBean dynamic) {
		dynamic.setModifyDate(BigInteger.valueOf(TimeUtil.getTime()));
		Integer newId = dynamicDao.addObj(dynamic);
		return newId;
	}
	
	@Transactional
	public DynamicBean getDynamicById(Integer dynamicId){
		return dynamicDao.getObjById(dynamicId);
	}

	@Transactional
	public List<DynamicBean> getMyAndFriendDynamcis(Integer userId, int action,
			int dynamicId, int limit) {
		List<DynamicBean> dynamicBeanList;
		if(action == ACTION_REFRESH){
			dynamicBeanList =
					dynamicDao.getLatestDynamicBeans(userId, limit);
		}else {
			dynamicBeanList =
					dynamicDao.getDynamisByPage(userId, dynamicId, limit);
		}

		if(dynamicBeanList != null){
			for(DynamicBean dynamicBean : dynamicBeanList){
				dynamicBean.setPublisherName(userService.getShowName(dynamicBean.getPublisherId()));
				dynamicBean.setCommentList(commentService.getCommentsByDynamicId(dynamicBean.getDynamicId()));
			}
		}

		return dynamicBeanList;
	}

}
