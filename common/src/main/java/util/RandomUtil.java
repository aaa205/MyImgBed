package util;

import org.apache.commons.lang.RandomStringUtils;

import java.util.Random;

/**
 * 随机生成数字或者字符串
 */

public class RandomUtil {


    /**
     * 生成一个随机数
     * @param min  最小值
     * @param max  最大值，不包括
     * @return  int
     */
    public  static int randomNumber(int min,int max){
        Random random=new Random();
        int result=random.nextInt(max-min)+max;
        return result;

    }

    /**
     * 随机生成长度为min到max（不包括）之间的字符串
     * @param min
     * @param max
     * @return  String
     */
    public static String randomString(int min, int max){

        return RandomStringUtils.randomAlphanumeric(randomNumber(min,max));
    }

    /**
     * 随机生成长度为length的字符串
     * @param length
     * @return length
     */
    public static String randomString(int length){
        return RandomStringUtils.randomAlphanumeric(length);
    }


}
