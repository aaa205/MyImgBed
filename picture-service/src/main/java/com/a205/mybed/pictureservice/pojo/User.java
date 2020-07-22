package com.a205.mybed.pictureservice.pojo;


import java.io.Serializable;

public class User implements Serializable {
    private Integer id;

    private String name;

    private String password;

    private String salt;

    private String groupId;

    private String memory;

    private String createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName())
                .append(" [")
                .append("Hash = ").append(hashCode())
                .append(", id=").append(id)
                .append(", name=").append(name)
                .append(", password=").append(password)
                .append(", salt=").append(salt)
                .append(", groupId=").append(groupId)
                .append(", memory=").append(memory)
                .append(", createTime=").append(createTime)
                .append(", serialVersionUID=").append(serialVersionUID)
                .append("]");
        return sb.toString();
    }
}
