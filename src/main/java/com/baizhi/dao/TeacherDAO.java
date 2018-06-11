package com.baizhi.dao;

import com.baizhi.entity.Teacher;

public interface TeacherDAO {
    //根据上师名字查询上师
   public Teacher selectByName(String name);

}