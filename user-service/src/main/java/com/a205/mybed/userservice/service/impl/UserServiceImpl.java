package com.a205.mybed.userservice.service.impl;

import com.a205.mybed.userservice.dao.UserMapper;
import com.a205.mybed.userservice.pojo.User;
import com.a205.mybed.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.PasswordEncoder;
import util.RandomUtil;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    Logger logger= LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public User register(String name, String password) {
        //信息不合法
        if(name==null || password ==null)
        {
            return null;
        }
        User user=new User();
        user.setName(name);

        //随机生成20-30位的盐
        user.setSalt(RandomUtil.randomString(20,30));
        user.setPassword(PasswordEncoder.encode(password,user.getSalt()));

        //0为用户组
        user.setGroupId(Byte.parseByte("0"));

        //初始化内存
        user.setMemory(1024);

        user.setCreateTime(new Date());

        userMapper.insert(user);

        return user;
    }

    @Override
    public int login(String name, String password) {
        return 0;
    }
}
