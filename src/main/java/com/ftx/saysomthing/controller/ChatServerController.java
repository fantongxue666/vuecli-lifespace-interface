package com.ftx.saysomthing.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author FanJiangFeng
 * @version 1.0.0
 * @ClassName ChatServerController.java
 * @Description TODO
 * @createTime 2020年10月15日 17:43:00
 */
@ServerEndpoint(value = "/charRoomServer/{param}")
@Component
public class ChatServerController {

    private Session session;
    //发送人account
    private String sendUserAccount;
    private String receiveUserAccount;
    //是否第一次进入
    private boolean isFirst=true;
    //key代表此次客户端的userId，value代表此次连接对象
    private static final HashMap<String, Object> connectMap=new HashMap<String, Object>();
    //保存所有用户昵称信息
    //key是session的id，value是用户昵称
    private static final HashMap<String, String> userMap=new HashMap<String, String>();

    //服务端收到客户端的连接请求，连接成功后会执行此方法
    @OnOpen
    public void start(@PathParam(value = "param") String param, Session session) {
        System.out.println("websocket传来的参数（发送人account）："+param);
        this.session=session;
        this.sendUserAccount=param; //接收参数
        connectMap.put(param,this);
    }

    //客户端发来的信息，服务端接收
    @OnMessage
    public void chat(String clientMessage,Session session) {
        ChatServerController client=null;
        if(isFirst){
            //第一次进入，收到接收人account
            System.out.println("接收人account已保存:"+clientMessage);
            receiveUserAccount=clientMessage;
            //将新进来的用户保存到用户map
            userMap.put(session.getId(), clientMessage);
            isFirst=false;
        }else{
            System.out.println("### 接收人已被初始化，开始通信，发送消息 ###");
            //给接收人窗口发送消息
            client=(ChatServerController)connectMap.get(receiveUserAccount);
            if(client!=null){
                try {
                    client.session.getBasicRemote().sendText(sendUserAccount+":"+clientMessage);
                } catch (IOException e) {
                    System.out.println("### 发送信息异常，发送失败 ###");
                    e.printStackTrace();
                }
            }
            //给发送人窗口发消息
            client=(ChatServerController)connectMap.get(sendUserAccount);
            if(client!=null){
                try {
                    client.session.getBasicRemote().sendText(sendUserAccount+":"+clientMessage);
                } catch (IOException e) {
                    System.out.println("### 发送信息异常，发送失败 ###");
                    e.printStackTrace();
                }
            }

        }

    }

    //前台js的ws.close事件，会触发后台的标注onClose的方法
    @OnClose
    public void close() {
        userMap.remove(session.getId());
        connectMap.remove(sendUserAccount);
    }

    @OnError
    public void onError(Session session,Throwable throwable){
        System.out.println("发生错误！");
        throwable.printStackTrace();
    }
}
