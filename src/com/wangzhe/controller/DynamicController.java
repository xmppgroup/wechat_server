package com.wangzhe.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.wangzhe.bean.DynamicBean;
import com.wangzhe.bean.UserBean;
import com.wangzhe.response.BaseResponse;
import com.wangzhe.service.DynamicService;
import com.wangzhe.util.UploadFileUtil;

@Controller
@EnableWebMvc
//@RequestMapping("/dynamic") 
public class DynamicController extends BaseController{
	private static final int DEF_LIMIT = 10; //一次拉取10条数据
	
	@Autowired
	private DynamicService dynamicService;
	
	@RequestMapping(value = "/addDynamic", method = RequestMethod.POST)
	public @ResponseBody BaseResponse<DynamicBean> addDynamic(HttpServletRequest request) 
			throws IllegalStateException, IOException{
		Integer userId = (Integer) request.getAttribute(UserBean.USERID);
		
		String content = request.getParameter(DynamicBean.CONTENT);
		String images = null;
		 //创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = 
        		new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
        	//转换成多部分request    
            MultipartHttpServletRequest multiRequest = multipartResolver.resolveMultipart(request);
            
            List<String> fileNames = UploadFileUtil.saveFile(multiRequest);
            images = "";
            for(String fileName : fileNames){
            	images += (fileName + ",");
            }
            images = images.substring(0, images.lastIndexOf(","));
        }
        
        DynamicBean dynamicBean = new DynamicBean();
        dynamicBean.setPublisherId(userId);
        dynamicBean.setContent(content);
        dynamicBean.setImages(images);
        
        Integer dynamicId = dynamicService.addDynamicBean(dynamicBean);
        dynamicBean = dynamicService.getDynamicById(dynamicId);
        
        BaseResponse<DynamicBean> baseResponse = new BaseResponse<DynamicBean>(0, "success", dynamicBean);
        return baseResponse;
	}
	
	@RequestMapping(value = "/getDynamicsByPage", method = RequestMethod.POST)
	public @ResponseBody BaseResponse<List<DynamicBean>> getDynamicsByPage(HttpServletRequest request
			, @RequestParam(value="action", defaultValue="0") Integer action, 
			@RequestParam(value="dynamicId", defaultValue="0") Integer dynamicId,
			@RequestParam(value="limit", defaultValue="0") Integer limit){
		Integer userId = (Integer) request.getAttribute(UserBean.USERID);
		
		if(limit == 0){
			limit = DEF_LIMIT;
		}
		
		List<DynamicBean> dynamicBeans = 
				dynamicService.getMyAndFriendDynamcis(userId, action, dynamicId, limit);
		BaseResponse<List<DynamicBean>> baseResponse =
				new BaseResponse<List<DynamicBean>>(0, "success", dynamicBeans);
		return baseResponse;
	}
	
}
