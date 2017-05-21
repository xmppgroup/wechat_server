package com.wangzhe.bean;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/17.
 */

@Entity
@Table(name = "wcuserproperty")
public class UserPropertyBean {
    private Integer userId;
    private String key;
    private String value;
    private BigInteger modifyDate;

    public UserPropertyBean(){

    }

    public UserPropertyBean(Integer userId, String key, String value, BigInteger modifyDate) {
        this.userId = userId;
        this.key = key;
        this.value = value;
        this.modifyDate = modifyDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public BigInteger getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(BigInteger modifyDate) {
        this.modifyDate = modifyDate;
    }
}
