package com.wangzhe.controller;

import com.wangzhe.bean.UserBean;
import com.wangzhe.bean.UserPropertyBean;
import com.wangzhe.response.BaseResponse;
import com.wangzhe.service.UserPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/5/21 0021.
 */
@Controller
@EnableWebMvc
public class UserPropertyController extends BaseController{
    @Autowired
    private UserPropertyService userPropertyService;

    @RequestMapping(value="/updateUserProperty", method= RequestMethod.POST)
    public @ResponseBody
    BaseResponse updateUserProperty(HttpServletRequest request,
                     @RequestParam(name = "userProperties[]") UserPropertyBean[] userPropertyBeans){
        Integer userId = (Integer) request.getAttribute(UserBean.USERID);
        if(userPropertyBeans != null){
            for(UserPropertyBean userPropertyBean : userPropertyBeans){
                userPropertyBean.setUserId(userId);
            }
            userPropertyService.addOrUpdateUserProperties(userPropertyBeans);
        }
        return new BaseResponse(0, "success");
    }
}
