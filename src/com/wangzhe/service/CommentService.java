package com.wangzhe.service;

import java.util.List;

import com.wangzhe.bean.CommentBean;
import com.wangzhe.bean.DynamicBean;
import org.springframework.stereotype.Service;

public interface CommentService {
	public Integer addComment(CommentBean commentBean);
	public CommentBean getCommentById(Integer commentId);
	public List<CommentBean> getCommentsByDynamicId(Integer dynamicId);
}
