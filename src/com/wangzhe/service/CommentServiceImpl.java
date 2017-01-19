package com.wangzhe.service;

import java.math.BigInteger;
import java.util.List;

import com.wangzhe.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangzhe.bean.CommentBean;
import com.wangzhe.dao.CommentDao;
import com.wangzhe.util.TimeUtil;

@Service
@Transactional
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private FriendService friendService;
	@Autowired
	private UserService userService;

	public Integer addComment(CommentBean commentBean){
		commentBean.setModifyDate(BigInteger.valueOf(TimeUtil.getTime()));
		return commentDao.addObj(commentBean);
	}

	@Transactional
	public CommentBean getCommentById(Integer commentId) {
		CommentBean commentBean = commentDao.getObjById(commentId);
		commentBean.setCommentUserName(userService.getShowName(commentBean.getCommenterId()));
		if(commentBean.getReplyUserId() != null){
			commentBean.setReplyUserName(friendService.getContactName(commentBean.getCommenterId(),
					commentBean.getReplyUserId()));
		}
		return commentBean;
	}

	public List<CommentBean> getCommentsByDynamicId(Integer dynamicId) {
		List<CommentBean> commentBeanList = commentDao.getCommentsByDynamicId(dynamicId);
		if(commentBeanList != null){
			for (CommentBean commentBean : commentBeanList){
				commentBean.setCommentUserName(userService.getShowName(commentBean.getCommenterId()));
				if(commentBean.getReplyUserId() != null && 0 != commentBean.getReplyUserId()){
					commentBean.setReplyUserName(friendService.getContactName(commentBean.getCommenterId(),
							commentBean.getReplyUserId()));
				}
			}
		}

		return commentBeanList;
	}
}
