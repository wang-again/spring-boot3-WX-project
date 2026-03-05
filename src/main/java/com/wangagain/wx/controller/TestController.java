package com.wangagain.wx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class TestController {
    @GetMapping("/sayHello")
    public String sayHello() {
        return "Hello World";
    }
}
