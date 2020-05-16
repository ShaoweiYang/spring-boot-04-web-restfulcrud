package com.atguigu.springboot.controller;

import com.atguigu.springboot.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Shawn.Yang
 * @create 2020-05-13-20:51
 */

@Controller
public class HelloController {

//    @RequestMapping({"/","/index.html"})
//    public String index(){
//        System.out.println("~~~~~~~~~~~~~~访问此方法~~~~~~~~~~~~~~");
//        return "index";
//    }

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(@RequestParam("user") String user){
        //抛出自定义异常
        if("aaa".equals(user)){
            throw new UserNotExistException();
        }
        return "Hello World";
    }

    @RequestMapping("/success")
    public String success(Map<String,Object> map){
        //classpath:/templates/**.html
        map.put("hello","<h1>你好</h1>");
        map.put("users", Arrays.asList("zhangsan","lisi","wangwu"));
        return "success";
    }

}
