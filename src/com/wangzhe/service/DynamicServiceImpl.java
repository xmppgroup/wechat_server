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
	@Autowired
	private DynamicDao dynamicDao;
	@Autowired
	private UserDao userDao;

	@Transactional
	public void addDynamicBean(DynamicBean dynamic) {
		dynamic.setModifyDate(BigInteger.valueOf(TimeUtil.getTime()));
		dynamicDao.addObj(dynamic);
	}

	public List<DynamicBean> getDynamcisByOwnerName(String ownerName, long lastModifyDate) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DynamicBean> getDynamcisByFriends(Integer userId, long lastModifyDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
