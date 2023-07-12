package com.example.test.mathtest;

import org.junit.jupiter.api.Test;
import org.mvel2.MVEL;

import java.io.Serializable;

/**
 * @author gaofan
 * @version 1.0
 * @date 2023/6/6 19:32
 **/
public class Mvel2Test {

    @Test
    public void test01() {
        System.out.println(MVEL.eval("5 > 2"));
        System.out.println(MVEL.eval("5 - 2"));

        Serializable expression = MVEL.compileExpression("5 + 2");
        Object result = MVEL.executeExpression(expression);
        System.out.println(result);

        Object result2 = MVEL.executeExpression(MVEL.compileExpression("[1, 2, 3] contains 2"));
        System.out.println(result2);
        Object result3 = MVEL.executeExpression(MVEL.compileExpression("2 * (1 + 2)"));
        System.out.println(result3);
        Object result4 = MVEL.executeExpression(MVEL.compileExpression("sum((1+2)*3-1,111)"));
        System.out.println(result4);
    }
}
