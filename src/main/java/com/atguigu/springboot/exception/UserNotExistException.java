package com.atguigu.springboot.exception;

/**
 * @author Shawn.Yang
 * @create 2020-05-16-11:09
 */
public class UserNotExistException extends RuntimeException{

    public UserNotExistException() {
        super("用户不存在");
    }
}
