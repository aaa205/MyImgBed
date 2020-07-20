package com.a205.mybed.pictureservice.service;

import com.a205.mybed.pictureservice.pojo.PictureDTO;
import org.springframework.web.multipart.MultipartFile;


/**
 * 文件传输服务
 */
public interface TransferService {
    /**
     * 上传图片
     *
     * @param file    待上传的图片
     * @param albumID 要上传到的相册
     * @return 上传成功后的图片信息
     */
    PictureDTO upload(MultipartFile file, int albumID) throws Exception;
}
