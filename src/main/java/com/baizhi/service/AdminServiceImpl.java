package com.baizhi.service;

import com.baizhi.dao.AdminDAO;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminDAO adminDAO;
    public AdminDAO getAdminDAO() {
        return adminDAO;
    }
    public void setAdminDAO(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }
    //管理员登录
    @Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
    public boolean login(String name,String password) {
        Admin admin =   adminDAO.queryOne(name);
        if(admin!=null && password.equals(admin.getPassword())){
            return true;
        }else{
            return false;
        }
    }
}