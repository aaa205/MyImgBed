package com.a205.mybed.pictureservice.service.impl;

import com.a205.mybed.pictureservice.dao.*;
import com.a205.mybed.pictureservice.pojo.*;
import com.a205.mybed.pictureservice.service.ManagementService;
import com.a205.mybed.pictureservice.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URISyntaxException;
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
    @Autowired
    UserMapper userMapper;

    @Value("${picture-url-prefix}")
    private String urlPrefix;


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
    public List<Album> getAlbums(String userName) {
        // 获取用户ID
        UserExample userExample=new UserExample();
        userExample.createCriteria().andNameNotEqualTo(userName);
        List<User> users= userMapper.selectByExample(userExample);
        int userId =users.get(0).getId();

        // 获取 用户-相册 关系表
        UserAlbumExample userAlbumExample = new UserAlbumExample();
        userAlbumExample.createCriteria().andUserIdEqualTo(userId);
        List<UserAlbum> userAlbums = userAlbumMapper.selectByExample(userAlbumExample);
        // 获取相册ID列表
        List<Integer> albumIDs = new LinkedList<>();
        for (UserAlbum userAlbum : userAlbums) {
            albumIDs.add(userAlbum.getAlbumId());
        }

        // 获取相册列表
        AlbumExample albumExample = new AlbumExample();
        albumExample.createCriteria().andIdIn(albumIDs);
        List<Album> albums = albumMapper.selectByExample(albumExample);
        return albums;
    }

    @Override
    public List<PictureDTO> getPicOfAlbum(String userName, String albumName){
        // 获取相册列表
        List<Album> albums = getAlbums(userName);
        // 获取该相册ID
        int albumID = albums.get(0).getId();

        // 获取 相册-图片 关系表
        AlbumPictureExample albumPictureExample = new AlbumPictureExample();
        albumPictureExample.createCriteria().andAlbumIdEqualTo(albumID);
        List<AlbumPicture> albumPictures = albumPictureMapper.selectByExample(albumPictureExample);

        // 获取图片ID列表
        List<Integer> pictureIDs = new ArrayList<>();
        for (AlbumPicture albumPicture : albumPictures) {
            pictureIDs.add(albumPicture.getPictureId());
        }

        // 获取图片列表
        PictureExample example1 = new PictureExample();
        example1.createCriteria().andIdIn(pictureIDs);
        List<Picture> pictures = pictureMapper.selectByExample(example1);

        // Picture to PictureDTO
        List<PictureDTO> pictureDTOs=new ArrayList<>();
        PictureDTO pictureDTO;
        String url = null;
        for (Picture picture : pictures){
            pictureDTO=new PictureDTO(picture);
            try {
                url=FileUtil.buildPicUrl(picture,urlPrefix);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            pictureDTO.setUrl(url);
            pictureDTOs.add(pictureDTO);
        }
        return pictureDTOs;
    }

    @Override
    public List<PictureDTO> getAllPicOfUser(String userName) {
        // 获取相册列表
        List<Album> albums = getAlbums(userName);
        // 获取相册ID列表
        List<Integer> albumIDs = new ArrayList<>();
        for (Album album : albums) {
            albumIDs.add(album.getId());
        }

        // 获取 相册-图片 关系表
        AlbumPictureExample albumPictureExample = new AlbumPictureExample();
        albumPictureExample.createCriteria().andAlbumIdIn(albumIDs);
        List<AlbumPicture> albumPictures = albumPictureMapper.selectByExample(albumPictureExample);

        // 获取图片ID列表
        List<Integer> pictureIDs = new ArrayList<>();
        for (AlbumPicture albumPicture : albumPictures) {
            pictureIDs.add(albumPicture.getPictureId());
        }

        // 获取图片列表
        PictureExample pictureExample = new PictureExample();
        pictureExample.createCriteria().andIdIn(pictureIDs);
        List<Picture> pictures = pictureMapper.selectByExample(pictureExample);

        // Picture to PictureDTO
        List<PictureDTO> pictureDTOs=new ArrayList<>();
        PictureDTO pictureDTO;
        String url = null;
        for (Picture picture : pictures){
            pictureDTO=new PictureDTO(picture);
            try {
                url=FileUtil.buildPicUrl(picture,urlPrefix);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            pictureDTO.setUrl(url);
            pictureDTOs.add(pictureDTO);
        }
        return pictureDTOs;
    }
}
