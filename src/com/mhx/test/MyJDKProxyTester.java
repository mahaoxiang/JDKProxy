package com.mhx.test;

import com.mhx.domain.Car;
import com.mhx.domain.Moveable;
import com.mhx.domain.MyLogInvocationHandler;
import com.mhx.domain.MyTimeInvocationHandler;
import com.mhx.lang.reflect.Proxy;

/**
 * @author MHX
 * @date 2018/4/8
 */
public class MyJDKProxyTester {
    public static void main(String[] args) throws Exception {
        Moveable car = new Car();
        MyLogInvocationHandler logHandler = new MyLogInvocationHandler(car);
        // TODO
        // MyTimeInvocationHandler timeHandler = new MyTimeInvocationHandler(logHandler);
        Class<?> clazz = car.getClass();
        Moveable carProxy = (Moveable) Proxy.newProxyInstance(clazz.getInterfaces(), logHandler);
        carProxy.move();
    }
}
