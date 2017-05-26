package com.wangzhe.controller;

import com.wangzhe.bean.UserBean;
import com.wangzhe.bean.UserPropertyBean;
import com.wangzhe.response.BaseResponse;
import com.wangzhe.service.UserPropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/5/21 0021.
 */
@Controller
@EnableWebMvc
public class UserPropertyController extends BaseController{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserPropertyController.class);
    @Autowired
    private UserPropertyService userPropertyService;

    @RequestMapping(value="/updateUserProperty", method= RequestMethod.POST)
    public @ResponseBody
    BaseResponse updateUserProperty(HttpServletRequest request,
                     @RequestBody UserPropertyBean[] userPropertyBeans){
        Integer userId = (Integer) request.getAttribute(UserBean.USERID);
        if(userPropertyBeans != null){
            LOGGER.info("propertySize: " + userPropertyBeans.length);
            for(UserPropertyBean userPropertyBean : userPropertyBeans){
                userPropertyBean.setUserId(userId);
            }
            userPropertyService.addOrUpdateUserProperties(userPropertyBeans);
        }
        return new BaseResponse(0, "success");
    }
}
