package com.wangzhe.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wangzhe.bean.UserBean;
import com.wangzhe.exception.TokenException;
import com.wangzhe.response.BaseResponse;
import com.wangzhe.service.TokenService;

public class DefaultInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    public void afterCompletion(HttpServletRequest arg0,
                                HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }

    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {
        // TODO Auto-generated method stub

    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

        String token = request.getHeader("token");//

        Map<String, Object> result = tokenService.checkToken(token);
        int resultCode = (Integer) result.get("code");
        if (resultCode == BaseResponse.TOKEN_INVALID) {
            throw new TokenException(BaseResponse.TOKEN_INVALID, "token_invalid");
        } else if (resultCode == BaseResponse.TOKEN_EXPIRED) {
            throw new TokenException(BaseResponse.TOKEN_EXPIRED, "token_expired");
        } else {
            Integer userId = (Integer) result.get(UserBean.USERID);
            String userName = (String) result.get(UserBean.USERNAME);
            request.setAttribute(UserBean.USERID, userId);
            request.setAttribute(UserBean.USERNAME, userName);
        }
        return true;
    }

}
