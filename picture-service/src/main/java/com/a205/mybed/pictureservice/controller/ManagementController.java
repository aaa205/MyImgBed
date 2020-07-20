package com.a205.mybed.pictureservice.controller;

import com.a205.mybed.pictureservice.pojo.Album;
import com.a205.mybed.pictureservice.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import request.CreateAlbumRequest;
import util.RestAPIResult;

/**
 * 图片管理
 */
@RestController
public class ManagementController {
    @Autowired
    private ManagementService managementService;

    /**
     * 请求为用户创建一个新相册
     * @param request
     * @return
     */
    @PostMapping("createAlbum")
    public RestAPIResult<Album> createAlbum(@RequestBody CreateAlbumRequest request) {
        Album newAlbum = managementService.createAlbum(request.getUserID(),request.getNewAlbumName());
        return new RestAPIResult<Album>().success(newAlbum, "相册创建成功");
    }
}
