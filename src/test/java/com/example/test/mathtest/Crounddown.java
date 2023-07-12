package com.example.test.mathtest;

import cn.hutool.core.util.NumberUtil;
import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;

import java.math.RoundingMode;
import java.util.Stack;

/**
 * 乘积
 *
 * @author gaofan
 * @version 1.0
 * @date 2023/6/6 17:43
 **/
public class Crounddown extends PostfixMathCommand {
    public Crounddown() {
        super.numberOfParameters = -1;
    }

    public void run(Stack var1) throws ParseException {
        if (null == var1) {
            throw new ParseException("Stack argument null");
        } else {
            Object var2 = null;
            double var3 = 0.0;
            if (super.curNumberOfParameters == 2) {
                var2 = var1.pop();
                if (!(var2 instanceof Number)) {
                    throw new ParseException("Invalid parameter type");
                }
                var3 = ((Number)var2).doubleValue();
                var2 = var1.pop();
                if (!(var2 instanceof Number)) {
                    throw new ParseException("Invalid parameter type");
                }
                var3 = NumberUtil.div(NumberUtil.round(NumberUtil.mul(var3, ((Number)var2).intValue()), 0,
                        RoundingMode.FLOOR).doubleValue(), ((Number)var2).intValue());
            }
            var1.push(Double.valueOf(var3));
        }
    }
}
