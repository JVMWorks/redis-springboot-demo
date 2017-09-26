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
        String result = "Pages to visit: /trending, /riak, /redis, /neo4j, /memcached, /mongodb, /couchdb, /cassandra \n";
        result += "To empty DB of all data hit [Think again!]: /resetdb \n";
        result += "Page Visit Count: " + redisDBService.incrementVisitorCountOf(PAGE_HOME);
        return result;
    }

    @RequestMapping(value = "/trending", method = RequestMethod.GET)
    public String trending() {
        String result = "Trending Pages: \n";
        result += redisDBService.getTrendingPages(0,2).stream().collect(Collectors.joining("\n"));
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
