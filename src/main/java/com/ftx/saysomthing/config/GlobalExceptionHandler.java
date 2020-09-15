package com.ftx.saysomthing.config;

/**
 * @author FanJiangFeng
 * @version 1.0.0
 * @ClassName GlobalExceptionHandler.java
 * @Description TODO
 * @createTime 2020年09月15日 15:37:00
 */

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于捕获全局异常
 */
@ControllerAdvice//控制器切面
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)//捕获运行时异常
    @ResponseBody
    public Map<String,Object> exceptionHandler(){//处理异常方法
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("errorCode","101");
        map.put("errorMsg","系统错误！");
        return map;
    }
}
