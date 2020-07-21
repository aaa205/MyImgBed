package request;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传图片请求
 */
public class UploadPicRequest {
    private int albumID;
    private int userID;
    private MultipartFile file;

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
