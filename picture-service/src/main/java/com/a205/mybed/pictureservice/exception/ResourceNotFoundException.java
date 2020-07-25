package com.a205.mybed.pictureservice.exception;

/**
 * 找不到资源
 */
public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException() {
        super("找不到该资源");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
