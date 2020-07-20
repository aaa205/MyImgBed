package com.a205.mybed.pictureservice.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis连接池配置类
 */
@Configuration
public class RedisPoolConfiguration {
    Logger logger = LoggerFactory.getLogger(RedisPoolConfiguration.class);
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;

    @Bean
    public JedisPool getRedisPool() {
        logger.info("Creating JedisPool..");
        JedisPoolConfig config = new JedisPoolConfig();
        JedisPool jedisPool = new JedisPool(config, host, port, timeout);
        logger.info("JedisPool is created!");
        return jedisPool;
    }

}
