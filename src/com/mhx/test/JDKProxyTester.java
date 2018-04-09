package com.mhx.test;

import com.mhx.domain.Car;
import com.mhx.domain.LogInvocationHandler;
import com.mhx.domain.Moveable;

import java.lang.reflect.Proxy;

/**
 * @author MHX
 * @date 2018/4/8
 */
public class JDKProxyTester {
    public static void main(String[] args) {
        Car car = new Car();
        LogInvocationHandler handler = new LogInvocationHandler(car);
        Class<?> clazz = car.getClass();
        Moveable carProxy = (Moveable) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), handler);
        carProxy.move();
    }
}
