package com.springbootredis.demo.controller;

import com.springbootredis.demo.service.RedisDBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;

@RestController
public class HomeController {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    RedisDBService redisDBService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "Page Visit Count: " + redisDBService.incrementVisitorCount();
    }
}
