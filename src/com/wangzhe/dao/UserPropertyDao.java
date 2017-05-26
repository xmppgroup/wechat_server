package com.wangzhe.dao;

import com.wangzhe.bean.UserPropertyBean;
import com.wangzhe.dao.base.DaoSupportImpl;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */
@Repository
public class UserPropertyDao extends DaoSupportImpl<UserPropertyBean> {

    public List<UserPropertyBean> getPropertiesByUserId(int userId){
        return currentSession().createCriteria(clazz).add(Restrictions.eq(UserPropertyBean.USER_ID, userId)).list();
    }

    public void addOrUpdateProperties(UserPropertyBean... userPropertyBeans){
        StringBuilder sqlBuilder = new StringBuilder("replace into wcuserproperty(userId, `key`, `value`) values ");
        for(UserPropertyBean userPropertyBean : userPropertyBeans){
            sqlBuilder.append("(\"").append(userPropertyBean.getUserId()).append("\", \"")
                    .append(userPropertyBean.getKey()).append("\", \"")
                    .append(userPropertyBean.getValue()).append("\"),");
        }

        sqlBuilder.deleteCharAt(sqlBuilder.lastIndexOf(","));
        currentSession().createSQLQuery(sqlBuilder.toString()).executeUpdate();
    }
}
