package com.baizhi.annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//代表该自定义注解在运行时使用（时机）
@Retention(RetentionPolicy.RUNTIME)
//代表该自定义注解加在属性上（位置）
@Target(ElementType.FIELD)
//自定义注解
public @interface UserAnnoaction {
    //这是一个参数，String是参数类型
    public String name();
}
