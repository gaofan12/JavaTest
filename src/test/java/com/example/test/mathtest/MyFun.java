package com.example.test.mathtest;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;

import java.util.Stack;

/**
 * @author gaofan
 * @version 1.0
 * @date 2023/6/6 17:37
 **/
//自定义函数类（非正数返回0）
public  class MyFun extends PostfixMathCommand {
    public MyFun(){
        //初始化参数格式（如参数个数为变长，测为-1）
        super.numberOfParameters = 1;
    }
    @Override
    public void run(Stack stack) throws ParseException {
        //检查栈
        this.checkStack(stack);
        //出栈
        Object obj = stack.pop();
        //计算结果入栈
        stack.push(this.myMethod(obj));
    }

    private Object myMethod(Object obj) throws ParseException{
        if(obj instanceof Number){
            double inputParam=((Number) obj).doubleValue();
            if(inputParam<0){
                return new Double("0.00");
            }else{
                return new Double(inputParam);
            }
        }else{
            throw new ParseException("Invalid parameter type");
        }
    }
}