package com.ftx.saysomthing.config;

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
}
