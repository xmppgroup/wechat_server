package com.wangzhe.controller;

import com.wangzhe.bean.UserBean;
import com.wangzhe.bean.UserPropertyBean;
import com.wangzhe.response.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/5/21 0021.
 */
public class UserPropertyController extends BaseController{

    @RequestMapping(value="/updateUserProperty", method= RequestMethod.POST)
    public @ResponseBody
    BaseResponse<UserBean> updateUserProperty(HttpServletRequest request,
                     @RequestParam(name = "userProperties[]") UserPropertyBean[] userPropertyBeans){
        Integer userId = (Integer) request.getAttribute(UserBean.USERID);
        if(userPropertyBeans != null){
            for(UserPropertyBean userPropertyBean : userPropertyBeans){
                userPropertyBean.setUserId(userId);
            }
        }
    }
}
