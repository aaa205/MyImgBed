package com.a205.mybed.pictureservice.service;

import com.a205.mybed.pictureservice.pojo.Album;
import com.a205.mybed.pictureservice.pojo.PictureDTO;

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
     * 请求某用户的相册列表
     *
     * @param userName 用户名
     * @return 某用户拥有的相册的信息
     */
    List<Album> getAlbums(String userName);

    /**
     * 请求用户某个相册里的图片列表
     *
     * @param userName    用户名
     * @param albumName 相册名称
     * @return
     */
    List<PictureDTO> getPicOfAlbum(String userName, String albumName);

    /**
     * 请求某用户所有图片信息
     *
     * @param userName 用户名
     * @return
     */
    List<PictureDTO> getAllPicOfUser(String userName);
}
