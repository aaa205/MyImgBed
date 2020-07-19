package util;
/**
 * 统一的返回结构
 */
public class  RestAPIResult<T> {
    // 返回码，0表示成功，其他表示失败,如果失败时不要填充data
    private int ret;
    // 返回信息
    private String message;
    // 返回数据
    private T data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
