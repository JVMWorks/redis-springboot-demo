package com.springbootredis.demo.controller;

import com.springbootredis.demo.service.RedisDBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;
import java.util.stream.Collectors;

import static com.springbootredis.demo.service.RedisDBService.*;

@RestController
public class RoutesController {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    RedisDBService redisDBService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        String result = "Pages that matter: /riak, /redis, /neo4j, /memcached, /mongodb, /couchdb, /cassandra \n";
        result += "Pages for stats:  /trending, /trendingDetails \n";
        result += "To empty DB of all data hit [Think again!]: /resetdb \n";
        result += "To list all keys in Redis DB, go hit [Admin Only]: /allkeys \n";
        result += "Page Visit Count: " + redisDBService.incrementVisitorCountOf(PAGE_HOME);
        return result;
    }

    @RequestMapping(value = "/trending", method = RequestMethod.GET)
    public String trending() {
        String result = "Trending Pages: \n";
        result += redisDBService.getTrendingPages(0,-1)
                .stream()
                .collect(Collectors.joining("\n"));
        return result;
    }

    @RequestMapping(value = "/trendingDetails", method = RequestMethod.GET)
    public String trendingWithHitCount() {
        String result = "Trending Page Details: \n";
        result += redisDBService.getTrendingPagesWithHitCount(0,-1)
                .stream()
                .map(t -> String.format("[%s:%.0f]",t.getElement(),t.getScore()))
                .collect(Collectors.joining("\n"));
        return result;
    }

    @RequestMapping(value = "/allkeys", method = RequestMethod.GET)
    public String allkeys() {
        String result = "All Keys in Redis DB @ Server-side: \n";
        result += redisDBService.getAllKeys().stream().collect(Collectors.joining("\n"));
        return result;
    }

    @RequestMapping(value = "/resetdb", method = RequestMethod.GET)
    public String resetdb() {
        return "DB Reset Result: " + redisDBService.resetdb();
    }

    @RequestMapping(value = "/redis", method = RequestMethod.GET)
    public String redis() {
        redisDBService.updateTrendingPages(PAGE_REDIS);
        return "Page Visit Count: " + redisDBService.incrementVisitorCountOf(PAGE_REDIS);
    }

    @RequestMapping(value = "/mongodb", method = RequestMethod.GET)
    public String mongodb() {
        redisDBService.updateTrendingPages(PAGE_MONGODB);
        return "Page Visit Count: " + redisDBService.incrementVisitorCountOf(PAGE_MONGODB);
    }

    @RequestMapping(value = "/riak", method = RequestMethod.GET)
    public String riak() {
        redisDBService.updateTrendingPages(PAGE_RIAK);
        return "Page Visit Count: " + redisDBService.incrementVisitorCountOf(PAGE_RIAK);
    }

    @RequestMapping(value = "/neo4j", method = RequestMethod.GET)
    public String neo4j() {
        redisDBService.updateTrendingPages(PAGE_NEO4J);
        return "Page Visit Count: " + redisDBService.incrementVisitorCountOf(PAGE_NEO4J);
    }

    @RequestMapping(value = "/memcached", method = RequestMethod.GET)
    public String memcached() {
        redisDBService.updateTrendingPages(PAGE_MEMCACHED);
        return "Page Visit Count: " + redisDBService.incrementVisitorCountOf(PAGE_MEMCACHED);
    }

    @RequestMapping(value = "/couchdb", method = RequestMethod.GET)
    public String couchdb() {
        redisDBService.updateTrendingPages(PAGE_COUCHDB);
        return "Page Visit Count: " + redisDBService.incrementVisitorCountOf(PAGE_COUCHDB);
    }

    @RequestMapping(value = "/cassandra", method = RequestMethod.GET)
    public String cassandra() {
        redisDBService.updateTrendingPages(PAGE_CASSANDRA);
        return "Page Visit Count: " + redisDBService.incrementVisitorCountOf(PAGE_CASSANDRA);
    }
}
