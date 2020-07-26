package com.a205.mybed.userservice.service.impl;

import com.a205.mybed.userservice.dao.UserMapper;
import com.a205.mybed.userservice.pojo.User;
import com.a205.mybed.userservice.pojo.UserDTO;
import com.a205.mybed.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.PasswordEncoder;
import util.RandomUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    //Token存活时间
    private static final int TOKEN_ALIVE_TIME = 3600;

    //日志类
    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    /**
     * 注册
     * @param name
     * @param password
     * @return
     */
    @Override
    public User register(String name, String password) {

        //信息不合法
        if (name == null || password == null) {
            return null;
        }
        User user = new User();
        user.setName(name);

        //随机生成20-30位的盐
        user.setSalt(RandomUtil.randomString(20, 30));
        user.setPassword(PasswordEncoder.encode(password, user.getSalt()));

        //0为用户组
        user.setGroupId(Byte.parseByte("0"));

        //初始化内存
        user.setMemory(1024);

        user.setCreateTime(new Date());

        userMapper.insert(user);

        return user;
    }

    /**
     * Redis封装类，springboot自带
     */
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 登录
     * @param name
     * @param password
     * @return
     */
    @Override
    @Transactional
    public UserDTO login(String name, String password) {


        if (name == null || password == null) {
            return null;
        }
        //只查询某些字段，返回的是hashmap
        HashMap<String, String> userInfoMap = userMapper.selectByName(name);
        System.out.println(userInfoMap + "mapinfo");
        String salt = userInfoMap.get("salt");
        String id = String.valueOf(userInfoMap.get("id"));
        String encodePassword = userInfoMap.get("password");

        //用户密码验证失败
        if (!PasswordEncoder.isPasswordValid(encodePassword, password, salt)) {
            return null;
        }

        String token = UUID.randomUUID().toString().replaceAll("-", "");


        //将用户的ID和名字信息存入redis缓存，并设置一小时的过期时间,设置的是k，v  key是token ，v是id
        stringRedisTemplate.opsForValue().set(token, String.valueOf(id) + "," + name, TOKEN_ALIVE_TIME, TimeUnit.SECONDS);


        UserDTO userDTO = new UserDTO(name, password, token);


        return userDTO;
    }


    /**
     * 用户是否登录
     *
     * @return
     */
    @Override
    public HashMap<String, String> isAlive(String token) {
        HashMap<String, String> result = new HashMap();
        if (token == null) {
            return null;
        }
        String idAndName = stringRedisTemplate.opsForValue().get(token);
        if (idAndName != null) {
            //token存在，重新延长时间
            String[] userInfo = idAndName.split(",");

            result.put("id", userInfo[0]);
            result.put("name", userInfo[1]);
            result.put("token", token);
            stringRedisTemplate.expire(token, TOKEN_ALIVE_TIME, TimeUnit.SECONDS);

            return result;
        }
        return null;
    }
}