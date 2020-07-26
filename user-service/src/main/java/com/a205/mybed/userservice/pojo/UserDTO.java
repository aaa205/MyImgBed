package com.a205.mybed.userservice.pojo;

public class UserDTO {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDTO(String name, String id, String token) {
        this.name = name;
        this.id = id;
        this.token = token;
    }

    String name;
    String id;
    String token;

}
