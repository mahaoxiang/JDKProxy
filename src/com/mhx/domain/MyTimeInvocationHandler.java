package com.mhx.domain;

import com.mhx.lang.reflect.InvocationHandler;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

/**
 * @author MHX
 * @date 2018/4/9
 */
public class MyTimeInvocationHandler implements InvocationHandler {
    private Object target;

    public MyTimeInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method) throws Throwable {
        Instant start = Instant.now();
        method.invoke(target);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));
        return null;
    }
}
