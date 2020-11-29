package org.ljg.starter.use.controller;

import com.ljg.starter.cls.Klass;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController

public class StarterUseController {

    @Resource
    private Klass klass;

    @GetMapping("/test")
    public String test() {
        return klass.toString();

    }

}
