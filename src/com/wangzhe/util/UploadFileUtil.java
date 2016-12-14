package com.wangzhe.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class UploadFileUtil {
	private static final String FILE_PATH;
	static{
		if(Config.isDebug()){
			FILE_PATH = "D:\\upload/";
		}else {
			FILE_PATH = "/usr/wangzhe/upload/";
		}
	}
	
	public static List<String> saveFile(MultipartHttpServletRequest multiRequest) 
			throws IllegalStateException, IOException{
		 //取得request中的所有文件名  
        Iterator<String> iter = multiRequest.getFileNames();  
        List<String> fileNameS = new ArrayList<String>();
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
                    //定义上传路径  
                   	String path = FILE_PATH + myFileName;                   
                    File localFile = new File(path);  
                    file.transferTo(localFile); 
                    
                    fileNameS.add(myFileName);
                }  
            }  
        }
        
        return fileNameS;
	}
	
}
