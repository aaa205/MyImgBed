package com.a205.mybed.pictureservice.controller;

import com.a205.mybed.pictureservice.exception.DuplicatePictureException;
import com.a205.mybed.pictureservice.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import util.RestAPIResult;

/**
 * 全局异常捕获
 */
@ControllerAdvice
@RestController
public class ExceptionHandler {
    /**
     * 找不到资源
     *
     * @param ex
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public RestAPIResult<Object> handleResourceNotFound(ResourceNotFoundException ex) {
        return new RestAPIResult<>().error(9, ex.getMessage());
    }

    /**
     * 相册出现重复图片
     * @param ex
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = DuplicatePictureException.class)
    @ResponseStatus(HttpStatus.OK)
    public RestAPIResult<Object> handleDuplicatePicture(DuplicatePictureException ex) {
        return new RestAPIResult<>().error(8, ex.getMessage());
    }

}
