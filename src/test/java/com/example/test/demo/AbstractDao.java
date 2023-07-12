package com.example.test.demo;

import java.lang.reflect.ParameterizedType;

public class AbstractDao<T> {
    public Class<T> getActualType() {
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        System.out.println(parameterizedType.toString());
        return (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }
}
