package com.a205.mybed.pictureservice.dao;

import com.a205.mybed.pictureservice.pojo.UserAlbum;
import com.a205.mybed.pictureservice.pojo.UserAlbumExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserAlbumMapper {
    long countByExample(UserAlbumExample example);

    int deleteByExample(UserAlbumExample example);

    int deleteByPrimaryKey(@Param("userId") Integer userId, @Param("albumId") Integer albumId);

    int insert(UserAlbum record);

    int insertSelective(UserAlbum record);

    List<UserAlbum> selectByExample(UserAlbumExample example);

    int updateByExampleSelective(@Param("record") UserAlbum record, @Param("example") UserAlbumExample example);

    int updateByExample(@Param("record") UserAlbum record, @Param("example") UserAlbumExample example);
}