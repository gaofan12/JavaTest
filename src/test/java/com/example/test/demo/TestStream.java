package com.example.test.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author gaofan
 * @version 1.0
 * @date 2023/1/16 10:45
 **/
public class TestStream {

    public static void main(String[] args) {
        List<String> list = null;
        List<String> strings = Optional.ofNullable(list).orElse(new ArrayList<>()).stream().collect(Collectors.toList());
        System.out.println(strings);

    }
}
