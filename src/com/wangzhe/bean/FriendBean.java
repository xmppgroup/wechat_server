package com.wangzhe.bean;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by 王宗文 on 2016/6/20.
 */

@Entity
@Table(name="wcfriend")
public class FriendBean{
	public static final String FRIEND_ID = "friendId";
	public static final String OWNER_ID = "ownerId";
	public static final String CONTACT_ID = "contactId";
	public static final String SUB_TYPE = "subType";
	public static final String REMARK = "remark";
	public static final String FLAG = "flag";
	public static final String MODIFY_DATE = "modifyDate";
	
	public enum SubType{
		NONE("none"), FROM("from"), TO("to"), BOTH("both"), BLOCK("block"), BE_BLOCKED("be_blocked");
		
		private String subType;
		
		private SubType(String type){
			this.subType = type;
		}
		
		@Override
		public String toString() {
			return subType;
		}
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer friendId;
    private Integer ownerId;
    private Integer contactId;
    private String subType;
    private String remark;
    private Integer flag;
    private BigInteger modifyDate;

    public FriendBean(){

    }

    public Integer getFriendId() {
		return friendId;
	}

	public void setFriendId(Integer friendId) {
		this.friendId = friendId;
	}

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactName(Integer contactId) {
        this.contactId = contactId;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public BigInteger getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(BigInteger modifyDate) {
		this.modifyDate = modifyDate;
	}
    
    

 
}
