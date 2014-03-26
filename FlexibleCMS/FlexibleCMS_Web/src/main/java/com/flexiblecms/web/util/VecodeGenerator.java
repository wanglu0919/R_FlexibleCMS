package com.flexiblecms.web.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by wanglu on 14-3-26.
 * 验证码生成器
 */
public class VecodeGenerator {


    private static int lineSize = 40;//干扰线数量

    private static String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";//随机产生的字符串


    public static BufferedImage genateIamge(String vecode,int width,int height){

        //BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);

        Graphics g = image.getGraphics();//产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        g.fillRect(0, 0, width, height);

        g.setFont(new Font("Times New Roman",Font.ROMAN_BASELINE,18));
        g.setColor(getRandColor(110, 133));



        //绘制干扰线
        /*
        for(int i=0;i<=lineSize;i++){
            drowLine(g,width,height);
        }*/
        //绘制随机字符

        drowString(vecode,g);

        g.dispose();




        return image;



    }

    /*
        * 绘制字符串
        */
    private static  void drowString(String vecode,Graphics g){
     Random random = new Random();

        for(int i=0;i<vecode.length();i++){
            g.setFont(getFont());//设置字体
            g.setColor(new Color(random.nextInt(101),random.nextInt(111),random.nextInt(121)));

            g.translate(random.nextInt(3), random.nextInt(3));
            g.drawString(vecode.charAt(i)+"", 13*i, 16);
        }



    }



    public static String generateCode(int codeLen){

        Random random = new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<codeLen;i++){

            String rand = String.valueOf(getRandomString(random.nextInt(randString.length())));//生成随机字符串
            sb.append(rand);
        }

        return sb.toString();



    }

    /*
    * 绘制干扰线
    */
    private static void drowLine(Graphics g,int width,int height){
        Random random = new Random();
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x+xl, y+yl);
    }


    /*
    * 获取随机的字符
    */
    public static String getRandomString(int num){
        return String.valueOf(randString.charAt(num));
    }

/*
    * 获得字体
    */
    private  static  Font getFont(){
        return new Font("Fixedsys",Font.CENTER_BASELINE,16);
    }
    /*
     * 获得颜色
     */
    private static Color getRandColor(int fc,int bc){
        Random random = new Random();
        if(fc > 255)
            fc = 255;
        if(bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc-fc-16);
        int g = fc + random.nextInt(bc-fc-14);
        int b = fc + random.nextInt(bc-fc-18);
        return new Color(r,g,b);
    }
}
