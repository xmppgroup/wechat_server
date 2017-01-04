package com.wangzhe.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public @ResponseBody BaseResponse addComment(HttpServletRequest request,
			@RequestParam(value="type", defaultValue="0") Integer type,
			@RequestParam(value="content", defaultValue="") String content,
			@RequestParam("dynamicId") Integer dynamicId){
		BaseResponse baseResponse = null;
		int commentUserId = (Integer) request.getAttribute(UserBean.USERID);
		if(type != 0 && type != 1){
			baseResponse = new BaseResponse(1, "type_invalid");
			return baseResponse;
		}
		
		if(type == 0){ //评论
			if("".equals(content) || "".equals(content.trim())){
				baseResponse = new BaseResponse(2, "content_invalid");
				return baseResponse;
			}		
		}
		
		DynamicBean dynamicBean = dynamicService.getDynamicById(dynamicId);
		if(dynamicBean == null){
			baseResponse = new BaseResponse(3, "failed");
			return baseResponse;
		}
		
		CommentBean commentBean = new CommentBean();
		commentBean.setCommenterId(commentUserId);
		commentBean.setType(CommentBean.Type.valueOf(type).toString());
		commentBean.setDynamicId(dynamicId);
		commentBean.setContent(content);
		
		commentService.addComment(commentBean);
		baseResponse = new BaseResponse(0, "success");
		return baseResponse;
		
	}
}
