package com.mhx.lang.reflect;

import java.lang.reflect.Method;

/**
 * @author MHX
 * @date 2018/4/8
 */
public interface InvocationHandler {
    Object invoke(Object proxy, Method method) throws Throwable;
}
