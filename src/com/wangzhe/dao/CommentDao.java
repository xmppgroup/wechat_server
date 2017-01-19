package com.wangzhe.dao;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SQLQuery;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
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
    private static final String QUERY_COMMENTS_BY_DYNAMIC_ID =
            "SELECT * from wccomment WHERE dynamicId = ? ORDER BY createDate";

    @Cacheable(key = "#dynamicId")
    public List<CommentBean> getCommentsByDynamicId(Integer dynamicId){
        SQLQuery sqlQuery = currentSession().createSQLQuery(QUERY_COMMENTS_BY_DYNAMIC_ID);
        sqlQuery.setInteger(0, dynamicId);
        sqlQuery.setResultTransformer(resultTransformer);
        return sqlQuery.list();
    }

    @Override
    @CacheEvict(key = "#commentBean.dynamicId")
    public Integer addObj(CommentBean commentBean) {
        return super.addObj(commentBean);
    }
}
