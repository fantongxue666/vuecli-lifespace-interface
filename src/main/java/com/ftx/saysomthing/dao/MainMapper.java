package com.ftx.saysomthing.dao;

import com.ftx.saysomthing.model.Content;
import com.ftx.saysomthing.model.ContentVo;
import com.ftx.saysomthing.model.Pictures;
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
}
