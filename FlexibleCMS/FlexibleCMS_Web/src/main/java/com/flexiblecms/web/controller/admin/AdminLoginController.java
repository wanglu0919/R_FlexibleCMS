package com.flexiblecms.web.controller.admin;

import com.flexiblecms.web.util.VecodeGenerator;
import com.flexiblecms.web.util.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Administrator on 14-3-26.
 */
@Controller
@RequestMapping("/admin")
public class AdminLoginController {


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {

        return "admin/login";

    }

    /**
     * 登录 AJax请求
     * @param name
     * @param password
     * @param vecode
     * @return
     */
    @RequestMapping(value="/login",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap  doLogin(@RequestParam String name,@RequestParam String password,@RequestParam String vecode,ModelMap mp,HttpSession session){

            String sesseVode=session.getAttribute(WebConstants.SessionKey.VECODE_KEY).toString();

        System.out.println("登录了...");
            if(!sesseVode.equals(vecode)){//验证码检测

                mp.put("msg","验证码错");
                mp.put("data","错误");

                return  mp;

            }


        mp.put("msg","成功");
        mp.put("data","错误");




        return null;
    }


    /**
     * 首页登录验证码
     *
     * @param session
     * @param response
     * @return
     */
    @RequestMapping(value = "/login/vecode")
    public void getVecode(HttpSession session, HttpServletResponse response) {

        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        String vecode = VecodeGenerator.generateCode(4);
        BufferedImage image = VecodeGenerator.genateIamge(vecode, 80, 26);

        session.removeAttribute(WebConstants.SessionKey.VECODE_KEY);//清除session

        session.setAttribute(WebConstants.SessionKey.VECODE_KEY, vecode);//放入session中


        try {
            ImageIO.write(image, "JPEG", response.getOutputStream());


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
