package com.baizhi.controller;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.entity.Ablum;
import com.baizhi.entity.Article;
import com.baizhi.entity.User;
import com.baizhi.service.AblumService;
import com.baizhi.service.ArticleService;
import com.baizhi.service.BannerService;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class frontController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @Autowired
    private AblumService ablumService;
    @Autowired
    private BannerService bannerService;

    //第一个接口
    @RequestMapping(value="/first_page")
    @ResponseBody
    public JSONObject firstPage(String uid,String type,String sub_type){
        JSONObject jsonObject = new JSONObject();
        User user = userService.showOne(uid);
        String name = user.getDharmaName();
        if(user!=null){
            if(type.equals("all")){
                jsonObject.put("artical",articleService.queryAll());
                jsonObject.put("header",bannerService.showAll());
                jsonObject.put("album",ablumService.showAll());
            }
            if(type.equals("wen")){
                jsonObject.put("album",ablumService.showAll());
            }
            if(type.equals("si")){
                if(sub_type==null||sub_type.equals("ssyj")){
                    jsonObject.put("artical",articleService.showArticleByTeach(name).get("isTeacher"));
                }else{
                    jsonObject.put("artical",articleService.showArticleByTeach(name).get("isNotTeacher"));
                }
            }
        }
        return jsonObject;
    }
    //第二个接口，思的详情页（文章）
    @RequestMapping(value="/detail",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public Article queryArticleDetail(String id,String uidc){
        User user = userService.showOne(uidc);
        if(user!=null&&id!=null){
            return articleService.showOne(id);
        }else {
            return null;
        }
    }
    //第三个接口，闻的详情页（专辑）
    @ResponseBody
    @RequestMapping(value="/detail/wen",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Ablum queryAblumDetail(String id,String uid){
        User user = userService.showOne(uid);
        if(user!=null&&id!=null){
            return ablumService.showOne1(id);
        }else {
            return null;
        }
    }
    //第四个接口，登录接口
    @RequestMapping(value="/count/login",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public Map<String,Object> login(String phone,String password){
        Map<String,Object> map = new HashMap<String, Object>();
        if(phone!=null&&password!=null){
            User user = userService.showOneByName(phone,password);
            if(user!=null){
                map.put("id",user.getId());
                map.put("name",user.getUsername());
                map.put("password",user.getPassword());
                map.put("dharmaName",user.getDharmaName());
                map.put("sex",user.getSex());
                map.put("headpic",user.getHeadPic());
                map.put("status",user.getStatus());
                map.put("date",user.getDate());
                map.put("province",user.getProvince());
                map.put("city",user.getCity());
            }else {
                map.put("error","-200");
                map.put("errmsg","密码错误");
            }
        }else{
            map.put("error","-200");
            map.put("errmsg","密码错误");
        }
        return map;
    }
    //第五个接口，注册接口
    @RequestMapping(value="/account/regist",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public Map<String,Object> register(String phone,String password) {
        User user = userService.register(phone, password);
        Map<String, Object> map = new HashMap<String, Object>();
        if (user!=null) {
            map.put("uid",user.getId());
            map.put("password", password);
            map.put("phone", phone);
        }else{
            map.put("error","-200");
            map.put("errmsg","该手机号已经存在");
        }
        return map;
    }

    //第六个接口，更新用户
    @RequestMapping(value="/account/modify",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public Object modify(User user){
        User user1 = userService.updateById(user);
        Map map = new HashMap();
        if (user1==null){
            map.put("error","-200");
            map.put("error_msg","该手机号已经存在");
            return map;
        }else{
            return user1;
        }
    }

    //第七个接口，获取短信验证码
    @RequestMapping(value="/identify/obtain",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public  String obtain(String phone){
        String s="";
        for(int j=0;j<4;j++){
            int i = (int)(0+Math.random()*9);
            s=s+i;
        }
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        requestAttributes.getRequest().getSession().setAttribute("code",s);
        return s;
    }
    //第八个接口，校验短信验证码
    @RequestMapping(value="/identify/check",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public Map<String,String> check(String phone,String code){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String code1 = (String) requestAttributes.getRequest().getSession().getAttribute("code");
        Map<String,String> map = new HashMap<String, String>();
        if(code.equals(code1)){
            map.put("result","success");
        }else {
            map.put("result","fail");
        }
        return map;
    }
    //第九个接口，获取会员列表
    @RequestMapping(value="/member",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseBody
    public List<User> members(String uid){
        return userService.showByRamdom(uid);
    }
}
