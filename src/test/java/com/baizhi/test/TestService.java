package com.baizhi.test;


import com.baizhi.dao.UserDAO;
import com.baizhi.entity.*;
import com.baizhi.service.*;
import com.baizhi.util.UUIDUtil;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

public class TestService {
    @Test
    public void tessss(){
        long l = 235345;
        System.out.println((int)(0+Math.random()*1177777));

    }
    @Test
    public void test2() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com.baizhi.mapper/applicationContext.xml");
        AdminService adminService = (AdminService) applicationContext.getBean("adminServiceImpl");
        System.out.println(adminService.login("root", "root"));

    }

    @Test
    public void test3() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com.baizhi.mapper/applicationContext.xml");
       UserDAO userDAO = (UserDAO) applicationContext.getBean("userDAO");
        User user = userDAO.queryOneByName("19993308761");
        System.out.println(user);
    }

    @Test
    public void test4() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com.baizhi.mapper/applicationContext.xml");
        BannerService bannerService = (BannerService) applicationContext.getBean("bannerServiceImpl");
        List<Banner> banners = bannerService.showAll();
        for (Banner b : banners) {
            System.out.println(b);
        }
    }
    @Test
    public void test5() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com.baizhi.mapper/applicationContext.xml");
        BannerService bannerService = (BannerService) applicationContext.getBean("bannerServiceImpl");

        for(int i=0;i<10;i++){
            UUID uuid = UUID.randomUUID();
            String s1[] = uuid.toString().split("-");
            String sum="";
            for (String s:s1){
                sum = sum+s;
            }
            Banner banner = new Banner(sum,"佛教"+i,"image/2.jpg","一本佛教书","y",new Date());
            bannerService.addBanner(banner);

        }

    }
    @Test
    public void test6() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com.baizhi.mapper/applicationContext.xml");
        BannerService bannerService = (BannerService) applicationContext.getBean("bannerServiceImpl");
        bannerService.deleteBanner("[Ljava.lang.String;@9acf2c");
    }
    @Test
    public void test7() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com.baizhi.mapper/applicationContext.xml");
        BannerService bannerService = (BannerService) applicationContext.getBean("bannerServiceImpl");
        Banner banner = new Banner("7705aac363a311e8a4f6b46d83db7585","神庙图","image/1.jpg","神圣辉煌的佛庙","y",new Date());
        bannerService.updateBanner(banner.getId(),banner.getStatus());
    }
    @Test

    public void tests() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com.baizhi.mapper/applicationContext.xml");
        AblumService ablumService = (AblumService)applicationContext.getBean("ablumServiceImpl");
        List<Ablum> ablums = ablumService.showAll();
        for (Ablum a:ablums){
            System.out.println(a);
        }
    }

    @Test

    public void test00() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com.baizhi.mapper/applicationContext.xml");
        AblumService ablumService = (AblumService)applicationContext.getBean("ablumServiceImpl");
        List<Ablum> ablums = ablumService.showAll();

        Ablum ablum = ablumService.showOne("bd5c9bbc64e111e8a60ab46d83db7585");
        System.out.println(ablum);
    }

    @Test

    public void test0p() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com.baizhi.mapper/applicationContext.xml");
        AblumService ablumService = (AblumService)applicationContext.getBean("ablumServiceImpl");
        Ablum ablum = new Ablum(UUIDUtil.createUUID(),"佛光普照",null,
                5,89.0,"阿里巴拉轰十世",null,null,new Date(),null);
        ablumService.addOne(ablum);
    }
    @Test

    public void test0pdd() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com.baizhi.mapper/applicationContext.xml");
        AblumService ablumService = (AblumService)applicationContext.getBean("ablumServiceImpl");
        Chapter chapter = new Chapter(UUIDUtil.createUUID(),"佛光普照1","44M",null,
                15,null,new Date(),"0d6ea47dac4e4a99b67bea88b4a71f71");

        ablumService.addChapter(chapter);
    }
    @Test

    public void test0psdsfs() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com.baizhi.mapper/applicationContext.xml");
        UserService userService = (UserService)applicationContext.getBean("userServiceImpl");


        List<ProvinceMap> list = userService.queryProvince("男");
        for(ProvinceMap p:list){
            System.out.println(p);
        }

    }

}