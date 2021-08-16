package com.lv.rediscsdn.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author lvjiangtao
 * @create 2021-08-13-20:31
 */
@RestController()
@ResponseBody
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestBody String hello){
        return hello;
    }


}
