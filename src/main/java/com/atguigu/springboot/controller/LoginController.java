package com.atguigu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author Shawn.Yang
 * @create 2020-05-14-21:14
 */
@Controller
public class LoginController {

//    @RequestMapping(value="/user/login",method = RequestMethod.POST)
    @PostMapping(value = "/user/login")
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        Map<String,Object> map, HttpSession session){
        if(!StringUtils.isEmpty(username) && "123456".equals(password)){
            //登陆成功
            session.setAttribute("loginUser",username);
            return "redirect:/dashboard.html";
        } else {
            //登录失败
            map.put("message","用户名密码错误");
            return "login";
        }
    }

}
