/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package interpreter.solution1;

public class ExpressionFactory {
    // 1 + 2 + 3 + 4 + 5
    public static Expression getExpression1() {
        return Expressions.sum("1", "2", "3", "4", "5");
    }

    // 1/2 + 3/4 + 5/6 + 7/8 + 9/10
    public static Expression getExpression2() {
        return Expressions.sum("1/2", "3/4", "5/6", "7/8", "9/10");
    }

    // 1/2 * 3/4 + 5/6 * 7/8 - 9/10
    public static Expression getExpression3() {
        return new Sum(new Product(new Value("1/2"), new Value("3/4")), new Product(new Value("5/6"), new Value("7/8")), new Value("-9/10"));
    }

    // ((1/2 * 1/3) - (5/12 + 3/12 + 2/12)) / 2/1
    public static Expression getExpression4() {
        Expression a = Expressions.product("1/2", "1/3");
        Expression b = Expressions.sum("5/12", "3/12", "2/12");
        Expression c = Expressions.negate(b);
        return new Product(new Sum(a, c), new Value("1/2"));
    }
}
