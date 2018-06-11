package com.baizhi.dao;


import com.baizhi.entity.Admin;

public interface AdminDAO {
    //登录
    public Admin queryOne(String name);
}
