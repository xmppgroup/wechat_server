package com.wangzhe.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.wangzhe.bean.DynamicBean;
import com.wangzhe.bean.UserBean;
import com.wangzhe.response.AddDynamicResponse;
import com.wangzhe.response.BaseResponse;
import com.wangzhe.service.DynamicService;
import com.wangzhe.service.UserService;
import com.wangzhe.util.UploadFileUtil;

@Controller
@EnableWebMvc
//@RequestMapping("/dynamic") 
public class DynamicController extends BaseController{
	@Autowired
	private DynamicService dynamicService;
	
	@RequestMapping(value = "/addDynamic", method = RequestMethod.POST)
	public @ResponseBody BaseResponse addDynamic(HttpServletRequest request) 
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
        dynamicService.addDynamicBean(dynamicBean);
        
        BaseResponse baseResponse = new BaseResponse(0, "success");
        return baseResponse;
	}
	
}
