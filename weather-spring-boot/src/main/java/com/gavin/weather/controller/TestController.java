package com.gavin.weather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @RequestMapping("/say")
    @ResponseBody
    public String say(){
        return "Hello World!";
    }
}
