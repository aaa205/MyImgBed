package com.a205.mybed.pictureservice.service.impl;

import com.a205.mybed.pictureservice.dao.AlbumMapper;
import com.a205.mybed.pictureservice.dao.AlbumPictureMapper;
import com.a205.mybed.pictureservice.dao.PictureMapper;
import com.a205.mybed.pictureservice.dao.UserAlbumMapper;
import com.a205.mybed.pictureservice.pojo.*;
import com.a205.mybed.pictureservice.service.ManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class ManagementServiceImpl implements ManagementService {
    Logger logger = LoggerFactory.getLogger(ManagementService.class);
    @Autowired
    AlbumMapper albumMapper;
    @Autowired
    UserAlbumMapper userAlbumMapper;
    @Autowired
    AlbumPictureMapper albumPictureMapper;
    @Autowired
    PictureMapper pictureMapper;


    @Override
    @Transactional
    public Album createAlbum(int userID, String newAlbumName) {
        // 创建相册
        Album album = new Album();
        album.setName(newAlbumName);
        album.setCreateTime(new Date());
        albumMapper.insertSelective(album);


        // 插入关系表
        UserAlbum userAlbum = new UserAlbum();
        userAlbum.setAlbumId(album.getId());
        userAlbum.setUserId(userID);
        userAlbumMapper.insert(userAlbum);
        logger.info("用户(" + userID + ") ->新建相册 " + album.getName());
        return album;
    }

    @Override
    public List<Album> getAlbums(int userID) {
        // 获取 用户-相册 关系表
        UserAlbumExample example = new UserAlbumExample();
        example.createCriteria().andUserIdEqualTo(userID);
        List<UserAlbum> userAlbums = userAlbumMapper.selectByExample(example);
        // 获取相册ID列表
        List<Integer> albumIDs = new LinkedList<>();
        for (UserAlbum userAlbum : userAlbums) {
            albumIDs.add(userAlbum.getAlbumId());
        }

        // 获取相册列表
        AlbumExample example1 = new AlbumExample();
        example1.createCriteria().andIdIn(albumIDs);
        List<Album> albums = albumMapper.selectByExample(example1);
        return albums;
    }

    @Override
    public List<Picture> getPicOfAlbum(int userID, String albumName) {
        // 获取相册列表
        List<Album> albums = getAlbums(userID);
        // 返回图片列表
        List<Picture> pictures = new ArrayList<>();
        for (Album album : albums) {
            if (albumName.equals(album.getName())) {
                // 获取该相册ID
                int albumID = album.getId();
                // 获取 相册-图片 关系表
                AlbumPictureExample example = new AlbumPictureExample();
                example.createCriteria().andAlbumIdEqualTo(albumID);
                List<AlbumPicture> albumPictures = albumPictureMapper.selectByExample(example);
                // 获取图片ID列表
                List<Integer> pictureIDs = new ArrayList<>();
                for (AlbumPicture albumPicture : albumPictures) {
                    pictureIDs.add(albumPicture.getPictureId());
                }
                // 获取图片列表
                PictureExample example1 = new PictureExample();
                example1.createCriteria().andIdIn(pictureIDs);
                pictures = pictureMapper.selectByExample(example1);
            }
        }
        return pictures;
    }

    @Override
    public List<Picture> getAllPicOfUser(int userID) {
        // 获取相册列表
        List<Album> albums = getAlbums(userID);
        // 获取相册ID列表
        List<Integer> albumIDs = new ArrayList<>();
        for (Album album : albums) {
            albumIDs.add(album.getId());
        }
        // 获取 相册-图片 关系表
        AlbumPictureExample example = new AlbumPictureExample();
        example.createCriteria().andAlbumIdIn(albumIDs);
        List<AlbumPicture> albumPictures = albumPictureMapper.selectByExample(example);
        // 获取图片ID列表
        List<Integer> pictureIDs = new ArrayList<>();
        for (AlbumPicture albumPicture : albumPictures) {
            pictureIDs.add(albumPicture.getPictureId());
        }
        // 获取图片列表
        PictureExample example1 = new PictureExample();
        example1.createCriteria().andIdIn(pictureIDs);
        List<Picture> pictures = pictureMapper.selectByExample(example1);
        return pictures;
    }
}
