package com.wangzhe.service;

import java.util.List;

import com.wangzhe.bean.UserBean;
import com.wangzhe.util.Paging;

public interface UserService {
	public List<UserBean> getAllUser();
	public List<UserBean> searchUser(String propName,String value);
	public UserBean getUserById(int userTd);
	public UserBean getUserById(int userId, long properModifyDate);
	// 获取用户展示名，nickName == null ? userName : nickName
	public String getShowName(int userId);
	public List<UserBean> getUsersByPaging(Paging paging);
	public List<UserBean> exportUser(String []ids);
	public boolean isUserExist(String loginname);
	public boolean isUserExist(Integer userId);
	public String addUser(UserBean user);
	public UserBean getUserByParams(UserBean user);
	public UserBean updateUser(String userName, String field, Object value);
	public List<UserBean> getUpdatedData(Integer userId, long modifyDate);
	public List<UserBean> getUsersByIds(Integer[] userIds);
}
