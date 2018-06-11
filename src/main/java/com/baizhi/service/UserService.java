package com.baizhi.service;

import com.baizhi.entity.ProvinceMap;
import com.baizhi.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    //显示所有用户
    public List<User> showAll();
    //改变用户状态
    public void updateStatus(String id,String status);
    //查询用户登录天数
    public List<Integer> queryDay();
    //按照省份查询用户
    public List<ProvinceMap> queryProvince(String sex);
    //批量插入
    public void addAll(List<User> users);
    public User showOne(String id);
    //根据姓名密码判断用户
    public User showOneByName(String phone,String password);
    //用户注册
    public User register(String phone,String password);
    //随机显示五个用户
    public List<User> showByRamdom(String uid);
    //根据用户id更新用户
    public User updateById(User user);
}
