package com.a205.mybed.pictureservice.dao;

import com.a205.mybed.pictureservice.pojo.UserPictureAlbum;
import com.a205.mybed.pictureservice.pojo.UserPictureAlbumExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserPictureAlbumMapper {
    long countByExample(UserPictureAlbumExample example);

    int deleteByExample(UserPictureAlbumExample example);

    int insert(UserPictureAlbum record);

    int insertSelective(UserPictureAlbum record);

    List<UserPictureAlbum> selectByExample(UserPictureAlbumExample example);

    int updateByExampleSelective(@Param("record") UserPictureAlbum record, @Param("example") UserPictureAlbumExample example);

    int updateByExample(@Param("record") UserPictureAlbum record, @Param("example") UserPictureAlbumExample example);
}