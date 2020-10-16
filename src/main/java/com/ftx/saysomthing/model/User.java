package com.ftx.saysomthing.model;

/**
 * @author FanJiangFeng
 * @version 1.0.0
 * @ClassName User.java
 * @Description TODO
 * @createTime 2020年09月15日 15:46:00
 */
public class User {
    private String id;
    private String account;
    private String username;
    private String toppicurl;
    private String status="离线";
    private Integer wd=0;

    public Integer getWd() {
        return wd;
    }

    public void setWd(Integer wd) {
        this.wd = wd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToppicurl() {
        return toppicurl;
    }

    public void setToppicurl(String toppicurl) {
        this.toppicurl = toppicurl;
    }
}
