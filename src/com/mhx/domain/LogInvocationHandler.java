package com.mhx.domain;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author MHX
 * @date 2018/4/8
 */
public class LogInvocationHandler implements InvocationHandler {

    private Object target;

    public LogInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("log begins...");
        method.invoke(target);
        System.out.println("log ends...");
        return null;
    }
}
