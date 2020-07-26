package com.a205.mybed.userservice.controller;


import com.a205.mybed.userservice.pojo.User;
import com.a205.mybed.userservice.pojo.UserDTO;
import com.a205.mybed.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import request.LoginRequest;
import request.RegisterRequest;
import response.LoginResponseData;
import util.RestAPIResult;

/**
 * 用户controller
 *
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
        // 删除敏感信息 @ Fungx
        user.setSalt(null);
        user.setPassword(null);
        return result.success(user, "注册成功");

    }

    @PostMapping("login")
    public RestAPIResult<LoginResponseData> login(@RequestBody  LoginRequest request) {
        RestAPIResult<LoginResponseData> result = new RestAPIResult<>();
        LoginResponseData loginResponseData = new LoginResponseData();
        UserDTO userDTO = userService.login(request.getName(), request.getPassword());
        if (userDTO == null)
            return result.error(9, "登录失败");
        loginResponseData.setToken(userDTO.getToken());
        loginResponseData.setUserID(userDTO.getId());
        loginResponseData.setUserName(userDTO.getName());
        return result.success(loginResponseData, "登录成功");

    }


    /**
     * 携带token查看用户是否登录
     * @param token
     * @return
     */
    @GetMapping("isAlive")
    public Boolean isAlive(String token) {
        RestAPIResult<LoginResponseData> result = new RestAPIResult<>();
        return (userService.isAlive(token));

    }


}
