package com.flexiblecms.service.impl;

import com.flexiblecms.db.dao.UserDao;
import com.flexiblecms.db.entiy.User;
import com.flexiblecms.service.dao.UserServiceDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 14-3-21.
 */
@Service
public class UserServiceImpl implements UserServiceDao {




    @Resource
   UserDao userServiceDao;

    @Override
    public User findUserById(String id) {
        return userServiceDao.getById(id);
    }
}
