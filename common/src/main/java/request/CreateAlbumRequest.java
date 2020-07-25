package request;

/**
 * 请求创建某用户相册
 */
public class CreateAlbumRequest {

    private String newAlbumName;

    public String getNewAlbumName() {
        return newAlbumName;
    }

    public void setNewAlbumName(String newAlbumName) {
        this.newAlbumName = newAlbumName;
    }
}
