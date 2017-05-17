package com.wangzhe.bean;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by Administrator on 2017/5/17.
 */
@Entity
@Table(name = "wcid")
public class IdBean {
    public Integer idType;
    public Long id;
    @Transient
    public Long maxId;
    @Transient
    public Integer blockSize;

    public IdBean(){

    }

    public IdBean(int idType, int blockSize){
        this.idType = idType;
        this.blockSize = blockSize;
        this.id = 0L;
        this.maxId = 0L;
    }

    public Integer getIdType() {
        return idType;
    }

    public IdBean setIdType(Integer idType) {
        this.idType = idType;
        return this;
    }

    public Long getId() {
        return id;
    }

    public IdBean setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getMaxId() {
        return maxId;
    }

    public IdBean setMaxId(Long maxId) {
        this.maxId = maxId;
        return this;
    }

    public Integer getBlockSize() {
        return blockSize;
    }

    public IdBean setBlockSize(Integer blockSize) {
        this.blockSize = blockSize;
        return this;
    }

    public enum IdType{
        UPDATE_TIME(1);
        public int value;
        IdType(int value){
            this.value = value;
        }
    }
}
