package com.jiangzhe.exception;

public class UserNotExsitException extends RuntimeException{
    public UserNotExsitException() {
        super("用户不存在");
    }
}
