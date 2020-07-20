package com.a205.mybed.pictureservice.service.impl;

import com.a205.mybed.pictureservice.dao.AlbumMapper;
import com.a205.mybed.pictureservice.dao.UserAlbumMapper;
import com.a205.mybed.pictureservice.pojo.Album;
import com.a205.mybed.pictureservice.pojo.UserAlbum;
import com.a205.mybed.pictureservice.service.ManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ManagementServiceImpl implements ManagementService {
    Logger logger= LoggerFactory.getLogger(ManagementService.class);
    @Autowired
    AlbumMapper albumMapper;
    @Autowired
    UserAlbumMapper userAlbumMapper;

    @Override
    @Transactional
    public Album createAlbum(int userID, String newAlbumName) {
        // 创建相册
        Album album=new Album();
        album.setId(userID);
        album.setName(newAlbumName);
        album.setCreateTime(new Date());
        albumMapper.insert(album);

        // 插入关系表
        UserAlbum userAlbum=new UserAlbum();
        userAlbum.setAlbumId(album.getId());
        userAlbum.setUserId(userID);
        userAlbumMapper.insert(userAlbum);
        logger.info("用户ID: "+userID+" 新建相册 "+album.getName());
        return album;
    }
}
