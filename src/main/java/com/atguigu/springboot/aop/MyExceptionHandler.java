package com.atguigu.springboot.aop;

import com.atguigu.springboot.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理器
 * @author Shawn.Yang
 * @create 2020-05-16-11:47
 */
@ControllerAdvice
public class MyExceptionHandler  {

//    @ResponseBody
//    @ExceptionHandler(UserNotExistException.class)
//    public Map<String,Object> handleException(Exception e){
//        Map<String,Object> map = new HashMap<>();
//        map.put("code","user.notexists");
//        map.put("message",e.getMessage());
//        return map;
//    }

    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("code","user.notexists");
        map.put("extmessage","用户出错啦");
        request.setAttribute("ext",map);

        //设置状态码
        request.setAttribute("javax.servlet.error.status_code",500);
        //转发到 error
        return "forward:/error";
    }

}
