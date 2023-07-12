package com.example.test.mathtest;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.nfunk.jep.JEP;

import java.math.BigDecimal;

/**
 * @author gaofan
 * @version 1.0
 * @date 2023/6/6 17:31
 **/
@Slf4j
public class JepTest {

    @Test
    public void test01() {
        BigDecimal bigDecimal = new BigDecimal("1");
        String s = bigDecimal.setScale(0).toPlainString();
        System.out.println(s);
        String expression = "(a+b)*b-1";
        JEP jep = new JEP();
        //1 设置变量
        jep.addVariable("a", 1);
        jep.addVariable("b", 2);
        //2 计算表达式
        jep.parseExpression(expression);
        //3 获取表达式的值
        double result = jep.getValue();
        System.out.println(result);
        //输出结果：5.0
    }
    @Test
    public void test02() {
        String expression = "(1+2)*3-1";
        JEP jep = new JEP();
        //2 计算表达式
        jep.parseExpression(expression);
        //3 获取表达式的值
        double result = jep.getValue();
        System.out.println(result);
        //输出结果：5.0
    }
    @Test
    public void test03() {
        String expression = "sum(2121,111)";
        JEP jep = new JEP();
        //引入标准函数（必须先引入函数，才可执行对应内置方法）
        jep.addStandardFunctions();
        jep.addFunction("myFun01",new MyFun());
        jep.addFunction("Csum",new Csum());
        jep.parseExpression("myFun01(10)");
        System.out.println(jep.getValue());
        jep.parseExpression("myFun01(-9)");
        System.out.println(jep.getValue());
        //2 计算表达式
        jep.parseExpression(expression);
        //3 获取表达式的值
        double result = jep.getValue();
        System.out.println(result);
        //输出结果：5.0
        jep.parseExpression("Csum((1+ 2 )*3-1, 111)");
        System.out.println(jep.getValue());
    }

    @Test
    public void test04() {
        JEP jep = new JEP();
        jep.parseExpression("min(6, 111)");
        System.out.println(jep.getValue());
    }
}
