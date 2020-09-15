package com.ftx.saysomthing.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author FanJiangFeng
 * @version 1.0.0
 * @ClassName ContentVo.java
 * @Description TODO
 * @createTime 2020年09月13日 18:00:00
 */
public class ContentVo {
    private String id;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date times;
    private Integer zan;
    private String username;
    private String toppicurl;
    private List<Map> pictures;

    public List<Map> getPictures() {
        return pictures;
    }

    public void setPictures(List<Map> pictures) {
        this.pictures = pictures;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimes() {
        return times;
    }

    public void setTimes(Date times) {
        this.times = times;
    }

    public Integer getZan() {
        return zan;
    }

    public void setZan(Integer zan) {
        this.zan = zan;
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
