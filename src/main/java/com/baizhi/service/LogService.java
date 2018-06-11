package com.baizhi.service;

import com.baizhi.entity.Ablum;
import com.baizhi.entity.Chapter;
import com.baizhi.entity.Log;
import org.springframework.stereotype.Service;

import java.util.List;


public interface LogService {
    //展示所有日志
    public List<Log> showAll();
}
