package com.example.test.mathtest;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;

import java.util.Stack;

/**
 * @author gaofan
 * @version 1.0
 * @date 2023/6/6 17:43
 **/
public class Csum extends PostfixMathCommand {
    public Csum() {
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

            var1.push(Double.valueOf(var3));
        }
    }
}
