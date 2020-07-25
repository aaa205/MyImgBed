package com.a205.mybed.pictureservice.controller;

import com.a205.mybed.pictureservice.exception.ResourceNotFoundException;
import com.a205.mybed.pictureservice.pojo.Album;
import com.a205.mybed.pictureservice.pojo.PictureDTO;
import com.a205.mybed.pictureservice.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import request.CreateAlbumRequest;
import util.RestAPIResult;

import java.net.URISyntaxException;
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
     * @param uid 用户id
     * @return 某用户拥有的相册的信息
     */
    @GetMapping("u/{uid}/albums")
    public RestAPIResult<List<Album>> getAlbums(@PathVariable("uid") int uid) {
        List<Album> albums = managementService.getAlbumsByUserID(uid);
        return new RestAPIResult<List<Album>>().success(albums, "获取成功");
    }

    /**
     * 请求某个相册里的图片
     *
     * @param uid
     * @param aid
     * @return
     * @throws URISyntaxException
     */
    @GetMapping("/u/{uid}/album/{albumId}")
    public RestAPIResult<List<PictureDTO>> getPicByAlbumID(@PathVariable("uid") int uid, @PathVariable("albumId") int aid) throws URISyntaxException {
        return new RestAPIResult<List<PictureDTO>>().success(managementService.getPicByAlbumID(aid), "获取成功");
    }

    /**
     * 请求某用户所有图片信息
     *
     * @param uid
     * @return
     */
    @GetMapping("/u/{uid}/pictures")
    public RestAPIResult<List<PictureDTO>> getAllPicOfUser(@PathVariable("uid") int uid) throws URISyntaxException {
        return new RestAPIResult<List<PictureDTO>>().success(managementService.getPicByUserID(uid), "获取成功");
    }

    /**
     * 点赞某图片
     *
     * @param picID
     * @return
     */
    @PatchMapping("addLike")
    public RestAPIResult<Object> likePic(int picID) {
        int affected = managementService.likePic(picID);
        RestAPIResult<Object> res = new RestAPIResult<>();
        if (affected > 0)
            return res.success(null, "点赞成功");
        else
            return res.error(1, "该图片不存在");
    }

    /**
     * 获取某图片详情
     * @param pid 图片ID
     * @return
     * @throws URISyntaxException
     */

    @GetMapping("p/{pid}")
    public RestAPIResult<PictureDTO> getPicByPicID(@PathVariable("pid") int pid) throws URISyntaxException, ResourceNotFoundException {
        return new RestAPIResult<PictureDTO>().success(managementService.getPicByPicID(pid), "查找成功");
    }


}
