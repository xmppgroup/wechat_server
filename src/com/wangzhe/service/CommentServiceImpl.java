package com.wangzhe.service;

import java.math.BigInteger;

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

	@Transactional
	public void addComment(CommentBean commentBean){
		commentBean.setModifyDate(BigInteger.valueOf(TimeUtil.getTime()));
		commentDao.addObj(commentBean);
	}
}
