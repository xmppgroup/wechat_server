package com.wangzhe.service;

import com.wangzhe.bean.IdBean;
import com.wangzhe.dao.IdDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/17.
 */
@Service
public class IdServiceImpl {
    private static final Logger Log = LoggerFactory.getLogger(IdServiceImpl.class);
    private Map<Integer, IdBean> map = new HashMap<Integer, IdBean>();

    @Autowired
    private IdDao idDao;

    public IdServiceImpl(){
        int updateTimeType = IdBean.IdType.UPDATE_TIME.value;
        map.put(updateTimeType, new IdBean(updateTimeType, 5));
    }

    public  long nextID(int type) {
        if(map.containsKey(Integer.valueOf(type))) {
            return nextUniqueID(map.get(Integer.valueOf(type)));
        } else {
            IdBean idBean = new IdBean(type, 5);
            map.put(type, idBean);
            return nextUniqueID(idBean);
        }
    }

    public long nextID(IdBean.IdType idType) {
        return nextID(idType.value);
    }

    @Transactional
    public synchronized long nextUniqueID(IdBean idBean) {
        if(idBean.getId() >= idBean.getMaxId()) {
            this.getNextBlock(idBean);
        }

        long id = (idBean.id++);
        return id;
    }

    private void getNextBlock(IdBean idBean) {
        long e = idDao.getIdByType(idBean.idType);
        if(e == 0L){
            idBean.id = 1L;
            createNewID(idBean);
            e = 1L;
        }

        IdBean tempBean = new IdBean();
        tempBean.id = e + idBean.blockSize;
        tempBean.idType = idBean.idType;
        idDao.updateObj(tempBean);

        idBean.id = e;
        idBean.maxId = e + idBean.blockSize;
    }

    private void createNewID(IdBean idBean){
        idDao.addObj(idBean);
    }
}
