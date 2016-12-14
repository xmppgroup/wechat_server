package com.wangzhe.service;

import java.util.List;

import com.wangzhe.bean.UserBean;
import com.wangzhe.util.Paging;

public interface UserService {
	public List<UserBean> getAllUser();
	public List<UserBean> searchUser(String propName,String value);
	public UserBean getUserById(int userid);
	public List<UserBean> getUsersByPaging(Paging paging);
	public void updateUser(UserBean user);
	public void deleteUser(int userid);
	public List<UserBean> exportUser(String []ids);
	public void bulkDelUser(String []ids);
	public boolean isUserExist(String loginname);
	public boolean isUserExist(Integer userId);
	public String addUser(UserBean user);
	public UserBean getUserByParams(UserBean user);
	public List<UserBean> getAllUserByParams(UserBean user);
	public List<UserBean> searchUserByM(String search,int ownerid);
	public String getPhotoByUName(String uname);
	public UserBean updateUser(String userName, String field, Object value);
	public List<UserBean> getUpdatedData(String userName, long modifyDate);
	public List<UserBean> getUsersByNames(String[] userNames);
}
