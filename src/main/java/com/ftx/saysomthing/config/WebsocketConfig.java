package com.ftx.saysomthing.config;

import com.ftx.saysomthing.controller.ChatServerController;
import com.ftx.saysomthing.dao.MainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author FanJiangFeng
 * @version 1.0.0
 * @ClassName WebsocketConfig.java
 * @Description TODO
 * @createTime 2020年10月16日 09:37:00
 */
@Configuration
public class WebsocketConfig {
    //websocket配置
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
    /**
     * 因 SpringBoot WebSocket 对每个客户端连接都会创建一个 WebSocketServer（@ServerEndpoint 注解对应的对象，Bean 注入操作会被直接略过，因而手动注入一个全局变量
     */
    @Autowired
    public void setSelectService(MainMapper mainMapper) {
        ChatServerController.mainMapper = mainMapper;
    }
}
