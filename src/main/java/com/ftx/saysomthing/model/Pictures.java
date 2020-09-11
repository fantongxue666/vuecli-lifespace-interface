package com.ftx.saysomthing.model;

/**
 * @author FanJiangFeng
 * @version 1.0.0
 * @ClassName Pictures.java
 * @Description TODO
 * @createTime 2020年09月11日 17:14:00
 */
public class Pictures {
    private String id;
    private String contentid;
    private String picurl;
    private String picname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContentid() {
        return contentid;
    }

    public void setContentid(String contentid) {
        this.contentid = contentid;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getPicname() {
        return picname;
    }

    public void setPicname(String picname) {
        this.picname = picname;
    }
}
