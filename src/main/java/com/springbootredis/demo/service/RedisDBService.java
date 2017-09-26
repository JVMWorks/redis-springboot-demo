package com.springbootredis.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

import java.util.Set;

@Service
public class RedisDBService {

    public static final String VISIT_COUNT_ = "count:page:";
    public static final String PAGE_HOME = "home";
    public static final String PAGE_RIAK = "riak";
    public static final String PAGE_REDIS = "redis";
    public static final String PAGE_NEO4J = "neo4j";
    public static final String PAGE_MEMCACHED = "memcached";
    public static final String PAGE_MONGODB = "mongodb";
    public static final String PAGE_COUCHDB = "couchdb";
    public static final String PAGE_CASSANDRA = "cassandra";
    public static final String TRENDING_PAGES = "trending:pages";

    // Jedis is one of the connectors supported by the Spring Data Redis module
    @Autowired
    private JedisPool jedisPool;

    public Set<String> getTrendingPages(int start, int end) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.zrevrange(TRENDING_PAGES, start, end);
        }
    }

    public Set<Tuple> getTrendingPagesWithHitCount(int start, int end) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.zrevrangeWithScores(TRENDING_PAGES, start, end);
        }
    }

    public double updateTrendingPages(String key) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.zincrby(TRENDING_PAGES, 1, key);
        }
    }

    public long incrementVisitorCountOf(String key) {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.incr(VISIT_COUNT_ + key);
        }
    }

    public String resetdb() {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.flushAll();
        }
    }

    public Set<String> getAllKeys() {
        try(Jedis jedis = jedisPool.getResource()) {
            return jedis.keys("*");
        }
    }
}
