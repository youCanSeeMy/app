package com.baizhi.service;

import com.baizhi.entity.Admin;

public interface AdminService {
    //管理员登录
    public boolean login(String name,String password);
}
