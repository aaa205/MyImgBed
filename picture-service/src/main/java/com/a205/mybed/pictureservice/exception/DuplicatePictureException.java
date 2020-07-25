package com.a205.mybed.pictureservice.exception;

/**
 * 往相册上传重复图片异常
 */
public class DuplicatePictureException extends Exception {
    public DuplicatePictureException() {
        super();
    }

    public DuplicatePictureException(String message) {
        super(message);
    }
}
