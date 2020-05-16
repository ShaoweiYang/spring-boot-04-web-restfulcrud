package com.atguigu.springboot.config;

import com.atguigu.springboot.filter.MyFilter;
import com.atguigu.springboot.listener.MyListener;
import com.atguigu.springboot.servlet.MyServlet;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

/**
 * @author Shawn.Yang
 * @create 2020-05-16-13:21
 */
@Configuration
public class MyServletConfig {

    //注册Servlet
    @Bean
    public ServletRegistrationBean myServlet(){
        ServletRegistrationBean<Servlet> registrationBean = new ServletRegistrationBean<>(new MyServlet(),"/myServlet");
        return registrationBean;
    }

    //注册Filter
    @Bean
    public FilterRegistrationBean myFilter(){
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        //设置过滤器
        filterRegistrationBean.setFilter(new MyFilter());
        //设置拦截路径
        List<String> urls = new ArrayList<>();
        urls.add("/myServlet");
        filterRegistrationBean.setUrlPatterns(urls);

        return filterRegistrationBean;
    }

    //注册listener
    @Bean
    public ServletListenerRegistrationBean myListener(){
        ServletListenerRegistrationBean<EventListener> listenerRegistrationBean =
                new ServletListenerRegistrationBean<>(new MyListener());
        return listenerRegistrationBean;
    }

    //配置嵌入式的servelt容器
    @Bean
    public WebServerFactoryCustomizer getWebServerFactoryCustomizer(){
        return new WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>(){

            //定制嵌入式Servlet容器相关的规则,hah
            @Override
            public void customize(ConfigurableServletWebServerFactory factory) {
                //设置返回端口号
//                factory.setPort(8090);
            }
        };
    }

}
