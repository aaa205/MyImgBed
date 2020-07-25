package com.a205.mybed.pictureservice.util;

import com.a205.mybed.pictureservice.pojo.Picture;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * 文件传输工具类
 */
@Component
public class FileUtil {
    // 放配置文件里读不出来，暂时写死
    private List<String> allowedImageTypes = Arrays.asList("jpeg","jpg","png","gif");
    // 计算单位的除数 2^20
    private long units=1<<20;

    @Value("${picture-url-prefix}")
    private String urlPrefix;

    /**
     * 2020-7-20上传的文件存放在 ${starge-path}/2020/7/20/picName.png
     * 获取相对路径
     * @return 文件应该保存的相对路径 例：2020/7/20/
     */
    public String getPicRelativePath() {
        LocalDate date = LocalDate.now();
        String relPath = String.format("%d/%d/%d/", date.getYear(),
                date.getMonth().getValue(), date.getDayOfMonth());
        return relPath;
    }

    /**
     * 检查该文件是否在允许的类型内（简单检查后缀名)
     *
     * @param s 文件的后缀
     * @return true 如果允许，否则 false
     */
    public boolean isAllowed(String s) {
        return allowedImageTypes.stream().anyMatch(i -> i.equals(s.trim()));
    }

    /**
     * 构建图片的URL
     * @param p 指定的照片
     * @return 完整的图片url
     * @throws URISyntaxException
     */
    public String buildPicUrl(Picture p) throws URISyntaxException {
        URIBuilder builder = new URIBuilder();
        String[] segArr=p.getParentLocation().split("/");
        builder.setHost(urlPrefix)
                .setPathSegments(segArr[0],segArr[1],segArr[2],(p.getName()+"."+p.getType()).replace("/",""));
        String url="http:"+builder.build().toString();
        return url;
    }

    /**
     * 将B转为MB为单位
     * @param len 字节长度
     * @return 以MB为单位的大小
     */
    public double sizeInMB(long len){
        return ((double)len)/units;
    }

}
