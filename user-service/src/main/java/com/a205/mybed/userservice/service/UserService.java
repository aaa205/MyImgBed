package com.a205.mybed.userservice.service;

import com.a205.mybed.userservice.pojo.User;
import com.a205.mybed.userservice.pojo.UserDTO;

import java.util.HashMap;

/*
* 用户service
* @date 2020.7.24
* @author yxh
* */

public interface UserService
{
    User register(String name,String password);
    UserDTO login(String name, String password);
    HashMap<String ,String> isAlive(String token);
}
