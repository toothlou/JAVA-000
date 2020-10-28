package com.java.train.week02.http.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public String testGet(String body) {
        String result = "in testGet, body: " + body;
        System.out.println(result);
        return result;
    }

    @RequestMapping(value = "post", method = RequestMethod.POST)
    public String testPost(String body) {
        String result = "in testPost, body: " + body;
        System.out.println(result);
        return result;
    }
}
