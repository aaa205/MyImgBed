package com.a205.mybed.pictureservice.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用于返回给网页的Picture
 */
public class PictureDTO {
    private Integer id;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date uploadTime;

    private BigDecimal size;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date expireTime;

    private String type;

    private Integer like;

    private String url;

    public PictureDTO() {
    }

    public PictureDTO(Picture p) {
        id=p.getId();
        name=p.getName();
        uploadTime=p.getUploadTime();
        size=p.getSize();
        expireTime=p.getExpireTime();
        type=p.getType();
        like=p.getLike();

    }

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
        this.type = type;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
