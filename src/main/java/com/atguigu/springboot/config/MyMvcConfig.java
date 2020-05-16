package com.atguigu.springboot.config;

import com.atguigu.springboot.component.LoginHandlerInterceptor;
import com.atguigu.springboot.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * 使用WebMvcConfigurer扩展SpringMVC的功能
 *
 * @author Shawn.Yang
 * @create 2020-05-14-16:45
 */
//@EnableWebMvc
@Configuration //不要接管SpringMVC
public class MyMvcConfig implements WebMvcConfigurer {

    //设置直接访问页面，不需要通过controller
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/dashboard.html").setViewName("dashboard");
    }

    /**
     * 添加国际化切换
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        final MyLocaleResolver myLocaleResolver = new MyLocaleResolver();
        return myLocaleResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LoginHandlerInterceptor loginHandlerInterceptor = new LoginHandlerInterceptor();
        //静态资源 *.js *.css
        //SpringBoot已经做好的静态资源映射
        registry.addInterceptor(loginHandlerInterceptor).addPathPatterns("/**")//拦截所有
                .excludePathPatterns("/login.html","/","/user/login","/webjars/**","/asserts/**");//白名单
    }
}
