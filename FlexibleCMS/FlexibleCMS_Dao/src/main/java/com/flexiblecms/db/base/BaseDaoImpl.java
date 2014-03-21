package com.flexiblecms.db.base;

import com.flexiblecms.db.common.CommonDaoSupport;

/**
 * Created by Administrator on 14-3-21.
 */
public class BaseDaoImpl<T> extends CommonDaoSupport {

    public T getById(String id){

        return (T)getSqlSession().selectOne("findUserById",id);
    }
}
