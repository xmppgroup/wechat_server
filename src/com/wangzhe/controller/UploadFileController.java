package com.wangzhe.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.wangzhe.bean.UserBean;
import com.wangzhe.response.BaseResponse;
import com.wangzhe.response.UploadFileResponse;
import com.wangzhe.response.UserResponse;
import com.wangzhe.service.UserService;
import com.wangzhe.util.Config;

@EnableWebMvc
@Controller
public class UploadFileController extends BaseController{
	@Autowired
	private UserService userService;
	
	private static final String FILE_PATH;
	static{
		if(Config.isDebug()){
			FILE_PATH = "D:\\upload/";
		}else {
			FILE_PATH = "/usr/wangzhe/upload/";
		}
	}
	
	
	@RequestMapping(path = "/uploadAvatar")
	public @ResponseBody UserResponse uploadAvatar(HttpServletRequest request) throws IllegalStateException, IOException{
		UserResponse response = null;
		
		String userName = (String) request.getAttribute(UserBean.USERNAME);
		 //创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = 
        		new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = multipartResolver.resolveMultipart(request);
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();  
            String newFileName = null;
            while(iter.hasNext()){  
                //记录上传过程起始时的时间，用来计算上传时间  
                int pre = (int) System.currentTimeMillis();  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();  
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(myFileName.trim() !=""){                 
                        //重命名上传后的文件名                    	                   	               
                        newFileName = getNewFileName(userName, file.getOriginalFilename()); 
                        //定义上传路径  
                       	String path = FILE_PATH + newFileName; 
                       
                        File localFile = new File(path);  
                        file.transferTo(localFile);  
                    }  
                }  
            }  
              
            UserBean updateUserBean = 
            		userService.updateUser(userName, UserBean.HEADURL, newFileName);
            response = new UserResponse(0, "success", updateUserBean);
        }
        
        return response;
	}
	
	private String getNewFileName(String userName, String oldFileName){
		String nowTime = System.currentTimeMillis() + "";
		
		String newFileName;
		if(oldFileName.length() < 8){
			newFileName = oldFileName;
		}else{
			newFileName = oldFileName.substring(oldFileName.length() - 8);
		}
		return userName + "_" + nowTime.substring(nowTime.length() - 6) + "_" + newFileName;
	}

}
