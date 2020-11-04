package com.jave.train.week02.http.server.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name, @RequestHeader HttpHeaders headers) {
        StringBuilder builder = new StringBuilder();
        builder.append("Hello ").append(name).append("\r\n");
        builder.append("headers:");
        builder.append("\r\n");
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            builder.append(entry.getKey());
            builder.append(":");
            builder.append(entry.getValue());
            builder.append("\r\n");
        }
//        return String.format("Hello %s!\nHeaders: %", name, headers_show);
        return builder.toString();
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public String testGet(@RequestParam(value = "para") String para) {
        String result = "in testGet, para: " + para;
        System.out.println(result);
        return result;
    }

    @RequestMapping(value = "getNoPara", method = RequestMethod.GET)
    public String testGetNoPara() {
        String result = "in testGetNoPara ...";
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
