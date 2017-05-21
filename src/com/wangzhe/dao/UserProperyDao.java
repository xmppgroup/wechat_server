package com.wangzhe.dao;

import com.wangzhe.bean.UserPropertyBean;
import com.wangzhe.dao.base.DaoSupportImpl;
import org.hibernate.SQLQuery;

/**
 * Created by Administrator on 2017/5/21 0021.
 */
public class UserProperyDao extends DaoSupportImpl<UserPropertyBean> {


    public void addUserProperties(UserPropertyBean... userPropertyBeans){
        StringBuilder sqlBuilder = new StringBuilder("insert or replace into wcuserproperty(userId, key, value) values ");
        for(UserPropertyBean userPropertyBean: userPropertyBeans){
            sqlBuilder.append("(\"").append(userPropertyBean.getUserId()).append("\", \"")
                    .append(userPropertyBean.getKey()).append("\", \"")
                    .append(userPropertyBean.getValue()).append("\"),");
        }
        sqlBuilder.deleteCharAt(sqlBuilder.lastIndexOf(","));
        SQLQuery sqlQuery = currentSession().createSQLQuery(sqlBuilder.toString());
        sqlQuery.executeUpdate();
    }
}
