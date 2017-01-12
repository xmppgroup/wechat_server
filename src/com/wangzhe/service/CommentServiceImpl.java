package com.wangzhe.service;

import java.math.BigInteger;

import com.wangzhe.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangzhe.bean.CommentBean;
import com.wangzhe.bean.DynamicBean;
import com.wangzhe.dao.CommentDao;
import com.wangzhe.util.TimeUtil;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private UserDao userDao;

	@Transactional
	public Integer addComment(CommentBean commentBean){
		commentBean.setModifyDate(BigInteger.valueOf(TimeUtil.getTime()));
		return commentDao.addObj(commentBean);
	}

	@Transactional
	public CommentBean getCommentById(Integer commentId) {
		CommentBean commentBean = commentDao.getObjById(commentId);
		commentBean.setCommentUser(userDao.getObjById(commentBean.getCommenterId()));
		if(commentBean.getReplyUserId() != null){
			commentBean.setReplyUser(userDao.getObjById(commentBean.getReplyUserId()));
		}
		return commentBean;
	}
}
