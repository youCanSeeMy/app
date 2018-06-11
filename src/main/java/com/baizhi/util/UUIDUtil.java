package com.baizhi.util;

import java.util.UUID;

public class UUIDUtil {
    public static String createUUID(){
        String uuid = UUID.randomUUID().toString();
        String[] strings = uuid.split("-");
        String sum="";
        for(String s:strings){
            sum = sum+s;
        }
        return  sum;
    }
}
