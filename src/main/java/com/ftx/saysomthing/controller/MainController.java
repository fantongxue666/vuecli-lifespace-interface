package com.ftx.saysomthing.controller;

import com.ftx.saysomthing.config.JwtConfig;
import com.ftx.saysomthing.dao.MainMapper;
import com.ftx.saysomthing.model.*;
import com.ftx.saysomthing.utils.UUIDutil;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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
    @Autowired
    FastFileStorageClient fastFileStorageClient;
    @Resource
    private JwtConfig jwtConfig ;

    /**
     * 注册
     */
    @PostMapping("/register")
    public int register(@RequestBody Map map){
    map.put("id",UUIDutil.getUUID());
        int i = mainMapper.registerToUserList(map);
        return i;
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public Map login (@RequestBody Map maps){

        Map map=new HashMap();
        Map map1=new HashMap();
        map1.put("account",(String)maps.get("userName"));
        map1.put("pwd",(String)maps.get("passWord"));
        User user = mainMapper.getUserIdByAccountAndPwd(map1);
        if(user!=null){
            String token = jwtConfig.createToken(user.getId()) ;
            if (!StringUtils.isEmpty(token)) {
                map.put("token",token);
                map.put("username",user.getUsername());
                map.put("touxiang",user.getToppicurl());
                map.put("account",user.getAccount());
                map.put("code","200");
                map.put("message","请求成功");
            }
            return map;
        }else{
            map.put("code","500");
            map.put("message","账号或密码错误");
            return map;
        }


    }

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
        //四个参数（输入流，文件大小，后缀名，null）,返回一个路径
        StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(),file.getSize(), suffix, null);
        //在线预览路径
        String previewPath = storePath.getFullPath();
        String picName = storePath.getPath();
        Map map=new HashMap();
        map.put("name",picName);
        map.put("url",previewPath);
        return map;
    }

    /**
     * 存储发表内容
     */
    @PostMapping("/saveContent")
    public Integer saveContent(@RequestBody Map map){
        System.out.println("模拟数据库存储发表内容："+map.toString());
        String content=(String)map.get("content");
        String user=(String)map.get("user");
        Content obj=new Content();
        obj.setId(UUIDutil.getUUID());
        obj.setAuthor(user);
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

    /**
     * 得到所有朋友圈列表
     */
    @RequestMapping("/getAllContent")
    public List<ContentVo> getAllContent(){
        List<ContentVo> allContent = mainMapper.getAllContent();
        for(ContentVo contentVo:allContent){
            String id = contentVo.getId();
            List<Map> picturesByContentId = mainMapper.getPicturesByContentId(id);
            contentVo.setPictures(picturesByContentId);

            List<Pinglun> pingLunListByContentId = mainMapper.getPingLunListByContentId(id);
            contentVo.setContentList(pingLunListByContentId);
        }
        return allContent;
    }


    /**
     * 点赞
     */
    @PostMapping("dianzan")
    @Transactional
    public int dianzan(@RequestBody Map map){
    map.put("id",UUIDutil.getUUID());
    //根据自己的account（userId）和contentid判断自己是否点过赞
        String zan_id = mainMapper.juigeIsZan(map);
        if(zan_id!=null){
            //已赞

            String contentid=(String)map.get("contentid");
            int i = mainMapper.RemoveZanNum(contentid);
            int i1 = mainMapper.delZanByContentId(zan_id);

            if(i>0&&i1>0){
                return 1;
            }else{
                return 0;
            }
        }else{
            //未赞
            int i = mainMapper.toZan(map);
            String contentid=(String)map.get("contentid");
            int i1 = mainMapper.changeZanNum(contentid);
            if(i>0&&i1>0){
                return 1;
            }else{
                return 0;
            }
        }
    }

    /**
     * 新增评论
     */
    @PostMapping("insertPingLun")
    public int inserPingLun(@RequestBody Map map){
        map.put("pltime",new Date());
        map.put("id",UUIDutil.getUUID());
        int pinglun = mainMapper.pinglun(map);
        return pinglun;
    }


}
