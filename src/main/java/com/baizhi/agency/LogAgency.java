package com.baizhi.agency;

import com.baizhi.annoation.LogAnnoaction;
import com.baizhi.dao.LogDAO;
import com.baizhi.entity.Log;
import com.baizhi.util.UUIDUtil;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Date;

public class LogAgency implements MethodInterceptor {
    @Autowired
    private LogDAO logDAO;
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        //什么人
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        requestAttributes.getRequest().getSession().setAttribute("name","root");
        String name = requestAttributes.getRequest().getSession().getAttribute("name").toString();
        //什么时间
        Date date = new Date();
        //什么事
        Method method = methodInvocation.getMethod();
        String thing = method.getAnnotation(LogAnnoaction.class).name();
        //是否成功
        boolean flag = false;
        Object proceed = null;
        try {
            proceed = methodInvocation.proceed();
            flag = true;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        Log log = new Log(UUIDUtil.createUUID(),name,date,thing,flag+"");
        logDAO.insertOne(log);
        return proceed;
    }
}
