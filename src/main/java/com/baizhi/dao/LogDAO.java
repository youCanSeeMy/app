package com.baizhi.dao;


import com.baizhi.entity.Log;

import java.util.List;

public interface LogDAO {
    //插入
    public  void insertOne(Log log);
    //展示所有
    public List<Log> queryAll();
}
