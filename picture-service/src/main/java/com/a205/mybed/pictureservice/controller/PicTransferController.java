package com.a205.mybed.pictureservice.controller;

import com.a205.mybed.pictureservice.pojo.PictureDTO;
import com.a205.mybed.pictureservice.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import request.UploadPicRequest;
import util.RestAPIResult;


/**
 * 图片传输
 */
@RestController
public class PicTransferController {
    @Autowired
    private TransferService transferService;

    /**
     * 上传图片，注意前端用Form-Data
     *
     * @param request
     * @return
     */
    @PostMapping(value = "upload",produces = MediaType.APPLICATION_JSON_VALUE)
    public RestAPIResult<PictureDTO> uploadPic(UploadPicRequest request) throws Exception {
        PictureDTO data = transferService.upload(request.getFile(), request.getAlbumID());
        RestAPIResult<PictureDTO> result = new RestAPIResult<>();
        return result.success(data, "上传成功");
    }
}
