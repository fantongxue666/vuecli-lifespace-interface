package com.ftx.saysomthing.controller;

import com.ftx.saysomthing.dao.MainMapper;
import com.ftx.saysomthing.model.Content;
import com.ftx.saysomthing.model.Pictures;
import com.ftx.saysomthing.utils.UUIDutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author FanJiangFeng
 * @version 1.0.0
 * @ClassName MainController.java
 * @Description TODO
 * @createTime 2020年09月11日 10:25:00
 */
@RestController
@RequestMapping("/lifespace")
public class MainController {
    @Autowired
    MainMapper mainMapper;

    /**
     * 上传图片
     * @param file
     * @return  返回图片存储路径
     * @throws IOException
     */
    @RequestMapping("/uploadImage")
    public Map uploadImage(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        // 文件后缀
        String suffix = filename.substring(filename.lastIndexOf("."));
        //生成uuid
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String code = uuid + suffix;
        //存储文件的目录
        String filePath="D:\\suibian\\lifespace-pictures\\";
        //文件路径
        String path=filePath+code;
        File newFile=new File(path);
        file.transferTo(newFile);
        Map map=new HashMap();
        map.put("name",code);
        map.put("url",path);
        return map;
    }

    /**
     * 存储发表内容
     */
    @PostMapping("/saveContent")
    public Integer saveContent(@RequestBody Map map){
        System.out.println("模拟数据库存储发表内容："+map.toString());
        String content=(String)map.get("content");
        Content obj=new Content();
        obj.setId(UUIDutil.getUUID());
        obj.setAuthor("暂无");
        obj.setTimes(new Date());
        obj.setContent(content);
        int i = mainMapper.insertContent(obj);
        List<Map> list=(List<Map>) map.get("picList");
        int j=0;
        for(Map map1:list){
            Pictures pictures=new Pictures();
            String name=(String)map1.get("name");
            String url=(String)map1.get("url");
            pictures.setId(UUIDutil.getUUID());
            pictures.setContentid(obj.getId());
            pictures.setPicname(name);
            pictures.setPicurl(url);
             j = mainMapper.insertPictures(pictures);
        }
        if(i>0&&j>0){
            return 1;
        }else{
            return 0;
        }
    }

}
