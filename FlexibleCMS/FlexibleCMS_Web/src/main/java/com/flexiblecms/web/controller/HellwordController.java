package com.flexiblecms.web.controller;

import com.flexiblecms.db.entiy.User;
import com.flexiblecms.service.dao.UserServiceDao;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

/**
 * Created by Administrator on 14-3-21.
 */
@Controller
public class HellwordController {


    @Autowired
    UserServiceDao userServiceDao;

    @Resource
    VelocityConfigurer velocityConfigurer;

    @RequestMapping(value = "/hello/{id}")

    public User  hellword(@PathVariable String id) {

        User user = userServiceDao.findUserById(id);

       return user;
       // return user == null ? "没有该用户" : user.getName();
    }


    @RequestMapping(value="/vim/{id}")
    public String testVolicity(@PathVariable String id,ModelMap map,HttpSession httpSession){

        User user=userServiceDao.findUserById(id);

        map.put("user",user);

        genateTemp( httpSession,user);
       // genateTemp
        return  "User";

    }


    private void ga(){



    }


    private void genateTemp(HttpSession httpSession,User user){//生成模板


       VelocityEngine velocityEngine=velocityConfigurer.getVelocityEngine();




       String path= httpSession.getServletContext().getRealPath("/");

        Properties p = new Properties();


        System.out.println(HellwordController.class);



        p.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
        p.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        p.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
        String tempPath=path+"WEB-INF\\template\\";

        p.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, tempPath); //此处的fileDir可以直接用绝对路径来

        velocityEngine.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, tempPath);
        try {
            //velocityEngine.init(p);

            Template template = velocityEngine.getTemplate("user.vim");



            VelocityContext context = new VelocityContext();
            context.put("user", user);
            FileOutputStream fos = new FileOutputStream(path + "aaa" + ".html");
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    fos, "UTF-8"));//设置写入的文件编码,解决中文问题
            template.merge(context, writer);
            writer.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }





}
