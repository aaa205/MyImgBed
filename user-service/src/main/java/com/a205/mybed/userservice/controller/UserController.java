package com.a205.mybed.userservice.controller;


import com.a205.mybed.userservice.pojo.User;
import com.a205.mybed.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import request.RegisterRequest;
import util.RestAPIResult;

/**
 * 用户controller
 * @author yxh
 * @Date 2020.7.24
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     */
    @PostMapping("register")
    public RestAPIResult<User> register(@RequestBody RegisterRequest request) {
        //构造返回对象
        RestAPIResult<User> result = new RestAPIResult<>();
        User user = userService.register(request.getName(), request.getPassword());
        //如果service层返回一个空对象
        if (user == null)
            return result.error(1, "注册失败");
        return result.success(user, "注册成功");

    }
    @GetMapping("test1")
    public String test(){
        return "heloo";
    }


}
