package com.wangzhe.service;

import com.wangzhe.bean.UserPropertyBean;
import com.wangzhe.dao.UserPropertyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */
@Service
@Transactional
public class UserPropertyServiceImpl implements UserPropertyService{
    @Autowired
    private UserPropertyDao userPropertyDao;

    public List<UserPropertyBean> getPropertiesByUserId(int userId){
        return userPropertyDao.getPropertiesByUserId(userId);
    }

    public void addOrUpdateUserProperties(UserPropertyBean... userPropertyBeans) {
        userPropertyDao.addOrUpdateProperties(userPropertyBeans);
    }
}
