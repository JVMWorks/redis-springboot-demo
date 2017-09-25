package com.springbootredis.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisDBService {

    private static final String VISITOR_COUNT = "visitorCount";

    // Jedis is one of the connectors supported by the Spring Data Redis module
    @Autowired
    private JedisPool jedisPool;

    public long incrementVisitorCount() {
        Jedis jedis = jedisPool.getResource();
        return jedis.incr(VISITOR_COUNT);
    }
}
