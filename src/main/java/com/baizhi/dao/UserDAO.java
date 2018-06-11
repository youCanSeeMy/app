package com.baizhi.dao;


import com.baizhi.entity.ProvinceMap;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDAO {
    //查询所有用户
    public List<User> queryAll();
    //修改用户的状态
    public void updateOne(@Param(value = "id") String id,@Param(value = "status") String status);
    //查询用户注册的天数
    public List<Integer> queryDay();
    //按省份查询用户个数(分男女)
    public List<ProvinceMap> queryProvince(String sex);
    //添加用户
    public void addOne(User user);
    //根据id查询用户
    public User queryOne(String id);
    //根据phone查询用户
    public User queryOneByName(String phoneNum);
    //根据用户id更新用户
    public void updateById(User user);


}
