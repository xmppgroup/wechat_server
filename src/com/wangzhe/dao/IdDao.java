package com.wangzhe.dao;

import com.wangzhe.bean.IdBean;
import com.wangzhe.dao.base.DaoSupportImpl;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/5/17.
 */
@Repository
public class IdDao extends DaoSupportImpl<IdBean> {

    public Long getIdByType(int idType){
        IdBean idBean = new IdBean();
        idBean.idType = idType;
        try {
            return getAllByParams(idBean).get(0).id;
        }catch (Exception e){
            return 0L;
        }
    }
}
