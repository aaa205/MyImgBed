package com.a205.mybed.pictureservice.pojo;

import java.io.Serializable;

public class AlbumPicture implements Serializable {
    private Integer albumId;

    private Integer pictureId;

    private static final long serialVersionUID = 1L;

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", albumId=").append(albumId);
        sb.append(", pictureId=").append(pictureId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}