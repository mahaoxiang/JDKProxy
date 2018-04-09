package com.mhx.domain;

/**
 * @author MHX
 * @date 2018/4/8
 */
public class Car implements Moveable {

    @Override
    public void move() {
        System.out.println("The car is moving...");
    }

}
