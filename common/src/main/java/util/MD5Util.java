package util;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 */
public class MD5Util {
    /**
     * 根据文件内容生成MD5
     *
     * @param is 文件的输入流
     * @return 十六进制MD5
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static String generateMD5(InputStream is) throws IOException, NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] buffer = new byte[1024];
        int hadRead;
        while ((hadRead = is.read(buffer)) > 0) {
            md5.update(buffer, 0, hadRead);
        }
        return bytesToHex(md5.digest());
    }

    /**
     * bytes转十六进制字符串
     *
     * @param bytes
     * @return
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexVal = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            int val = ((int) bytes[i] & 0xff);
            if (val < 16)
                hexVal.append("0");
            hexVal.append(Integer.toHexString(val));
        }
        return hexVal.toString();
    }
}
