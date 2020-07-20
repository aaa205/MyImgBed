package com.a205.mybed.pictureservice.dao;

import com.a205.mybed.pictureservice.pojo.AlbumPicture;
import com.a205.mybed.pictureservice.pojo.AlbumPictureExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AlbumPictureMapper {
    long countByExample(AlbumPictureExample example);

    int deleteByExample(AlbumPictureExample example);

    int deleteByPrimaryKey(@Param("albumId") Integer albumId, @Param("pictureId") Integer pictureId);

    int insert(AlbumPicture record);

    int insertSelective(AlbumPicture record);

    List<AlbumPicture> selectByExample(AlbumPictureExample example);

    int updateByExampleSelective(@Param("record") AlbumPicture record, @Param("example") AlbumPictureExample example);

    int updateByExample(@Param("record") AlbumPicture record, @Param("example") AlbumPictureExample example);
}