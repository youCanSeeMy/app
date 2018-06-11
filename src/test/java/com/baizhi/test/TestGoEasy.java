package com.baizhi.test;

import com.baizhi.service.UserService;
import io.goeasy.GoEasy;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestGoEasy {
    @Autowired
    UserService userService;
    @Test
    public void testUser(){

        //1.服务器地址 appKey:commonKey
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io","BC-c5e7cb4343594fb0a51c3b7e9142b31c");
        //1.管道标识  2.消息内容
        goEasy.publish("my_channel",userService.queryDay().toString());
    }
}
