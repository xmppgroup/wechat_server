package com.wangzhe.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.wangzhe.bean.CommentBean;
import com.wangzhe.bean.DynamicBean;
import com.wangzhe.bean.UserBean;
import com.wangzhe.response.BaseResponse;
import com.wangzhe.service.CommentService;
import com.wangzhe.service.DynamicService;

@Controller
@EnableWebMvc
public class CommentController extends BaseController{
	@Autowired
	private DynamicService dynamicService;
	@Autowired
	private CommentService commentService;
	
	/**
	 * 添加评论
	 * @param request
	 * @param type 评论类型 0 评论 1 点赞
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	public @ResponseBody BaseResponse<CommentBean> addComment(HttpServletRequest request,
			@ModelAttribute("comment") CommentBean commentBean){
		BaseResponse<CommentBean> baseResponse = null;
		int commentUserId = (Integer) request.getAttribute(UserBean.USERID);
		String type = commentBean.getType();
		if(CommentBean.Type.validOf(type) == null){
			baseResponse = new BaseResponse<CommentBean>(1, "type_invalid");
			return baseResponse;
		}
		
		if(type.equals(CommentBean.Type.COMMENT)){ //评论
			String content = commentBean.getContent();
			if("".equals(content) || "".equals(content.trim())){
				baseResponse = new BaseResponse<CommentBean>(2, "content_invalid");
				return baseResponse;
			}		
		}
		
		int dynamicId = commentBean.getDynamicId();
		DynamicBean dynamicBean = dynamicService.getDynamicById(dynamicId);
		if(dynamicBean == null){
			baseResponse = new BaseResponse<CommentBean>(3, "failed");
			return baseResponse;
		}
		
		commentBean.setCommenterId(commentUserId);
		commentBean.setCommentId(null);
		commentBean.setCreateDate(null);
		commentBean.setModifyDate(null);
		int commentId = commentService.addComment(commentBean);

		commentBean = commentService.getCommentById(commentId);
		baseResponse = new BaseResponse(0, "success", commentBean);
		return baseResponse;
		
	}
}
