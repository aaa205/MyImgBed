package com.a205.mybed.pictureservice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Redis工具类
 */
@Component
public class RedisUtil {
    @Autowired
    private JedisPool jedisPool;

    /**
     * 查询key是否存在
     *
     * @param key
     * @return
     */
    public boolean exists(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.exists(key);
        }
    }

    /**
     * 设置缓存
     *
     * @param key    键
     * @param second 过期时间
     * @param val    值
     */
    public void setex(String key, int second, String val) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.setex(key, second, val);
        }
    }

    /**
     * 值自增
     *
     * @param key
     * @return 自增后的值
     */
    public int incr(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.incr(key).intValue();
        }
    }

}
