package com.wangzhe.dao;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangzhe.bean.CommentBean;
import com.wangzhe.bean.DynamicBean;
import com.wangzhe.bean.UserBean;
import com.wangzhe.dao.base.DaoSupportImpl;
import com.wangzhe.response.BaseResponse;

import java.util.List;

@Repository
@CacheConfig(cacheNames = "cache_comment")
public class CommentDao extends DaoSupportImpl<CommentBean>{

    @Cacheable(key = "#dynamicId")
    public List<CommentBean> getCommentsByDynamicId(Integer dynamicId){
        CommentBean commentBean = new CommentBean();
        commentBean.setDynamicId(dynamicId);
        return getAllByParams(commentBean);
    }
}
