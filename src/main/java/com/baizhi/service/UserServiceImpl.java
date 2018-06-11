package com.baizhi.service;

import com.baizhi.annoation.LogAnnoaction;
import com.baizhi.dao.UserDAO;
import com.baizhi.entity.ProvinceMap;
import com.baizhi.entity.User;
import com.baizhi.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements  UserService {
   @Autowired
    private UserDAO userDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> showAll() {
        return  userDAO.queryAll();
    }

    @Override
    @LogAnnoaction(name = "修改一个用户的状态")
    public void updateStatus(String id, String status) {
        userDAO.updateOne(id,status);
    }
    //查询用户登录天数
    @Override
    public List<Integer> queryDay() {
        List<Integer> data = new ArrayList<Integer>();
        List<Integer> list = userDAO.queryDay();
        for(int i=0;i<6;i++){
            data.add(0);
        }
        Integer i1 = data.get(0);
        Integer i2 = data.get(1);
        Integer i3 = data.get(2);
        Integer i4 = data.get(3);
        Integer i5 = data.get(2);
        Integer i6 = data.get(3);
        for(Integer i:list){
            if(i<=7){
                i1 = i1+1;
            }
            if(i<=15){
                i2 = i2+1;
            }
            if(i<=30){
                i3 = i3+1;
            }
            if(i<=90){
                i4 = i4+1;
            }
            if(i<=184){
                i5 = i5+1;
            }
            if(i<=365){
                i6 = i6+1;
            }
        }
        data.set(0,i1);data.set(1,i2);data.set(2,i3);data.set(3,i4);data.set(4,i5);data.set(5,i6);
        return  data;
    }

    @Override
    public List<ProvinceMap> queryProvince(String sex) {
        return userDAO.queryProvince(sex);
    }

    @Override
    @LogAnnoaction(name = "导入了用户的数据")
    public void addAll(List<User> users) {
        for (User user : users) {
            userDAO.addOne(user);
        }
    }

    @Override
    public User showOne(String id) {
        return userDAO.queryOne(id);
    }
    @Override
    public User showOneByName(String phone, String password) {
        User user = userDAO.queryOneByName(phone);
        if(user!=null&&user.getPassword().equals(password)){
            return  user;
        }else{
            return null;
        }
    }

    @Override
    public User register(String phone, String password) {
        User user = userDAO.queryOneByName(phone);
        if(user!=null){
            return null;
        }else {
            User user1 = new User();
            user1.setId(UUIDUtil.createUUID());
            user1.setDate(new Date());
            user1.setStatus("0");
            user1.setPhoneNum(phone);
            user1.setPassword(password);
            userDAO.addOne(user1);
            return user1;
        }
    }
    //随机显示五个成员
    @Override
    public List<User> showByRamdom(String uid) {
        User user = userDAO.queryOne(uid);
        List<User> users = userDAO.queryAll();
        List<User> members = new ArrayList<User>();
        for(int j=0;j<5;j++){
            int i = (int)(0+Math.random()*(users.size()-1));
            if(!users.get(i).equals(user)){
                users.remove(i);
                members.add(users.get(i));
            }
        }
        return members;
    }

    //根据用户id更新用户
    @Override
    public User updateById(User user) {
        User user1 = null;
        if(userDAO.queryOneByName(user.getPhoneNum())!=null){
         user1 =null;
        }else{
            userDAO.updateById(user);
            user1 = userDAO.queryOne(user.getId());
        }
        return user1;
    }
}

