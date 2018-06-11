package com.baizhi.service;

import com.baizhi.dao.LogDAO;
import com.baizhi.entity.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements  LogService {
    @Autowired
    private LogDAO logDAO;

    @Override
    public List<Log> showAll() {
        return logDAO.queryAll();
    }
}
