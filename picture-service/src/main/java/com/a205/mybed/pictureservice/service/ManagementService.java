package com.a205.mybed.pictureservice.service;

import com.a205.mybed.pictureservice.pojo.Album;

public interface ManagementService {
    /**
     * 创建新相册
     * @param userID 需要创建相册的用户ID
     * @param newAlbumName 新相册名称
     * @return 新创建的相册信息
     */
    Album createAlbum(int userID, String newAlbumName);

    /**
     * 赞某图片，可以重复点赞
     * @param picID
     * @return 点赞后的赞数
     */
    int likePic(int picID);

}
