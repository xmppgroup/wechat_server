package com.wangzhe.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangzhe.bean.UserBean;
import com.wangzhe.dao.UserDao;
import com.wangzhe.util.Paging;
import com.wangzhe.util.TimeUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserPropertyService userPropertyService;
		
	public List<UserBean> getAllUser() {
		return userDao.getAll();
	}

	public UserBean getUserById(int userId) {
		return getUserById(userId, 0);
	}

    public UserBean getUserById(int userId, long properModifyDate) {
	    UserBean userBean = userDao.getUserById(userId);
	    if(userBean != null){
            userBean.setUserProperties(userPropertyService.getPropertiesByUserId(userId));
        }
        return userBean;
    }

    public String getShowName(int userId) {
		UserBean userBean = getUserById(userId);
		if(userBean == null) return null;
		return TextUtils.isEmpty(userBean.getNickName()) ? userBean.getUserName() : userBean.getNickName();
	}

	public List<UserBean> getUsersByPaging(Paging paging) {
		return userDao.getObjByPaging(paging);
	}

	@Transactional
	public List<UserBean> searchUser(String propName, String value) {
		return userDao.searchUser(propName, value);
	}

	public List<UserBean> exportUser(String[] ids) {
		List<UserBean> list = new ArrayList<UserBean>();
		for(int i=0; i<ids.length; i++){
			int id = Integer.parseInt(ids[i]);
			UserBean user = userDao.getUserById(id);
			list.add(user);
		}
		return list;
	}

	public boolean isUserExist(String loginname) {
		return userDao.isUserExist(loginname);
	}
	
	public boolean isUserExist(Integer userId) {
		return userDao.isUserExist(userId);
	}

	public String addUser(UserBean user) {
		if(this.isUserExist(user.getUserName())){
			return "fail";
		}
		user.setModifyDate(BigInteger.valueOf(TimeUtil.getTime()));
		userDao.addObj(user);
		return "succ";
		
	}

	public UserBean getUserByParams(UserBean user) {
		if(user.getUserId() != null && user.getUserId() != 0){
			return userDao.getUserById(user.getUserId());
		}
		return userDao.getTByParams(user);
	}

	public UserBean updateUser(String userName, String field, Object value) {
		userDao.updateUser(userName, field, value);
		UserBean userBean = new UserBean();
		userBean.setUserName(userName);
		userBean = getUserByParams(userBean);
		return userBean;
	}

	@Transactional
	public List<UserBean> getUpdatedData(Integer userId,
			long modifyDate) {
		List<UserBean> userBeans = userDao.getMyFriendsByModifyDate(userId, modifyDate);
		return userBeans;
	}

	@Transactional
	public List<UserBean> getUsersByIds(Integer[] userIds) {
		if(userIds == null || userIds.length == 0){
			return null;
		}
		return userDao.getUsersByIds(userIds);
	}
	
	

}
