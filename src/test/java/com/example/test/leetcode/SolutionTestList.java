package com.example.test.leetcode;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author gaofan
 * @version 1.0
 * @date 2022/8/11 14:42
 **/
@Slf4j
public class SolutionTestList {

    @Test
    public void test01() {
        log.info(StrUtil.toString(1));
        log.info(StrUtil.toString(new Double(2)));
        log.info("输出：{}", isHappy(19));

        int[] ints = new int[10];
        log.info("输出：{}", ints.length);
        List<Integer> integers = Arrays.asList(ArrayUtils.toObject(ints));
        log.info("输出：{}", integers.size());
        String str = "abc";
        log.info("输出：{}", str.length());

        m( Arrays.asList("aa", "bb", "cc"));
    }

    //@SafeVarargs // Not actually safe!
    static void m(List<String>... stringLists) {
        Object[] array = stringLists;
        List<Integer> tmpList = Arrays.asList(42);
        array[0] = tmpList; // Semantically invalid, but compiles without warnings
        String s = stringLists[0].get(0); // Oh no, ClassCastException at runtime!
    }

    @Test
    void test02() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList<Integer> list = new ArrayList<Integer>();

        list.add(1);  //这样调用 add 方法只能存储整形，因为泛型类型的实例为 Integer

        list.getClass().getMethod("add", Object.class).invoke(list, "asd");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            Integer integer = list.get(i);
            System.out.println(integer);
        }
    }


    public boolean isHappy(int n) {
        Set<Integer> numSet = new HashSet<>();
        while(n != 1 && !numSet.contains(n)) {
            numSet.add(n);
            n = getNextNum(n);
        }
        return n == 1;
    }

    private int getNextNum(int n) {
        int sum = 0;
        while(n > 0) {
            sum += (n % 10) * (n % 10);
            n = n /10;
        }
        return sum;
    }
}
