package com.atguigu.springboot.component;


import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 *
 * 可以在链接上携带国际化的区域信息
 *
 * @author Shawn.Yang
 * @create 2020-05-14-20:43
 */
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String languageStr = httpServletRequest.getParameter("l");
        Locale locale = Locale.getDefault();
        if(!StringUtils.isEmpty(languageStr)){
            String[] strs = languageStr.split("_");
            Locale locale1 = new Locale(strs[0], strs[1]);
            locale = locale1;
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {
    }
}
