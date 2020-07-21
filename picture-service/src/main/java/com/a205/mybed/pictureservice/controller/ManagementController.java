package com.a205.mybed.pictureservice.controller;

import com.a205.mybed.pictureservice.pojo.Album;
import com.a205.mybed.pictureservice.pojo.Picture;
import com.a205.mybed.pictureservice.pojo.PictureDTO;
import com.a205.mybed.pictureservice.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
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

    @Autowired
    private RestTemplate template;

    // 用户服务url
    private String url = "http://user-service/user/";

    @Bean
    @LoadBalanced
    public RestTemplate getTemplate() {
        return new RestTemplate();
    }


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
        // 获取用户ID
        int userID = template.getForObject(url + userName, Integer.class);
        List<Album> albums = managementService.getAlbums(userID);
        return new RestAPIResult<List<Album>>().success(albums, "所有相册信息返回成功");
    }

    /**
     * 请求用户某个相册里的图片列表(图片URL信息生成方式未定，先不管)
     *
     * @param userName  用户名称
     * @param albumName 相册名称
     * @return 请求的图片信息
     */
    @GetMapping("{userName}/{albumName}")
    public RestAPIResult<List<PictureDTO>> getPicOfAlbum(@PathVariable("userName") String userName,
                                                         @PathVariable("albumName") String albumName) {
        // 获取用户ID
        int userID = template.getForObject(url + userName, Integer.class);
        List<Picture> pictures = managementService.getPicOfAlbum(userID, albumName);
        // 外链url待定
        List<PictureDTO> pictureDTOS = null;
        return new RestAPIResult<List<PictureDTO>>().success(pictureDTOS, albumName + "相册图片返回成功");
    }

    /**
     * 请求某用户所有图片信息(图片URL信息生成方式未定，先不管)
     *
     * @param userName 用户名称
     * @return 请求的图片信息
     */
    @GetMapping("{userName}/pictures")
    public RestAPIResult<List<PictureDTO>> getAllPicOfUser(@PathVariable("userName") String userName) {
        // 获取用户ID
        int userID = template.getForObject(url + userName, Integer.class);
        List<Picture> pictures = managementService.getAllPicOfUser(userID);
        // 外链url待定
        List<PictureDTO> pictureDTOS = null;
        return new RestAPIResult<List<PictureDTO>>().success(pictureDTOS, userName + "用户所有图片返回成功");
    }
}
