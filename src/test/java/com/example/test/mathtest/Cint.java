package com.example.test.mathtest;

import cn.hutool.core.util.NumberUtil;
import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

/**
 * 向下取整
 *
 * @author gaofan
 * @version 1.0
 * @date 2023/6/6 17:43
 **/
public class Cint extends PostfixMathCommand {
    public Cint() {
        super.numberOfParameters = -1;
    }

    public void run(Stack var1) throws ParseException {
        if (null == var1) {
            throw new ParseException("Stack argument null");
        } else {
            Object var2 = null;
            double var3 = 0.0;
            if (super.curNumberOfParameters > 0) {
                var2 = var1.pop();
                if (!(var2 instanceof Number)) {
                    throw new ParseException("Invalid parameter type");
                }
                BigDecimal round = NumberUtil.round(((Number)var2).doubleValue(), 0, RoundingMode.FLOOR);
                var3 = round.doubleValue();
            }
            var1.push(Double.valueOf(var3));
        }
    }
}
