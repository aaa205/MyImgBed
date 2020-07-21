package request;

/**
 * 请求创建某用户相册
 */
public class CreateAlbumRequest {
    private int userID;
    private String newAlbumName;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getNewAlbumName() {
        return newAlbumName;
    }

    public void setNewAlbumName(String newAlbumName) {
        this.newAlbumName = newAlbumName;
    }
}
