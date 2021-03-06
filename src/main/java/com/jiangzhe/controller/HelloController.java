package com.jiangzhe.controller;

import com.jiangzhe.exception.UserNotExsitException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {
    //@RequestMapping({"/","/index.html"})
    //public String index(){
    //    return "index";
    //}

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(@RequestParam("user") String user){
        if (user.equals("aaa")){
            throw new UserNotExsitException();
        }
        return "Hello World";
    }

    @RequestMapping("/success")
    public String success(Map<String,Object> map){
        //classpath:/templates/success.html

        map.put("hello","<h1>你好</h1>");
        map.put("users", Arrays.asList("zhangshan","lisi","wangwu"));
        return "success";
    }
}
