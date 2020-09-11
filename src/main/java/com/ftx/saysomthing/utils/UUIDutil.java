package com.ftx.saysomthing.utils;

import java.util.UUID;

/**
 * @author FanJiangFeng
 * @version 1.0.0
 * @ClassName UUIDutil.java
 * @Description TODO
 * @createTime 2020年09月11日 17:24:00
 */
public class UUIDutil {

    //得到uuid
    public static String getUUID(){
        String str= UUID.randomUUID().toString().replaceAll("-", "");
        return str;
    }
}
