package com.mhx.domain;

import com.mhx.lang.reflect.InvocationHandler;

import java.lang.reflect.Method;

/**
 * @author MHX
 * @date 2018/4/8
 */
public class MyLogInvocationHandler implements InvocationHandler {

    private Object target;

    public MyLogInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method) throws Throwable {
        System.out.println("log begins...");
        method.invoke(target);
        System.out.println("log ends...");
        return null;
    }
}
