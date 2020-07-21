package util;
/**
 * 统一的返回结构
 */
public class  RestAPIResult<T> {
    // 返回码，0表示成功，其他表示失败,如果失败时不要填充data
    private int ret = 1919;
    // 返回信息
    private String message = "undefined";
    // 返回数据
    private T data = null;

    /**
     * 当返回成功时快捷设置内容
     * @param data
     * @param message
     * @return
     */
    public RestAPIResult<T> success(T data,String message){
        ret=0;
        this.data=data;
        this.message=message;
        return this;
    }

    /**
     * 当出错时快捷设置内容
     * @param ret
     * @param message
     * @return
     */
    public RestAPIResult<T> error(int ret,String message){
        this.ret=ret;
        this.message=message;
        this.data=null;
        return this;
    }

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
