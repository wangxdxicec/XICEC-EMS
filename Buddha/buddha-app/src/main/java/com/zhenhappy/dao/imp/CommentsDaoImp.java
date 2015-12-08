package com.zhenhappy.dao.imp;

import org.springframework.stereotype.Repository;

import com.zhenhappy.dao.CommentsDao;
import com.zhenhappy.entity.TComments;

/**
 * 
 * @author rocsky
 *
 */
@Repository
public class CommentsDaoImp extends BaseDaoHibernateImp<TComments> implements CommentsDao {
    public CommentsDaoImp() {
        super(TComments.class);
    }
}
