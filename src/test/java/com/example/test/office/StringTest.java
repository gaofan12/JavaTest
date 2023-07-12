package com.example.test.office;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaofan
 * @version 1.0
 * @date 2023/4/17 13:34
 **/
public class StringTest {

    @Test
    public void test01() {
        StringBuilder collect = "abcdefa" .chars().filter(c -> !Character.isISOControl(c)).collect(StringBuilder::new,
                StringBuilder::appendCodePoint, StringBuilder::append);

        System.out.println(collect.toString());
        System.out.println(String.format("（%d行重复，%d行错误）", null, 33));
        //String[] http = new String[]{"\\(https://", "http://"};
        //List<String> splice = splice("(https://blog.csdn.net/weixin_34194317/article/details/92342662", http);
        //System.out.println(splice.toString());
    }

    public static List<String> splice(String str, String[] regexs) {
        if (regexs == null || regexs.length == 0) {
            return new ArrayList<>();
        }
        StringBuffer regexStr = new StringBuffer();
        for (int i = 0; i < regexs.length; i++) {
            if (i != 0) {
                regexStr.append("|");
            }
            regexStr.append(regexs[i]);
        }
        String[] parts = str.split(regexStr.toString());
        List<String> result = new ArrayList<>();
        for (String part : parts) {
            if (!part.isEmpty()) {
                result.add(part.trim());
            }
        }
        return result;
    }
}
