package com.flexiblecms.web.controller;

import com.flexiblecms.db.entiy.User;
import com.flexiblecms.service.dao.UserServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Administrator on 14-3-21.
 */
@Controller
public class HellwordController {


    @Autowired
    UserServiceDao userServiceDao;

    @RequestMapping(value="/hello/{id}")
    @ResponseBody
    public String hellword(@PathVariable String id){

      User user= userServiceDao.findUserById(id);



        return user==null ? "没有该用户" :user.getName();
    }
}
