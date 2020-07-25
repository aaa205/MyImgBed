package util;


import javax.crypto.EncryptedPrivateKeyInfo;
import java.security.MessageDigest;

/**
 * 密码加密类，采用加盐后加密,这里默认加盐后使用md5
 * 加盐：其实是把password添加一定的字段，这样在md5加密时，即使破解了md5加密规则，由于每个用户盐不同，
 * 也很难获得密码
 */
public class PasswordEncoder {
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};




    public static String encode(String rawPass,String salt) {
        String result = null;
        try {

            //这里可以采用其他算法
            MessageDigest md = MessageDigest.getInstance("md5");
            //加密后的字符串
            result = byteArrayToHexString(md.digest(mergePasswordAndSalt(rawPass,salt).getBytes("utf-8")));
        } catch (Exception ex) {
        }
        return result;
    }


    /*
     * 判断原始密码是否等于加密密码
     * encPassword一般为数据库存储的密码，rawPass一般是
     * */
    public  static boolean isPasswordValid(String encPass, String rawPass,String salt) {
        String pass1 = "" + encPass;
        String pass2 = encode(rawPass,salt);
        return pass1.equals(pass2);
    }

    private  static String mergePasswordAndSalt(String password,String salt) {
        if (password == null) {
            password = "";
        }

        if ((salt == null) || "".equals(salt)) {
            return password;
        } else {
            return password + "{" + salt.toString() + "}";
        }
    }

    /**
     * 转换字节数组为16进制字串
     *
     * @param b 字节数组
     * @return 16进制字串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }


}
