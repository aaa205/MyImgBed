package com.a205.mybed.pictureservice.controller;

import com.a205.mybed.pictureservice.pojo.Album;
import com.a205.mybed.pictureservice.pojo.Picture;
import com.a205.mybed.pictureservice.pojo.PictureDTO;
import com.a205.mybed.pictureservice.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import request.CreateAlbumRequest;
import util.RestAPIResult;

import java.util.List;

/**
 * 图片管理
 */
@RestController
public class ManagementController {
    @Autowired
    private ManagementService managementService;

    /**
     * 请求为用户创建一个新相册
     *
     * @param request
     * @return 新建的相册信息
     */
    @PostMapping("createAlbum")
    public RestAPIResult<Album> createAlbum(@RequestBody CreateAlbumRequest request) {
        Album newAlbum = managementService.createAlbum(request.getUserID(), request.getNewAlbumName());
        return new RestAPIResult<Album>().success(newAlbum, "相册创建成功");
    }

    /**
     * 请求某用户的相册列表
     *
     * @param userName 用户名称
     * @return 某用户拥有的相册的信息
     */
    @GetMapping("getAlbums")
    public RestAPIResult<List<Album>> getAlbums(String userName) {
        //todo
        return null;
    }

    /**
     * 请求用户某个相册里的图片列表
     *
     * @param userName  用户名称
     * @param albumName 相册名称
     * @return 请求的图片信息
     */
    @GetMapping("{userName}/{albumName}")
    public RestAPIResult<List<PictureDTO>> getPicOfAlbum(@PathVariable("userName") String userName,
                                                         @PathVariable("albumName") String albumName) {
        // todo
        Picture p = new Picture();
        return null;
    }

    /**
     * 请求某用户所有图片信息
     *
     * @param userName 用户名称
     * @return 请求的图片信息
     */
    @GetMapping("{userName}/pictures")
    public RestAPIResult<List<PictureDTO>> getAllPicOfUser(@PathVariable("userName") String userName) {
        // todo
        return null;
    }

    /**
     * 点赞某图片
     *
     * @param picID
     * @return
     */
    @PatchMapping("addLike")
    public RestAPIResult<Object> likePic(int picID) {
        int affected=managementService.likePic(picID);
        RestAPIResult<Object> res = new RestAPIResult<>();
        if (affected>0)
            return res.success(null, "点赞成功");
        else
            return res.error(1,"该图片不存在");
    }


}
