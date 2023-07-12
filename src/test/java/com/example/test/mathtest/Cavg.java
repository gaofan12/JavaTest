package com.example.test.mathtest;

import cn.hutool.core.math.MathUtil;
import cn.hutool.core.util.NumberUtil;
import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;

import java.util.Stack;

/**
 * 取平均
 *
 * @author gaofan
 * @version 1.0
 * @date 2023/6/6 17:43
 **/
public class Cavg extends PostfixMathCommand {
    public Cavg() {
        super.numberOfParameters = -1;
    }

    public void run(Stack var1) throws ParseException {
        if (null == var1) {
            throw new ParseException("Stack argument null");
        } else {
            Object var2 = null;
            double var3 = 0.0;
            for(int var5 = 0; var5 < super.curNumberOfParameters; ++var5) {
                var2 = var1.pop();
                if (!(var2 instanceof Number)) {
                    throw new ParseException("Invalid parameter type");
                }
                var3 += ((Number)var2).doubleValue();
            }
            if (super.curNumberOfParameters > 0) {
                var3 = NumberUtil.div(var3, super.curNumberOfParameters);
            }
            var1.push(Double.valueOf(var3));
        }
    }
}
