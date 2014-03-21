package com.flexiblecms.service.dao;

import com.flexiblecms.db.entiy.User;

/**
 * Created by Administrator on 14-3-21.
 */
public interface UserServiceDao {

    public User findUserById(String id);
}
