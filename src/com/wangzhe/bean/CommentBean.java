package com.wangzhe.bean;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * Created by 王宗文 on 2016/6/20.
 */

@Entity
@Table(name="wccomment")
public class CommentBean{
	public static final String COMMENT_ID = "commentId";
	public static final String COMMENTER_ID = "commenterId";
	public static final String DYNAMIC_ID = "dynamicId";
	public static final String REPLY_COMMENT_ID = "replyUserId";
	public static final String CONTENT = "content";
	public static final String SUB_TYPE = "subType";
	public static final String CREATE_DATE = "createDate";
	public static final String MODIFY_DATE = "modifyDate";
	
	public enum Type{
		SUPPORT("support"), COMMENT("comment");
		
		private String subType;
		
		private Type(String type){
			this.subType = type;
		}
		
		@Override
		public String toString() {
			return subType;
		}
		
		public static Type validOf(String type){
			if("support".equals(type)){
				return SUPPORT;
			}else if("comment".equals(type)){
				return COMMENT;
			}
			return null;
		}
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer commentId;// 评论id
	private Integer commenterId;// 评论人或者点赞人id
	private Integer dynamicId;// 动态id
	private Integer replyUserId;//回复用户id
    private String type;//  类型
	private String content;// 评论内容
    private Date createDate; //创建日期
    private BigInteger modifyDate;///修改信息日期
    @Transient
	private UserBean commentUser;//评论人或者点赞人
	@Transient
	private UserBean replyUser; //回复用户

    public CommentBean(){

    }

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Integer getCommenterId() {
		return commenterId;
	}

	public void setCommenterId(Integer commenterId) {
		this.commenterId = commenterId;
	}

	public Integer getDynamicId() {
		return dynamicId;
	}

	public void setDynamicId(Integer dynamicId) {
		this.dynamicId = dynamicId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Integer getReplyUserId() {
		return replyUserId;
	}

	public void setReplyUserId(Integer replyUserId) {
		this.replyUserId = replyUserId;
	}

	public UserBean getCommentUser() {
		return commentUser;
	}

	public void setCommentUser(UserBean commentUser) {
		this.commentUser = commentUser;
	}

	public UserBean getReplyUser() {
		return replyUser;
	}

	public void setReplyUser(UserBean replyUser) {
		this.replyUser = replyUser;
	}
}
