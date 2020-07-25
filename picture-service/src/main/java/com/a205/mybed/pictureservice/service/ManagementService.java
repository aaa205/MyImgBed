package com.a205.mybed.pictureservice.service;

import com.a205.mybed.pictureservice.pojo.Album;
import com.a205.mybed.pictureservice.pojo.PictureDTO;

import java.net.URISyntaxException;
import java.util.List;

public interface ManagementService {
    /**
     * 创建新相册
     *
     * @param userID       需要创建相册的用户ID
     * @param newAlbumName 新相册名称
     * @return 新创建的相册信息
     */
    Album createAlbum(int userID, String newAlbumName);

    /**
     * 赞某图片，可以重复点赞
     *
     * @param picID
     * @return 点赞后的赞数
     */
    int likePic(int picID);

    /**
     * 获取某用户所有相册
     * @param uid
     * @return
     */
    List<Album> getAlbumsByUserID(int uid);

    /**
     * 获取某用户所有图片
     * @param uid
     * @return
     */
    List<PictureDTO> getPicByUserID(int uid) throws URISyntaxException;

    /**
     * 获取某相册所有图片
     * @param aid 相册id
     * @return
     */
    List<PictureDTO> getPicByAlbumID(int aid) throws URISyntaxException;

}
