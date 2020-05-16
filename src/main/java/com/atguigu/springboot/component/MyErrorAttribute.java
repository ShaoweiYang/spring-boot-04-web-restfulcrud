package com.atguigu.springboot.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 *
 * 加入自己定义的错误属性
 *
 * @author Shawn.Yang
 * @create 2020-05-16-12:18
 */
@Component
public class MyErrorAttribute extends DefaultErrorAttributes {

    //返回的map就是页面和json能获取的字段
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        errorAttributes.put("company","atguigu");

        //获取额外信息
        //getAttribute 参数一：attribute名；参数二：0 request 1 session
        Map<String,Object> ext = (Map<String,Object>)webRequest.getAttribute("ext",0);
        errorAttributes.put("code",ext.get("code"));
        errorAttributes.put("extmessage",ext.get("extmessage"));
        return errorAttributes;
    }


}
