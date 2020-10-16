package com.ftx.saysomthing.dao;

import com.ftx.saysomthing.model.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author FanJiangFeng
 * @version 1.0.0
 * @ClassName MainMapper.java
 * @Description TODO
 * @createTime 2020年09月11日 16:43:00
 */
@Mapper
@Component
public interface MainMapper {

    int insertContent(Content content);
    int insertPictures(Pictures pictures);
    List<ContentVo> getAllContent();
    List<Map> getPicturesByContentId(String contentId);
    User getUserIdByAccountAndPwd(Map map);
    int registerToUserList(Map map);
    int toZan(Map map);
    int changeZanNum(String contentid);
    String juigeIsZan(Map map);
    int delZanByContentId(String id);
    int RemoveZanNum(String contentid);
    int pinglun(Map map);
    List<Pinglun> getPingLunListByContentId(String contentid);
    List<User> getAllUsers();
}
