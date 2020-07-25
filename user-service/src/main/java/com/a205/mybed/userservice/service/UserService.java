package com.a205.mybed.userservice.service;

import com.a205.mybed.userservice.pojo.User;
import org.springframework.stereotype.Service;

/*
* 用户service
* @date 2020.7.24
* @author yxh
* */

public interface UserService
{
    User register(String name,String password);
    int login(String name,String password);
}
