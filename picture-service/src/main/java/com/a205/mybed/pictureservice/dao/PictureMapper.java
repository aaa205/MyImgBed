package com.a205.mybed.pictureservice.dao;

import com.a205.mybed.pictureservice.pojo.Picture;
import com.a205.mybed.pictureservice.pojo.PictureExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PictureMapper {
    long countByExample(PictureExample example);

    int deleteByExample(PictureExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Picture record);

    int insertSelective(Picture record);

    List<Picture> selectByExample(PictureExample example);

    Picture selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Picture record, @Param("example") PictureExample example);

    int updateByExample(@Param("record") Picture record, @Param("example") PictureExample example);

    int updateByPrimaryKeySelective(Picture record);

    int updateByPrimaryKey(Picture record);

    int likeIncrUpdateByPrimaryKey(@Param("id") Integer id);

    List<Picture> selectByAlbumID(@Param("aid") Integer albumID);

    List<Picture> selectByPidList(@Param("list") List<Integer> idList);
}