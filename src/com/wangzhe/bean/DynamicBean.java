package com.wangzhe.bean;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wangzhe.util.CustomDateSerialize;


/**
 * Created by 王宗文 on 2016/6/20.
 */

@Entity
@Table(name="wcdynamic")
public class DynamicBean{
	public static final String DYNAMIC_ID = "dynamicId";
	public static final String PUBLISHER_ID = "publisherId";
	public static final String CONTENT = "content";
	public static final String IMAGES = "images";
	public static final String CREATE_DATE = "createDate";
	public static final String MODIFY_DATE = "modifyDate";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer dynamicId;// Id
	private Integer publisherId;// 发表人
	private String content;// 动态内容
	private String images;// 图片
	@JsonSerialize(using = CustomDateSerialize.class)
	private Date createDate; //创建日期
	private BigInteger modifyDate;///修改信息日期
	@Transient
	private String publisherName;//发布者
	@Transient
	private List<CommentBean> commentList;//评论列表

    public DynamicBean(){

    }

	

	public Integer getDynamicId() {
		return dynamicId;
	}



	public void setDynamicId(Integer dynamicId) {
		this.dynamicId = dynamicId;
	}



	public Integer getPublisherId() {
		return publisherId;
	}



	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}



	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
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

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public List<CommentBean> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentBean> commentList) {
		this.commentList = commentList;
	}

}
