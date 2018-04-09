package com.mhx.lang.reflect;
import java.lang.reflect.Method;
public class $Proxy1 implements com.mhx.domain.Moveable {
    public $Proxy1(InvocationHandler h) {
        this.h = h;
    }
    private com.mhx.lang.reflect.InvocationHandler h;
    @Override
    public void move() {
        try {
            Method method = com.mhx.domain.Moveable.class.getMethod("move");
            h.invoke(this, method);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}