package com.ftx.saysomthing.model;

import java.util.Date;

/**
 * @author FanJiangFeng
 * @version 1.0.0
 * @ClassName Content.java
 * @Description TODO
 * @createTime 2020年09月11日 17:04:00
 */
public class Content {
    private String id;
    private String author;
    private String content;
    private Date times;
    private Integer zan;

    public String getAuthor() {
        return author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
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
}
