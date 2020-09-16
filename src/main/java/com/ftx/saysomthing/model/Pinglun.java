package com.ftx.saysomthing.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * @author FanJiangFeng
 * @version 1.0.0
 * @ClassName Pinglun.java
 * @Description TODO
 * @createTime 2020年09月16日 14:23:00
 */
public class Pinglun {
    private String id;
    private String user;
    private String contentid;
    private String pinglun;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date pltime;
    private String userpicurl;
    private String account;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContentid() {
        return contentid;
    }

    public void setContentid(String contentid) {
        this.contentid = contentid;
    }

    public String getPinglun() {
        return pinglun;
    }

    public void setPinglun(String pinglun) {
        this.pinglun = pinglun;
    }

    public Date getPltime() {
        return pltime;
    }

    public void setPltime(Date pltime) {
        this.pltime = pltime;
    }

    public String getUserpicurl() {
        return userpicurl;
    }

    public void setUserpicurl(String userpicurl) {
        this.userpicurl = userpicurl;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
