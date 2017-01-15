package com.wangzhe.bean;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wangzhe.util.CustomDateSerialize;

@Entity
@Table(name="wcuser")
@JsonIgnoreProperties(value = "passWord")
public class UserBean implements Serializable{
	
	public static final String USERID = "userId";
	public static final String USERNAME = "userName";
	public static final String PASSWORD = "passWord";
	public static final String NICKNAME = "nickName";
	public static final String HEADURL = "headUrl";
	public static final String SIGNATURE = "signature";
	public static final String SEX = "sex";
	public static final String LOCATION = "location";
	public static final String BIRTHDAY = "birthday";
	public static final String TYPE ="type";
	public static final String TELEPHONE = "telephone";
	public static final String CREATEDATE = "createDate";
	public static final String MODIFYDATE = "modifyDate";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer userId;//
	@Length(min = 6, max = 16)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message="用户名只能包含下划线、数字及大小写字母")
    private String userName;// 用户名
	@Length(min = 6, max = 16)
    private String passWord;// 密码
    private String nickName;// 昵称
    private String telephone;// 手机号码
    private String headUrl;// 头像
    private String signature;// 签名
    private String sex;// 性别
    private String location;// 位置
    private String birthday;// 生日
    private String type;// N-普通用户 P-公众号
    @JsonSerialize(using = CustomDateSerialize.class)
    private Date createDate; //创建账号日期
    private BigInteger modifyDate; //修改信息日期

    public UserBean(){

    }

	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonIgnore
	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}


	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public BigInteger getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(BigInteger modifyDate) {
		this.modifyDate = modifyDate;
	}

    
   

}
