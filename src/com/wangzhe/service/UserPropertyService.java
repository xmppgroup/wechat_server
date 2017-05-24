package com.wangzhe.service;

import com.wangzhe.bean.UserPropertyBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */
public interface UserPropertyService {
    List<UserPropertyBean> getPropertiesByUserId(int userId);
    void addOrUpdateUserProperties(UserPropertyBean... userPropertyBeans);
}
