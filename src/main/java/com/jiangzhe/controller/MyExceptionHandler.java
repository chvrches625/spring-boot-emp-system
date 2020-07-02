package com.jiangzhe.controller;

import com.jiangzhe.exception.UserNotExsitException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

    //浏览器客户端返回的都是json
    //@ResponseBody
    //@ExceptionHandler(UserNotExsitException.class)
    //public Map<String, Object> handleException(Exception e){
    //    Map<String, Object> map = new HashMap<>();
    //    map.put("code","user.notexist");
    //    map.put("message",e.getMessage());
    //    return map;
    //}



    @ExceptionHandler(UserNotExsitException.class)
    public String handleException(Exception e, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        //传入自己的错误状态码
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user.notexist");
        map.put("message","用户出错了");

        request.setAttribute("ext",map);

        //转发到/error
        return "forward:/error";
    }


}

