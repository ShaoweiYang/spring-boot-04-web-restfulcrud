package com.atguigu.springboot.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Shawn.Yang
 * @create 2020-05-16-13:31
 */
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MyFilter Process.....");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
