package com.a205.mybed.pictureservice.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import util.RestAPIResult;

/**
 * 全局异常捕获
 * 开发阶段先关掉，方便调试
 */
//@ControllerAdvice
@RestController
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public RestAPIResult<Object> exceptionHandler(Exception e) {
        return new RestAPIResult<>().error(9,  e.getMessage());
    }
}
