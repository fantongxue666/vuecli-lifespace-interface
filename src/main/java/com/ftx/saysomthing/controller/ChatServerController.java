package com.ftx.saysomthing.controller;

import com.alibaba.fastjson.JSONObject;
import com.ftx.saysomthing.dao.MainMapper;
import com.ftx.saysomthing.utils.UUIDutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    @Autowired
    public static MainMapper mainMapper;

    private Session session;
    //发送人account
    private String sendUserAccount;
    private String receiveUserAccount;
    //是否第一次进入
    private boolean isFirst=true;
    //key代表此次客户端的userId，value代表此次连接对象
    public static final HashMap<String, Object> connectMap=new HashMap<String, Object>();
    //key是session的id，value是用户account
    public static final HashMap<String, String> userMap=new HashMap<String, String>();

    //服务端收到客户端的连接请求，连接成功后会执行此方法
    @OnOpen
    public void start(@PathParam(value = "param") String param, Session session) {
        System.out.println("websocket传来的参数（发送人account）："+param);
        this.session=session;
        this.sendUserAccount=param; //接收参数
        connectMap.put(param,this);
        userMap.put(session.getId(),param);//保存在线的所有用户
    }

    //客户端发来的信息，服务端接收
    @OnMessage
    public void chat(String clientMessage,Session session) {
        ChatServerController client=null;
        if(isFirst){
            //第一次进入，收到接收人account
            System.out.println("接收人account已保存:"+clientMessage);
            receiveUserAccount=clientMessage;
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

            //查询所有的聊天记录的总条数，如果>500条，删除所有记录，并开始保存新的记录


            //保存聊天记录
            Map map=new HashMap();
            map.put("id", UUIDutil.getUUID());
            map.put("message",clientMessage);
            map.put("status","1");//未读
            map.put("time",new Date());
            map.put("senduser",sendUserAccount);
            map.put("receiveuser",receiveUserAccount);
            try {
                mainMapper.insertMessages(map);
            } catch (Exception e) {
                System.out.println("### 保存聊天记录异常，服务器错误 ###");
                e.printStackTrace();
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
