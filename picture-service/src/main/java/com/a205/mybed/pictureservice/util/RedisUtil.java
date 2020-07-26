package com.a205.mybed.pictureservice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @Autowired
    private ObjectMapper objectMapper;// 实际上共用一个objectMapper会影响性能
    private Logger logger = LoggerFactory.getLogger(RedisUtil.class);


    public <T> T getValue(String key, Class<T> clazz) {
        try (Jedis jedis = jedisPool.getResource()) {
            try {
                String content = jedis.get(key);
                return content == null ? null : objectMapper.readValue(content, clazz);
            } catch (JsonProcessingException e) {
                logger.error("json解析异常 key {}", key);
            }
            return null;
        }

    }

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
    public void setex(String key, int second, Object val) {
        try (Jedis jedis = jedisPool.getResource()) {
            String json = objectMapper.writeValueAsString(val);
            jedis.setex(key, second, json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.warn("json序列化异常 key {}", key);
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
