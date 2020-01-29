/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package interpreter.exercise1;

public class ExpressionFactory {
    // 1 + 2 + 3 + 4 + 5
    public static Expression getExpression1() {
        return new Value("TODO");
    }

    // 1/2 + 3/4 + 5/6 + 7/8 + 9/10
    public static Expression getExpression2() {
        return new Value("TODO");
    }

    // 1/2 * 3/4 + 5/6 * 7/8 - 9/10
    public static Expression getExpression3() {
        return new Value("TODO");
    }

    // ((1/2 * 1/3) - (5/12 + 3/12 + 2/12)) / 2/1
    public static Expression getExpression4() {
        return new Value("TODO");
    }
}
