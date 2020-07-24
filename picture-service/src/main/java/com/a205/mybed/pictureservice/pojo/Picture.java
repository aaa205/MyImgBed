package com.a205.mybed.pictureservice.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Picture implements Serializable {
    private Integer id;

    private String name;

    private String parentLocation;

    private Date uploadTime;

    private BigDecimal size;

    private Date expireTime;

    private String type;

    private Integer like;

    private String md5;

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
        this.name = name == null ? null : name.trim();
    }

    public String getParentLocation() {
        return parentLocation;
    }

    public void setParentLocation(String parentLocation) {
        this.parentLocation = parentLocation == null ? null : parentLocation.trim();
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5 == null ? null : md5.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", parentLocation=").append(parentLocation);
        sb.append(", uploadTime=").append(uploadTime);
        sb.append(", size=").append(size);
        sb.append(", expireTime=").append(expireTime);
        sb.append(", type=").append(type);
        sb.append(", like=").append(like);
        sb.append(", md5=").append(md5);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}