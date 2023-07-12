package com.example.test.demo;

/**
 * @author gaofan
 * @version 1.0
 * @date 2022/7/13 9:21
 **/
public class FanXin<T, E> {

    public T getA(E index) {
        return (T) index;
    }

    public T get(T index) {
        return index;
    }

}
